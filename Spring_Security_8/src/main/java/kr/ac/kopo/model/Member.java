package kr.ac.kopo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Member implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String password;
	private String tel;
	private String name;
	
	private List<Authority> authority;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		var list = new ArrayList<GrantedAuthority>();
		
		for(Authority item : authority) {
			String role = item.getAuthority();
			if(role != null && !role.isEmpty()) {
				list.add(new SimpleGrantedAuthority(role));
			}
		}
		return list;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getUsername() {
		return id;
	}

	public List<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(List<Authority> authority) {
		this.authority = authority;
	}

}
