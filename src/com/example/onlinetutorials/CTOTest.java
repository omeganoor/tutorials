package com.example.onlinetutorials;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CTOTest {

	public static void main(String[] args) {
		double x = 5.5;
		double [] arr = {-1.5, 0, 4.4, 5, 6, 7};
		String url = "http://www.amazon.co.uk/something";
		int [][] sudokuInp = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
		{7, 3, 5, 8, 1, 9, 6, 4, 2},
		{1, 9, 4, 2, 6, 5, 8, 7, 3},
		{3, 1, 7, 5, 8, 4, 2, 6, 9},
		{6, 5, 9, 1, 7, 2, 4, 3, 8},
		{4, 8, 2, 9, 3, 6, 7, 1, 5},
		{9, 4, 8, 7, 5, 1, 3, 2, 6},
		{5, 6, 1, 4, 2, 3, 9, 8, 7},
		{2, 7, 3, 6, 9, 8, 1, 5, 4}};
//		System.out.println(searchClosest(x, arr ));
//		System.out.println(getTDL(url));
//		System.out.println(doit(1071, 1029));
//		getUrlContent(url);
//		System.out.println(minStepPalindrome("aaab"));
		System.out.println(sudokuValidator(sudokuInp));
	}

	/*Task 1: Searching in an array
	Please write a function that searches in an array of a numeric values for the value  that is closest to a given value N. If there are two values that are equally far away, return the greater value.

	Example:
	searchClosest(4.5, array(-1.5, 0, 4.4, 5, 6, 7));
	=> 4.4
	searchClosest(5.5, array(-1.5, 0, 4.4, 5, 6, 7));
	=> 6*/
	private static double  searchClosest(double x, double[] arr) {
		double closestValue = 0;
		double diff = Double.MAX_VALUE;
		for (double d : arr) {
			if (Math.abs(d - x) < diff) {
				diff = Math.abs(d - x);
				closestValue = d;
			}else if(Math.abs(d - x) == diff) {
				closestValue = closestValue > d? closestValue:d;
			}
		}
		return closestValue;
	}
	
	/*Task 2: Extract top level domain from URL
	Create a function that returns the top level domain from any given input URL.

	Examples:
	getTLD('http://www.google.de/something');
	=> de
	getTLD('http://www.amazon.co.uk/something');
	=> co.uk*/
	private static String getTDL(String stringUrl) {
		String tdl = "";
		URL url = null;
	    try {
	        url = new URL(stringUrl);
	        String[] domainNameParts = url.getHost().split("\\.");
	        tdl = domainNameParts[domainNameParts.length-1];
	        if (domainNameParts[domainNameParts.length-2].length() < 4) {
				tdl = domainNameParts[domainNameParts.length-2]+"."+tdl;
			}
	    }
	    catch (MalformedURLException e) {   
	    }
		return tdl;
	}
	
	private static int doit(int a, int b) {
		// your code here
		// use of the gmp_* functions are not allowed
//		int mod = a % b;
//		System.out.println(mod);
		while(b != 0){
			int mod = a % b;
			a = b;
			b = mod;
			System.out.println(a+", "+b);
		}
//		return (b == 0) ? a : doit(b, a % b);
		return a;
	}
	
	private static void getUrlContent(String url) {
		HttpURLConnection content = null;
		try {
			content = (HttpURLConnection) new URL("http://www.rocket-internet.de").openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(content.getContentLength());
	}
	
	private static int minStepPalindrome(String str) {
		int minStep = 0;
		char[] charArr = str.toCharArray();
		int i = 0;
		int len = charArr.length;
		while(i < len/2) {
			if (charArr[i] != charArr[len-1-i]) {				
				minStep += Math.abs(Integer.valueOf(charArr[i]) - Integer.valueOf(charArr[len-1-i]));
			}
			i++;
		}		
		return minStep;
	}
	
	/*	{1, 2, 3, 4, 5, 6, 7, 8, 9}
		{7, 3, 5, 8, 1, 9, 6, 4, 2}
		{1, 9, 4, 2, 6, 5, 8, 7, 3}
		{3, 1, 7, 5, 8, 4, 2, 6, 9}
		{6, 5, 9, 1, 7, 2, 4, 3, 8}
		{4, 8, 2, 9, 3, 6, 7, 1, 5}
		{9, 4, 8, 7, 5, 1, 3, 2, 6}
		{5, 6, 1, 4, 2, 3, 9, 8, 7}
		{2, 7, 3, 6, 9, 8, 1, 5, 4}*/
	private static boolean sudokuValidator(int[][] arr) {
		
		int block1[] = {arr[0][0],arr[0][1],arr[0][2],arr[1][0],arr[1][1],arr[1][1],arr[2][0],arr[2][1],arr[2][2]};
		int block2[] = {arr[0][3],arr[0][4],arr[0][5],arr[1][3],arr[1][4],arr[1][5],arr[2][3],arr[2][4],arr[2][5]};
		int block3[] = {arr[0][6],arr[0][7],arr[0][8],arr[1][6],arr[1][7],arr[1][8],arr[2][6],arr[2][7],arr[2][8]};
		int block4[] = {arr[3][0],arr[3][1],arr[3][2],arr[4][0],arr[4][1],arr[4][1],arr[5][0],arr[5][1],arr[5][2]};
		int block5[] = {arr[3][3],arr[3][4],arr[3][5],arr[4][3],arr[4][4],arr[4][5],arr[5][3],arr[5][4],arr[5][5]};
		int block6[] = {arr[3][6],arr[3][7],arr[3][8],arr[4][6],arr[4][7],arr[4][8],arr[5][6],arr[5][7],arr[5][8]};
		int block7[] = {arr[6][0],arr[6][1],arr[6][2],arr[7][0],arr[7][1],arr[7][1],arr[8][0],arr[8][1],arr[8][2]};
		int block8[] = {arr[6][3],arr[6][4],arr[6][5],arr[7][3],arr[7][4],arr[7][5],arr[8][3],arr[8][4],arr[8][5]};
		int block9[] = {arr[6][6],arr[6][7],arr[6][8],arr[7][6],arr[7][7],arr[7][8],arr[8][6],arr[8][7],arr[8][8]};
		
		for(int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				for (int k = j+1; k < arr.length; k++) {
					if (arr[i][j] == arr[i][k]) {
						System.err.println(i+", "+j+", "+k);
						return false;
					}
				}
			}
		}
		
		if (validateBlock(block1) && validateBlock(block2) && validateBlock(block3) &&
				validateBlock(block4) && validateBlock(block5) && !validateBlock(block6) &&
				validateBlock(block7) && validateBlock(block8) && !validateBlock(block9)) {
			return true;
		}else {
			return false;
		}		
	}

	private static boolean validateBlock(int[] block) {
		for(int i = 0; i < block.length; i+=3) {
			for (int j = i+1; j < block.length; j++) {
				if (block[i] == block[j]) {
					return false;
				}
			}
		}
		return true;
	}



}
