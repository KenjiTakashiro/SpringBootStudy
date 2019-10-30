package com.example.demo.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.*;

public interface ExtendMainRepository extends JpaRepository<Main, Long>{
	@Query(value = 
			"select pic2.id,"
			+ " pic2.filename,"
			+ " pic2.user,"
			+ " pic2.text,"
			+ " pic2.category,"
			+ " AVG(ratings2.rate) as rate"
			+ " from pic2 "
			+ "left join ratings2 "
			+ "using(filename) "
			+ "group by pic2.filename "
			+ "order by pic2.id "
			+ "desc limit 5",
			nativeQuery = true)
	List<Main> findPicLim5();

	@Query(value = 
			"select pic2.id,"
			+ " pic2.filename,"
			+ " pic2.user,"
			+ " pic2.text,"
			+ " pic2.category,"
			+ " AVG(ratings2.rate) as rate"
			+ " from pic2 "
			+ "left join ratings2 "
			+ "using(filename) "
			+ "group by pic2.filename "
			+ "order by pic2.id "
			+ "desc limit 5 offset :num",
			nativeQuery = true)
	List<Main> findPicLim5Offset5(@Param("num")int num);

	
	  @Query(value = 
					  "select pic2.id, pic2.filename, pic2.user,"
						+" pic2.text,"
						+" pic2.category,"
						+" AVG(ratings2.rate) as rate"
						+" from pic2"
						+" left join ratings2"
						+" using(filename)"
						+" where pic2.category = :Category" 
						+" group by pic2.filename"
						+" order by pic2.id"
						+" desc limit 5",
	  nativeQuery = true) 
	  List<Main> usingCategory(@Param("Category")String category);

	  @Query(value = 
			  "select pic2.id, pic2.filename, pic2.user,"
				+" pic2.text,"
				+" pic2.category,"
				+" AVG(ratings2.rate) as rate"
				+" from pic2"
				+" left join ratings2"
				+" using(filename)"
				+" where pic2.category = :Category" 
				+" group by pic2.filename"
				+" order by pic2.id"
				+" desc limit 5 offset :num",
nativeQuery = true) 
List<Main> usingCategoryOffset(@Param("Category")String category,  @Param("num")int num);

	  @Query(value = 
			  "select pic2.id, pic2.filename, pic2.user,"
				+" pic2.text,"
				+" pic2.category,"
				+" AVG(ratings2.rate) as rate"
				+" from pic2"
				+" left join ratings2"
				+" using(filename)"
				+" where pic2.user = :user" 
				+" group by pic2.filename"
				+" order by pic2.id"
				+" desc limit 5",
				nativeQuery = true) 
	  List<Main> myPost(@Param("user")String user);

	  @Query(value = 
			  "select pic2.id, pic2.filename, pic2.user,"
				+" pic2.text,"
				+" pic2.category,"
				+" AVG(ratings2.rate) as rate"
				+" from pic2"
				+" left join ratings2"
				+" using(filename)"
				+" where pic2.user = :user" 
				+" group by pic2.filename"
				+" order by pic2.id"
				+" desc limit 5 offset :num",
				nativeQuery = true) 
	  List<Main> myPostOffset(@Param("user")String user, @Param("num") int num);
	  
		@Query(value = 
				"select pic2.id,"
				+ " pic2.filename,"
				+ " pic2.user,"
				+ " pic2.text,"
				+ " pic2.category,"
				+ " AVG(ratings2.rate) as rate"
				+ " from pic2 "
				+ "left join ratings2 "
				+ "using(filename) "
				+ "group by pic2.filename "
				+ "order by rate "
				+ "desc limit 5",
				nativeQuery = true)
		List<Main> ranking();

		@Query(value = 
				"select pic2.id,"
				+ " pic2.filename,"
				+ " pic2.user,"
				+ " pic2.text,"
				+ " pic2.category,"
				+ " AVG(ratings2.rate) as rate"
				+ " from pic2 "
				+ "left join ratings2 "
				+ "using(filename) "
				+ "group by pic2.filename "
				+ "order by rate "
				+" desc limit 5 offset :num",
				nativeQuery = true)
		List<Main> rankingOffset(@Param("num") int num);

	 }
