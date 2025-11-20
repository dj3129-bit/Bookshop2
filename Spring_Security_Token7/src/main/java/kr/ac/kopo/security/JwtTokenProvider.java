package kr.ac.kopo.security;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

public class JwtTokenProvider implements TokenProvider {
	
	private final String DELEMITER = ";";

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${kopo.jwt.secret}")
	private String jwtSecret = "";
	private SecretKey secretKey;
	
	@PostConstruct
	void init() {
		byte[] encodedSecret = Base64.getEncoder().encode(jwtSecret.getBytes());
		
		secretKey = Keys.hmacShaKeyFor(encodedSecret);
	}
	
	@Override
	public boolean valid(String token) {
		String[] items = token.split(DELEMITER);
		
		if(items.length == 3) {      //username;ROLE_XXX;yyyy-MM-dd HH:mm:ss
			Date now = new Date();
			
			try {
				Date expireDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(items[2]);
				
				if(now.before(expireDate))
					return true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public Authentication getAuthentication(String token) {
		String[] items = token.split(DELEMITER);
		
		if(items.length == 3) {
			Member member = new Member();
			member.setId(items[0]);
			
			//[ROLE_BOOK, ROLE_CUSTOMER] -> ROLE_BOOK,ROLE_CUSTOMER
			String roles = items[1].replace("[", "").replace("]", "").replaceAll(" ", "");
			
			List<SimpleGrantedAuthority> authorites = Arrays.stream(roles.split(","))
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
			
			return new UsernamePasswordAuthenticationToken(member, null, authorites);
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
		String expireStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expireDate);
		
		return userDetails.getUsername() + DELEMITER + userDetails.getAuthorities().toString() + DELEMITER + expireStr;
	}

}
