package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//	アップロードされたデータを受け取るフォーム
@Data
public class UploadForm {
  private MultipartFile file;
  private String text;
  private String category;
}