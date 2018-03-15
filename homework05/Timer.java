/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  Moriah Tolliver
 *  Date written  :  2017-02-28
 *  Description   :  This class simulates a timer with an input time increment
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  Moriah Tolliver  Initial writing and release
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

    // main to test methods
    public static void main( String args[] ) {
      Timer t = new Timer( 1800 );

      //TEST tick()
      System.out.println( "TESTING tick()" );

      System.out.println( " time slice = 1800 seconds");
      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( "    Time: " + t.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        t.tick();
      }

      System.out.println( "\n time slice = 10 seconds");
      t.timeSlice = 10;
      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( "    Time: " + t.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        t.tick();
      }

      System.out.println( "\n time slice = 12.3 seconds");
      t.timeSlice = 12.3;
      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( "    Time: " + t.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        t.tick();
      }

      System.out.println( "\n time slice = .456 seconds");
      t.timeSlice = .456;
      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( "    Time: " + t.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        t.tick();
      }

      //TEST getTotalSeconds()
      System.out.println( "\nTESTING getTotalSeconds()" );
      for ( int i = 0; i < 25; i++ ) {
        try { System.out.println( "    Time: " + t.toString() + " Total Seconds: " + t.getTotalSeconds() ); }
        catch (Exception e) { System.out.println( e ); }
        t.tick();
      }
    }
}
