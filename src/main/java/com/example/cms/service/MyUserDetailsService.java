package com.example.cms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Users;
import com.example.cms.repository.UserRepository;
import com.example.cms.securityConfig.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	

	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
	 Users user=userRepository.findByUserName(userName);
	 
	 if(user==null) {
		 throw new UsernameNotFoundException("user not found");
	 }
	
//	 user.setPassword("{noop}"+user.getPassword());
		
		return new MyUserDetails(user);
	}
	
	

}

