package kr.ac.kopo.security;

import java.text.ParseException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import jakarta.annotation.PostConstruct;

public class JwtTokenProvider implements TokenProvider {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${kopo.jwt.secret}")
	private String jwtSecret;
	private JWSSigner signer;
	private byte[] encodedSecret;
	
	private final static Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	@PostConstruct
	void init() throws KeyLengthException {
		encodedSecret = Base64.getEncoder().encode(jwtSecret.getBytes());
		
		signer = new MACSigner(encodedSecret);
	}

	@Override
	public boolean valid(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			
			JWSVerifier verifier = new MACVerifier(encodedSecret);
			
			if(signedJWT.verify(verifier)) {
				JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
				
				Date now = new Date();
				Date iat = claimsSet.getIssueTime();
				Date exp = claimsSet.getExpirationTime();
				
				log.debug("토큰 검증 NOW {}", now);
				log.debug("토큰 검증 IAT {}", iat);
				log.debug("토큰 검증 EXP {}", exp);
				
				if(now.after(iat) && now.before(exp))
					return true;
			}
		} catch (ParseException e) {			
			e.printStackTrace();
		} catch (JOSEException e) {			
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Authentication getAuthentication(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			
			JWSVerifier verifier = new MACVerifier(encodedSecret);
			
			if(signedJWT.verify(verifier)) {
				JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
				
				String username = claimsSet.getClaimAsString("username");
				String authorities = claimsSet.getClaimAsString("authorities");
				
				Member member = new Member();
				member.setId(username);
				
				List<GrantedAuthority> authorites = Arrays
						.stream(authorities.split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
				
				return new UsernamePasswordAuthenticationToken(member, null, authorites);
			}
		} catch (ParseException e) {			
			e.printStackTrace();
		} catch (JOSEException e) {			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean login(Member member) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(member.getUsername());
		
		return passwordEncoder.matches(member.getPassword(), userDetails.getPassword());
	}

	@Override
	public String generateToken(Member member, Duration expire) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(member.getUsername());
		
		Date now = new Date();
		Date expireDate = new Date(now.getTime() + expire.toMillis());
		
		String authorities = userDetails
				.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.claim("username", userDetails.getUsername())
				.claim("authorities", authorities)
				.issueTime(now)
				.expirationTime(expireDate)
				.build();
		
		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
		
		try {
			signedJWT.sign(signer);
			
			String token = signedJWT.serialize();

			return token;
		} catch (JOSEException e) {			
			log.error(e.getLocalizedMessage());
			
			return "토큰을 생성할 수 없습니다";
		}		
	}
}