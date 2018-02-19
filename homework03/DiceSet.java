/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Moriah Tolliver
 *  Date          :  2018-02-22
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
 *                   public Die[] getArrayRep();                      // Returns the array of dice as an array instead of a DiceSet
 *                   public int getSides();                           // Get # of sides on each dice
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
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
     this.count = ( count < MINIMUM_DICE ) ? MINIMUM_DICE : count;
     this.sides = sides;

     dice = new Die[ this.count ];
     for (int dieIndex = 0; dieIndex < this.count; dieIndex++ ) {
       dice[dieIndex] = new Die( this.sides );
     }
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
    * @return the # of sides on each dice
    */
    public int getSides() {
      return this.sides;
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
   * NOTE: gives error if index is out of range
   */
   public int getIndividual( int dieIndex ) {
      return ( dice[ dieIndex ].getValue() );
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
   * NOTE: identical if same # of dice, samem # of sides, and dice at the same index are equal
   */
   public boolean isIdentical( DiceSet ds ) {
      return this.toString().equals(ds.toString()) && this.getSides() == ds.getSides();
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {

     DiceSet mySet = new DiceSet( 12, 6 );
     DiceSet theirSet = new DiceSet( 6, 12 );

     // TEST sum()
     System.out.println("\n6 TESTS FOR sum() [12 6-sided dice]");
     System.out.print(" Test with no roll (expected output = 0): ");
     try { System.out.println( mySet.sum()  ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     for (int testVar = 0; testVar < 5; testVar++ ) {
       System.out.print(" Test with roll " + mySet.toString() + ": ");
       try { mySet.roll(); System.out.println( mySet.sum()  ); }
       catch (Exception e) { System.out.println( "ERROR" ); }
     }

     // TEST roll()
     System.out.println("\n6 TESTS FOR roll() [6 12-sided dice]");
     System.out.println(" Starting set: " + theirSet.toString() );

     for (int testVar = 0; testVar < 6; testVar++ ) {
       System.out.print(" Next roll: ");
       try { theirSet.roll(); System.out.println( theirSet.toString() ); }
       catch (Exception e) { System.out.println( "ERROR" ); }
     }

     // TEST rollIndividual()
     System.out.println("\n20 TESTS FOR rollIndividual()");

     System.out.println(" Starting set [12 6-sided dice]: " + mySet.toString() );
     for (int testVar = -1; testVar < 13; testVar++ ) {
       System.out.print(" Roll Dice # " + testVar + ": ");
       try { mySet.rollIndividual( testVar ); System.out.println( mySet.toString() ); }
       catch (Exception e) { System.out.println( "ERROR" ); }
     }

     System.out.println(" Starting set [6 12-sided dice]: " + theirSet.toString() );
     for (int testVar = -1; testVar < 7; testVar++ ) {
       System.out.print(" Roll Dice # " + testVar + ": ");
       try { theirSet.rollIndividual( testVar ); System.out.println( theirSet.toString() ); }
       catch (Exception e) { System.out.println( "ERROR" ); }
     }

     // TEST getIndividual( int i )
     System.out.println("\n20 TESTS FOR getIndividual( int i )");

     System.out.println("Starting set [12 6-sided dice]: " + mySet.toString() );
     for (int testVar = -1; testVar < 13; testVar++ ) {
       System.out.print("Get Dice #" + testVar + ": ");
       try { System.out.println( mySet.getIndividual( testVar ) ); }
       catch (Exception e) { System.out.println( "ERROR" ); }
     }

     System.out.println("Starting set [6 12-sided dice]: " + theirSet.toString() );
     for (int testVar = -1; testVar < 7; testVar++ ) {
       System.out.print("Get Dice #" + testVar + ": ");
       try { System.out.println( theirSet.getIndividual( testVar ) ); }
       catch (Exception e) { System.out.println( "ERROR" ); }
     }

     // TEST toString()
     System.out.println("\n3 TESTS FOR toString()");
     System.out.print(" Test DiceSet with 12 6-sided dice: ");
     try { System.out.println( mySet.toString() ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     System.out.print(" Test DiceSet with 6 12-sided dice: ");
     try { System.out.println( theirSet.toString() ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     System.out.print(" Test DiceSet with 12 6-sided dice that are unrolled: ");
     mySet = new DiceSet( 12, 6 );
     try { System.out.println( mySet.toString() ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     // TEST getSides()
     System.out.println("\n2 TESTS for getSides()");
     System.out.print(" Dice with 6 sides: ");
     try { System.out.println( mySet.getSides() ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     System.out.print(" Dice with 12 sides: ");
     try { System.out.println( theirSet.getSides() ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     // TEST toString( DiceSet ds )
     System.out.println("\n3 TESTS FOR toString( DiceSet ds )");
     System.out.print(" Test with input DiceSet with 12 6-sided dice: ");
     try { System.out.println( mySet.toString( theirSet ) ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     System.out.print(" Test with input DiceSet with 6 12-sided dice that are unrolled: ");
     try { System.out.println( theirSet.toString( mySet ) ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     System.out.print(" Test with input DiceSet same as executing DiceSet: ");
     try { System.out.println( mySet.toString( mySet ) ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     // TEST isIdentical( DiceSet ds )
     System.out.println("\n23 TESTS FOR isIdentical( DiceSet ds )");
     System.out.print(" Test with DiceSet[12 6-sided dice] and DiceSet[6 12-sided dice]: ");
     try { System.out.println( ( mySet.isIdentical( theirSet ) ) ? " " + mySet.toString() + " is identical to " + theirSet.toString() : " " + mySet.toString() + " is different than " + theirSet.toString() ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     System.out.println(" Test DiceSet[2 4-sided dice] with  DiceSet[2 4-sided dice]: ");
     mySet = new DiceSet( 2, 4 );
     theirSet = new DiceSet( 2, 4 );

     try { System.out.println( ( mySet.isIdentical( theirSet ) ) ? " " + mySet.toString() + " is identical to " + theirSet.toString() :" " + mySet.toString() + " is different than " + theirSet.toString() ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

     mySet.roll();

     for (int i = 0; i < 20; i++ ) {
       try { System.out.println( ( mySet.isIdentical( theirSet ) ) ? " " + mySet.toString() + " is identical to " + theirSet.toString() : " " + mySet.toString() + " is different than " + theirSet.toString() ); }
       catch (Exception e) { System.out.println( "ERROR" ); }
       theirSet.roll();
     }

     System.out.println("\nTest DiceSet[2 4-sided dice] with  DiceSet[2 6-sided dice]: ");
     mySet = new DiceSet( 2, 4 );
     theirSet = new DiceSet( 2, 6 );
     try { System.out.println( ( mySet.isIdentical( theirSet ) ) ?" " + mySet.toString() + " is identical to " + theirSet.toString() :" " + mySet.toString() + " is different than " + theirSet.toString() ); }
     catch (Exception e) { System.out.println( "ERROR" ); }

   }

}
