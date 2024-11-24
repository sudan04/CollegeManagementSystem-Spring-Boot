package com.example.cms.securityConfig;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.cms.entity.Users;


public class MyUserDetails implements UserDetails{
	
	private Users user;
	private String password;
	private String username;
	
	public MyUserDetails(Users user) {
		this.user=user;
		this.password= user.getPassword();
		this.username= user.getUserName();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}
	
	

	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.username;
	}

}
