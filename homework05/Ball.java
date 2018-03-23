/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  This program contains the method for the Ball object
 *  @see
 *  @author       :  Moriah Tolliver
 *  Date written  :  2018-03-27
 *  Description   :  This class creates the methods for a Ball object
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-27  M. Tolliver   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Ball {

  /*
   *  Class field definitions
   */
    private static final double INCHES_IN_FOOT = 12;
    private static double timeSlice;
    private static double xPosition;
    private static double yPosition;
    private static double xSpeed;
    private static double ySpeed;
    private static double speed;

   /*
    *  Constructor
    */
    public Ball( double startingX , double startingY , double startingXSpeed , double startingYSpeed, double inputTimeSlice ) {
      this.xSpeed = startingXSpeed;
      this.ySpeed = startingYSpeed;
      this.speed = Math.sqrt( (xSpeed * xSpeed) + (ySpeed * ySpeed) );
      this.xPosition = startingX;
      this.yPosition = startingY;
      this.timeSlice = inputTimeSlice;
    }

   /**
   *  Method to decrease ball's speed by 1% per second and update position
   *  @return  double-precision value of current speed
   */
   public void updateBall() {
     //update position based on previous speed
     xPosition += xSpeed * timeSlice;
     yPosition += ySpeed * timeSlice;

     //update speed
     xSpeed *= ( Math.pow( 0.99, timeSlice ) );
     ySpeed *= ( Math.pow( 0.99, timeSlice ) );
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

      Ball b = new Ball( 10, 10, 1, 1, 1 );
      String tab = "    "; //String used to simulate tab in prints

      //TEST updateBall()
      System.out.println( "TESTING updateBall()" );

      System.out.println( "\n" + tab + "time slice = 1s" );
      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( tab + tab + b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      System.out.println( "\n" + tab + "time slice = 10s" );
      b.timeSlice = 10;

      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( tab + tab + b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      System.out.println( "\n" + tab + "time slice = 15s" );
      b.timeSlice = 15;
      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( tab + tab + b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      System.out.println( "\n" + tab + "time slice = 2.34s" );
      b.timeSlice = 2.34;
      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( tab + tab + b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      System.out.println( "\n" + tab + "time slice = 0.03s" );
      b.timeSlice = 0.03;
      for ( int i = 0; i < 10; i++ ) {
        try { System.out.println( tab + tab + b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      //TEST getXPosition()
      System.out.println( "\nTESTING getXPosition()" );

      System.out.println( "\n" + tab + "Ball with start position: <0 , 0> start speed <10 , 10> time slice: 1s" );
      b = new Ball( 0 , 0 , 10 , 10 , 1 );
      for ( int i = 0; i < 5; i++ ) {
        try { System.out.println( tab + tab + "x-position: " + b.getXPosition() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      System.out.println( "\n" + tab + "Ball with start position: <40 , 40> start speed <-10 , -10> time slice: 1s" );
      b = new Ball( 40 , 40 , -10 , -10 , 1 );
      for ( int i = 0; i < 5; i++ ) {
        try { System.out.println( tab + tab + "x-position: " + b.getXPosition() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      System.out.println( "\n" + tab + "Ball with start position: <5 , 15> start speed <11 , 12> time slice: 10s" );
      b = new Ball( 5 , 15 , 11 , 12 , 10 );
      for ( int i = 0; i < 5; i++ ) {
        try { System.out.println( tab + tab + "x-position: " + b.getXPosition() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      //TEST getYPosition()
      System.out.println( "\nTESTING getYPosition()" );

      System.out.println( "\n" + tab + "Ball with start position: <0 , 0> start speed <10 , 10> time slice: 1s" );
      b = new Ball( 0 , 0 , 10 , 10 , 1 );
      for ( int i = 0; i < 5; i++ ) {
        try { System.out.println( tab + tab + "y-position: " + b.getYPosition() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      System.out.println( "\n" + tab + "Ball with start position: <40 , 40> start speed <-10 , -10> time slice: 1s" );
      b = new Ball( 40 , 40 , -10 , -10 , 1 );
      for ( int i = 0; i < 5; i++ ) {
        try { System.out.println( tab + tab + "y-position: " + b.getYPosition() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      System.out.println( "\n" + tab + "Ball with start position: <5 , 15> start speed <11 , 12> time slice: 10s" );
      b = new Ball( 5 , 15 , 11 , 12 , 10 );
      for ( int i = 0; i < 5; i++ ) {
        try { System.out.println( tab + tab + "y-position: " + b.getYPosition() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

      //TESTING isMoving()
      System.out.println( "\nTESTING isMoving()" );

      System.out.println( "\n" + tab + "Ball with start position: <5 , 15> start speed <11 , 12> time slice: 10s" );
      b = new Ball( 5 , 15 , 11 , 12 , 20 );
      for ( int i = 0; i < 40; i++ ) {
        try { System.out.println( tab + tab + "is moving: " + b.isMoving() + tab + b.toString() ); }
        catch (Exception e) { System.out.println( e ); }
        b.updateBall();
      }

   }
}
