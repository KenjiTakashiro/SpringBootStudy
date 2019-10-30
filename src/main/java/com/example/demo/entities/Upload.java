package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pic2")
public class Upload {
	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
	 * "filename")
	 */// cascade = CascadeType.ALLを設定することで1の操作を多にも伝播できる
	/*
	 * private List<Rating> rating;
	 */
	@Id
	@Column(length = 100)
	/*
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 */	
	@GeneratedValue
	private Long id;

	@Column(length = 50)
	private String filename;

	@Column
	private String user;

	@Column
	private String text;
	
	@Column
	private String category;
}