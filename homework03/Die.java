/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  B.J. Johnson
 *  @author       :  Moriah Tolliver
 *  Date          :  2017-02-06
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
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
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-06  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-02-17  B.J. Johnson  Filled in method code
 *  @version 2.0.0  2018-02-13  M. Tolliver   Filled in code so methods work
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Die {

  /**
   * private instance data
   */
   private int sides;
   private int curValue;
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die( int nSides ) {
     sides = ( nSides > MINIMUM_SIDES ) ? nSides : MINIMUM_SIDES;
     curValue = 0;
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll() {
      curValue = (int)Math.floor(Math.random() * sides) + 1;
      return curValue;
   }

  /**
   * Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
   public int getValue() {
      return curValue;
   }

  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public int setSides( int nSides ) {
     sides = ( nSides > MINIMUM_SIDES ) ? nSides : MINIMUM_SIDES;
     return sides;
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
      return "[" + curValue + "]";
   }

  /**
   * Class-wide method that returns a String representation of an input die instance
   * @param  d  the input die
   * @return String representation of this Die
   */
   public static String toString( Die d ) {

      return "[" + d.getValue() + "]";
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {

     // CREATE TEST DICE
     Die d;
     Die d2;
     int testRoll;

     // TEST roll
     System.out.println("\n20 TESTS roll()");
     // TEST DIE WITH 2 INPUT SIDES
     System.out.println( "\n TEST roll() FOR DIE WITH '2' INPUT SIDES" );
     d = new Die( 2 );
     for (int testNum = 0; testNum < 4; testNum++ ) {
       System.out.print("  TEST roll(): ");
       testRoll = d.roll();
       try { System.out.println( (testRoll > 0 && testRoll <= 4) ? "PASSED" : "FAILED" ); }
       catch (Exception e) { System.out.println("ERROR"); }
     }

     // TEST DIE WITH 2 INPUT SIDES
     System.out.println( "\n TEST roll() FOR DIE WITH '16' INPUT SIDES" );
     d = new Die( 16 );
     for (int testNum = 0; testNum < 16; testNum++ ) {
       System.out.print("  TEST roll(): ");
       testRoll = d.roll();
       try { System.out.println( (testRoll > 0 && testRoll <= 16) ? "PASSED" : "FAILED" ); }
       catch (Exception e) { System.out.println("ERROR"); }
     }

     // TEST getValue()
     System.out.println( "\n4 TESTS FOR getValue()");
     System.out.print(" TEST getValue() with new roll: ");
     testRoll = d.roll();
     try { System.out.println( (d.getValue() == testRoll) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST getValue() without new roll: ");
     try { System.out.println( (d.getValue() == testRoll) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST getValue() with new roll: ");
     testRoll = d.roll();
     try { System.out.println( (d.getValue() == testRoll) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST getValue() with new roll: ");
     testRoll = d.roll();
     try { System.out.println( (d.getValue() == testRoll) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     // TEST setSides()
     System.out.println("\n5 TESTS FOR setSides()");
     System.out.print(" TEST setSides() with input '1' ");
     try { System.out.println( (d.setSides(1) == 4) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST setSides() with input '-1' ");
     try { System.out.println( (d.setSides(-1) == 4) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST setSides() with input '16' ");
     try { System.out.println( (d.setSides(16) == 16) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST setSides() with input '50' ");
     try { System.out.println( (d.setSides(50) == 50) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST setSides() with input '319' ");
     try { System.out.println( (d.setSides(319) == 319) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     // TEST toString()
     System.out.println("\n4 TEST FOR toString()");
     System.out.print(" TEST toString() with 319 sided die new roll: ");
     testRoll = d.roll();
     try { System.out.println( (d.toString().equals("[" + testRoll + "]")) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST toString() with 319 sided die new roll: ");
     testRoll = d.roll();
     try { System.out.println( (d.toString().equals("[" + testRoll + "]")) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST toString() with 319 sided die last roll: ");
     try { System.out.println( (d.toString().equals("[" + testRoll + "]")) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST toString() with 319 sided die new roll: ");
     testRoll = d.roll();
     try { System.out.println( (d.toString().equals("[" + testRoll + "]")) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     // TEST toString(Die d)
     System.out.println("\n4 TEST FOR toString(Die d2)");
     System.out.print(" TEST toString(Die d2) where d2 = -1 sided die: ");
     d2 = new Die( -1 );
     testRoll = d2.roll();
     try { System.out.println( (d.toString(d2).equals("[" + testRoll + "]")) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST toString(Die d2) where d2 = 15 sided die: ");
     d2 = new Die( 15 );
     testRoll = d2.roll();
     try { System.out.println( (d.toString(d2).equals("[" + testRoll + "]")) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST toString(Die d2) where d2 = 3 sided die: ");
     d2 = new Die( 3 );
     testRoll = d2.roll();
     try { System.out.println( (d.toString(d2).equals("[" + testRoll + "]")) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }

     System.out.print(" TEST toString(Die d2) where d2 = 92 sided die: ");
     d2 = new Die( 92 );
     testRoll = d2.roll();
     try { System.out.println( (d.toString(d2).equals("[" + testRoll + "]")) ? "PASSED" : "FAILED" ); }
     catch (Exception e) { System.out.println("ERROR"); }
  }
}
