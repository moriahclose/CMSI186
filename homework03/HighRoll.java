/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Plays a game of high roll
 *  Author        :  Moriah Tolliver
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public static void main( String[] args );        // The implements game play

 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-13  M. Tolliver  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Scanner;

public class HighRoll {

  public static void main( String[] args ) {

    // INITIALIZE GLOBAL VARIABLES
    boolean quit = false;
    int count = 0;
    int sides = 0;
    int curScore = 0;
    int highScore = 0;
    DiceSet dice = new DiceSet( count , sides );

    // CREATE SCANNER
    Scanner sc = new Scanner( System.in );


    // CREATE DICE SET WITH USER PARAMETERS
    boolean countSet = false;
    while ( !countSet ) {
      System.out.print( "Enter number of dice in your set: ");
      try { count = sc.nextInt(); countSet = true; }
      catch( Exception e ) { System.out.println ( "Usage: #" ); sc.next(); }
    }

    boolean sidesSet = false;
    while ( !sidesSet ) {
      System.out.print( "Enter number of sides on each die: ");
      try { sides = sc.nextInt(); sidesSet = true; }
      catch( Exception e ) { System.out.println ( "Usage: #" ); sc.next(); }
    }

    dice = new DiceSet( count , sides );

    // GAME PLAY
    while ( !quit ) {

      int input = 0;

      // DISPLAY MENU
      System.out.println( "[1] ROLL ALL THE DICE");
      System.out.println( "[2] ROLL ONE DIE");
      System.out.println( "[3] CALCULATE THE SCORE FOR THIS SET");
      System.out.println( "[4] SAVE THIS SCORE AS HIGH SCORE");
      System.out.println( "[5] DISPLAY THE HIGH SCORE");
      System.out.println( "[6] QUIT");

      // DISPLAY CURRENT DICE SET AND PROMPT FOR INPUT
      System.out.print( "CURRENT DICE SET: " + dice.toString() + "\nEnter number of your desired action: ");

      try { input = sc.nextInt(); }
      catch( Exception e ) { sc.next(); }

      System.out.println( "\r" );

      switch ( input ) {
        case 1:
          dice.roll();
          break;

        case 2:
          int dieIndex = 0;
          System.out.println( "Enter index of die you want to roll (Dice are indexed from 0): ");

          try { dieIndex = sc.nextInt(); }
          catch( Exception e ) { System.out.println( "Usage: # between 0 and number of dice exclusive" ); }

          if (dieIndex < 0 || dieIndex >= count ) {
            System.out.println( "Usage: # between 0 and number of dice exclusive" );
            break;
          }
          dice.rollIndividual( dieIndex );
          break;

        case 3:
          curScore = dice.sum();
          break;

        case 4:
          highScore = dice.sum();
          break;

        case 5:
          System.out.println( "High Score: " + highScore + "\n" );
          break;
        case 6:
          quit = true;
          break;
        default:
          System.out.println( "Usage: # between 1 and 6 inclusive " );
      }
   }
   System.out.println("Final High Score: " + highScore + "\n" + "Thanks for playing!");
  }
}
