package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.MemberDao;
import kr.ac.kopo.model.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	@Qualifier("memberDaoImpl")
	MemberDao dao;
	
	@Override
	public List<Member> list() {
		return dao.list();
	}
	
	@Override
	public void add(Member item) {
		dao.add(item);
	}
	
	@Override
	public void update(Member item) {
		dao.update(item);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
		
	}
	
	@Override
	public Member item(String id) {
		return dao.item(id);	
	}
	
	@Override
	public boolean login(Member member) {
		Member result = dao.item(member.getId());
				
		if(result != null && member.getPassword().equals(result.getPassword())) {
			BeanUtils.copyProperties(result, member);
			member.setPassword(null);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean checkId(String id) {
		Member item = dao.item(id);
		
		if(item ==  null)
			return true;
		
		return false;
	}
}
