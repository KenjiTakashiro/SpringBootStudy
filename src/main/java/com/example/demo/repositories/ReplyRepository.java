package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>{
	
	@Query(value =
			"select * from  reply where rate_id = rateId",nativeQuery = true
			)
	List<Reply>findByFilename(String rateId);
	

}
