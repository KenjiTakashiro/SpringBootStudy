package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Rating;
import com.example.demo.repositories.RatingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService {
	private final RatingRepository ratingRepository;

	public void create(String filename, int rate, String comment, String comment_user) {
		Rating rating = new Rating();
		rating.setRate(rate);
		rating.setFilename(filename);
		rating.setComment(comment);
		rating.setComment_user(comment_user);
		ratingRepository.save(rating);
	}
}