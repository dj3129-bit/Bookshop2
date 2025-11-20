package kr.ac.kopo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Bean
	TokenProvider tokenProvider() {
		return new PlainTokenProvider();
	}
	
	@Bean
	TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, TokenAuthenticationFilter tokenAuthenticationFilter) throws Exception{
		return http
				.authorizeHttpRequests(req -> req
						.requestMatchers("/login", "/logout", "/error").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
				
				.csrf(csrf -> csrf.disable())
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new PlainPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	
}
