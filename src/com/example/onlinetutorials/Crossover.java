package com.example.onlinetutorials;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crossover {

	public static void main(String[] args) {
//		compareObj();
//		System.out.println(first(5, 3));
//		com.exampl.onlinetest.TestFirstApp.doIt(5, 4, 9);
//		System.out.println(9);
//		List<Integer> list = Arrays.asList(1,2,2,3,3,5);
//		getSetSize(list);
//		compareI(1);
		int [] proportions = {3,2,11};
		int [] availableGrams = {25, 18, 72};
		for (int i : calculateRemainders(proportions, availableGrams)) {
			System.out.println(i);
		};
		
		List<Integer> ints = new ArrayList<Integer>();
	}
	
	static int[]  calculateRemainders(int[] proportions,int[] availableGrams) {
	    int[] remaining_grams = new int[3];
	    int availA = availableGrams[0];
	    int availB = availableGrams[1];
	    int availC = availableGrams[2];
	    int propA = proportions[0]; 
	    int propB = proportions[1]; 
	    int propC = proportions[2];
	    
	    while (availA >= propA && availB >= propB && availC >= propC) {
			availA = availA - propA;
			availB = availB - propB;
			availC = availC - propC;
		}
//	    System.out.println(availA+" "+availB+" "+availC);
	    remaining_grams[0] = availA;
	    remaining_grams[1] = availB;
	    remaining_grams[2] = availC;
	    return remaining_grams;  

	}
	
	private static void compareObj() {
		Object obj1 = new Object();
		Object obj2 = new Object();
		
		System.out.println("result : "+(obj1==obj2)+" "+(obj1.equals(obj2))+" ");
		obj1 = obj2;
		System.out.print(obj1.equals(obj2));
	}
	private static int first(int a, int b) {
		if (b==0) {
			return a;
		}else {
			return second(b, a-b);
		}
	}

	private static int second(int a, int b) {
		if (a == 0) {
			return b;
		}else {
			return first(b, a);
		}
	}
	
	private static void getSetSize(List<Integer> list) {
		
		Set<Integer> set = new HashSet<>(list);
		System.out.println(set.size());
	}
	
	private static void compareI(int i) {
		if (i++ == --i) {
			System.out.println("0");
		}else {
			System.out.println("1");
		}
	}
	
}

class TestFirstApp{
	static void doIt(int x, int y, int m) {
		if(x== 5) m=y;
		else m =x;
	}
}