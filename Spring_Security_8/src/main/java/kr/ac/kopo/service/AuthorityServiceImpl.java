package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.AuthorityDao;
import kr.ac.kopo.model.Authority;

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

	@Override
	public List<Authority> list(String id) {
		return dao.list(id);
	}

}
