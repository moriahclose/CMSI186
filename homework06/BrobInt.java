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

  // instance variables
  private int[] intArray;
  private String strValue;


  public BrobInt( String value ) {

    intArray = new int[ (int)(value.length() / 8) + 1 ];

    int arrayIndex = 0;

    for ( int stringIndex = value.length(); stringIndex > value.length() % 8; stringIndex -= 8 ) {
      System.out.println("It should just ignore it");
    }

    intArray[ arrayIndex ] = Integer.parseInt( value.substring( 0 , value.length() % 8 ) );
    for ( int i : intArray ) {
      System.out.println( i );
    }

  }

  public static void main( String args[] ) {
    BrobInt b = new BrobInt("123");
  }

}
