package kr.ac.kopo.rolecontrol.service;

import java.util.List;

import kr.ac.kopo.rolecontrol.model.Role;

public interface RoleService {

	public List<Role> list();

	public void add(Role item);

	public Role item(String role);

	public void update(String role);

	public void delete(String role);

	public List<Role> list(String id);

	public void add_member(Role item);

	public void delete_member(Role item);

}
