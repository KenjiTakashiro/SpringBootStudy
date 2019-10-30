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
public class RankingController {

	@Autowired
	ExtendMainRepository mainRepository;

	@GetMapping("ranking")
	public ModelAndView mav(ModelAndView mav, Principal principal) {
		mav.setViewName("ranking");

		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		mav.addObject("account", account);

		List<Main> myPostList = (List<Main>) mainRepository.ranking();
		mav.addObject("datalist", myPostList);
		System.out.println(myPostList);
		return mav;
	}

	@PostMapping("ranking")
	@ResponseBody
	public List<Main> rank(String num, Principal principal, Model model) {

		int i = Integer.parseInt(num);
		System.out.println(i);

		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		model.addAttribute("account", account);

		List<Main> limList = (List<Main>) mainRepository.rankingOffset(i);
		model.addAttribute("obj", limList);
		System.out.println(limList);
		return limList;
	}

}
