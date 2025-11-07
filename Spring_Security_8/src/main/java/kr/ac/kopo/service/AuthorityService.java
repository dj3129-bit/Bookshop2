package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.model.Authority;

public interface AuthorityService {

	List<Authority> list();

	void add(Authority item);

	Authority item(String authority);

	void update(Authority item);

	void delete(Authority item);

	List<Authority> list(String id);

}
