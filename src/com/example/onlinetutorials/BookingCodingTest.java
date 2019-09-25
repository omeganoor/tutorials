package com.example.onlinetutorials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookingCodingTest {

	public static void main(String[] args) {
//		int[] array = { 25626, 25757, 24367, 24267, 16, 100, 2, 7277 };
//		int marks[][] = { { 1481122000, 1481122020 }, 
//				{ 1481122000, 1481122040 }, 
//				{ 1481122030, 1481122035 },
//				{ 1481122010, 1481122025 } 
//				};
//		
//		delta_encode(array);
//		System.out.println(howManyAgentsToAdd(2, marks));
		
		String as = "The breakfast is ok. Regarding location, it is breakfast quite far from citycenter breakfast but price is cheap so it is worth.";
		String reviews[] = {as,
				"breakfast beach citycenter location metro view staff price",
				"breakfast beach citycenter location metro view staff price",
				"breakfast beach citycenter location metro view staff price","alalala aljsal akjasr"};
		String regex = "breakfast beach view staff price";
        String [] keys = regex.split(" ");
        int [] ids = {2,5,1,3,4};
       
//        for (String keyword : keys) {
//			System.out.println(keyword);
//			for (int i = 0; i<ids.length; i++) {
//	        	String review = reviews[i];
//	        	System.out.println(review);
//	        	System.out.println(ids[i]);
//	        	System.out.println(getCount(keyword, review));
//
//	        }
//		}
        
		for (int i : sort_hotels(regex, ids, reviews)) {
			System.out.println(i);
		}


	}

	static int triangle(int a, int b, int c) {
		int result = 0;
		if (a > 0 && b > 0 && c > 0) {
			if (a + b > c && b + c > a && c + a > b) {
				result = 1;
			} else if (a == b && b == c) {
				result = 2;
			}
		}

		return result;
	}
	
	public static int getCount(String keyword, String review) {
		int count = 0;
		while(review.contains(keyword)) {
			count ++;
			review = review.replaceFirst(keyword, " ");
		}
		return count;
	}
	
	static int[] sort_hotels(String keywords, int[] hotel_ids, String[] reviews) {
        Map<Integer, String> hotelReview = new HashMap<>();
        for(int i = 0; i<reviews.length; i++) {
        	int id = hotel_ids[i];
			String review = reviews[i].toLowerCase().replaceAll("[^A-Za-z0-9]"," ")+" ";
        	if(null != hotelReview.get(id)) {
        		String newReview = hotelReview.get(id) +" "+review;
        		hotelReview.put(id, newReview);
        		break;
        	}
        	hotelReview.put(id, review);
        }
        Map<Integer, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, String> data : hotelReview.entrySet()) {
			for (String rev : keywords.split(" ")) {
				int id = data.getKey();
				int count = 0;
				String [] counts = data.getValue().split(rev.trim().toLowerCase());
				count = counts.length-1;
				if (null != result.get(id)) {
					result.put(id, result.get(id)+count);
				}else {
					result.put(id, count);
				}
			}
	        System.out.println(data.getKey() + " -> " + data.getValue());
		}
        System.out.println(result);
        List<Integer> res = new ArrayList<>();
        result.entrySet()
        .stream()
        .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
        .forEachOrdered(x -> res.add(x.getKey()));

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

	static int[] delta_encode(int[] array) {
		List<Integer> listResult = new ArrayList<>();
		int min = 0;

		for (int i = 0; i < array.length - 1; i++) {
			// System.out.println(array[i]);
			listResult.add(array[0]);
			min = array[i + 1] - array[i];
			if (Math.abs(min) > 127) {
				listResult.add(-128);
			}
			listResult.add(min);

		}
		int[] result = new int[listResult.size()];

		int k = 0;
		for (int i : listResult) {
			System.out.println(i);
			result[k] = i;
			k++;
		}
		return result;
	}

	static int howManyAgentsToAdd(int noOfCurrentAgents, int[][] callsTimes) {

		int result = 0;
		int count = 0;
		for (int i = 0; i < callsTimes.length; i++) {
			int startTime1 = callsTimes[i][0];
			int endTime1 = callsTimes[i][1];
			for (int j = i+1; j < callsTimes.length; j++) {
				int startTime2 = callsTimes[j][0];
				int endTime2 = callsTimes[j][1];
				System.out.println(startTime1+" => "+endTime1+" -> "+startTime2+" => "+endTime2);	
				if (!(startTime1 >= endTime2 || startTime2 >= endTime1)) {
					count++;
					System.out.println(count);

				}

			}
		}
		System.out.println("count..." + count);
		if (count > noOfCurrentAgents) {
			result = count - noOfCurrentAgents;
		}
		return result;
	}

}
