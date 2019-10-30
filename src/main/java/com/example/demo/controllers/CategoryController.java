package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Account;
import com.example.demo.entities.Main;
import com.example.demo.repositories.ExtendMainRepository;

@Controller
public class CategoryController {

	@Autowired
	ExtendMainRepository mainRepository;
	
	@PersistenceContext // エンティティとの紐付け
	EntityManager entityManager;

	@GetMapping("category")
	@ResponseBody
	public ModelAndView sort(ModelAndView mav, Principal principal, String category) {
		mav.setViewName("category");

		mav.addObject("category", category);
		System.out.println(category);

		List<Main> limList = (List<Main>) mainRepository.usingCategory(category);
		mav.addObject("datalist", limList);

		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		mav.addObject("account", account);

		return mav;
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseBody
	public  List<Main> sortOffset(Model model, String category, int num) {

		model.addAttribute("category", category);
		System.out.println(category);

		List<Main> limList = (List<Main>) mainRepository.usingCategoryOffset(category, num);
		model.addAttribute("obj", limList);
		System.out.println(limList);

		return limList;
	}
}