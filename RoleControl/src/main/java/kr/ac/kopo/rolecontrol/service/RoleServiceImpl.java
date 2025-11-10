package kr.ac.kopo.rolecontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.rolecontrol.dao.RoleDao;
import kr.ac.kopo.rolecontrol.model.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleDao dao;

	@Override
	public List<Role> list() {
		return dao.list();
	}

	@Override
	public void add(Role item) {
		dao.add(item);
	}

	@Override
	public Role item(String role) {
		return dao.item(role);
	}

	@Override
	public void update(String role) {
		dao.update(role);
	}

	@Override
	public void delete(String role) {
		dao.delete(role);
	}

	@Override
	public List<Role> list(String id) {
		return dao.list(id);
	}

	@Override
	public void add_member(Role item) {
		dao.add_member(item);
	}

	@Override
	public void delete_member(Role item) {
		dao.delete_member(item);
	}

}
