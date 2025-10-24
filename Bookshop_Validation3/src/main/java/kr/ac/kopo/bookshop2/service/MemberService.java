package kr.ac.kopo.bookshop2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.kopo.bookshop2.model.Member;

@Service
public interface MemberService {
	List<Member> list();

	void add(Member item);

	void update(Member item);

	void delete(String id);

	Member item(String id);

	boolean login(Member member);

	boolean checkId(String id);
}
