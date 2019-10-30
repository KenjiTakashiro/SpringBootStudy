package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Account;
import com.example.demo.entities.Main;
import com.example.demo.repositories.ExtendMainRepository;

@Controller
public class MyPostController {

	@Autowired
	ExtendMainRepository mainRepository;

	@GetMapping("myPost")
	public ModelAndView mav(ModelAndView mav, Principal principal, String user) {
		mav.setViewName("myPost");

		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		mav.addObject("account", account);

		user = account.getUsername();

		List<Main> myPostList = (List<Main>) mainRepository.myPost(user);
		mav.addObject("datalist", myPostList);
		System.out.println(myPostList);
		return mav;
	}
	
	@PostMapping("myPost")
	@ResponseBody
	public List<Main> recieveCnt(String num, String user, Principal principal, Model model) {

		int i = Integer.parseInt(num);
		System.out.println(i);

		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		model.addAttribute("account", account);

		user = account.getUsername();

		
		List<Main> limList = (List<Main>) mainRepository.myPostOffset(user, i);
		model.addAttribute("obj", limList);
		System.out.println(limList);
		return limList;
	}

}
