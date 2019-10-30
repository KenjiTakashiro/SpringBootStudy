package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Account;
import com.example.demo.entities.Main;
import com.example.demo.repositories.ExtendMainRepository;
import com.example.demo.repositories.RatingDaoImpl;
import com.example.demo.repositories.UploadRepository;

@Controller
public class MainController {

	@Autowired
	ExtendMainRepository mainRepository;

	@PersistenceContext // エンティティとの紐付け
	EntityManager entityManager;

	RatingDaoImpl dao;

	@RequestMapping(value = "/Main", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView index(ModelAndView mav, Principal principal) {
		mav.setViewName("main");

		List<Main> limList = (List<Main>) mainRepository.findPicLim5();
		mav.addObject("datalist",limList);

		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		mav.addObject("account", account);

		return mav;
	}

	@RequestMapping(value = "/ajaxtest", method = RequestMethod.POST)
	@ResponseBody
	public List<Main> recieveCnt(String num, Model model) {

		int i = Integer.parseInt(num);
		System.out.println(i);

		List<Main> limList = (List<Main>) mainRepository.findPicLim5Offset5(i);
		model.addAttribute("obj", limList);
		System.out.println(limList);
		return limList;
	}
	
}