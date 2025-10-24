package kr.ac.kopo.bookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {
	
	@ResponseBody
	@GetMapping("/")
	public String index() {
		return "index";
	}
}

