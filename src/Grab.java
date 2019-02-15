
public class Grab {

	public static void main(String[] args) {
		int[] arr = { 1, 1, 2, 3, 4 };
		int[] a = { 5, 5, -500, -500, -500, 5, 5 };
		int[] b = { -3, -3, 3, 5 };
		int[] c = { 3, 3, 3, 3, 3, 3, 1, 1, 1, 1 };
		int[] same = { 3, 3, 3, 3 };
		int[] d = { 1, 1, 2, 3, 4, 3, 2, 0, 1 };
		int[] e = { 3, 3, -1, 2, 3, 1, 0, 1 };
//		System.out.println(solution(c));
//		System.out.println(solution(99));
		System.out.println("testes");
	}

//	private static int solution(int[] A) {
//		int result = 2;
//		boolean sameVal = true;
//		int len = A.length;
//		int prev = A[0], curr = 0, next = 0;
//
//		for (int i = 1; i < len - 1; i++) {
//			curr = A[i];
//			next = A[i + 1];
//			if ((prev < curr && curr > next) || (prev > curr && curr < next)) {
//				prev = curr;
//				result++;
//			}
//
//			if (sameVal) {
//				if (prev != curr || prev != next || curr != next)
//					sameVal = false;
//			}
//		}
//		return sameVal ? 1 : result;
//	}
//
//	public static String solution(int N) {
//		StringBuilder result = new StringBuilder();
//		int i = 1;
//		while (i <= N) {
//			if (i % 2 == 1) {
//				result.append("+");
//			} else if (i % 2 == 0) {
//				result.append("-");
//			}
//			i++;
//		}
//		return result.toString();
//	}
	
	public static boolean isPalindrome(String str) {
		int len = str.length();
		int  i = 0;
		while(i > len/2) {
			if (str.charAt(i) != str.charAt(len-(i+1))) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	/*
	SELECT mv.id AS id, mv.title AS title, IFNULL(SUM(rs.number_of_tickets), 0) AS sold_tickets 
	FROM movies mv
	LEFT JOIN reservations rs ON mv.id = rs.movie_id
	GROUP BY mv.id
	ORDER BY SUM(number_of_tickets) DESC, mv.id ASC;
	*/

}
