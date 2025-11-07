package kr.ac.kopo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Authority;

@Repository
public class AuthorityDaoImpl implements AuthorityDao {

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Authority> list() {
		return sql.selectList("authority.list");
	}

	@Override
	public void add(Authority item) {
		sql.insert("authority.add", item);
	}

	@Override
	public Authority item(String authority) {
		return sql.selectOne("authority.item", authority);
	}

	@Override
	public void update(Authority item) {
		sql.update("authority.update", item);
	}

	@Override
	public void delete(Authority item) {
		sql.delete("authority.delete", item);
	}

	@Override
	public List<Authority> list(String id) {
		return sql.selectList("authority.list", id);
	}

}
