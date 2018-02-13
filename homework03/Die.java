/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  B.J. Johnson
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
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
// QUESTION: does toString() return current value or number of side?
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
     // CREATE TEST DIE
     Die threeSides = new Die(3);
     Die eightSides = new Die(8);
     Die sixSides = new Die(6);
     Die eighteenSides = new Die(18);
     Die threeHundredSides = new Die(300);
     Die seventyTwoSides = new Die(72);
     Die dontRoll = new Die(6);

     // TEST roll()
     System.out.println("\nSIX TESTS FOR ROLL");
     System.out.print(" Test for dice of sides < 4 ");
     try { System.out.println( (threeSides.roll() <= 4) ? "PASSED" : "FAIL"); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test for dice of 8 sides ");
     try { System.out.println( (eightSides.roll() <= 8) ? "PASSED" : "FAIL"); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test for dice of 6 sides ");
     try { System.out.println( (eightSides.roll() <= 6) ? "PASSED" : "FAIL"); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test for dice of 8 sides ");
     try { System.out.println( (eighteenSides.roll() <= 18) ? "PASSED" : "FAIL"); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test for dice of 300 sides ");
     try { System.out.println( (threeHundredSides.roll() <= 300) ? "PASSED" : "FAIL"); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test for dice of 8 sides ");
     try { System.out.println( (seventyTwoSides.roll() <= 72) ? "PASSED" : "FAIL"); }
     catch( Exception e ) { System.out.println ( false ); }

     // TEST getValue()
     System.out.println( "\nTHREE TESTS FOR getValue() ");
     int threeRoll = threeSides.roll();
     System.out.print(" Test with threeSides: ");
     try { System.out.println( (threeSides.getValue() == threeRoll) ? "PASSED" : "You should have gotten " + threeRoll + " not " + threeSides.getValue() ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test with unrolled die: ");
     try { System.out.println( (dontRoll.getValue() == 0) ? "PASSED" : "You should have gotten 0 not " + dontRoll.getValue() ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test with high number die: ");
     int threeHundredRoll = threeHundredSides.roll();
     try { System.out.println( (threeHundredSides.getValue() == threeHundredRoll) ? "PASSED" : "You should have gotten " + threeHundredRoll + " not " + threeSides.getValue() ); }
     catch( Exception e ) { System.out.println ( false ); }

     // TEST setSides()
     System.out.println( "\nTHREE TESTS FOR setSides()");

     System.out.print(" Test setting die to number < 4: ");
     try { System.out.println( (threeHundredSides.setSides( 2 ) == 4) ? "PASSED" : "You should have gotten 4 not " + threeHundredSides.setSides( 2 ) ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test setting die to 50 sides : ");
     try { System.out.println( (eightSides.setSides( 50 ) == 50) ? "PASSED" : "You should have gotten 50 not " + eightSides.setSides( 50 ) ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test setting die to super high number: ");
     try { System.out.println( (sixSides.setSides( 400 ) == 400) ? "PASSED" : "You should have gotten 400 not " + sixSides.setSides( 400 ) ); }
     catch( Exception e ) { System.out.println ( false ); }

     // TEST instance toString()
     System.out.println( "\nSEVEN TESTS FOR INSTANCE METHOD toString");

     System.out.print(" Test threeSides: ");
     try { System.out.println( (threeSides.toString().equals("[4]") ) ? "PASSED" : "You should have gotten [4] not " + threeSides.toString() ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test eightSides: ");
     try { System.out.println( (eightSides.toString().equals("[50]") ) ? "PASSED" : "You should have gotten [50] not " + eightSides.toString() ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test sixSides: ");
     try { System.out.println( (sixSides.toString().equals("[400]") ) ? "PASSED" : "You should have gotten [400] not " + sixSides.toString() ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test eighteenSides: ");
     try { System.out.println( (eighteenSides.toString().equals("[18]") ) ? "PASSED" : "You should have gotten [18] not " + eighteenSides.toString() ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test threeHundredSides: ");
     try { System.out.println( (threeHundredSides.toString().equals("[4]") ) ? "PASSED" : "You should have gotten [4] not " + threeHundredSides.toString() ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test seventyTwoSides: ");
     try { System.out.println( (seventyTwoSides.toString().equals("[72]") ) ? "PASSED" : "You should have gotten [72] not " + seventyTwoSides.toString() ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test dontRoll: ");
     try { System.out.println( (dontRoll.toString().equals("[6]") ) ? "PASSED" : "You should have gotten [6] not " + dontRoll.toString() ); }
     catch( Exception e ) { System.out.println ( false ); }

     // TEST classwide toString()
     System.out.println( "\nSEVEN TESTS FOR CLASSWIDE METHOD toString");

     System.out.print(" Test threeSides tells eighteenSides : ");
     try { System.out.println( (threeSides.toString( eighteenSides ).equals("[18]") ) ? "PASSED" : "You should have gotten [18] not " + threeSides.toString( eightSides ) ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test eightSides tells threeSides: ");
     try { System.out.println( (eightSides.toString( threeSides ).equals("[4]") ) ? "PASSED" : "You should have gotten [4] not " + eightSides.toString( threeSides ) ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test sixSides tells threeHundredSides: ");
     try { System.out.println( (sixSides.toString( threeHundredSides ).equals("[4]") ) ? "PASSED" : "You should have gotten [4] not " + sixSides.toString( threeHundredSides ) ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test eighteenSides tells seventyTwoSides: ");
     try { System.out.println( (eighteenSides.toString( seventyTwoSides ).equals("[72]") ) ? "PASSED" : "You should have gotten [72] not " + eighteenSides.toString( seventyTwoSides ) ); }
     catch( Exception e ) { System.out.println ( false ); }

     System.out.print(" Test for no die entered: ");
     try { System.out.println( (threeHundredSides.toString().equals("[4]") ) ? "PASSED" : "You should have gotten [4] not " + threeHundredSides.toString() ); }
     catch( Exception e ) { System.out.println ( false ); }

     for (int i = 0; i < 10; i++) {
       System.out.println( seventyTwoSides.roll() );
       System.out.println( seventyTwoSides.toString() );
     }
  }
}
