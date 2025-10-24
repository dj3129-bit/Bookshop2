package kr.ac.Bookshop.service;

import java.util.List;

import kr.ac.Bookshop.model.Book;
import kr.ac.Bookshop.model.Review;
import kr.ac.Bookshop.pager.Pager;

public interface BookService {
	List<Book> list(Pager pager);
	
	void add(Book item);

	void update(Book item);

	void delete(Long code);

	void dummy();

	void init();

	Book item(Long code);

	boolean deleteAttach(Long code);

	boolean addReview(Review item);
}
