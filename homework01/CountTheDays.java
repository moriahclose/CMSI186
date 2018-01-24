/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  CountTheDays.java
 *  Purpose       :  Counts the days in between dates given from the command line
 *  Author        :  Moriah Tolliver
 *  Date          :  2018-01-25
 *  Description   :  Takes in two dates as command line arguments and returns the number of days between the days
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:      Reason for change/modification
 *           -----  ----------  ------------     -----------------------------------------------------------
 *  @version 1.0.0  2018-01-25  Moriah Tolliver  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class CountTheDays {
   public static void main( String args[] ) {
     if (args.length < 6) {
       System.out.println("There are not enough arguments.");
     }
     else {
      System.out.println( "There are " + (CalendarStuff.daysBetween((long)Integer.parseInt(args[0]), (long)Integer.parseInt(args[1]), (long)Integer.parseInt(args[2]), (long)Integer.parseInt(args[3]), (long)Integer.parseInt(args[4]), (long)Integer.parseInt(args[5]))) + " days between " + CalendarStuff.toMonthString(Integer.parseInt(args[0])) + " " + args[1] + ", " + args[2] + " and " + CalendarStuff.toMonthString(Integer.parseInt(args[3])) + " " + args[4] + ", " + args[5] + "." );
     }
   }
}
