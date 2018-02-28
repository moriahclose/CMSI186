/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  Moriah Tolliver
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private static final double MAX_NUM_OF_TOTAL_SECONDS = 43200.0;
   private static final double EPSILON_VALUE = 5;      // small value for double-precision comparisons

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds


   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
     double angle = 0.0;
     double timeSlice = 0.0;

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
     System.out.println( " \nFound target angle of " + angle + " at times: " );
     while( clock.getTotalSeconds() < MAX_NUM_OF_TOTAL_SECONDS ) {
        if ( Math.abs( clock.getHandAngle() -  angle ) <= EPSILON_VALUE ) {
          System.out.println( "   " + clock.toString() );
        }
        clock.tick();
      }
      System.exit(0);
   }
}
