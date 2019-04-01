package com.example.onlinetutorials;

import java.util.ArrayList;
import java.util.List;

public class Amazon {

  public static void main (String[] args){
    System.out.println("apake");
    int [] arr = {1, 23, 12, 9, 30, 2, 50};
    int k = arr.length;
    System.out.println(kthLargestElement(arr, k));
  }
  
/*
Find K-th largest element in array
Question: Write an efficient program for printing k largest elements in an array. Elements in array can be in any order.
For example, if given array is [1, 23, 12, 9, 30, 2, 50] and you are asked for the largest 3 elements 
i.e., k = 3 then your program should print 50, 30 and 23.
*/
  	public static int kthLargestElement(int [] arr, int k) {
  		int[] res = new int[k];
  		int n = k-1;
  		int max = arr[0];
  		res[n] = max;
  		for(int i = 1; i< arr.length; i++) {
  			int curr = arr[i];
			System.out.println("res : "+max +" -> "+curr);

  			if(max <= curr) {
  				if(n == 0) {
  					max  = curr;
  					res[0] = max;
  					int j = 0;
  					while(j < k-1 && res[j] >= res[j+1] ) {
  						max = res[j+1];
  						res[j+1] = res[j];
  						res[j] = max;
  						j++;
  					}
  					max = res[0];
  				}else if(n > 0){
  					res[n-1] = max;
  					res[n] = curr;
  					int j = n;
  					while(j < k-1 && res[j] >= res[j+1] ) {
  						max = res[j+1];
  						res[j+1] = res[j];
  						res[j] = max;
  						j++;
  					}
  					max = res[n-1];
  					n--;
  				}
  			}else if(max > curr){
  				if(n > 0) {
  					res[n-1] = curr;
  					max = res[n-1];
  				}
  			}
  		}
  		
  		return res[0];
  	}
  
}
