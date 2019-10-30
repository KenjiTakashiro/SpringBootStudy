package com.example.demo.form;

import java.io.Serializable;

import lombok.*;

@Data
public class CountEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	private int countNum;
	private int loadNum;
}
