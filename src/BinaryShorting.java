import java.util.ArrayList;
import java.util.TreeSet;

public class BinaryShorting {

	public static void main(String[] args) {
//		int [] arr = {3, 7, 8, 1, 2, 3};
		int [] arr = {5, 5,3,7,10,14};
		int [] result = null;
		result = shorting(arr);
		for (int i : result) {
			System.out.print(i+" ");
		}
	}

	private static int[] shorting(int[] arr) {
		TreeSet<Integer> getVal = new TreeSet<Integer>();
		for (int i : arr) getVal.add(i);
		int[] result = new int[getVal.size()];
		int i = 0;
		int temp = 0;
		for (Integer data : getVal) {
			result[i] = data;
			i++;
		}
		
		for(i=0; i<result.length-1; i++){
			if(Integer.bitCount(result[i])>Integer.bitCount(result[i+1])){
				temp = result[i+1];
				result[i+1] = result[i];
				result[i] = temp;
			}
		}
		
		return result;
	}

}
