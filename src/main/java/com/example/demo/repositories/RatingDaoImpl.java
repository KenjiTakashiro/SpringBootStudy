package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Rating;

@SuppressWarnings("rawtypes")
@Repository
public class RatingDaoImpl implements RatingDao<RatingDao>{

	private EntityManager ratingEM;
	
	public RatingDaoImpl() {
		super();
	}
	
	public RatingDaoImpl(EntityManager manager) {
		ratingEM = manager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Rating> getAll() {
		return ratingEM.createQuery("from Rating").getResultList();
	}

	@Override
	public Rating findById(Long id) {
		return (Rating)ratingEM.createQuery("from Rating where id = " + id).getSingleResult();
	}
	
}
