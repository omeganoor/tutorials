
public class PalindromeTest {

	public static void main(String[] args) {
		System.out.println(isPalindrome("123432"));

	}

	private static boolean isPalindrome(String string) {
		
		int len = string.length();
		int i = 0;
		while(i < len/2) {
			if (string.charAt(i) != string.charAt(len-(i+1))) {
				return false;
			}
			i++;
		}
		
		return true;
	}

}
