package com.example.demo.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Account;

@Controller
public class MyPageController {

	@GetMapping("/myPage")
	public ModelAndView getSignUp(ModelAndView mav, Principal principal) {
		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		mav.addObject("account", account);
		mav.setViewName("myPage");
		return mav;
	}

	@PostMapping("/myPage")
	public ModelAndView postSignUp(ModelAndView mav) {
		mav.setViewName("myPage");
		return mav;
	}

}
