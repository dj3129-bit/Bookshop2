package kr.ac.kopo.bookshop2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.bookshop2.model.Member;
import kr.ac.kopo.bookshop2.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/{id}")
	Member item(@PathVariable String id) {
		return service.item(id);
	}
	
	@GetMapping("/list")
	String list(Model model) {
		List<Member> list = service.list();
		
		model.addAttribute("list", list);
		return "member/list";
	}
	
	@GetMapping("/add")
	String add() {
		return "member/add";
	}
	
	@PostMapping("/add")
	String add(@Valid Member item, BindingResult bindingResult) {
		//Validator validator = new MemberValidator();
		
		//if(validator.supports(item.getClass())) {
		//	validator.validate(item, bindingResult);
			
			if(bindingResult.hasErrors()) {
				for(ObjectError error : bindingResult.getAllErrors())
					System.out.println(error.getDefaultMessage());
				
				return "member/valid/add";
			}
		//}
		service.add(item);
		
		return "redirect:list";
	}
	
	@GetMapping("/delete/{id}")
	String delete(@PathVariable String id) {
		service.delete(id);
		
		return "redirect:../list";
		
	}
	
	@GetMapping("/update/{id}")
	String update(@PathVariable String id, Model model) {
		Member item = service.item(id);
		
		model.addAttribute("item", item);
		return "member/update";
	}
	
	@PostMapping("/update/{id}")
	String update(@PathVariable String id, Member item) {
		item.setId(id);
		
		service.update(item);
		return "redirect:../list";
	}
}
