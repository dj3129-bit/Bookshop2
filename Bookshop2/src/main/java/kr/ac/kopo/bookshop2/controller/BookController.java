package kr.ac.kopo.bookshop2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.ac.kopo.bookshop2.model.Book;
import kr.ac.kopo.bookshop2.model.Member;
import kr.ac.kopo.bookshop2.pager.Pager;
import kr.ac.kopo.bookshop2.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	final String path = "book/";
	
	@Autowired
	BookService service;
	
	@GetMapping("/dummy")
	String dummy() {
		service.dummy();
		
		return "redirect:list";
	}
	
	@GetMapping("/init")
	String init() {
		service.init();
		
		return "redirect:list";
	}
	
	@GetMapping("/list")
	String list(Model model, Pager pager) {
		List<Book> list = service.list(pager);
		
		model.addAttribute("list", list);
		return "book/list";
	}
	
	@GetMapping("/add")
	String add() {
		return "book/add";
	}
	
	@PostMapping("/add")
	String add(Book item) {
		service.add(item);
		
		return "redirect:list";
	}
	
	@GetMapping("/delete/{code}")
	String delete(@PathVariable Long code) {
		service.delete(code);
		
		return "redirect:../list";
		
	}
	
	@PostMapping("/update/{item}")
	String update(@PathVariable Long code, Book item) {
		item.setCode(code);
		
		service.update(item);
		return "redirect:../list";
	}
}
