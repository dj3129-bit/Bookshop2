package kr.ac.kopo.rolecontrol.dao;

import java.util.List;

import kr.ac.kopo.rolecontrol.model.Role;

public interface RoleDao {

	List<Role> list();

	void add(Role item);

	Role item(String role);

	void update(String role);

	void delete(String role);

	List<Role> list(String id);

	void add_member(Role item);

	void delete_member(Role item);

}
