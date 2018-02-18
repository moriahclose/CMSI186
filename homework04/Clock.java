/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  Moriah Tolliver
 *  Date          :  2018-03-
 *  Description   :  This class provides the data fields and methods to describe a single clock.  A
 *                   has 2 hands, the hour and the minute hand.Includes the following:
 *                   public Clock( int timeSlice, int inputAngle );             // Constructor for a Clock
 *                   public String tick();                                      // Increment the time by the given time slice
 *                   public double getHourHandAngle()                           // get the current angle of the hour hand
 *                   public double getMinuteHandAngle()                         // get the current angle of the minute hand
 *                   public double getHandAngle()                               // get the current angle between both hands
 *                   public String toString()                                   // get current time on this Clock
 *                   public static void main( String args[] );                  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input angle and time slice are out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-17  M. Tolliver  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {

  /**
   * private instance data
   */
    private String time;
    private double hourHandAngle;
    private double minuteHandAngle;
    private double timeSlice;
    private double angle;
    private double hour;
    private double minute;
    private double second;
    private final double MAXIMUM_ANGLE = 360.0;
    private final double MAXIMUM_TIMESLICE = 1800.0;


   // public constructor:
  /**
   * constructor
   * @param timeSlice double containing time interval at which the angle of the hands will change
   * @param angleWanted double containing desired angle between hands
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Clock( double angle , double timeSlice ) {
     time = "00.00 : 00.00 : 00.00";
     hourHandAngle = 0;
     minuteHandAngle = 0;
     hour = 0.0;
     minute = 0.0;
     second = 0.0;

     if ( timeSlice < 0 || timeSlice > MAXIMUM_TIMESLICE ) {
       throw new IllegalArgumentException();
     }
     else {
       this.timeSlice = timeSlice;
     }

     if ( angle > MAXIMUM_ANGLE ) {
       throw new IllegalArgumentException();
     }
     else {
       this.angle = angle;
     }

   }

  /**
   * Increment the time by the time slice
   * @return  string value of the time after the current tick
   */
   public String tick() {
     second += timeSlice;
     minuteHandAngle += 360 * 60 * 60 * timeSlice;

     if ( second >= 60 ) {
       minute++;
       second = second % 60;
     }

     if ( minute >= 60 ) {
       hour++;
       minute = 0;
     }
      return hour + " : " + minute + " : " + second;
   }

   /**
    * @return  double value of the current angle of the hour hand
    */
    public double getHourHandAngle() {
       return 0;
    }

    /**
     * @return  double value of the current angle of the minute hand
     */
     public double getMinuteHandAngle() {
        return 0;
     }

     /**
      * @return  double value of the current angle between the hour and the second hand
      */
      public double getHandAngle() {
         return 0;
      }

      /**
       * @return  double value of the current angle of the hour hand
       */
       public String toString() {
          return "";
       }

       public static void main( String args[] ) {
         Clock c = new Clock( 60 , 25.8 );
         for (int i = 0; i < 240; i++ ) {
           System.out.println( c.tick() );
         }
       }
 }







/*
360 d  1 min
------
60 min 60 sec
*/
