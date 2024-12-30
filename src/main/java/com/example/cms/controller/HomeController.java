package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cms.entity.Users;
import com.example.cms.enums.Role;
import com.example.cms.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	

    @GetMapping("/homepage/home")
    public String getAdminHomePage(Model model, HttpSession session) {
    	model.addAttribute("content", "AdminDashboard");
        return "redirect:/admin/adminHomeData";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login"; 
    }

   

    @GetMapping("/userProfile")
    public String userProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        // Fetch the user details from the database
        Users user = userRepository.findByUserName(username);
        model.addAttribute("user", user);
        model.addAttribute("content", "userProfile");
        if(user.getRole() == Role.ADMIN)
        return "sidebar"; 
        
        else if(user.getRole() == Role.STUDENT)
        	return "student_nav";
        
        else
        	return "faculty_nav";
    }
    
    @GetMapping("/student/home")
    public String getStudentHomePage(Model model) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name;

        if (principal instanceof UserDetails) {
            name = ((UserDetails) principal).getUsername();
        } else {
            name = principal.toString();
        }
    	model.addAttribute("name", userRepository.findByUserName(name).getFirstName());
    	model.addAttribute("content", "studentHome");
    	return "student_nav";
    }
    
    
    @GetMapping("/faculty/home")
    public String getFacultyHomePage(Model model) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name;

        if (principal instanceof UserDetails) {
            name = ((UserDetails) principal).getUsername();
        } else {
            name = principal.toString();
        }
    	model.addAttribute("name", userRepository.findByUserName(name).getFirstName());
    	model.addAttribute("content", "facultyHome");
    	return "faculty_nav";
    }
    
}
