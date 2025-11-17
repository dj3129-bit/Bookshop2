package kr.ac.kopo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	
	@GetMapping("/")
	ResponseEntity<String> index(){
		return ResponseEntity.ok("RestController OK");
	}
}
