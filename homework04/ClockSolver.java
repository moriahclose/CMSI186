/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @author       :  Moriah Tolliver
 *  Date written  :  2018-03-01
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-01  M. Tolliver  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  
  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {

     double MAX_NUM_OF_TOTAL_SECONDS = 43200.0;
     double EPSILON_VALUE = .5;      // small value for double-precision comparisons
     double angle = 0.0;
     double timeSlice = 3;

     System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
     if( 0 == args.length ) {
        System.out.println( "   Sorry you must enter at least one argument\n" +
                            "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                            "   Please try again..........." );
        System.exit( 1 );
     }

     Clock clock = new Clock();

     try { angle = clock.validateAngleArg( args[0] ); }
     catch (Exception e) { System.out.println( "Angle argument must be between 0 and 360 degrees inclusive." ); }

     try { timeSlice = clock.validateTimeSliceArg( args[1] ); }
     catch (Exception e) { timeSlice = clock.validateTimeSliceArg(""); } // will set timeSlice to 60.0

     System.out.println("Clock is running...");
     System.out.println( "Looking for angle " + angle + " with time intervals of " + timeSlice + " seconds." );
     System.out.println( " \nFound target angle of " + angle + " at times: " );
     while( clock.getTotalSeconds() < MAX_NUM_OF_TOTAL_SECONDS ) {
        if ( Math.abs( clock.getHandAngle() -  angle ) <= EPSILON_VALUE ) {
          System.out.println( "   " + clock.toString() );
        }
        clock.tick();
      }

      System.exit( 0 );
   }
}
