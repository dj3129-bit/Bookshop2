package kr.ac.kopo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import kr.ac.kopo.model.Member;

@RestController
@RequestMapping("/member")
public class MemberController {
	private final String path = "member/";
	private final String url = "http://localhost:9090";
	
	@Autowired
	RestTemplate rest;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	HttpHeaders header;
	
	
	 @GetMapping("/list") String list(Model model) throws JsonMappingException,
	 JsonProcessingException { // RestTemplate rest = new RestTemplate();
	 
	 ResponseEntity<String> result = rest.getForEntity(url, String.class);
	 
	 // ObjectMapper mapper = new ObjectMapper();
	 
	 List<Member> list = mapper.readValue(result.getBody(), new
	 TypeReference<List<Member>>() {});
	 
	 for(Member item : list) System.out.println(item.getName());
	 
	 model.addAttribute("list", list);
	 
	 return path + "list"; }
	 
	
	@GetMapping("/lists")
	String lists(Model model) {
//		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<List<Member>> result = rest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Member>>() {});
		
		if(result.getStatusCode().is2xxSuccessful())
			model.addAttribute("list", result.getBody());
		else
			model.addAttribute("list", new ArrayList<Member>());
		
		return path + "list";
	}
	
	@GetMapping("/add")
	String add() {
		return path + "add";
	}
	
	@PostMapping("/add")
	String add(@Valid Member item, BindingResult bindingResult) {
		//JSON.stringify()
//		ObjectMapper mapper = new ObjectMapper();
		
		if(bindingResult.hasErrors()) {
			for(ObjectError error : bindingResult.getAllErrors())
				System.out.println(error.getDefaultMessage());
			
			return path + "valid/add";
		}
		
		try {
			String json = mapper.writeValueAsString(item);
			
			HttpEntity<String> req = new HttpEntity<String>(json, header);
			
//			RestTemplate rest = new RestTemplate();
			
			ResponseEntity<Member> result = rest.exchange(url, HttpMethod.POST, req, new ParameterizedTypeReference<Member>(){});
			
			System.out.println(result.getBody());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		HttpEntity<String> req = new HttpEntity<String>(path, header);
		
		return "redirect:list";
	}
	
	@GetMapping("/update/{id}")
	String update(@PathVariable String id, Model model) throws JsonProcessingException {
//		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<String> result = rest.getForEntity(url + "/" + id, String.class);
		
//		ObjectMapper mapper = new ObjectMapper();
	
		try {
			Member item = mapper.readValue(result.getBody(), new TypeReference<Member>() {});
			
			model.addAttribute("item", item);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			
			return "redirect:../list";
		}
		
		return path + "update";
	}
	
	@PostMapping("/update/{id}")
	String update(@PathVariable String id, Member item) {
//		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String json = mapper.writeValueAsString(item);
			
			HttpEntity<String> req = new HttpEntity<String>(json, header);
			
//			RestTemplate rest = new RestTemplate();
			ResponseEntity<Member> result = rest.exchange(url + "/" + id, HttpMethod.PUT, req, new ParameterizedTypeReference<Member>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "redirect:../list";
	}
	
	@GetMapping("/delete/{id}")
	String delete(@PathVariable String id) {
//		RestTemplate rest = new RestTemplate();
		
		rest.delete(url + "/" + id);
		
		return "redirect:../list";
	}
}
