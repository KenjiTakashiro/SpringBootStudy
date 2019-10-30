package com.example.demo.repositories;

import java.util.List;

import com.example.demo.entities.Rating;

public interface RatingDao<T> {
	public List<Rating>getAll();
	public Rating findById(Long id);
}