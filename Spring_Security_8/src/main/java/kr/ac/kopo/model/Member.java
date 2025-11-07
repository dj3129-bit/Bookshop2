package kr.ac.kopo.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Member implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String password;
	private String tel;
	private String name;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		var list = new ArrayList<GrantedAuthority>();
		
		if("kopo".equals(id)) {
			list.add(new SimpleGrantedAuthority("ROLE_BOOK"));
		} else if("admin".equals(id)) {
			list.add(new SimpleGrantedAuthority("ROLE_BOOK"));
			list.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
			list.add(new SimpleGrantedAuthority("ROLE_ORDERS"));
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

}
