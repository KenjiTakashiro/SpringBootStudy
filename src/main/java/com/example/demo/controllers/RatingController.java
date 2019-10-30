package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Account;
import com.example.demo.entities.Rating;
import com.example.demo.repositories.RatingRepository;
import com.example.demo.service.RatingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class RatingController {

	@Autowired
	private final RatingService ratingService;

	@Autowired
	RatingRepository ratingRepository;

	@GetMapping("/Rating")
	ModelAndView GetRating(ModelAndView mav, Principal principal, HttpServletRequest request) {
		mav.setViewName("rating");

		String filename = request.getParameter("filename");
		mav.addObject("filename", filename);

		String upUser = request.getParameter("user");
		mav.addObject("upUser", upUser);

		String picId = request.getParameter("picId");
		mav.addObject("picId", picId);

		String text = request.getParameter("text");
		mav.addObject("text", text);

		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		mav.addObject("account", account);

		List<Rating> rating = (List<Rating>) ratingRepository.findByFilename(filename);
		mav.addObject("rating", rating);
		System.out.println(rating);

		return mav;
	}

	@PostMapping("/Rating") // 写真評価投稿処理
	ModelAndView PostRating(ModelAndView mav, Principal principal, @RequestParam("filename") String filename,
			@RequestParam("star") int rate, @RequestParam("") String text) {
		mav.setViewName("postSuccess");

		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		mav.addObject("account", account);

		String comment_user = account.getUsername();

		// 登録処理
		ratingService.create(filename, rate, text, comment_user);

		return mav;
	}
	@ResponseBody
	@PostMapping("/Answer")
	String insertAnswer(@RequestParam("answer") String answer, @RequestParam("rateId") Long rateId) {
		System.out.println(answer);
		System.out.println(rateId);
		ratingRepository.insertReply(rateId, answer);
		return answer;
	}

}
