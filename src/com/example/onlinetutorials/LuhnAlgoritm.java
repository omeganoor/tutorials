package com.example.onlinetutorials;

import java.util.Random;

public class LuhnAlgoritm {
		
	public static void main(String[] args) {
//		String ccNumb = "4716224944054314";
		String ccNumb = "4661601200018262";
		int cardLen = 16;
		String value = getCardNumber(cardLen);

		System.out.println(value);
		System.out.println(luhnChecker(value));
//		System.out.println(luhnChecker(ccNumb));

	}

	private static String getCardNumber(int cardLen){
		String ccNumb = generateCardNumb(cardLen);
		
		while (!luhnChecker(ccNumb)) {
			ccNumb = generateCardNumb(cardLen);
		}
		
		return ccNumb;
	}
	
	private static String generateCardNumb(int cardLen) {
		Random rand = new Random(); 
		String ccNumb = "";
		int value = 0;		
		StringBuilder sb = new StringBuilder();
		sb.append(rand.nextInt(5) + 3);
		for (int i = 0; i< 16-1;i++){
			value = rand.nextInt(9);
			sb.append(value);
		}
		ccNumb = sb.toString();			
		return ccNumb;
	}
	
	public static boolean luhnChecker(String ccNumber){
            int sum = 0;
            boolean alternate = false;
            for (int i = ccNumber.length() - 1; i >= 0; i--)
            {
                    int n = Integer.parseInt(ccNumber.substring(i, i + 1));
                    if (alternate)
                    {
                            n *= 2;
                            if (n > 9)
                            {
                                    n = (n % 10) + 1;
                            }
                    }
                    sum += n;
                    alternate = !alternate;
            }
            return (sum % 10 == 0);
    }
}
