package kr.ac.kopo.bookshop.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.bookshop.dao.BookDao;
import kr.ac.kopo.bookshop.model.Attach;
import kr.ac.kopo.bookshop.model.Book;
import kr.ac.kopo.bookshop.model.Review;
import kr.ac.kopo.bookshop.pager.Pager;


@Service
public class BookServiceImpl extends EgovAbstractServiceImpl implements BookService{

	@Autowired
	BookDao dao;
	
	@Override
	public List<Book> list(Pager pager) {
		int total = dao.total(pager);
		
		pager.setTotal(total);
		
		return dao.list(pager);
	}
	
	@Transactional
	@Override
	public void add(Book item) {
		dao.add(item);
		
		for(Attach image : item.getAttachs()) {
			image.setBookCode(item.getCode());
			
			dao.addAttach(image);
		}
	}

	@Transactional
	@Override
	public void update(Book item) {
		dao.update(item);
		
		for(Attach image : item.getAttachs()) {
			image.setBookCode(item.getCode());
			
			dao.addAttach(image);
		}
	}

	@Override
	public void delete(Long code) {
		dao.delete(code);	
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
		pager.setPerPage(0);
		
		List<Book> list = dao.list(pager);
		
		for(Book item : list)
			dao.delete(item.getCode());	
	}

	@Override
	public Book item(Long code) {
		return dao.item(code);
	}

	@Override
	public boolean deleteAttach(Long code) {
		if(dao.deleteAttach(code) == 1)
			return true;
		return false;
	}
	
	@Override
	public boolean addReview(Review item) {
		if(dao.addReview(item) == 1)
			return true;
		return false;
	}
}
