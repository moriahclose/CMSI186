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
import java.math.BigInteger;

public class BrobInt {

  // CONSTANTS
  private static final int MAX_NUM_CHARS = 8;
  private static final int BASE = 10;
  private static final BrobInt ZERO = new BrobInt( "0" );

  // instance variables
  private int[] intArray;
  private String strValue;

  public BrobInt( String value ) {

    strValue = value;

    // create array to hold int values of the string
    intArray = new int[ (int)(value.length() / MAX_NUM_CHARS ) + 1 ];


    // variable to iterate through the array
    int arrayIndex = 0;

    // add characters from back to front until there are less than 8 left
    for ( int stringIndex = value.length(); stringIndex > value.length() % MAX_NUM_CHARS; stringIndex -= MAX_NUM_CHARS ) {
      if ( stringIndex == intArray.length - 1 && strValue.substring( 0 , 1).equals( "-" ) ) {
        for ( int i = 0; i < intArray.length; i++ ) {
          intArray[ i ] *= -1;
        }

      }
      else {
        intArray[ arrayIndex ] = Integer.parseInt( value.substring( stringIndex - MAX_NUM_CHARS , stringIndex ) );
        arrayIndex++;
      }
    }

    // add remaining characters to the array if length of value is not divisible by 8
    if ( value.length() % 8 != 0 ) {
      if ( strValue.substring( 0 , 1 ).equals( "-" ) ) {
        for ( int i = 0; i < intArray.length; i++ ) {
          intArray[ i ] *= -1;
        }
        intArray[ intArray.length - 1 ] = -1 * Integer.parseInt( value.substring( 1 , value.length() % MAX_NUM_CHARS ) );
      }
      else {
        intArray[ intArray.length - 1 ] = Integer.parseInt( value.substring( 0 , value.length() % MAX_NUM_CHARS ) );
      }
    }
  }

  public BrobInt add( BrobInt value ) {
    String newBrobIntString = "";   // string to be input for the return BrobInt
    int[] shortArray = ( value.getArrayRep().length < intArray.length ) ? value.getArrayRep() : intArray;   // variable for shorter array
    int[] longArray = ( value.getArrayRep().length < intArray.length ) ?  intArray : value.getArrayRep();   // variable for shorter array
    int carry = 0;    // holds the carry amount
    int index = 0;    // holds current index
    int sum = 0;      // holds current sum digit

    for ( index = 0; index < longArray.length; index++ ) {

      if ( index < shortArray.length ) {
        sum = longArray[ index ] + shortArray[ index ] + carry;
        carry = ( String.valueOf( sum ).length() > MAX_NUM_CHARS ) ? 1 : 0;
      }
      else {
        sum = longArray[ index ] + carry;
        carry = ( String.valueOf( sum ).length() > MAX_NUM_CHARS ) ? 1 : 0;
      }


      newBrobIntString = ( carry == 1 ) ? String.valueOf( sum ).substring( 1 , String.valueOf( sum ).length() ) + newBrobIntString : sum + newBrobIntString;  // add sum to the string

      // add 0 padding if needed
      if ( index < longArray.length - 1 ) {
        String strSum = String.valueOf( sum );
        while ( strSum.length() < MAX_NUM_CHARS ) {
          newBrobIntString = "0" + newBrobIntString;
          strSum += "0";
        }
      }
    }

    return new BrobInt( newBrobIntString );
  }

  public BrobInt subtract( BrobInt value ) {
    String newBrobIntString = "";   // string to be input for the return BrobInt
    String valueString = "";

    if ( value.compareTo( ZERO ) == 1 ) {
      valueString  = "-" + value.toString();
      newBrobIntString = this.add( new BrobInt( valueString ) ).toString();
    }
    else {
      valueString = value.toString().substring( 1 , value.toString().length() );
      newBrobIntString = this.add( new BrobInt( valueString ) ).toString();
    }

    if ( newBrobIntString.indexOf( "-" ) > 0 ) {
      newBrobIntString = newBrobIntString.substring( newBrobIntString.indexOf( "-" ) , newBrobIntString.length() );
    }

    return new BrobInt( newBrobIntString );
  }

  public int compareTo( BrobInt value ) {
    if ( this.toString().substring( 0 , 1 ).equals( "-" ) && !value.toString().substring( 0 , 1 ).equals( "-" ) ) {
      return -1;
    }
    else if ( this.toString().substring( 0 , 1 ).equals( "-" ) && !value.toString().substring( 0 , 1 ).equals( "-" ) ) {
      return 1;
    }
    else if ( this.toString().length() > value.toString().length() ) {
      if ( !this.toString().substring( 0 , 1 ).equals( "-" ) ) {
        return 1;
      }
      return -1;
    }
    else if ( this.toString().length() < value.toString().length() ) {
      if ( this.toString().substring( 0 , 1 ).equals( "-" ) ) {
        return 1;
      }
      return -1;
    }
    else {
      for ( int index = value.getArrayRep().length - 1; index >= 0; index--) {
        if ( intArray[ index ] > value.getArrayRep()[ index ] ) {
          if ( !this.toString().substring( 0 , 1 ).equals( "-" ) ) {
            return 1;
          }
          return -1;
        }
        else if ( intArray[ index ] < value.getArrayRep()[ index ] ) {
          if ( this.toString().substring( 0 , 1 ).equals( "-" ) ) {
            return 1;
          }
          return -1;
        }
      }
    }
    return 0;
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
    BrobInt g1 = new BrobInt( "144444444" );
    BrobInt g2 = new BrobInt( "-144444444" );
    System.out.println( g1.add( g2 ) );

  }

}
