package com.example.filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile_Scanner_NextLine {
	public static void main(String[] pArgs) throws FileNotFoundException {
		System.out.println("javaVersion=" + System.getProperty("java.version"));

//		String fileName = "D:\\Project\\FMP\\DATA-SUBS\\Parse File\\QGA.PROD.S02AFAD.D180429.T1701.txt";
		String fileName = "D:\\Project\\FMP\\DATA-SUBS\\Parse File\\QGA.PROD.S02AFPD.D180429.T1701.txt";

		// String fileName = "D:\\Project\\FMP\\DATA-SUBS\\Parse
		// File\\QGA.PROD.S02AFPD.D180429.T1701.txt";
		// String fileName = "D:\\Project\\FMP\\DATA-SUBS\\Parse
		// File\\QGA.PROD.S02ARED.D180429.T1701.txt";
		File file = new File(fileName);
		List<String> list = new ArrayList<>();

		try (Scanner scanner = new Scanner(file)) {
			long startTime = System.currentTimeMillis();
			System.out.println("Start..." + startTime);
			int count = 0;
			String line;
			boolean hasNextLine = false;
			while (hasNextLine = scanner.hasNextLine()) {
				line = scanner.nextLine();
				// list.add(line);
				count++;
				// System.out.println(line);
			}
			long stopTime = System.currentTimeMillis();
			System.out.println("Done..." + stopTime);

			long elapsedTime = stopTime - startTime;
			System.out.println("elapsedTime=" + elapsedTime);
			System.out.println(count);
		}
	}
}
