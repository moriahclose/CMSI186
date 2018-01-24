/**
 *  File name     :  CalendarStuff.java </br>
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program </br>
 *  Author        :  B.J. Johnson (prototype)</br>
 *  Date          :  2017-01-02 (prototype)</br>
 *  Author        :  Moriah Tolliver</br>
 *  Date          :  2018-25-01</br>
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.</br>
 *  Notes         :  None</br>
 *  Warnings      :  None</br>
 *  Exceptions    :  None</br>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</br>
 *  Revision History</br>
 *  ----------------</br>
 *            Rev      Date     Modified by:  Reason for change/modification</br>
 *           -----  ----------  ------------  -----------------------------------------------------------</br>
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release</br>
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018</br>
 */

 // QUESTION: Why is the expected output for toDayOfWeekString(0) "Friday"?
 // QUESTION: Why is the expected output for toDayOfWeekString(8) "Saturday"?
public class CalendarStuff {

  public static void main (String args[]) {
    System.out.println("You are " + daysBetween(9,9,1998,1,23,2018) + " days old.");
  }

  /**
   * An array containing the days of the week with Sunday being the first day of the week
   */
   private static final String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

  /**
   * An array containing the months of the year
   */
   private static final String[] monthsOfYear = {"January","February","March","April","May","June","July","August","September","October","November","December"};

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };


    private static int daysInFebLeap = 29;
    private static int daysInFeb = 28;

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
   }

  /**
   * A method to determine if the year argument is a leap year or not
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   * NOTE: does not account for validity of year
   */
   public static boolean isLeapYear( long year ) {
      return (year % 4 == 0 && year % 100 != 0) ? true : (year % 400 == 0) ? true : false;
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: outputs 0 if input month is invalid
   */
   public static long daysInMonth( long month, long year ) {
      return ( (month-1) >= monthsOfYear.length ) ? 0 : (monthsOfYear[(int)(month-1)].equals("February") && isLeapYear(year)) ? daysInFebLeap : days[(int)(month-1)] ;
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the
   * NOTE: does not account for validity of dates
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return ( (year1 == year2) && (month1 == month2) && (day1 == day2) ) ? true : false;
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   * NOTE: does not account for validity of dates
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     if (dateEquals(month1, day1, year1, month2, day2, year2)) { return 0; }
     else if ( (year1 == year2) && (month1 == month2) && (day1 > day2) ) { return 1; }
     else if ( (year1 == year2) && (month1 > month2) ) { return 1; }
     else if ( (year1 > year2) ) { return 1; }
     else { return -1; }
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      return ( month < 1 || day < 1 || year < 1 || (month-1) >= days.length || day > daysInMonth(month , year) ) ? false : true;
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   * NOTE: returns "Not a Valid Month" for invalid inputs
   */
   public static String toMonthString( int month ) {
      return ( ((month - 1) >= days.length) || month < 1 ) ? "Not a Valid Month" : monthsOfYear[(int)(month-1)];
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   * NOTE: returns "Not a Valid Day of the Week" for invalid inputs
   */
   public static String toDayOfWeekString( int day ) {
      return ( ((day - 1) >= daysOfWeek.length) || day < 1 ) ? "Not a Valid Day of the Week" : daysOfWeek[(int)(day-1)];
   }

   /**
    * A method to show which parts of the input dates are equal
    * @param    month1 long   containing month number, starting with "1" for "January"
    * @param    day1   long   containing day number
    * @param    year1  long   containing four-digit year
    * @param    month2 long   containing month number, starting with "1" for "January"
    * @param    day2   long   containing day number
    * @param    year2  long   containing four-digit year
    * @return       0,1,2,3   if nothing is equal/years are equal/ month and year are equal/ dates are completely equal
    */

    public static int compareDateParts( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return ( dateEquals(month1, day1, year1, month2, day2, year2) ) ? 3 : (year1 == year2 && month1 == month2 && day1 != day2) ? 2 : (year1 == year2 && month1 != month2 && day1 != day2) ? 1 : 0;
    }


  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   * NOTE: returns 0 if invalid date is entered
   * NOTE: put if end dates are included are not
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
    long daysInYear = 365;
    long daysInLeapYear = 366;
    long dayCount = 0;

    if (dateEquals(month1, day1, year1, month2, day2, year2)) { return 0; }

    if (!isValidDate(month1, day1, year1) || !isValidDate(month2, day2, year2)){ System.out.println("Invalid Date Entered"); return 0;}

    // Switches dates so earlier date is always date 1
   if (compareDate(month1, day1, year1, month2, day2, year2) == 1){
      long monthTemp = month2;
      month2 = month1 - 1;     // subtracted one so month number is equal to the index of the month in days
      month1 = monthTemp - 1; // subtracted one so month number is equal to the index of the month in days

      long dayTemp = day2;
      day2 = day1;
      day1 = dayTemp;

      long yearTemp = year2;
      year2 = year1;
      year1 = yearTemp;
    }
    else {
      month1--;
      month2--;
    }

    long daysUpToStart = 0;
    for (int month = 0; month < month1; month++){
      daysUpToStart += days[month];
    }
    daysUpToStart += day1;

    long daysAfterJan = 0;
    for (int month = 0; month < month2; month++) {
      daysAfterJan += days[month];
    }
    daysAfterJan += day2;

    long numOfLeapYears = 0;
    for (int year = (int)year1; year <= year2; year++){
      numOfLeapYears += ( isLeapYear(year) ) ? 1 : 0;
    }
    dayCount += ((daysInYear*(year2 - year1)) + 1) - daysUpToStart + daysAfterJan + numOfLeapYears;

    return --dayCount;
   }

}
