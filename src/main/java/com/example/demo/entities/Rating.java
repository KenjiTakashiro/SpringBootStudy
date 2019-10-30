package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ratings2")
public class Rating {
	@Id
	/*
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 */	
	@GeneratedValue
	@Column
	private Long id;

	@Column(length = 50)
	private String filename;

	@Column
	private int rate;

	@Column
	private String comment;
	
	@Column
	private String comment_user;
	
	@Column
	private String answer;
	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "filenames") private Upload upload;
	 */
}