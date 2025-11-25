package kr.ac.kopo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.model.Member;

@Controller
@RequestMapping ("/book")
public class BookController {
	private final String path = "book/";
	private final String url = "http://localhost:3000";   //node.js
	
	@Autowired
	RestTemplate rest;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	HttpHeaders header;
	
	@GetMapping("/list")
	String list(Model model) {
//		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<List<Book>> result = rest.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Book>>() {});
		
		if(result.getStatusCode().is2xxSuccessful())
			model.addAttribute("list", result.getBody());
		else
			model.addAttribute("list", new ArrayList<Book>());
		
		return path + "list";
	}
	
	@GetMapping("/add")
	String add() {
		return path + "add";
	}
	
	@PostMapping("/add")
	String add(Book item) {
		try {
			String json = mapper.writeValueAsString(item);
			
			HttpEntity<String> req = new HttpEntity<String>(json, header);
			
			rest.exchange(url, HttpMethod.POST, req, new ParameterizedTypeReference<List<Book>>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "redirect:list";
	}
	
	@GetMapping("/delete/{bookid}")
	String delete(@PathVariable String bookid) {
		rest.delete(url + "/" + bookid);
		
		return "redirect:../list";
	}
	
	@GetMapping("/update/{bookid}")
	String update(@PathVariable Long bookid, Model model) throws JsonProcessingException {
		ResponseEntity<String> result = rest.getForEntity(url + "/" + bookid, String.class);
	
		try {
			Book item = mapper.readValue(result.getBody(), new TypeReference<Book>() {});
			
			model.addAttribute("item", item);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			
			return "redirect:../list";
		}
		
		return path + "update";
	}
	
	@PostMapping("/update/{bookid}")
	String update(@PathVariable Long bookid, Book item) {
		item.setBookid(bookid);
		
		try {
			String json = mapper.writeValueAsString(item);
			
			HttpEntity<String> req = new HttpEntity<String>(json, header);
			
			rest.put(url, req);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "redirect:../list";
	}
}
