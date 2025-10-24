package kr.ac.kopo.bookshop.dao;

import java.util.List;

import kr.ac.kopo.bookshop.model.Book;

public interface BookDao{
	List<Book> list();
	
	void add(Book item);

	void delete(Long code);

	void update(Book item);

	Book item(Long code);
}
