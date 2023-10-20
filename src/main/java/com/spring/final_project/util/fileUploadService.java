package com.spring.final_project.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.spring.final_project.util.dateService.uploadTime;
import static com.spring.final_project.util.folderService.createFolder;

public class fileUploadService {

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
