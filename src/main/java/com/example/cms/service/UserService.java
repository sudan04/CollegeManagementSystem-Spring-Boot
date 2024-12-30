package com.example.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cms.entity.Users;
import com.example.cms.repository.UserRepository;

@Service
public class UserService {
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private DepartmentRepository departmentRepository;
	
	
	
	
	public Users findByUserId(Long userId) throws Exception {
		Users user= userRepository.findByUserId(userId);
		 	if (user == null) {
	            throw new Exception("Cannot find User by this email");
	        }
	        return user;
	}

	public Users addNewUser(Users user) throws Exception{
		user.setPassword(encoder.encode(user.getPassword()));
		Users newUser= userRepository.save(user);
		if(newUser==null) throw new Exception("User not created");
		return newUser;
	}

	public Users findByEmail(String email) throws Exception {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("Cannot find User by this email");
        }

        return user;
    }

	
	public List<Users> fetchAllUsers() {
        List<Users> usersList = userRepository.findAll(); 
        return usersList; 
    }
	
	
	@Transactional
	public void deleteUser(Long userId) {
		userRepository.deleteByUserId(userId);
	}

//	public Users updateUser(Users oldUser, Users user) {
//		oldUser.setFirstName(user.getFirstName()!=null && !user.getFirstName().equals("")? user.getFirstName():oldUser.getFirstName());
//		oldUser.setLastname(user.getLastname()!=null && !user.getLastname().equals("")? user.getLastname():oldUser.getLastname());
//		oldUser.setEmail(user.getEmail()!=null && !user.getEmail().equals("")? user.getEmail():oldUser.getEmail());
//		return userRepository.save(oldUser);
//	
//	}
	
	
	
}
