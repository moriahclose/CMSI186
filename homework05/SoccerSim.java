/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main program for the SoccerSim class
 *  @see
 *  @author       :  Moriah Tolliver
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  Pole made using a stationary ball with random position
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.ArrayList;

public class SoccerSim {

   public static void main ( String args[] ) {

     // constants needed
     String tab = "    ";
     String doubleError = "All arguments must be of the type Double";
     double DEFAULT_TIMESLICE = 1;
     double RADIUS_IN_INCHES = 4.5;
     double MAX_X = 500;
     double MAX_Y = 500;

     Ball b = new Ball(0 , 0 , 0 , 0 , 0);
     Ball pole = new Ball( 0 , 0 , 0 , 0 , 0);
     Timer timer = new Timer( 0 );

     // create array of balls and pole
     ArrayList<Ball> balls = new ArrayList<Ball>();

     // check number of arguments
     if ( args.length % 4 == 0 && args.length >= 4 ) {
       // initialize timer
       timer = new Timer( DEFAULT_TIMESLICE );

      // initialize pole
       pole = new Ball( (double)Math.floor( ( Math.random() * MAX_X ) + 1  ) , (double)Math.floor( ( Math.random() * MAX_Y ) + 1 ) , 0 , 0 , DEFAULT_TIMESLICE );

       // initialize array of balls
       for ( int argIndex = 0; argIndex < args.length; argIndex += 4 ) {
         try {
           b = new Ball( Double.parseDouble( args[ argIndex ] ) , Double.parseDouble( args[ argIndex + 1 ] ) , Double.parseDouble( args[ argIndex + 2 ] ) , Double.parseDouble( args[ argIndex + 3 ] ) , DEFAULT_TIMESLICE );
           balls.add( b );
         }
         catch( Exception e ) { System.out.println( doubleError ); System.exit( 1 ); }
       }

     }

     else if ( args.length % 4 == 1 && args.length >= 4 ) {
       // initialize pole
       try { pole = new Ball( (double)Math.floor( ( Math.random() * MAX_X ) + 1  ) , (double)Math.floor( ( Math.random() * MAX_Y ) + 1 ) , 0 , 0 , Double.parseDouble( args[ args.length - 1 ] ) ); }
       catch(Exception e){ System.out.println( doubleError ); System.exit( 1 ); }

       // initialize timer
       timer = new Timer( Double.parseDouble( args[ args.length - 1] ) );

       // initialize array of balls
       for ( int argIndex = 0; argIndex < args.length - 1; argIndex += 4 ) {
         try {
           b = new Ball( Double.parseDouble( args[ argIndex ] ) , Double.parseDouble( args[ argIndex + 1 ] ) , Double.parseDouble( args[ argIndex + 2 ] ) , Double.parseDouble( args[ argIndex + 3 ] ) , Double.parseDouble( args[ args.length - 1 ] ) );
           balls.add( b );
         }
         catch( Exception e ) { System.out.println( doubleError ); System.exit( 1 ); }
       }
     }

     else {
       System.out.println( "Usage: java SoccerSim <Ball_1_X> <Ball_1_Y> <Ball_1_X_Speed> <Ball_1_Y_Speed> (continue for other balls) <Time Increment>" );
       System.exit( 1 );
     }

     //create Array of elements in bounds
     Ball[] inBounds = new Ball[ balls.size() + 1];
     for ( int ballIndex = 0; ballIndex < balls.size(); ballIndex++ ) {
       inBounds[ ballIndex ] = balls.get( ballIndex );
     }
     inBounds[ balls.size() ] = pole;

     //print initial state
     System.out.println("INITIAL REPORT");
     System.out.println( tab + "Time Slice = " + timer.getTimeSlice() );
     System.out.println( tab + "Pole: X-Position = " + pole.getXPosition() + " Y-Position = " + pole.getYPosition() + "\n" );

     int atRest = 0; // number of balls still moving and in bounds

     // run simulation and check for collisions
     while ( atRest < balls.size() ) {

       System.out.println( "\nREPORT AT " + timer.toString() );
       for ( int ballIndex = 0; ballIndex < balls.size(); ballIndex++ ) {
         // change print if ball is off field
         if ( Math.abs( balls.get( ballIndex ).getXPosition() ) > MAX_X || Math.abs( balls.get( ballIndex ).getYPosition() ) > MAX_Y ) {
           System.out.println( tab + "Ball " + ballIndex + ": " + tab + tab + tab + "<out of bounds>" );
         }
         else {
           System.out.println( tab + "Ball " + ballIndex + ": " + balls.get( ballIndex ).toString() );
         }
       }

       //remove balls from check array that are out of Bounds

       // check for collisions

       //check if balls are still in motion
       atRest = 0;

       for ( Ball ba : balls ) {
         if ( ba.isMoving() ) {
           ba.updateBall();
         }
         else {
           atRest++;
         }
       }



      timer.tick();
    }

    System.out.println( "\nNO COLLISION POSSIBLE" );
  }
}
