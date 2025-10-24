package kr.ac.kopo.bookshop2.service;

import java.util.List;

import kr.ac.kopo.bookshop2.model.Book;
import kr.ac.kopo.bookshop2.model.Review;
import kr.ac.kopo.bookshop2.pager.Pager;

public interface BookService {
	List<Book> list(Pager pager);
	
	void add(Book item);

	void update(Book item);

	void delete(Long code);

	void dummy();

	void init();

	Book item(Long code);
}
