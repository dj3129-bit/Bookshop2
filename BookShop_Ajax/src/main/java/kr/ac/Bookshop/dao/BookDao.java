package kr.ac.Bookshop.dao;

import java.util.List;

import kr.ac.Bookshop.model.Attach;
import kr.ac.Bookshop.model.Book;
import kr.ac.Bookshop.model.Review;
import kr.ac.Bookshop.pager.Pager;

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
