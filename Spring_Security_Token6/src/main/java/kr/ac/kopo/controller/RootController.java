package kr.ac.kopo.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.security.Member;
import kr.ac.kopo.security.TokenProvider;

@RestController
public class RootController {
	
	@Autowired
	TokenProvider tokenProvider;
	
	@GetMapping("/")
	ResponseEntity<String> index(){
		return ResponseEntity.ok("RestController OK");
	}
	
	@PostMapping("/login")
	ResponseEntity<String> login(@RequestBody Member member){
		if(tokenProvider.login(member)) {
			String token = tokenProvider.generateToken(member, Duration.ofMinutes(5));
		
			return ResponseEntity.ok(token);
		}
		
		return ResponseEntity.badRequest().body("사용자 정보가 일치하지 않습니다.");
	}
}
