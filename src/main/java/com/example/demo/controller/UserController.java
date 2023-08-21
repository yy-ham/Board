package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User_info;
import com.example.demo.service.UserService;

import lombok.Setter;

@Controller
@Setter
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/signup")
	public String signup() {
		return "/signup";
	}

	@PostMapping("/signup")
	public ModelAndView signupSubmit(User_info user_info) {
		ModelAndView mav = new ModelAndView("redirect:/login");
		
		user_info.setUser_no(userService.getNextNo());
		
		System.out.println(user_info.getPwd());
		
		user_info.setPwd(passwordEncoder.encode(user_info.getPwd()));
		System.out.println(user_info.getPwd());
		userService.insert(user_info);
		return mav;
	}

	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}
	
}
