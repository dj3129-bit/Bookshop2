package kr.ac.kopo.rolecontrol.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	private final String path = "customer/";
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/list")
	String list() {
		return path + "list";
	}
}
