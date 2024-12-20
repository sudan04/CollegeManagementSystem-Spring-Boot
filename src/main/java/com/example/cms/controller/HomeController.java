package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cms.entity.Users;
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

        return "sidebar"; // Ensure this matches the Thymeleaf template name
    }
    
}
