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
import java.util.ArrayList;
import java.math.BigInteger;
import java.lang.Long;
public class BrobInt {

  // CONSTANTS
  private static final int MAX_NUM_CHARS = 8;
  private static final int BASE = 10;
  private static final BrobInt ZERO = new BrobInt( "0" );
  private static final BrobInt ONE = new BrobInt( "1" );
  private static final BrobInt NEG_ONE = new BrobInt( "-1" );
  private static final BrobInt TEN = new BrobInt( "10" );


  // instance variables
  private int[] intArray;
  private String strValue;

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
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

 /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
  *  @param  value         BrobInt to add to this
  *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
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
      if ( index != longArray.length - 1 ) {
        String strSum = String.valueOf( sum );
        while ( strSum.length() < MAX_NUM_CHARS ) {
          newBrobIntString = "0" + newBrobIntString;
          strSum += "0";
        }
      }
    }

    // get rid of excess 0s
    while ( newBrobIntString.substring( 0 , 1 ).equals( "0" ) ) {
      newBrobIntString = newBrobIntString.substring( 1 );
    }

    return new BrobInt( newBrobIntString );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobInt passed as argument to this BrobInt using int array
   *  @param  value         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
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

    return new BrobInt( newBrobIntString );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to multiply the value of a BrobInt passed as argument to this BrobInt using Russian Peasant Multiplication
     *  @param  value         BrobInt to multiply by this
     *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
     *  NOTE: uses Halver.java by B.J. Johnson
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
     public BrobInt multiply( BrobInt value ) {
       Halver h = new Halver();
       boolean n1IsPositive = this.compareTo( ZERO ) >= 0;
       boolean n2IsPositive = value.compareTo( ZERO ) >= 0;

       String halveThis = ( !n1IsPositive ) ? this.toString().substring( 1 ) : this.toString();
       BrobInt doubleThis = ( !n2IsPositive ) ? new BrobInt( value.toString().substring( 1 ) ) : value;

       BrobInt product = ZERO;

       while ( !halveThis.equals( "1" ) ) {

         // if the halved number is even, subtract the doubled number from the product
         if ( Integer.parseInt( halveThis.substring( halveThis.length() - 1 ) ) % 2 == 1 ) {
           product = product.add( doubleThis );
         }

         doubleThis = doubleThis.add( doubleThis );
         halveThis = h.halve( halveThis );
       }

       // do same check as in while loop but for last iteration
       if ( Integer.parseInt( halveThis.substring( halveThis.length() - 1 ) ) % 2 == 1 ) {
         product = product.add( doubleThis );
       }

       // return the product brobInt with the correct sign
       return ( n1IsPositive && !n2IsPositive || !n1IsPositive && n2IsPositive ) ? new BrobInt( "-" + product.toString() ) : product;

     }

     /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this GinormousIntk by the BrobInt passed as argument
   *  @param  value         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this GinormousInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt value ) {
    if ( value.equals( ZERO ) ) {
      System.out.println( "Cannot divide by 0 " );
      System.exit( 0 );
    }

    boolean n1IsPositive = this.compareTo( ZERO ) >= 0;
    boolean n2IsPositive = value.compareTo( ZERO ) >= 0;

    BrobInt quotient = ZERO;
    BrobInt dividend = ( n1IsPositive ) ? this : this.multiply( NEG_ONE );
    BrobInt divisor = ( n2IsPositive ) ? value : value.multiply( NEG_ONE );

     while ( dividend.compareTo( ZERO ) == 1 ) {
       dividend = dividend.subtract( divisor );
       quotient = quotient.add( ONE );
     }

     quotient = quotient.subtract( ONE );
     quotient = ( n1IsPositive && !n2IsPositive || !n1IsPositive && n2IsPositive ) ? quotient.multiply( NEG_ONE ) : quotient;
     return quotient;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  value         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt value ) {
     return this.subtract( this.divide( value ).multiply( value ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  value  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
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

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( String.valueOf( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return the array representation of this BrobInt
   *  @return array  that holds integer array representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public int[] getArrayRep() {
    return intArray;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  value     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public boolean equals( BrobInt value ) {
    return ( this.toString().equals( value.toString() ) ) ? true : false;
  }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to return a String representation of this BrobInt
 *  @return String  which is the String representation of this BrobInt
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public String toString() {
    return strValue;
  }

  public static void main( String args[] ) {
    BrobInt g1 = new BrobInt( "108" );
    BrobInt g2 = new BrobInt( "-9" );


    System.out.println( g1.divide( g2 ) );
  }

}
