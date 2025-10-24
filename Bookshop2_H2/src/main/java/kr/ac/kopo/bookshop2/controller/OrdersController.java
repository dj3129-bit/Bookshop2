package kr.ac.kopo.bookshop2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.ac.kopo.bookshop2.model.Member;


@Controller
@RequestMapping("/orders")
public class OrdersController {

	@GetMapping("/list")
	String list(@SessionAttribute Member member) {
		return "orders/list";
	}
}
