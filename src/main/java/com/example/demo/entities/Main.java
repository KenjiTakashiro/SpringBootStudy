package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Main {

	@Id
	private long id;
	private String filename;
	private String user;
	private String text;
	private String rate;
	private String category;
}