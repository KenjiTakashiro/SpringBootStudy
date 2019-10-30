package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Upload;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long>{
	
	@Query(value = 
			"select pic2.id,"
			+ " pic2.filename,"
			+ " pic2.user,"
			+ " pic2.text,"
			+ " AVG(ratings2.rate)"
			+ " from pic2 "
			+ "left join ratings2 "
			+ "using(filename) "
			+ "group by pic2.filename "
			+ "order by pic2.id "
			+ "desc",
			nativeQuery = true)
	List<Upload> findAll();

	@Query(value = 
			"select pic2.id,"
			+ " pic2.filename,"
			+ " pic2.user,"
			+ " pic2.text,"
			+ " AVG(ratings2.rate)"
			+ " from pic2 "
			+ "left join ratings2 "
			+ "using(filename) "
			+ "group by pic2.filename "
			+ "order by pic2.id"
			+ "desc"
			+ "limit 5",
			nativeQuery = true)
	List<Upload> find5Pic(int count);
}