/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] dice;
   private final int MINIMUM_DICE = 1;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
     count = ( count < MINIMUM_DICE ) ? MINIMUM_DICE : count;
     dice = new Die[ count ];
     for (int dieIndex = 0; dieIndex < count; dieIndex++ ) {
       dice[dieIndex] = new Die( sides );

     }
     this.sides = sides;

   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
     int sum = 0;
     for ( Die die : dice ) {
       sum += die.getValue();
     }
      return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
     for ( Die die : dice ) {
       die.roll();
     }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      return dice[ dieIndex ].roll();
   }

  /**
   * Gets the value of an individual die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * NOTE: returns 0 if dieIndex is out of range
   */
   public int getIndividual( int dieIndex ) {
     if (dieIndex >= dice.length) {
       return 0;
     }
     else {
      return ( dice[ dieIndex ].getValue());
    }
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {

     String returnThis = "";

     for ( Die die : this.dice ) {
       returnThis += die.toString();
     }

     return returnThis;
   }

   /**
    * @return the array representing the set of die
    */
    public Die[] getArrayRep() {
      return this.dice;
    }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
     String returnThis = "";

     for ( Die die : ds.getArrayRep() ) {
       returnThis += die.toString();
     }

     return returnThis;
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   * NOTE: identical if dice in both sets have same # of sides and values of dice at the same index are equal
   */
   // needs same amount, same sides, and same values
   public boolean isIdentical( DiceSet ds ) {
      return this.toString().equals(ds.toString());
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      DiceSet myDice = new DiceSet( 1, 4 );
      DiceSet hisDice = new DiceSet( 1, 4);
      myDice.roll();
      hisDice.roll();
      System.out.println( "My dice: " + myDice.toString() );
      System.out.println( "His dice: " + myDice.toString( hisDice ) );
      System.out.println( myDice.isIdentical(hisDice) );
   }

}
