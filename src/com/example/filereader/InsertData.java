package com.example.filereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class InsertData {

	public static void main(String[] args) {
		String url = "D:\\Project\\FMP\\DATA-SUBS\\Parse File\\QGA.PROD.S02AFAD.D180429.T1701.txt";
		insertData(url);
	}

	private static void insertData(String url) {
		long startTime = System.currentTimeMillis();
		System.out.println("Start..."+ startTime);
		long size = 0;
		try {//
			Stream<String> stream = Files.lines(Paths.get(url)); 
			stream.forEach(data -> insert(data) );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Done..."+stopTime);
		System.out.println("total rowwwwws " + size);
		System.out.println("elapsedTime=" + elapsedTime);
	}
	
	public static String insert(String e) {
		String result = "";
		result = result.concat(","+e);
		return result;
	}
	
	

}
