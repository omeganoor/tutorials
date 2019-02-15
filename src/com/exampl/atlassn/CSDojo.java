package com.exampl.atlassn;

import java.util.Queue;
import java.util.Stack;

public class CSDojo {

	public static void main(String[] args) {

		int[] arr = { 9, 9, 9, 9 };// 1329 +1
		int[] result = new int[arr.length];
		int len = arr.length - 1;
		String temp = "";
		// for (int i = 0; i < arr.length; i++) {
		// temp += String.valueOf(arr[i]);
		// }
		int add = 1;
		while (len >= 0) {
			int total = arr[len] + add;
			if (total == 10) {
				add = 1;
				total = total % 10;
			} else {
				add = 0;
			}
			result[len] = total;
			len--;
		}
		if (add == 1) {
			result = new int[result.length + 1];
		}
		result[0] = 1;
		for (int i : result) {
			// System.out.print(i+", ");
		}

		Stack<Integer> st = new Stack<>();
		st.push(1);
		st.push(3);
		st.push(2);

		System.out.println(st);
		System.out.println(st.peek());
		System.out.println(st);
		System.out.println(st.pop());
		System.out.println(st);
		
		Bird norway = new NorwegianBlue();
		double speed = norway.getSpeed();
		System.out.println(speed);
	}

	public static int[] makeItDistinct(int[] arr) {
		int len = arr.length;
		int[] result = new int[len];
		int[] temp = new int[len];
		for (int i = 0; i < len; i++) {
			int index = hash(arr[i]);
			if (temp[index] == 1) {

			} else {
				result[i] = arr[i];
			}
			temp[index]++;
		}

		return result;
	}

	private static int hash(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

}

abstract class Bird {
	// ...
	abstract double getSpeed();
}

class European extends Bird {
	double getSpeed() {
		return 10;
	}
}

class African extends Bird {
	double getSpeed() {
		return 20;
	}
}

class NorwegianBlue extends Bird {
	double getSpeed() {
		return 30;
	}
}
