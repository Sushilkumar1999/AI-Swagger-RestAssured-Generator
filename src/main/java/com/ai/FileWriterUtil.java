package com.ai;

import java.io.File;
import java.io.FileWriter;

public class FileWriterUtil {

	public void saveFile(String fileName, String code) {
		try {
			String folderPath = System.getProperty("user.dir") + "\\src\\test\\java";
			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			FileWriter writer = new FileWriter(folderPath + "\\" + fileName);
			writer.write(code);
			writer.close();
			System.out.println("File Created : " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String extractClassName(String code) {
		int classIndex = code.indexOf("class ");
		if (classIndex == -1) {
			return "GeneratedTest";
		}
		String remaining = code.substring(classIndex + 6);
		return remaining.split("\\s+")[0];
	}
}