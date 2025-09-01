package kr.ac.kopo.bookshop2.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.bookshop2.model.Book;
import kr.ac.kopo.bookshop2.model.Member;
import kr.ac.kopo.bookshop2.pager.Pager;

@Repository
public class BookDaoImpl implements BookDao{
	@Autowired
	SqlSession sql;
	
	public List<Book> list(Pager pager){
		return sql.selectList("book.list", pager);
	}
	
	@Override
	public void add(Book item) {
		sql.insert("book.add", item);
	}

	@Override
	public void delete(String id) {
		sql.update("book.delete", id);
	}

	@Override
	public void delete(Long code) {
		sql.delete("book.delete", code);
	}

	@Override
	public void update(Book item) {
		sql.update("book.update", item);
	}
	
	@Override
	public Book item(Long code) {
		return sql.selectOne("book.item", code);
	}

	@Override
	public int total(Pager pager) {
		return sql.selectOne("book.total", pager);
	}
}
