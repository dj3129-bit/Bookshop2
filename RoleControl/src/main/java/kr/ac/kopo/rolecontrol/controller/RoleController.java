package kr.ac.kopo.rolecontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.rolecontrol.model.Role;
import kr.ac.kopo.rolecontrol.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	private final String path = "role/";
	
	@Autowired
	RoleService service;
	
	@GetMapping("/list")
	String list(Model model) {
		List<Role> list = service.list();
		
		model.addAttribute("list", list);
		
		return path + "list";
	}
	
	@GetMapping("/add")
	String add() {
		return path + "add";
	}
	
	@PostMapping("/add")
	String add(Role item) {
		service.add(item);
		
		return "redirect:list";
	}
	
	@GetMapping("/update/{role}")
	String update(@PathVariable String role, Model model) {
		Role item = service.item(role);
		
		model.addAttribute("item", item);
		
		return path + "update";
	}
	
	@PostMapping("/update/{role}")
	String update(@PathVariable String role, Role item) {
		item.setRole(role);
		
		service.update(role);
		
		return "redirect:../list";
	}
	
	@GetMapping("/delete/{role}")
	String delete(@PathVariable String role) {
		service.delete(role);
		
		return "redirect:../list";
	}
}
