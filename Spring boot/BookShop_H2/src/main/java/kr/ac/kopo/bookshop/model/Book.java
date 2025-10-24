package kr.ac.kopo.bookshop.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Book {
	private Long code;
	private String title;
	private String publisher;
	private int price;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate pubDate;
	
	public LocalDate getPubDate() {
		return pubDate;
	}
	public void setPubDate(LocalDate pubDate) {
		this.pubDate = pubDate;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
