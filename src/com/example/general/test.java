package com.example.general;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		StringBuilder freq = new StringBuilder("0000000");
		System.out.println(freq.toString());
		
		freq.replace(0, freq.length(), "1111111");
		System.out.println(freq.toString());
	}

}
