package com.spring.final_project.util;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class folderServiceTest {

		private static String savePath = "/src/main/resources/static";
	private static String saveFolder = "/image/product/2023-10-20/1";
	@Test
	void createFolder() {
		String realPath = System.getProperty("user.dir") + savePath + saveFolder;
		File makeChFolder = new File(realPath);
		if (!makeChFolder.exists()) {
			System.out.println("dir : " + realPath);
			makeChFolder.mkdirs();
		}
	}
}