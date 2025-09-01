package kr.ac.kopo.bookshop2.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.bookshop2.dao.BookDao;
import kr.ac.kopo.bookshop2.model.Book;
import kr.ac.kopo.bookshop2.model.Member;
import kr.ac.kopo.bookshop2.pager.Pager;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookDao dao;
	
	@Override
	public List<Book> list(Pager pager) {
		int total = dao.total(pager);
		
		pager.setTotal(total);
		
		return dao.list(pager);
	}
	
	@Override
	public void add(Book item) {
		dao.add(item);
	}

	@Override
	public void update(Book item) {
		dao.update(item);
	}

	@Override
	public void delete(Long code) {
		dao.delete(code);	
	}

	@Override
	public Book item(Long code) {
		return dao.item(code);
	}

	@Override
	public void dummy() {
		for(int i=0; i < 100; i++) {
			Book item = new Book();
			
			item.setTitle("도서명 " + i);
			item.setPublisher("출판사 " + i);
			item.setPrice(i + 1000);
			item.setPubDate(LocalDate.now());
			
			dao.add(item);
		}
	}

	@Override
	public void init() {
		Pager pager = new Pager();
		List<Book> list = dao.list(pager);
		
		for(Book item : list)
			dao.delete(item.getCode());	
	}
}
