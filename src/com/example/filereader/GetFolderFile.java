package com.example.filereader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFolderFile {

	public GetFolderFile() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String dirPath = "D:\\Project";
		File file = new File(dirPath);
		System.out.println(getTotalFiles(dirPath));
	}

	public static List<String> getTotalFiles(String dirPath) {
		File file = new File(dirPath);
		File[] files = file.listFiles();
		List<String> result = new ArrayList<String>();
		for (File filename : files) {
			result.add(filename.toString());
		}
		return result;
	}
}
