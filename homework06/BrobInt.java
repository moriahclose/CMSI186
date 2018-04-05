/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  M.Tolliver
 * Date       :  2018-04-19
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-04-19  M.Tolliver  Initial writing and begin coding
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

  // CONSTANTS
  private static final int MAX_NUM_CHARS = 8;

  // instance variables
  private int[] intArray;
  private int carry;
  private String strValue;


  public BrobInt( String value ) {

    // set string value to input
    strValue = value;

    // create array to hold int values of the string
    intArray = new int[ (int)(value.length() / MAX_NUM_CHARS ) + 1 ];

    // variable to iterate through the array
    int arrayIndex = 0;

    // add characters from back to front until there are less than 8 left
    for ( int stringIndex = value.length(); stringIndex > value.length() % MAX_NUM_CHARS; stringIndex -= MAX_NUM_CHARS ) {
      intArray[ arrayIndex ] = Integer.parseInt( value.substring( stringIndex - MAX_NUM_CHARS , stringIndex ) );
      arrayIndex++;
    }

    // add remaining characters to the array
    intArray[ intArray.length - 1 ] = Integer.parseInt( value.substring( 0 , value.length() % MAX_NUM_CHARS ) );

  }

  public BrobInt add( BrobInt value ) {
    int[] valueArray = value.getArrayRep();
    if ( intArray.length == 1 || valueArray.length == 1 ) {
      return new BrobInt( String.valueOf( intArray[0] + valueArray[0] ) );
    }
    return new BrobInt("");
  }

  public int[] getArrayRep() {
    return intArray;
  }

  public boolean equals( BrobInt bI ) {
    return ( this.toString().equals( bI.toString() ) ) ? true : false;
  }

  public String toString() {
    return strValue;
  }

  public static void main( String args[] ) {
    BrobInt b = new BrobInt("123");
    BrobInt c = new BrobInt("987");
    System.out.println( b.add( c ).toString() );
  }

}
