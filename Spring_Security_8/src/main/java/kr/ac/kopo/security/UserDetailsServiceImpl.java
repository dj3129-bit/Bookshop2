package kr.ac.kopo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.ac.kopo.dao.MemberDao;
import kr.ac.kopo.model.Member;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	MemberDao dao;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Member member = dao.item(name);
		
		if (member == null) {
	        throw new UsernameNotFoundException("사용자 " + name + "을(를) 찾을 수 없습니다:");
	    }

	    // 권한 정보가 null 또는 빈 리스트일 경우 예외 처리 또는 기본 권한 부여
	    if (member.getAuthority() == null || member.getAuthority().isEmpty()) {
	        throw new UsernameNotFoundException(name + "의 권한 정보가 없습니다: ");
	    }
		
		return dao.item(name);
	}

}
