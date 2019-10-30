package com.example.demo.repositories;

import java.io.Serializable;
import java.util.List;

public interface MainDao<T> extends Serializable {
	public List<T> getAll();
}
