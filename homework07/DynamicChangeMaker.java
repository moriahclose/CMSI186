/** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
 * File name  :  DynamicChangeMaker.java<br>
 * Purpose    :  Methods to use Dynamic Programming to find optimal amount of coins of input denominations
 *               to get a wanted value<br>
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

public class DynamicChangeMaker {

    // constants
    private static final String USAGE_STRING = "Usage: java DynamicChangeMaker < coin denomination 1 , coin denomination 2 , ... >  [ wanted coin value ] \n";
    private static final String NEGATIVE_INPUT_STRING = "All values must be positive. \n";
    private static final String NOT_ENOUGH_ARGS_STRING = "Must have at least two (2) arguments. \n";
    private static final String DUPLICATE_INPUT_STRING = "No duplicate inputs allowed. \n";

    private static Tuple[][] checkGrid;     // 2d array used to go through Dynamic Programming Algorithm

    /**
     * Inherits properties from java.lang.Object class
     */
    public DynamicChangeMaker() {
        super();
    }
    /**
     *  Validate arguments
     *  @param  coinDenoms         int[] containing user input  of coin denominations
     *  @param  wantedValue        int coinating user input wanted value
     *  @throws IllegalArgumentException for negative inputs, duplicates, or 0
     */
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

    /**
     *  Returns optimal amount of coins used to get wanted value
     *  @param  coinDenoms        int[] containing user input  of coin denominations
     *  @param  wantedValue        int coinating user input wanted value
     *  @return tuple with optimal amounts of coins needed to make wanted value or imposible tuple when
     *          arguments are invalid or denominations cannot make the value
     */
    public static Tuple makeChangeWithDynamicProgramming( int[] coinDenoms , int wantedValue ) {

        try { validateArguments( coinDenoms , wantedValue ); }
        catch ( Exception e ) { return( Tuple.IMPOSSIBLE); }

        Tuple useForResult = new Tuple ( coinDenoms );

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

                        // look back for tuple to add
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

                        // // look above for better sub solution
                        if ( row > 0 ) {
                            if ( checkGrid[ row ][ col ].isImpossible() ) {
                                checkGrid[ row ][ col ] = checkGrid[ row - 1 ][ col ];
                            }
                            else if ( !checkGrid[ row - 1 ][ col ].isImpossible() ) {
                                checkGrid[ row ][ col ] = ( checkGrid[ row ][ col ].total() <= checkGrid[ row - 1 ][ col ].total() ) ? checkGrid[ row ][ col ] : checkGrid[ row - 1 ][ col ];
                            }
                        }
                    }
                    else {
                        checkGrid[ row ][ col ] = Tuple.IMPOSSIBLE;

                        // look above for better sub solution
                        if ( row > 0 ) {
                            if ( checkGrid[ row ][ col ].isImpossible() ) {
                                checkGrid[ row ][ col ] = checkGrid[ row - 1 ][ col ];
                            }
                            else if ( !checkGrid[ row - 1 ][ col ].isImpossible() ) {
                                checkGrid[ row ][ col ] = ( checkGrid[ row ][ col ].total() > checkGrid[ row - 1 ][ col ].total() ) ? checkGrid[ row - 1 ][ col ] : checkGrid[ row ][ col ];
                            }
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

    /**
      * Runs makeChangeWithDynamicProgramming() from the command line
      * @param  args      String[] cointaining coin denominations and wanted value
      */
    public static void main( String args[] ) {
        int[] coinDenoms = new int[1];
        int wantedValue = 0;

        // validate args
        if ( args.length != 2  ) {
            System.out.println( USAGE_STRING );
            System.exit( 0 );
        }

        try {
            String[] argsSplit = args[0].split( "," );
            coinDenoms = new int[ argsSplit.length ];

            for ( int index = 0; index < coinDenoms.length; index++ ) {
                coinDenoms[ index ] = Integer.parseInt( argsSplit[ index ] );
            }

            wantedValue = Integer.parseInt( args[ 1 ] );
        }
        catch( Exception e ) { System.out.println( USAGE_STRING ); System.exit( 0 ); }

        Tuple result = new Tuple( 1 );

        try { result = makeChangeWithDynamicProgramming( coinDenoms , wantedValue ); }
        catch ( Exception e ) { System.out.println( USAGE_STRING ); System.exit( 0 ); }

        if ( result.isImpossible() ) {
            System.out.println( wantedValue + " cents cannot be made with these coin denominations." );
        }
        else {
            System.out.println( wantedValue + " cents can be made with " + result.total() + " coins as follows: " );
            for ( int index = 0; index < coinDenoms.length; index++ ) {
                System.out.println( "   " + result.getElement( index ) + " x " + coinDenoms[ index ] + " cent" );
            }
        }
    }
}
