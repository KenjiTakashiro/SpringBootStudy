package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	public List<Rating> findByFilename(String filename);

	@Modifying
	@Transactional
	@Query(value = 
	"update ratings2 set answer =:anS where  id =:ID", nativeQuery = true)
	int insertReply(@Param("ID")Long id, @Param("anS")String answer);
}