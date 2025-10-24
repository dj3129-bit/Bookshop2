package kr.ac.kopo.bookshop2.dao;

import java.util.List;

import kr.ac.kopo.bookshop2.model.Member;

public interface MemberDao {
	List<Member> list();
	
	void add(Member item);

	void update(Member item);

	void delete(String id);

	Member item(String id);
}
