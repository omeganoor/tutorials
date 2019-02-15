import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class BukalapakTest {

	public static void main(String[] args) {
//		int[] a = { 4, 4, 3, 3, 1, 0 };
//		System.out.println(solution(a));
//		System.out.println(solution3(18, 3));
//		Integer i = new Integer(1) + new Integer(2);
//		switch (i) {
//		case 3:
//			System.out.println("three");
//			break;
//
//		default:
//			System.out.println("other");
//			break;
//		}
		
//		Set s = new TreeSet<>();
//		s.add("7");
//		s.add(9);
//		Iterator itr = s.iterator();
//		while(itr.hasNext())System.out.println(itr.next()+" ");
		
//		TreeSet set = new TreeSet<>();
//		set.add("one");
//		set.add("two");
//		set.add("three");
//		set.add("four");
//		set.add("one");
//		Iterator itrs = set.iterator();
//		while(itrs.hasNext())System.out.println(itrs.next()+" ");
		
	}

	public static int solution3(int N, int K) {
		int result = 0;
		int n = 0;
		while (N > 1) {
			if (N % 2 == 0 && n < K) {
				N = N / 2;
				n++;
			}else {
				N = N - 1;
			}
			result ++;
		}
		return result;

	}

	public static int solution(int[] ranks) {
		int result = 0;
		for (int i = 0; i < ranks.length; i++) {
			int currRank = ranks[i];
			if (IntStream.of(ranks).anyMatch(x -> x == currRank + 1)) {
				result++;
//				System.out.println(ranks[i]);
			}
		}
		return result;
	}

	public static String solution(int A, int B) {
		String result = "";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < A; i++) {
			sb.append("a");
		}
		for (int i = 0; i < B; i++) {
			sb.append("b");
		}

		while (sb.toString().contains("aaa") || sb.toString().contains("bbb")) {
			// if () {
			shuffle(sb);
			// }else {
			// result = sb.toString();
			// return sb.toString();
			// }
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static void shuffle(StringBuilder sb) {
		Random rand = new Random();
		for (int i = sb.length() - 1; i > 1; i--) {
			int swapWith = rand.nextInt(i);
			char tmp = sb.charAt(swapWith);
			sb.setCharAt(swapWith, sb.charAt(i));
			sb.setCharAt(i, tmp);
		}
	}

}
