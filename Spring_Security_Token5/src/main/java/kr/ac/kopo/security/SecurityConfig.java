package kr.ac.kopo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	CheckAuthenticationFilter checkAuthenticationFilter(AuthenticationManager authenticationManager) {
		return new CheckAuthenticationFilter(authenticationManager);
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, CheckAuthenticationFilter checkAuthenticationFilter) throws Exception{
		return http
				.authorizeHttpRequests(req -> req
						.requestMatchers("/login", "/logout", "/error").permitAll()
						.anyRequest().authenticated())
				.addFilter(checkAuthenticationFilter)
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
