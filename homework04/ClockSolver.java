/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @author       :  Moriah Tolliver
 *  Date written  :  2018-02-28
 *  Description   :  Runs a discrete clock simulation and prints when the hands of the clock are at the user specified angle
 *
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  M. Tolliver   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class ClockSolver {

  public static void main(String args[] ) {

    // initialize epsilon value
    double EPSILON = 5;

    // check validity and initialize angle value
    double angle = 0;
    try { angle = Double.parseDouble( args[0] ); }
    catch (Exception e) { System.out.println( "Usage: java ClockSolver <degrees> [time interval]"); System.exit(1); }

    // check validity and initialize timeSlice value;
    // changes timeSlice to 60 if input in invalid or not there
    double timeSlice = 60.0;
    try { timeSlice = Double.parseDouble( args[1] ); }
    catch (Exception e) { System.out.println( "Invalid Time Slice Argument" ); }

    // create new clock
    Clock c = new Clock( angle , timeSlice );

    // let user know program is working
    System.out.println( " \n Clock is running... \n" );
    System.out.println( " Looking for angle " + angle + " at time intervals of " + timeSlice + " seconds");
    System.out.println( "\n Found Angle " + angle + " At Times: " );

    // run clock until 12 hours has passed
    // time 00 : 00 : 00 = 12 o'clock
    while ( !c.toString().substring(0,2).equals("12") ) {
      c.tick();                                                     // increment clock by timeSlice
      if ( Math.abs( c.getHandAngle() - angle ) <= EPSILON ) {      // check if clock hand angle is equal to wanted angle
        System.out.println( "    " + c.toString() );                // print times of when wanted angle is found
      }
    }
  }
}
