package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

@Service
public class GetExifService {
	
	public void getPicInf(File jpegFile) throws ImageProcessingException, IOException {

		    // メタ情報の取得
	      jpegFile = new File("C://ConnectgramImage/20190730114133.jpg");
		Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
	      for (Directory directory : metadata.getDirectories()) {
	          for (Tag tag : directory.getTags()) {
	              System.out.println(tag);
	          }
	      }
	    

	}
	
}
