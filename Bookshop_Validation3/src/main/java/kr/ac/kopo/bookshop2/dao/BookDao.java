package kr.ac.kopo.bookshop2.dao;

import java.util.List;

import kr.ac.kopo.bookshop2.model.Attach;
import kr.ac.kopo.bookshop2.model.Book;
import kr.ac.kopo.bookshop2.model.Review;
import kr.ac.kopo.bookshop2.pager.Pager;

public interface BookDao {
	List<Book> list(Pager pager);
	
	void add(Book item);

	void delete(Long code);

	void update(Book item);

	void delete(String id);

	Book item(Long code);

	int total(Pager pager);

	void addAttach(Attach image);

	int deleteAttach(Long code);

	int addReview(Review item);
}
