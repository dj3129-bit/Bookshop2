package kr.ac.kopo.rolecontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.rolecontrol.dao.AuthorityDao;
import kr.ac.kopo.rolecontrol.model.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	AuthorityDao dao;
	
	@Override
	public List<Authority> list() {
		return dao.list();
	}

	@Override
	public void add(Authority item) {
		dao.add(item);
	}

	@Override
	public Authority item(String authority) {
		return dao.item(authority);
	}

	@Override
	public void update(Authority item) {
		dao.update(item);
	}

	@Override
	public void delete(Authority item) {
		dao.delete(item);
	}

	@Transactional
	@Override
	public List<Authority> list(String id) {
		return dao.list(id);
	}

	@Override
	public void add_role(Authority item) {
		dao.add_role(item);
	}

	@Override
	public void delete_role(Authority item) {
		dao.delete_role(item);
	}

}
