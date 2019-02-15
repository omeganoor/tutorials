import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BBMtest {

	public BBMtest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
//		System.out.println(displayMulti("Or", 3));
	    String input = "Charlie,Zoe,08123123123;Andre,Xavier,08111222333;Charlie,Xyz,08123123123;Jean,Summers,08001001001";
	    insertPhoneNumb(input);
	}

	private static void insertPhoneNumb(String input) {
		System.out.println("=== Output START ===");
		System.out.println("Log:");
		Map<String, String> phoneNumberList = new HashMap<>();
		insertData(input, phoneNumberList);
		
		System.out.println("");
		System.out.println("Phone book:");
		printData(phoneNumberList);
		System.out.println("=== Output START ===");

	}

	private static void insertData(String input, Map<String, String> phoneNumberList) {
		for (String str : input.split(";")) {
			String [] data = str.split(",");
			String name = data[0]+" "+data[1];
			String phoneNumb = data[2];
			if (!phoneNumberList.containsKey(phoneNumb)) {
				phoneNumberList.put(phoneNumb, name);
				System.out.println(name+" – "+ phoneNumb+" : insert success");
			}else {
				System.out.println(name+" – "+ phoneNumb+" : duplicate phone number");
			}
		}
	}

	private static void printData(Map<String, String> phoneNumberList) {
		Set<String> keySet = phoneNumberList.keySet();
		int i = 0;
		for (String key : keySet) {
			i++;
			System.out.println(i+". "+ phoneNumberList.get(key)+" - "+key);
		}		
	}


	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	private static String displayMulti(String input, int i) {
		if (input.length() < 3) {
			return appendString(new StringBuilder(input).reverse().toString(), i);
		}else {
			return appendString(input.substring(0, 3), i);
		}
	}

	private static String appendString(String input, int i) {
		String result = "";
		for (int j = 0; j < i; j++) {
			result += input;
		}
		return result;
	}
	
	

}
