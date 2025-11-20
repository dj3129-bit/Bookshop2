package kr.ac.kopo.security;

import java.time.Duration;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

	boolean valid(String token);

	Authentication getAuthentication(String token);

	boolean login(Member member);

	String generateToken(Member member, Duration ofMinutes);
	
}
