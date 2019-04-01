package com.example.general;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZoneDateTest {

	public static void main(String[] args) {

/*		String time = ("14032015");
//		ZonedDateTime dateTime = null;
		System.out.println("lalala");
		Date date = new Date();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(date);
		System.out.println(localDate);
		String text = localDate.format(formatter).toString();
		ZonedDateTime parsedDate = ZonedDateTime.parse(text, formatter.ISO_LOCAL_DATE);
		System.out.println(text);
		System.out.println(parsedDate);*/
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//        Date simpleDate = null;
//		ZoneId jkt = ZoneId.of("Asia/Jakarta");
//		String time = " 00:00";
//		String date = "1-mar-16"; 
//		String strDate = "14/01/2016";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//		LocalDateTime localtDateAndTime = LocalDateTime.parse(strDate.concat(time), formatter);
//		ZonedDateTime dateAndTimeInSydney = ZonedDateTime.of(localtDateAndTime, jkt );
//		List<String> month = Stream.of("JAN", "FEB", "MAR").collect(Collectors.toList());

/*		try {
			simpleDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
//		System.out.println(date.substring(3, 6));
//		System.out.println("Current date and time in a particular timezone : " + dateAndTimeInSydney);
////		System.out.println(month.indexOf(date.substring(3, 6).toUpperCase()));
//		System.out.println(getDate(date));
		
		String time = "9999";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String dateInString = "7-Jun-2013";

        try {

            Date date = formatter.parse(time);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
}
	
    private static List<String> month = Stream.of("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC").collect(Collectors.toList());
    private static ZoneId timeZone = ZoneId.of("Asia/Jakarta");
    private static DateTimeFormatter zoneTimeFormatter = DateTimeFormatter.ofPattern("dd-m-yyyy HH:mm");
    
	public static ZonedDateTime getDate(String value) {
		if(value.length()<9){
			value = "0".concat(value);			
		}
		String date = value.substring(0, 2);
		String year = "20"+value.substring(7);
		String Month = String.valueOf(month.indexOf(value.substring(3, 6).toUpperCase())+1);
		String strDate = date+"-"+Month+"-"+year+" 00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HH:mm");
		LocalDateTime localtDateAndTime = LocalDateTime.parse(strDate, formatter);
		ZonedDateTime dateAndTime = ZonedDateTime.of(localtDateAndTime, timeZone );

		return dateAndTime;
	}

}
