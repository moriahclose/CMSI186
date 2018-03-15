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
    private static double speed;
    private static double xPosition;
    private static double yPosition;
    private static double xSpeed;
    private static double ySpeed;
    private static double totalSeconds;
    private static double timeSlice;

   /*
    *  Constructor
    */
    public Ball( double startingX , double startingY , double startingXSpeed , double startingYSpeed , double inputTimeSlice ) {
      this.xSpeed = startingXSpeed;
      this.ySpeed = startingYSpeed;
      this.speed = Math.sqrt( (xSpeed * xSpeed) + (ySpeed * ySpeed) );
      this.xPosition = startingX;
      this.yPosition = startingY;
      this.timeSlice = inputTimeSlice;
      totalSeconds = 0;

    }

   /**
   *  Method to decrease ball's speed by 1% per second and update position
   *  @return  double-precision value of current speed
   */
   public double updateBall() {
     totalSeconds += timeSlice;
     speed -= speed * ( RATE_OF_DECREASE * totalSeconds );

     xPosition += xSpeed * timeSlice;
     yPosition += ySpeed * timeSlice;
     return speed;
   }

   /**
   *  Method to return the ball's current speed
   *  @return  double-precision value of current speed
   */
   public double getSpeed() {
     return speed;
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
   *  @return  boolean of ball moving
   */
   public boolean stillMoving() {
     if ( ( speed * INCHES_IN_FOOT ) < 1 ) {
       return false;
     }
     return true;
   }

   /**
   *  Method to return the totalSeconds
   *  @return  double-precision value of total seconds elapsed
   */
   public double getTotalSeconds() {
     return totalSeconds;
   }

   /**
   *  Method to return the ball's current speed
   *  @return  double-precision value of current speed
   */
   public String toString() {
     String speedPattern = "00.00";
     String positionPattern = "00.00";
     DecimalFormat formatSpeed = new DecimalFormat( speedPattern );
     DecimalFormat formatPosition = new DecimalFormat( positionPattern );
     return "Speed: " + formatSpeed.format( speed ) + "ft/s X-Position: " + formatPosition.format( xPosition ) + "ft Y-Position: " + formatPosition.format( yPosition ) + "ft";
   }


   /*
    * main used for testing
    */
    public static void main( String args[] ) {

      Ball b = new Ball( 0 , 0 , 3 , 4 , 10 );

      // TEST decreaseSpeed() and getTotalSeconds()
      while ( b.stillMoving() ) {
        try { System.out.println( b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }


    }
}
