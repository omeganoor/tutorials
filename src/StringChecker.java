import java.io.File;

public class StringChecker {

	public static void main(String[] args) {
		String str = "Nnsdj";
		char ch = new StringBuilder(str).charAt(0);
		System.out.println(ch + "......" + Character.toUpperCase(ch));
		System.out.println(charChecker(str));
		String dirPath = "D:\\Project\\FMP\\DATA-SUBS";
		File file = new File(dirPath);
		System.out.println(getTotalFiles(dirPath));

	}

	public static boolean charChecker(String str) {
		char ch = new StringBuffer(str).charAt(0);
		if (ch == Character.toUpperCase(ch)) {
			return true;
		} else {
			return false;
		}

	}

	public static long getTotalFiles(String dirPath) {
		File file = new File(dirPath);
		File[] files = file.listFiles();
		int count = 0;

		for (File f : files) {
			System.out.println(f);
			if (f.isDirectory())
				count += getTotalFiles(f.toString());
			else
				count++;
		}

		return count;
	}

}
