/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Timer {

  // classwide variables
  private static final double SECONDS_IN_HOUR = 3600.0;
  private static final double SECONDS_IN_MINUTE = 60.0;
  public static double timeSlice;
  public static double totalSeconds;
  public static double hour;
  public static double minute;
  public static double second;

  public Timer( double timeSlice ) {
    this.timeSlice = timeSlice;
    totalSeconds = 0;
  }

  /**
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      totalSeconds += timeSlice;

      hour = Math.floor( totalSeconds / SECONDS_IN_HOUR );
      minute = Math.floor( ( totalSeconds - ( SECONDS_IN_HOUR * hour ) ) / SECONDS_IN_MINUTE );
      second = totalSeconds - ( SECONDS_IN_HOUR * hour ) - ( SECONDS_IN_MINUTE * minute );

      return totalSeconds;
   }

   /**
    *  Method to fetch the total number of second
    *   we can use this to tell when 12 hours have elapsed
    *  @return double-precision value the total second private variable
    */
    public double getTotalSeconds() {
       return totalSeconds;
    }

   /**
    *  Method to return a String representation of this timer
    *  @return String value of the current timer
    */
    public String toString() {
      String hourAndMinPattern = "00";
      String secondPattern = "00.0";
      DecimalFormat formatHAndM = new DecimalFormat(hourAndMinPattern);
      DecimalFormat formatSec = new DecimalFormat(secondPattern);
      return formatHAndM.format(hour) + " : " + formatHAndM.format(minute) + " : " + formatSec.format(second);
    }

    public static void main( String args[] ) {
      Timer t = new Timer( 1800 );

      //TEST tick()
      for ( int i = 0; i < 50; i++ ) {
        try { System.out.println( "Time: " + t.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        t.tick();
      }
    }
}
