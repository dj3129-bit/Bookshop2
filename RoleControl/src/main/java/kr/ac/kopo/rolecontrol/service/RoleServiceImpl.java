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

}
