package com.izba.security;

import java.util.ArrayList;
import java.util.List;

import com.izba.users.Authorities;

public class AplicationUser {
	
	/*private long userId;*/
	private String username;
	private String password;
	List<Authorities> roles;
	
	public AplicationUser() {
		
	}
	
	public AplicationUser(String username, String password, List<Authorities> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getRoles() {
		List<String> temp = new ArrayList<>();
		for(Authorities rol: roles) {
			temp.add(rol.getAuthoritie());
		}
		String[] array = temp.toArray(new String[temp.size()]);
		return array;
	}

	public void setRoles(List<Authorities> roles) {
		this.roles = roles;
	}
	
}
