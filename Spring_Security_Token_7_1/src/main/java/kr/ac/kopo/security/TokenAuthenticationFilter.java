package kr.ac.kopo.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	private final static Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
	
	// HTTP	Basic -> Bearer
	private final String TOKEN_PREFIX = "Bearer ";
	
	@Autowired
	TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		log.debug(header);
		
		if(header != null) {
			if(header.startsWith(TOKEN_PREFIX)) {
				String token = header.substring(TOKEN_PREFIX.length());
				
				if(tokenProvider.valid(token)) {
					log.debug("유효한 토큰: {}", token);
					
					Authentication authentication = tokenProvider.getAuthentication(token);
					
					SecurityContextHolder.getContext().setAuthentication(authentication);	
				} else {
					log.debug("유효하지 않은 토큰: {}", token);
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
