package com.example.onlinetutorials;

public class RakutenCodilityTest {

	public static void main(String[] args) {

		int arr[] = { 1, 3, 6, 4, 1, 2 };

		System.out.println(solution(arr));
	}

	private static int solution(int[] arr) {
		int result = -1;

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] != arr[j]) {
					System.out.println("i " + i + " j " + j);
					if (isAdjacent(arr, arr[i], arr[j])) {
						System.out.println("gotcha...");
						if (result < (j - i)) {
							System.out.println("gotcha..." + i + " & " + j);
							result = j - i;
						}
					}
				}

			}
		}
		return result;
	}

	private static boolean isAdjacent(int[] arr, int i, int j) {
		boolean cond = true;
		int temp = 0;
		if (i > j) {
			temp = i;
			i = j;
			j = temp;
		}
		for (int val : arr) {
			if (i < val && val < j) {
				System.out.println(val);
				return false;
			}
		}
		return cond;
	}


}
