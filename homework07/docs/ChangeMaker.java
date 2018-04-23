public class ChangeMaker {

    public static void main( String args[] ) {

        String usageString = "Usage: java ChangeMaker < coin denomination 1 , coin denomination 2 , ... , wanted coin value> \n";
        String positiveInputString = "Usage:  All values must be positive. \n";
        String notEnoughArgsString = "Usage: Must have at least two (2) arguments. \n";
        String duplicateInputString = "Usage: No duplicate inputs allowed. \n";
        int[] ZERO_ARRAY = { 0 , 0 };
        Tuple ZERO_TWO_TUPLE = new Tuple( ZERO_ARRAY );
        int[] coinDenoms = new int[ args.length - 1 ];
        int wantedValue = 0;

        // make sure there are enough inputs
        if ( args.length <= 1 ) {
            System.out.println( notEnoughArgsString );
            System.exit( 0 );
        }

        // make sure all inputs are integers
        try {
            for ( int argIndex = 0; argIndex < args.length - 1; argIndex++ ) {
                coinDenoms[ argIndex ] = Integer.parseInt( args[ argIndex ] );
            }
            wantedValue = Integer.parseInt( args[ args.length - 1 ] );
        }
        catch( Exception e ) { System.out.println( usageString ); System.exit( 0 ); }

        // make sure all inputs are positive
        if ( wantedValue <= 0 ) {
            System.out.println( positiveInputString );
            System.exit( 0 );
        }
        for ( int value : coinDenoms ) {
            if ( value <= 0 ) {
                System.out.println( positiveInputString );
                System.exit( 0 );
            }
        }

        // make sure there are no duplicate inputs
        for ( int checkThis = 0; checkThis < coinDenoms.length - 1; checkThis++ ) {
            for ( int againstThis = checkThis + 1; againstThis < coinDenoms.length; againstThis++ ) {
                if ( coinDenoms[ checkThis ] == coinDenoms[ againstThis ] ) {
                    System.out.println( duplicateInputString );
                    System.exit( 0 );
                }
            }
        }

        // create grid that Tuples will be put into
        Tuple[][] checkGrid = new Tuple[ coinDenoms.length ][ wantedValue ];

        // for ( int row = 0; row < coinDenoms.length; row++ ) {
        //     for ( int col = 0; col < wantedValue; col++ ) {
        //
        //         if ( col == 0 ) {
        //             checkGrid[ row ][ col ] = ZERO_TWO_TUPLE;
        //         }
        //         else {
        //
        //         }
        //     }
        // }


    }
}
