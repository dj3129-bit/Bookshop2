package kr.ac.kopo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDeatilsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = new Member();
		
		member.setId(username);
		
		if("kopo".equals(username))
			member.setPasswd("1234");
		else if("admin".equals(username))
			member.setPasswd("5678");
		
		return member;
	}

}
