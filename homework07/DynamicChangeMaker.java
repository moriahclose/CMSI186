/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Methods to use Dynamic Programming to find optimal amount of coins of input denominations
 *               to get a wanted value
 * @author    :  Moriah Tolliver
 * Date       :  2018-04-24
 * Description:  This program provides methods necessary to find optimal amount of coins to make an input
 *               number using input coin denominations
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-19  M.Tolliver   Initial writing and release
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class DynamicChangeMaker {

    // constants
    public static final String USAGE_STRING = "Usage: java ChangeMaker < coin denomination 1 , coin denomination 2 , ... , wanted coin value> \n";
    public static final String NEGATIVE_INPUT_STRING = "All values must be positive. \n";
    public static final String NOT_ENOUGH_ARGS_STRING = "Must have at least two (2) arguments. \n";
    public static final String DUPLICATE_INPUT_STRING = "No duplicate inputs allowed. \n";

    public static Tuple[][] checkGrid;

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to validate arguments
     *  @param  coinDenoms         int[] containing user input  of coin denominations
     *  @param  wantedValue        int coinating user input wanted value
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static void validateArguments( int[] coinDenoms ,  int wantedValue ) {

        // make sure arguments are positive
        for ( int coinDenom : coinDenoms ) {
            if ( coinDenom <= 0 ) {
                throw new IllegalArgumentException( NEGATIVE_INPUT_STRING );
            }
        }

        if ( wantedValue <= 0 ) {
            throw new IllegalArgumentException( NEGATIVE_INPUT_STRING );
        }

        // make sure there are no duplicates
        for ( int currentInt = 0; currentInt < coinDenoms.length; currentInt++ ) {
            for ( int laterInt = currentInt + 1; laterInt < coinDenoms.length; laterInt++ ) {
                if ( coinDenoms[ currentInt ] == coinDenoms[ laterInt ] ) {
                    throw new IllegalArgumentException( DUPLICATE_INPUT_STRING );
                }
            }
        }
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to validate arguments
     *  @param  coinDenoms        int[] containing user input  of coin denominations
     *  @param  wantedValue        int coinating user input wanted value
     *  @return tuple with optimal amounts of coins needed to make wanted value
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static Tuple makeChangeWithDynamicProgramming( int[] coinDenoms , int wantedValue ) {

        validateArguments( coinDenoms , wantedValue );
        Tuple useForResult = new Tuple ( coinDenoms );
        Arrays.sort( coinDenoms );

        checkGrid = new Tuple[ coinDenoms.length ][ wantedValue + 1 ];
        Tuple zeroedTuple = new Tuple( coinDenoms.length );

        for ( int row = 0; row < coinDenoms.length; row++ ) {
            for ( int col = 0; col <= wantedValue; col++ ) {
                if ( col == 0 ) {
                    checkGrid[ row ][ col ] = zeroedTuple;
                }
                else {
                    if ( col >= coinDenoms[ row ] ) {

                        // put 1 in the spot
                        checkGrid[ row ][ col ] = new Tuple( coinDenoms.length );
                        checkGrid[ row ][ col ].setElement( row , 1 );

                        // look back for another solution
                        if ( col - coinDenoms[ row ] >= 0 ) {
                            if ( checkGrid[ row ][ col - coinDenoms[ row ] ].isImpossible() ) {
                                checkGrid[ row ][ col ] = Tuple.IMPOSSIBLE;
                            }
                            else {
                                checkGrid[ row ][ col ] = checkGrid[ row ][ col ].add( checkGrid[ row ][ col - coinDenoms[ row ] ] );
                            }
                        }
                        else {
                            checkGrid[ row ][ col ] = Tuple.IMPOSSIBLE;
                        }

                        // look above for better sub solution
                        if ( row > 0 ) {
                            checkGrid[ row ][ col ] = ( checkGrid[ row - 1 ][ col ].total() < checkGrid[ row ][ col ].total() && !checkGrid[ row - 1 ][ col ].isImpossible() ) ? checkGrid[ row - 1 ][ col ] : checkGrid[ row ][ col ];
                        }
                    }
                    else {
                        checkGrid[ row ][ col ] = Tuple.IMPOSSIBLE;

                        // look above for better sub solution
                        if ( row > 0 ) {
                            checkGrid[ row ][ col ] = ( !checkGrid[ row - 1 ][ col ].isImpossible() ) ? checkGrid[ row - 1 ][ col ] : checkGrid[ row ][ col ];
                        }
                    }
                }
            }
        }

        // make tuple so output Tuple has same indices as input
        Tuple result = new Tuple( coinDenoms.length );
        for ( int i = 0; i < checkGrid[ coinDenoms.length - 1 ][ wantedValue ].length(); i++ ) {
             result.setElement( useForResult.indexOf( coinDenoms[ i ] ) , checkGrid[ coinDenoms.length - 1 ][ wantedValue ].getElement( i ) );
        }
        return ( result.equals( zeroedTuple ) ) ? Tuple.IMPOSSIBLE : result;
    }

    /* main used for testing */
    public static void main( String args[] ) {
        int[] testArray1 = { 1 , 2 , 3 , 4 };
        int[] testArray2 = { 3 , 5 , 7 , 8 };
        int[] testArray3 = { 0 , 1 , 2 , 3 };
        int[] testArray4 = { 57 , 3 , 5 , -8 };
        int[] testArray5 = { 23 , 4 , 38 , 23 };
        int[] usaDenominations = { 25 , 10 , 5 , 1 };
        int[] weirdDenomination = { 121 , 47 , 23 , 17, 3 };
        int[] francDenominations  = { 5, 10, 20, 50 };


        int testValue1 = 4;
        int testValue2 = 23;
        int testValue3 = -67;
        int testValue4 = 0;
        int testValue5 = 99;
        int weirdValue = 13579;

        // // tests for arrays
        // System.out.println( "TESTING ARRAYS " );
        // try { validateArguments( testArray1 , testValue1 ); System.out.println( " PASS " ); }
        // catch( Exception e ) { System.out.println( " FAIL "); }
        //
        // try { validateArguments( testArray2 , testValue2 ); System.out.println( " PASS " ); }
        // catch( Exception e ) { System.out.println( " FAIL "); }
        //
        // try { validateArguments( testArray3 , testValue1 ); System.out.println( " FAIL " ); }
        // catch( Exception e ) { System.out.println( " PASS "); }
        //
        // try { validateArguments( testArray4 , testValue1 ); System.out.println( " FAIL " ); }
        // catch( Exception e ) { System.out.println( " PASS "); }
        //
        // try { validateArguments( testArray5 , testValue1 ); System.out.println( " FAIL " ); }
        // catch( Exception e ) { System.out.println( " PASS "); }
        //
        // // test for wanted values
        // System.out.println( "\nTESTING WANTED VALUES" );
        // try { validateArguments( testArray1 , testValue1 ); System.out.println( " PASS " ); }
        // catch( Exception e ) { System.out.println( " FAIL "); }
        //
        // try { validateArguments( testArray1 , testValue1 ); System.out.println( " PASS " ); }
        // catch( Exception e ) { System.out.println( " FAIL "); }
        //
        // try { validateArguments( testArray1 , testValue1 ); System.out.println( " PASS " ); }
        // catch( Exception e ) { System.out.println( " FAIL "); }
        //
        // try { validateArguments( testArray1 , testValue1 ); System.out.println( " PASS " ); }
        // catch( Exception e ) { System.out.println( " FAIL "); }
        //
        // // test for making grids
        // System.out.println( "\nTESTING ARRAYS " );
        //
        // try { System.out.println( makeChangeWithDynamicProgramming( testArray1 , testValue1 ) );  }
        // catch( Exception e ) { System.out.println( e ); }
        //
        // try { System.out.println( makeChangeWithDynamicProgramming( testArray2 , testValue2 ) ); }
        // catch( Exception e ) { System.out.println( e ); }
        //
        // try { System.out.println( makeChangeWithDynamicProgramming( usaDenominations , testValue5 ) ); }
        // catch( Exception e ) { System.out.println( e ); }
        try { System.out.println( makeChangeWithDynamicProgramming( weirdDenomination , weirdValue ) ); }
        catch( Exception e ) { System.out.println( e ); }
        //expecting <3,0,111,2,1>
        // try { System.out.println( makeChangeWithDynamicProgramming( francDenominations , testValue5 ) ); }
        // catch( Exception e ) { System.out.println( e ); }
        //
        // try { makeChangeWithDynamicProgramming( testArray2 , testValue2 ); }
        // catch( Exception e ) { System.out.println( e ); }
        //
        // try { makeChangeWithDynamicProgramming( testArray4 , testValue1 ); System.out.println( " FAIL " ); }
        // catch( Exception e ) { System.out.println( e ); }
        //
        // try { makeChangeWithDynamicProgramming( testArray5 , testValue1 ); System.out.println( " FAIL " ); }
        // catch( Exception e ) { System.out.println( e ); }
    }
}
