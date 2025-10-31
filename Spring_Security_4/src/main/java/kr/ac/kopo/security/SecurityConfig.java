package kr.ac.kopo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, UserDetailsService userDetailsService) throws Exception {
		return httpSecurity
				.authorizeHttpRequests(req -> req.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.userDetailsService(userDetailsService)
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new PlainPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
		
		/*
		 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		 * 
		 * UserDetails user = User.withUsername("kopo") .password("1234") .roles("USER")
		 * .build(); manager.createUser(user);
		 * 
		 * UserDetails admin = User.withUsername("admin") .password("5678")
		 * .roles("ADMIN") .build(); manager.createUser(admin);
		 * 
		 * return manager;
		 */
	}
	
	
}
