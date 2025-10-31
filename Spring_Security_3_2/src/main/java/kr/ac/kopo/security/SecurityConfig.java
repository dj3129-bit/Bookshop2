package kr.ac.kopo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
	
	
	@Bean
	PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		
		UserDetails user = User.withUsername("kopo")
				.password(encoder.encode("1234"))
				.roles("USER")
				.build();
		manager.createUser(user);
		
		UserDetails admin = User.withUsername("admin")
				.password(encoder.encode("5678"))
				.roles("ADMIN")
				.build();
		manager.createUser(admin);
		
		return manager;
	}
	
	
}
