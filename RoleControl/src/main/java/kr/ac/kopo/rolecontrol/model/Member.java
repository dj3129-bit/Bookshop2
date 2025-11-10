package kr.ac.kopo.rolecontrol.model;

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
	
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		var list = new ArrayList<GrantedAuthority>();
		
		for(Role roles : this.roles)
			for(Authority authority : roles.getAuthority()) {
				GrantedAuthority item = new SimpleGrantedAuthority(authority.getAuthority());
				if(!list.contains(item)) 
					list.add(item);
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


}
