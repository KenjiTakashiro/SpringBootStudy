package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Upload;
import com.example.demo.repositories.UploadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadService {
	private final UploadRepository uploadRepository;

	public void create(String filename, String user, String text, String category) {
		Upload upload = new Upload();
		upload.setFilename(filename);
		upload.setUser(user);
		upload.setText(text);
		upload.setCategory(category);
		uploadRepository.saveAndFlush(upload);
	}
}
