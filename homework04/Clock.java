/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
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

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double MAXIMUM_TIME_SLICE = 1800.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private static final double SECONDS_IN_HOUR = 3600.0;
   private static final double SECONDS_IN_MINUTE = 60.0;
   private static final double EPSILON_VALUE = 0.001;
   private static double totalSeconds;
   private static double timeSlice;
   private static double second;
   private static double minute;
   private static double hour;
   private static double minuteHandAngle;
   private static double hourHandAngle;

   // public Constructor
   public Clock() {
      totalSeconds = 0;
      timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
      second = 0;
      minute = 0;
      hour = 0;
      minuteHandAngle = 0;
      hourHandAngle = 0;
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
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {

     double angle = 0.0;

      try { angle = Double.parseDouble( argValue ); }
      catch (Exception e) { throw new NumberFormatException(); }

      if ( angle < 0 || angle > MAXIMUM_DEGREE_VALUE ) {
        throw new NumberFormatException();
      }
      return angle;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  NOTE: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  NOTE: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   * NOTE: any errors (including if argument is > 1800.0) with timeSlice set the variable to 60 secs
   */
   public double validateTimeSliceArg( String argValue ) {

      try { timeSlice = Double.parseDouble( argValue ); }
      catch (Exception e) { timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS; }

      if ( timeSlice <= 0 || timeSlice > MAXIMUM_TIME_SLICE ) {
        timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
      }

      return timeSlice;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      hourHandAngle = totalSeconds * HOUR_HAND_DEGREES_PER_SECOND;
      return hourHandAngle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      minuteHandAngle = ( totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND ) - ( MAXIMUM_DEGREE_VALUE * Math.floor( ( totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND ) / MAXIMUM_DEGREE_VALUE ) );
      return minuteHandAngle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      return Math.abs( this.getMinuteHandAngle() - this.getHourHandAngle() );
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
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
     String hourAndMinPattern = "00";
     String secondPattern = "00.0";
     DecimalFormat formatHAndM = new DecimalFormat(hourAndMinPattern);
     DecimalFormat formatSec = new DecimalFormat(secondPattern);
     return formatHAndM.format(hour) + " : " + formatHAndM.format(minute) + " : " + formatSec.format(second);
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

     // create epsilon for TESTING
     double EPSILON = 0.2;

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );

      // TESTS FOR validateAngleArg()
      System.out.println( "\n    Testing validateAngleArg()....");
      System.out.print( "      sending '  -5.0 degrees', expecting NumberFormatException()" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "-5.0" )) ? "    PASSED" : clock.validateAngleArg( "-5.0" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0" )) ? "    PASSED" : clock.validateAngleArg( "0" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  23.09 degrees', expecting double value   23.09" );
      try { System.out.println( (23.09 == clock.validateAngleArg( "23.09" )) ? "    PASSED" : clock.validateAngleArg( "23.09" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  189.3333 degrees', expecting double value   189.3333" );
      try { System.out.println( (189.3333 == clock.validateAngleArg( "189.3333" )) ? "    PASSED" : clock.validateAngleArg( "189.3333" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  360.0 degrees', expecting double value   360.0" );
      try { System.out.println( (360.0 == clock.validateAngleArg( "360.0" )) ? "    PASSED" : clock.validateAngleArg( "360.0" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  0.0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? "    PASSED" : clock.validateAngleArg( "0.0" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  450 degrees', expecting double value   NumberFormatException()" );
      try { System.out.println( (450 == clock.validateAngleArg( "450" )) ? "    PASSED" : clock.validateAngleArg( "450" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      // TESTS FOR validateTimeSliceArg()
      System.out.println( "\n    Testing validateTimeSliceArg()....");
      System.out.print( "      sending '  -13.7 second', expecting 60.0" );
      try { System.out.println( (60.0 == clock.validateTimeSliceArg( "-13.7" )) ? "    PASSED" : clock.validateTimeSliceArg( "-13.7" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending ' 0.0 second', expecting 60.0" );
      try { System.out.println( (60.0 == clock.validateTimeSliceArg( "0.0" )) ? "    PASSED" : clock.validateTimeSliceArg( "0.0" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  18.5 second', expecting 18.5" );
      try { System.out.println( (18.5 == clock.validateTimeSliceArg( "18.5" )) ? "    PASSED" : clock.validateTimeSliceArg( "18.5" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  345 second', expecting 345.0" );
      try { System.out.println( (345.0 == clock.validateTimeSliceArg( "345" )) ? "    PASSED" : clock.validateTimeSliceArg( "345" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  1234.567 second', expecting 1234.567" );
      try { System.out.println( (1234.567 == clock.validateTimeSliceArg( "1234.567" )) ? "    PASSED" : clock.validateTimeSliceArg( "1234.567" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  0.000432 second', expecting 0.000432" );
      try { System.out.println( (0.000432 == clock.validateTimeSliceArg( "0.000432" )) ? "    PASSED" : clock.validateTimeSliceArg( "0.000432" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  0.01 second', expecting 0.01" );
      try { System.out.println( (0.01 == clock.validateTimeSliceArg( "0.01" )) ? "    PASSED" : clock.validateTimeSliceArg( "0.01" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  1943.75 second', expecting 60.0" );
      try { System.out.println( (60.0 == clock.validateTimeSliceArg( "1943.75" )) ? "    PASSED" : clock.validateTimeSliceArg( "1943.75" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      System.out.print( "      sending '  A second', expecting 60.0" );
      try { System.out.println( (60.0 == clock.validateTimeSliceArg( "A" )) ? "    PASSED" : clock.validateTimeSliceArg( "A" ) ); }
      catch( Exception e ) { System.out.println ( "  ERROR" ); }

      // TESTS FOR tick() and toString()
      System.out.println( "\n    Testing tick()....");
      System.out.println( "      time slice = 10.0" );
      clock.timeSlice = 10.0;
      for (int testVar = 0; testVar < 5; testVar++ ) {
        try { System.out.println( "time = " + clock.toString() + " total seconds = " + clock.tick() ); }
        catch( Exception e ) { System.out.println ( "  ERROR" ); }
      }

      System.out.println( "      time slice = 100.0" );
      clock.timeSlice = 100.0;
      for (int testVar = 0; testVar < 5; testVar++ ) {
        try { System.out.println( "time = " + clock.toString() + " total seconds = " + clock.tick() ); }
        catch( Exception e ) { System.out.println ( "  ERROR" ); }
      }

      System.out.println( "      time slice = 1507.0" );
      clock.timeSlice = 1507.0;
      for (int testVar = 0; testVar < 5; testVar++ ) {
        try { System.out.println( "time = " + clock.toString() + " total seconds = " + clock.tick() ); }
        catch( Exception e ) { System.out.println ( "  ERROR" ); }
      }

      System.out.println( "      time slice = 1789.0" );
      clock.timeSlice = 1789;
      for (int testVar = 0; testVar < 5; testVar++ ) {
        try { System.out.println( "time = " + clock.toString() + " total seconds = " + clock.tick() ); }
        catch( Exception e ) { System.out.println ( "  ERROR" ); }
      }

      System.out.println( "      time slice = 0.123" );
      clock.timeSlice = 0.123;
      for (int testVar = 0; testVar < 5; testVar++ ) {
        try { System.out.println( "time = " + clock.toString() + " total seconds = " + clock.tick() ); }
        catch( Exception e ) { System.out.println ( "  ERROR" ); }
      }

      // TESTING getHourHandAngle()
      System.out.println( "\n    Testing getHourHandAngle()....");

      System.out.println( "  Testing getHourHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getHourHandAngle() - 141.922) <= EPSILON ) ? "   PASSED" : "    Should have gotten 141.92  got " + clock.getHourHandAngle() );

      clock.timeSlice = 1800;
      clock.tick();
      System.out.println( "  Testing getHourHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getHourHandAngle() - 156.922) <= EPSILON ) ? "   PASSED" : "    Should have gotten 156.922 " + clock.getHourHandAngle() );

      clock.timeSlice = 350;
      clock.tick();
      System.out.println( "  Testing getHourHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getHourHandAngle() - 159.838) <= EPSILON ) ? "   PASSED" : "    Should have gotten 159.838  got " + clock.getHourHandAngle() );

      clock.timeSlice = 1000;
      clock.tick();
      System.out.println( "  Testing getHourHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getHourHandAngle() - 168.171) <= EPSILON ) ? "   PASSED" : "    Should have gotten 168.171  got " + clock.getHourHandAngle() );

      // TESTING getMinuteHandAngle()
      System.out.println( "\n    Testing getMinuteHandAngle()....");

      System.out.println( "  Testing getMinuteHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getMinuteHandAngle() - 218.0615) <= EPSILON ) ? "   PASSED" : "    Should have gotten 218.0615  got " + clock.getMinuteHandAngle() );

      clock.timeSlice = 1800;
      clock.tick();
      System.out.println( "  Testing getMinuteHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getMinuteHandAngle() - 38.0615) <= EPSILON ) ? "   PASSED" : "    Should have gotten 38.0615 got " + clock.getMinuteHandAngle() );

      clock.timeSlice = 1350.55;
      clock.tick();
      System.out.println( "  Testing getMinuteHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getMinuteHandAngle() - 173.116) <= EPSILON ) ? "   PASSED" : "    Should have gotten 173.116 got " + clock.getMinuteHandAngle() );

      clock.timeSlice = 1000;
      clock.tick();
      System.out.println( "  Testing getMinuteHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getMinuteHandAngle() - 273.1164) <= EPSILON ) ? "   PASSED" : "    Should have gotten 273.1164  got " + clock.getMinuteHandAngle() );

      // TESTING getHandAngle()
      System.out.println( "\n    Testing getHandAngle()....");

      System.out.println( "  Testing getHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getHandAngle() - 70.3567) <= EPSILON ) ? "   PASSED" : "    Should have gotten 70.3567  got " + clock.getHandAngle() );


      clock.timeSlice = 1532.602;
      clock.tick();
      System.out.println( "  Testing getHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getHandAngle() - 149.154) <= EPSILON ) ? "   PASSED" : "    Should have gotten 149.154 got " + clock.getHandAngle() );


      clock.timeSlice = 1350.55;
      clock.tick();
      System.out.println( "  Testing getHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getHandAngle() - 25.354) <= EPSILON ) ? "   PASSED" : "    Should have gotten 25.354 got " + clock.getHandAngle() );


      clock.timeSlice = 1234;
      clock.tick();
      System.out.println( "  Testing getHandAngle() for current time " + clock.toString() );
      System.out.println( ( Math.abs( clock.getHandAngle() - 87.762) <= EPSILON ) ? "   PASSED" : "    Should have gotten 87.762  got " + clock.getHandAngle() );

      //TESTING getTotalSeconds
      System.out.println( "\n    Testing getTotalSeconds()....");

      System.out.println( "    Get total seconds at time " + clock.toString() );
      try { System.out.println( ( Math.abs( clock.getTotalSeconds() - 28448.3 ) <= EPSILON ) ? "    PASSED" : "    " + clock.getTotalSeconds() ); }
      catch (Exception e) { System.out.println( "    ERROR" ); }

      clock = new Clock();
      System.out.println( "    Get total seconds at time " + clock.toString() );
      try { System.out.println( ( Math.abs( clock.getTotalSeconds() - 0.0 ) <= EPSILON ) ? "    PASSED" : "    " + clock.getTotalSeconds() ); }
      catch (Exception e) { System.out.println( "    ERROR" ); }

      clock.validateTimeSliceArg( "23.49" );
      clock.tick();
      clock.validateTimeSliceArg( "1800" );
      clock.tick();
      System.out.println( "    Get total seconds at time " + clock.toString() );
      try { System.out.println( ( Math.abs( clock.getTotalSeconds() - 1823.5 ) <= EPSILON ) ? "    PASSED" : "    " + clock.getTotalSeconds() ); }
      catch (Exception e) { System.out.println( "    ERROR" ); }

   }
}
