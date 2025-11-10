package kr.ac.kopo.rolecontrol.service;

import java.util.List;

import kr.ac.kopo.rolecontrol.model.Authority;

public interface AuthorityService {

	List<Authority> list();

	void add(Authority item);

	Authority item(String authority);

	void update(Authority item);

	void delete(Authority item);

	List<Authority> list(String id);

	void add_role(Authority item);

	void delete_role(Authority item);

}
