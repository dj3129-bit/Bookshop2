package kr.ac.kopo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
	
	@GetMapping("/")
	String index(Model model) {
		model.addAttribute("name", "LOVE");
		
		return "index";
	}
}
