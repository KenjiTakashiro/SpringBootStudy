package com.example.demo.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.example.demo.entities.Account;
import com.example.demo.form.UploadForm;
import com.example.demo.repositories.UploadRepository;
import com.example.demo.service.UploadService;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FileUploadController {
	private final UploadService uploadService;

	private String getExtension(String filename) { // 拡張子の取得
		int dot = filename.lastIndexOf(".");
		if (dot > 0) {
			return filename.substring(dot).toLowerCase();
		}
		return "";
	}

	private String getUploadFileName(String fileName) { // リネーム処理

		return DateTimeFormatter.ofPattern("yyyyMMddHHmm").format(LocalDateTime.now()) + getExtension(fileName);
	}

// AZURE関係
	// azure認証情報
	public static final String storageConnectionString = "DefaultEndpointsProtocol=https;"
			+ "AccountName=connectgramimg;"
		//キー情報を記述
			+ "AccountKey=***********************************************************";

	// MPF→Fileクラスに変換
	public File convert(MultipartFile file) throws IOException, ImageProcessingException {
		File convFile = new File(file.getOriginalFilename());	
		
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	// azureアップロード
	public void saveImg(MultipartFile file) throws InvalidKeyException, IOException, ImageProcessingException {
		File fOld = null;
		System.out.println("Azure Blob storage quick start sample");
		// フォームから取得したデータをBLOBにアップロードできるよう変換
		fOld = convert(file);

		// 変更したファイル名を取得
		File sourceFile = new File(getUploadFileName(file.getOriginalFilename()));

		if (fOld.exists()) {
			// ファイル名変更
			fOld.renameTo(sourceFile);
			//検証用
			System.out.println("ファイル名を変更しました。	確認：" + sourceFile);
		} else {
			System.out.println("ファイルが存在しません。");
		}

		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container = null;

		try {
			// Parse the connection string and create a blob client to interact with
			// Blob(Blobへ接続)
			// storage
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			blobClient = storageAccount.createCloudBlobClient();
			container = blobClient.getContainerReference("img");

			// Create the container if it does not exist with public
			// access.（指定したコンテナがなければ作成）
			System.out.println("Creating container: " + container.getName());
			container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(),
					new OperationContext());

			// Getting a blob reference
			CloudBlockBlob blob = container.getBlockBlobReference(sourceFile.getName());

			// Creating blob and uploading file to it
			System.out.println("Uploading  file ");
			blob.uploadFromFile(sourceFile.getAbsolutePath());

		} catch (StorageException ex) {
			System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s",
					ex.getHttpStatusCode(), ex.getErrorCode()));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			System.out.println("The program has completed successfully.");
		}
	}
// 以上　AZURE関係

	@RequestMapping(path = "/upload", method = RequestMethod.GET)
	String uploadview(Model model) {
		model.addAttribute("form", new UploadForm());
		return "upload";
	}

	@Autowired
	UploadRepository uploadRepository;

	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	String upload(Model model, UploadForm form, Principal principal) throws InvalidKeyException, IOException, ImageProcessingException {

		// DBへアップロードしたファイルの付帯情報を登録
		// ユーザー情報を取得
		Authentication auth = (Authentication) principal;
		Account account = (Account) auth.getPrincipal();
		String user = account.getUsername();
		System.out.println(user + "を読み込んだ");

		// テキスト情報を取得
		String text = form.getText();
		System.out.println(text + "を読み込んだ");

		// カテゴリを取得
		String category = form.getCategory();
		System.out.println(category + "を読み込んだ");

		// 変更したファイル名を取得
		MultipartFile fileMltprt = form.getFile();
		String filename = getUploadFileName(fileMltprt.getOriginalFilename());
		System.out.println(filename + "を読み込んだ");

		// ファイル保存処理
		if (form.getFile() == null || form.getFile().isEmpty()) { // 投稿内容に不備があった場合
			// エラー処理は省略
			return "/Main";
		} // 不備がなければアップロード処理を実行
		saveImg(form.getFile());

		// DBへ保存
		uploadService.create(filename, user, text, category);
		
		//コンソールへ情報を書き出し
		File sourceFile = new File(getUploadFileName(form.getFile().getOriginalFilename()));
		Metadata metadata = null;;
		metadata = ImageMetadataReader.readMetadata(sourceFile);
		for (Directory directory : metadata.getDirectories()) {
	          for (Tag tag : directory.getTags()) {
	              System.out.println(tag);
	          }
	      }	

		return "redirect:/Main";
	}
}
