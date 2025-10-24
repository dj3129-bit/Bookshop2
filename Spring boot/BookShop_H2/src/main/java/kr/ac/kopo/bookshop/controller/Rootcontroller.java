package kr.ac.kopo.bookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Rootcontroller {
	
	@GetMapping("/")
	String index() {
		return "index";
	}
}
