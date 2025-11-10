package kr.ac.kopo.rolecontrol.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	private final String path = "orders/";
	
	@PreAuthorize("hasRole('ORDERS')")
	@GetMapping("/list")
	String list() {
		return path + "list";
	}
}
