package com.spring.final_project.util;

import java.io.File;

public class FolderService {

	public static void createFolder(String realPath) {
		File makeChFolder = new File(realPath);
		if (!makeChFolder.exists()) {
			System.out.println("dir : " + realPath);
			makeChFolder.mkdirs();
		}
	}
}
