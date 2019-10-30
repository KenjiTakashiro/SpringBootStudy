package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Main;

@Repository
public class MainDaoImpl implements MainDao<Main> {
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public MainDaoImpl() {
		super();
	}
	public MainDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}
	@Override
	public List<Main> getAll(){
		Query query = entityManager.createQuery("from Main");	//	クエリを実行
		@SuppressWarnings("unchecked")
		List<Main> list = query.getResultList();	//	クエリから結果を取得
		entityManager.close();
		return list;
	}
}
