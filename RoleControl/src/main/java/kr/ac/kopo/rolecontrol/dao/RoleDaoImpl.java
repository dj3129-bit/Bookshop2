package kr.ac.kopo.rolecontrol.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.rolecontrol.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	SqlSession sql;

	@Override
	public List<Role> list() {
		return sql.selectList("role.list");
	}

	@Override
	public void add(Role item) {
		sql.insert("role.add", item);
	}

	@Override
	public Role item(String role) {
		return sql.selectOne("role.item", role);
	}

	@Override
	public void update(String role) {
		sql.update("role.update", role);
	}

	@Override
	public void delete(String role) {
		sql.delete("role.delete", role);
	}

	@Override
	public List<Role> list(String id) {
		return sql.selectList("role.list", id);
	}

	@Override
	public void add_member(Role item) {
		sql.insert("role.add_member", item);
	}

	@Override
	public void delete_member(Role item) {
		sql.delete("role.delete_member", item);
	}

}
