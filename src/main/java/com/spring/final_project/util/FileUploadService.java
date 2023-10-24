package com.spring.final_project.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.spring.final_project.util.DateService.uploadTime;
import static com.spring.final_project.util.FolderService.createFolder;

public class FileUploadService {

	private static String savePath = "/src/main/resources/static";

	public static String imageUpload(String saveFolder, MultipartFile file) {
		String saveDbPath = "";
		try {
			// 이미지를 저장할 경로 설정
			String realPath = System.getProperty("user.dir") + savePath + saveFolder;
			createFolder(realPath);
			String fileName = uploadTime() + "_" + file.getOriginalFilename();
			String filePath = realPath + File.separator + fileName;
			File dest = new File(filePath);
			file.transferTo(dest);
			saveDbPath = saveFolder + "/" + fileName;
		} catch (
				IOException e) {
			e.printStackTrace();
		}
		return saveDbPath;
	}
}
