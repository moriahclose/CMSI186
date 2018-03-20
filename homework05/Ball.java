/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
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

public class Ball {

  /*
   *  Class field definitions
   */
    private static final double Epsilon = 9;
    private static final double WEIGHT_IN_POUNDS = 1;
    private static final double RATE_OF_DECREASE = .01;
    private static final double INCHES_IN_FOOT = 12;
    private static Timer timer;
    private static double timeSlice;
    private static double xPosition;
    private static double yPosition;
    private static double initXSpeed;
    private static double initYSpeed;
    private static double xSpeed;
    private static double ySpeed;
    private static double speed;

   /*
    *  Constructor
    */
    public Ball( double startingX , double startingY , double startingXSpeed , double startingYSpeed, double inputTimeSlice ) {
      this.xSpeed = startingXSpeed;
      this.ySpeed = startingYSpeed;
      this.initXSpeed = startingXSpeed;
      this.initYSpeed = startingYSpeed;
      this.speed = Math.sqrt( (xSpeed * xSpeed) + (ySpeed * ySpeed) );
      this.xPosition = startingX;
      this.yPosition = startingY;
      timer = new Timer( inputTimeSlice );
      this.timeSlice = inputTimeSlice;

    }

   /**
   *  Method to decrease ball's speed by 1% per second and update position
   *  @return  double-precision value of current speed
   */
   public void updateBall() {
     //update position based on previous speed
     xPosition += ( xSpeed * timeSlice );
     yPosition += ( ySpeed * timeSlice );

     //update time
     //timer.tick();

     //update speed
     xSpeed -= ( ( xSpeed * RATE_OF_DECREASE ) * timeSlice );
     ySpeed -= ( ( ySpeed * RATE_OF_DECREASE ) * timeSlice );
     speed = Math.sqrt( (xSpeed * xSpeed) + (ySpeed * ySpeed) );
   }

   /**
   *  Method to return the ball's current xPosition
   *  @return  double-precision value of current xPosition
   */
   public double getXPosition() {
     return xPosition;
   }

   /**
   *  Method to return the ball's current yPosition
   *  @return  double-precision value of current yPosition
   */
   public double getYPosition() {
     return yPosition;
   }

   /**
   *  Method to return if the ball's speed is < 1 inch per second
   * if ball is not moving, adjusts position variables to represent
   * where speed is exactly 0
   *  @return  boolean of ball moving
   */
   public boolean isMoving() {
     if ( Math.abs( speed * INCHES_IN_FOOT ) < 1 ) {
       xPosition -= xSpeed;
       yPosition -= ySpeed;
       return false;
     }
     return true;
   }

   /**
   *  Method to return the totalSeconds
   *  @return  double-precision value of total seconds elapsed
   */
   public double getTotalSeconds() {
     return timer.getTotalSeconds();
   }

   /**
   *  Method to return string of current time for the ball
   *  @return  string of time ball is at
   */
   public String getBallTimeString() {
     return timer.toString();
   }

   /**
   *  Method to return the ball's current speed
   *  @return  double-precision value of current speed
   */
   public String toString() {
     String speedPattern = "00.0000";
     String positionPattern = "00.0000";
     DecimalFormat formatSpeed = new DecimalFormat( speedPattern );
     DecimalFormat formatPosition = new DecimalFormat( positionPattern );
     return "Position: <" + formatPosition.format( xPosition ) + " , " + formatPosition.format( yPosition ) + "> Speed: <" + formatSpeed.format( xSpeed ) + " , " + formatSpeed.format( ySpeed ) + ">";
   }


   /*
    * main used for testing
    */
    public static void main( String args[] ) {

      Ball b = new Ball( 2 , 3 , 4 , 11 , 1 );

      //TEST tick()
      System.out.println( "TESTING updateBall()" );

      System.out.println( " testing...");
      //for ( int i = 0; i < 20; i++ ) {
      while ( b.isMoving()) {
        try { System.out.println( b.getBallTimeString() ); System.out.println( "    " + b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }
   }
}
