package com.example.onlinetutorials;

public class IntersectRecArea {

		public static void main(String[] args) {
			System.out.println("com.exampl.general.test result");
			System.out.println(solution(-40000, 10000, 20000, 60000, 0, -10000, 40000, 30000));
		}

		private static int solution(int K, int L, int M, int N, int P, int Q, int R, int S) {
			int fullarea = 0;
			int intersection_area = 0;
			int first_rec_area = calculateArea(K, L, M, N);
			System.out.println(M-K);
			System.out.println(N-L);
			
			int second_rec_area = calculateArea(P, Q, R, S);
			if ( P < M && S > L ){
				intersection_area = (M-P)*(S-L);
			}		
			System.out.println(first_rec_area+" "+second_rec_area+" "+intersection_area);
			fullarea = (first_rec_area+second_rec_area)-intersection_area;
			if (fullarea < 0) {
				fullarea = -1;
			}
			
			return fullarea;
		}

		private static int calculateArea(int i, int j, int k, int l) {
			
			System.out.println(k-i);
			System.out.println(l-j);
			int area = (k-i)*(l-j);
			if (area < 0) {
				area = 2147483647;
			}
			return area ;
			
		}

}

