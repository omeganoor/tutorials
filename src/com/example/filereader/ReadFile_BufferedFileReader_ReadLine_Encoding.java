package com.example.filereader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile_BufferedFileReader_ReadLine_Encoding {
	public static void main(String [] args) throws IOException {
		System.out.println("javaVersion=" + System.getProperty("java.version"));

//		String fileName = "D:\\Project\\FMP\\DATA-SUBS\\Parse File\\QGA.PROD.S02AFAD.D180429.T1701.txt";
		String fileName = "D:\\Project\\FMP\\DATA-SUBS\\Parse File\\QGA.PROD.S02AFPD.D180429.T1701.txt";

		FileInputStream fileInputStream = new FileInputStream(fileName); 
		
		//specify UTF-8 encoding explicitly
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
		
		try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			long startTime = System.currentTimeMillis();
			String line;
			List<String> data = new ArrayList<String>();
			System.out.println(startTime);
			int max = 6000000;
			while((line = bufferedReader.readLine()) != null) {
				//comment out next line for performance tests
				if ( data.size() >= max) {
					data.add(line);
					System.out.println("file record " + data.size());
					break;
				}
//				System.out.println(line);
			}
			long stopTime = System.currentTimeMillis();
			System.out.println(stopTime);
			long elapsedTime = stopTime - startTime;
			System.out.println("elapsedTime=" + elapsedTime);
		}
	}
}
