package com.exampl.atlassn;

public class Rktn {

	public static void main(String[] args) {
		System.out.println("test result");
		System.out.println(solution(1, 1, 4, 4, 2, 2, 3, 3));
		System.out.println(solution(-40000, 10000, 20000, 60000, 0, -10000, 40000, 30000));
	}

	private static int solution(int K, int L, int M, int N, int P, int Q, int R, int S) {
		int fullarea = 0;
		int intersection_area = 0;
		int second_rec_area = 0;
		int first_rec_area = 0;

		first_rec_area = getArea(K, L, M, N);
		second_rec_area = getArea(P, Q, R, S);
		intersection_area = getIntersecArea(K, L, M, N, P, Q, R, S);
		fullarea = (first_rec_area + second_rec_area) - intersection_area;

		if (fullarea < 0) {
			fullarea = -1;
		}

		return fullarea;
	}

	private static int getIntersecArea(int k, int l, int m, int n, int p, int q, int r, int s) {
		int x1 = Math.max(k,p);
		int x2 = Math.min(m, p);
		int y1 = Math.max(l, q);
		int y2 = Math.min(n, s);	

		return ((x2-x1)*(y2-y1));
	}

	private static int getArea(int i, int j, int k, int l) {

		System.out.println(k - i);
		System.out.println(l - j);
		int area = (k - i) * (l - j);
		if (area < 0) {
			area = 2147483647;
		}
		return area;

	}
}
