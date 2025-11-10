package kr.ac.kopo.rolecontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.rolecontrol.model.Authority;
import kr.ac.kopo.rolecontrol.service.AuthorityService;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	private final String path = "authority/";
	
	@Autowired
	AuthorityService service;
	
	@GetMapping("/list")
	String list(Model model) {
		List<Authority> list = service.list();
		
		model.addAttribute("list", list);
		
		return path + "list";
	}
	
	@GetMapping("/add")
	String add() {
		return path + "add";
	}
	
	@PostMapping("/add")
	String add(Authority item) {
		service.add(item);
		
		return "redirect:list";
	}
	
	@GetMapping("/update/{authority}")
	String update(@PathVariable String authority, Model model) {
		Authority item = service.item(authority);
		
		model.addAttribute("item", item);
		
		return path + "update";
	}
	
	@PostMapping("/update/{authority}")
	String update(@PathVariable String authority, Authority item) {
		item.setAuthority(authority);
		
		service.update(item);
		
		return "redirect:../list";
	}
	
	@GetMapping("/delete/{authority}")
	String delete(@PathVariable String authority, Authority item) {
		item.setAuthority(authority);
		
		service.delete(item);
		
		return "redirect:../list";
	}
	
	@GetMapping("/{role}/list")
    public String authority_list(@PathVariable String role, Model model) {
        List<Authority> list = service.list(role);
        model.addAttribute("list", list);
        
        return path + "list"; 
    }
}
