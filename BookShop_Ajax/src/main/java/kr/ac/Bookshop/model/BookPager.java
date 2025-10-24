package kr.ac.Bookshop.model;

import java.util.List;

import kr.ac.Bookshop.pager.Pager;

public class BookPager {
	private List<Book> list;
	private Pager pager;
	
	public BookPager(List<Book> list, Pager pager) {
		super();
		this.list = list;
		this.pager = pager;
	}
	public List<Book> getList() {
		return list;
	}
	public void setList(List<Book> list) {
		this.list = list;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
