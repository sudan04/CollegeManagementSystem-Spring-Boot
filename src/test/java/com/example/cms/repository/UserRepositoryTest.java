package com.example.cms.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.cms.entity.Users;

import jakarta.transaction.Transactional;

@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	@Test
	public void createUser() {
		Users user = new Users();
		
		user.setFirstName("Nisil");
		user.setLastname("Pantha");
		user.setDepartment("BCA");
		user.setEmail("nisil1@gmail.com");
		user.setPassword("nisil123");
		user.setUserName("nisil09");
		
		userRepository.save(user);
	}
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void deleteUser() {
		userRepository.deleteById(2L);
	}	
}
