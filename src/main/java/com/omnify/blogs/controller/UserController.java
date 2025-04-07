package com.omnify.blogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.omnify.blogs.model.UserModel;
import com.omnify.blogs.service.UserService;

@Controller
public class UserController {
	@Autowired
	 private UserService userService;
	
		@GetMapping("/")
		public String homePage() {
			return "index";
		}

	    @GetMapping("/register")
	    public String registerForm(Model model) {
	        model.addAttribute("user", new UserModel());
	        return "register";
	    }

	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute UserModel user) {
	        userService.registerUser(user);
	        return "redirect:/login";
	    }

	    @GetMapping("/login")
	    public String loginForm() {
	        return "login";
	    }
}
