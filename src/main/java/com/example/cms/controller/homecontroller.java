package com.example.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homecontroller {
	@GetMapping("/homepage/home")
public String getHomePage() {
	return "index";
}
}
