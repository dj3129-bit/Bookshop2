package kr.ac.kopo.bookshop.model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Member {
	//@Length(min=4, message="아이디는 4글자 이상 입력하셔야 합니다.")
	private String id;
	
	//@Length(min=4, message="비밀번호는 4글자 이상 입력하셔야 합니다.")
	private String password;
	
	//@NotBlank(message="이름는 반드시 입력하셔야 합니다.")
	private String name;
	
	//@Pattern(regexp = "^\\d{11}$", message="전화번호는 숫자만 11자리로 입력하셔야 합니다.")
	private String tel;
	
	public Member() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
