package com.exampl.atlassn;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Codillity {

	public static void main(String[] args) {

		int arr[] = { 1, 3, 6, 4, 1, 2 };
		int result = 1;
		// arr = sort(arr);
		// for (int i : arr) {
		// System.out.println(i);
		// }
		////
		// result = solution(arr);
		// System.out.println(result);

		// Integer[] numbers = { 7, 7, 8, 9, 10, 8, 8, 9, 6, 5, 4 };
		Integer[] numbers = IntStream.of(arr).boxed().toArray(Integer[]::new);
		List<Integer> list = Arrays.asList(numbers);
		Set<Integer> set = new TreeSet<Integer>(list);
		List<Integer> news = new ArrayList<>();
		int size = set.size();
		while (size > 0) {
			if (!set.contains(result)) {
				System.out.println(result);
			}
			size--;
			result++;
		}
		System.out.println("The set contains..." + result);

		System.out.println(Collections.max(list));
		// for (Integer n : set) {
		// System.out.println(n);
		// }

	}

	private static int solution(Integer[] arr) {
		int missVal = 1;
		int size = arr.length;
		List<Integer> list = Arrays.asList(arr);
		for (Integer i : arr) {
			list.add(i);
		}
		// System.out.println("last " + list.get(list.size()-1));
		while (size > 0) {
			if (!list.contains(missVal)) {
				System.out.println(missVal);
				return missVal;
			}
			size--;
			missVal++;
		}
		System.out.println("res " + missVal);
		// missVal = list.get(list.size()-1)+1;
		return missVal;
	}

	private static int[] sort(int[] arr) {
		TreeSet myTreeSet = new TreeSet();
		List<Integer> result = new ArrayList<>();
		for (int i : arr) {
			myTreeSet.add(i);
		}
		result.addAll(myTreeSet);
		int[] ints = result.stream().mapToInt(Integer::intValue).toArray();

		return ints;
	}

}
