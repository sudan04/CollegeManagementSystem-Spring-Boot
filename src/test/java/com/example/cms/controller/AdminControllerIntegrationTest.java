package com.example.cms.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.example.cms.entity.Student;
import com.example.cms.entity.Users;
import com.example.cms.enums.Role;
import com.example.cms.repository.StudentRepository;
import com.example.cms.repository.UserRepository;

@SpringBootTest
class AdminControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;
    
//    @Autowired
//    private StudentRepository studentRepository;
    
    @Autowired
    private UserController adminController;

    @Test
    public void createUser() throws Exception {
        Users user = new Users();
        user.setFirstName("Sira");
        user.setLastname("Gairey");
        user.setDepartment("IT");
        user.setEmail("Sudan@gmail.com");
        user.setRole(Role.FACULTY);
        user.setUserName("sudan911");
        user.setPassword("sudan123");
        
        Model model = new ExtendedModelMap();
       adminController.createUser(user, model);
    }
      
    
}
