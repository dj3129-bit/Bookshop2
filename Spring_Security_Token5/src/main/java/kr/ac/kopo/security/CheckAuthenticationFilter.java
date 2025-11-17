package kr.ac.kopo.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckAuthenticationFilter extends BasicAuthenticationFilter {
	
	private final static Logger log = LoggerFactory.getLogger(CheckAuthenticationFilter.class);

	public CheckAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("Authorization: " + request.getHeader("Authorization"));
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null)
			log.debug("로그인 정보 없음");
		else
			log.debug("권한: " + authentication.getAuthorities());
		
		super.doFilterInternal(request, response, chain);
	}

}
