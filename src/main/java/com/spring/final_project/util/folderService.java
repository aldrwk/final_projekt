package com.spring.final_project.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class folderService {

	public static void createFolder(String realPath) {
		File makeChFolder = new File(realPath);
		if (!makeChFolder.exists()) {
			System.out.println("dir : " + realPath);
			makeChFolder.mkdir();
		}
	}
}
