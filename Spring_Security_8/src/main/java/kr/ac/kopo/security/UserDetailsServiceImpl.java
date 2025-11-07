package kr.ac.kopo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.ac.kopo.dao.MemberDao;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	MemberDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return dao.item(username);
	}

}
