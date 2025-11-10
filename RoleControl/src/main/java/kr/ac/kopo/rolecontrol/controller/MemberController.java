package kr.ac.kopo.rolecontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.rolecontrol.model.Member;
import kr.ac.kopo.rolecontrol.model.RestResult;
import kr.ac.kopo.rolecontrol.model.Role;
import kr.ac.kopo.rolecontrol.service.MemberService;
import kr.ac.kopo.rolecontrol.service.RoleService;

@Controller
@RequestMapping("/member")
public class MemberController {
	private final String path = "member/";
	
	@Autowired
	MemberService service;
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/{id}")
	Member item(@PathVariable String id) {
		return service.item(id);
	}
	
	@GetMapping
	List<Member> list(){
		return service.list();
	}
	
	@GetMapping("/list")
	String list(Model model) {
		List<Member> list = service.list();
		
		model.addAttribute("list", list);
		return path + "list";
	}
	
	@GetMapping("/add")
	String add() {
		return path + "add";
	}
	
	@PostMapping("/add")
	ResponseEntity<RestResult<Member>> add(@RequestBody Member item, BindingResult bindingResult) {
		RestResult<Member> result = new RestResult<>();
		
		if(bindingResult.hasErrors()) {
			for(ObjectError error : bindingResult.getAllErrors())
				System.out.println(error.getDefaultMessage());
			
			result.setResult(false);
			result.setErrors(bindingResult.getAllErrors());
			result.setBody(item);
			result.setMessage("요청에 오류가 있습니다. 확인 후에 다시 요청하여 주시기 바랍니다.");
			
			return null;
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult);
		}
		
		service.add(item);
		
		result.setResult(true);
		result.setBody(item);
		result.setMessage("등록되었습니다.");
		
		return null; 
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(item);
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
		return path + "update";
	}
	
	@PostMapping("/update/{id}")
	String update(@PathVariable String id, Member item) {
		item.setId(id);
		
		service.update(item);
		return "redirect:../list";
	}
	
	@GetMapping("/role/{id}/list")
	String role_list(@PathVariable String id, Model model) {
		List<Role> list = roleService.list(id);
		
		model.addAttribute("list", list);
		
		return "role/authority";
	}
	
	@GetMapping("/role/{id}/add/{authority}")
	String role_add(@PathVariable String id, @PathVariable String role) {
		Role item = new Role();
		item.setId(id);
		item.setRole(role);
		
		roleService.add_member(item);
		
		return "redirect:../list";
	}
	
	@GetMapping("/role/{id}/delete/{authority}")
	String authority_delete(@PathVariable String id, @PathVariable String role) {
		Role item = new Role();
		item.setId(id);
		item.setRole(role);
		
		roleService.delete_member(item);
		
		return "redirect:../list";
	}

}
