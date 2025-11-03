package kr.ac.kopo.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if(encodedPassword.equals(rawPassword.toString())) {
			return true;
		}
		
		return false;
	}

}
