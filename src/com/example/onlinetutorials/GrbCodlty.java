package com.eltov.clientUpdater;


public class GrbCodlty {
  public static void main(String[] args) {
    int Y = 2014;
    String A = "April";
    String B = "May";
    String W = "Wednesday";

    int totalHoliday = 0;

    int firstHol = getFirstDayOfMonth(Y, W, A);
    System.out.println("first : "+firstHol);
    int lastHol = getLastDayOfMonth(Y, W, B);
    System.out.println("last : "+lastHol);
    int sMonth = getMonth(A);
    int eMonth = getMonth(B);
    int firstMonday = getFirstMondayHol(getTotalDay(sMonth, Y), firstHol);
    int lastSunday = getLastSundayHol(getTotalDay(eMonth, Y), lastHol);

    if(A.equalsIgnoreCase(B)){
      totalHoliday = lastSunday - firstMonday;
    }else {
      int addDay = 0;
      if(eMonth - sMonth > 1) {
        for (int i = eMonth+1; i < sMonth; i++){
          addDay += getTotalDay(i, Y);
        }
      }
      totalHoliday = (getTotalDay(sMonth, Y) - firstMonday) + lastSunday + addDay;
    }
    System.out.println("total : "+totalHoliday+" - "+totalHoliday/7);
  }

  private static Integer getTotalDay(int month, int year) {
    switch (month){
      case 1 :
        return 31;
      case 2 :
        return year%4==0?29:28;
      case 3 :
        return 31;
      case 4 :
        return 30;
      case 5 :
        return 31;
      case 6 :
        return 30;
      case 7 :
        return 31;
      case 8 :
        return 31;
      case 9 :
        return 30;
      case 10 :
        return 31;
      case 11 :
        return 30;
      case 12 :
        return 31;
      default:
        return null;
    }
  }

  private static int getLastSundayHol(int month, int lastDay) {
    return lastDay==6?month:month-(lastDay+1);
  }

  private static int getFirstMondayHol(int month, int firstDay) {
    return firstDay==0?1:7-firstDay;
  }

  private static int getLastDayOfMonth(int year, String firstJan, String month) {
    int day = getIntDay(firstJan);
    int monthInt = getMonth(month);
    int jan = getMonth("January");
    while (jan <= monthInt){
      day = (getTotalDay(jan, year)+day)%7;
      jan++;
    }
    return day-1;
  }

  private static Integer getFirstDayOfMonth(int year, String firstJan, String month) {
    int day = getIntDay(firstJan);
    int monthInt = getMonth(month);
    int jan = getMonth("January");
    while (jan < monthInt){
      day = (getTotalDay(jan, year)+day)%7;
      jan++;
    }
    return day;
  }

  private static int getIntDay(String day) {
    switch (day){
      case "Monday" :
        return 0;
      case "Tuesday" :
        return 1;
      case "Wednesday" :
        return 2;
      case "Thursday" :
        return 3;
      case "Friday" :
        return 4;
      case "Saturday" :
        return 5;
      case "Sunday" :
        return 6;
      default:
        return 7;
    }
  }

  public static Integer getMonth(String month){
    switch (month){
      case "January" :
        return 1;
      case "February" :
        return 2;
      case "March" :
        return 3;
      case "April" :
        return 4;
      case "May" :
        return 5;
      case "June" :
        return 6;
      case "July" :
        return 7;
      case "August" :
        return 8;
      case "September" :
        return 9;
      case "October" :
        return 10;
      case "November" :
        return 11;
      case "December" :
        return 12;
      default:
        return null;
    }
  }
}
