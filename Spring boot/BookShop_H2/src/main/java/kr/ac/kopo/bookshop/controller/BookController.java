package kr.ac.kopo.bookshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.bookshop.model.Book;
import kr.ac.kopo.bookshop.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	final String path = "book/";
	final String uploadPath = "d:/upload/";
	
	@Autowired
	BookService service;
	
	@GetMapping("/list")
	List<Book> list(){
		
		return service.list();
	}
	
	@GetMapping("/{code}")
	Book item(@PathVariable Long code) {
		return service.item(code);
	}
	
	@PostMapping
	Book add(@RequestBody Book item) {
		service.add(item);
		
		return item;
	}
	
	@GetMapping("/detail/{code}")
	String detail(@PathVariable Long code, Model model) {
		Book item = service.item(code);
		
		model.addAttribute("item", item);
		
		return "book/detail";
	}
	
	@GetMapping("/dummy")
	String dummy() {
		service.dummy();
		
		return "redirect:list";
	}
	
	@PutMapping
	Book update(@RequestBody Book item) {
		service.update(item);
		
		return item;
	}
	
	@GetMapping("/update/{code}")
	String update(@PathVariable Long code, Model model) {
		Book item = service.item(code);
		
		model.addAttribute("item", item);
		return "book/update";
	}
	
	@DeleteMapping("/{code}")
	void delete(@PathVariable Long code) {
		service.delete(code);
	}
}
