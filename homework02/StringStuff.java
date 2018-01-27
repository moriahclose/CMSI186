/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  B.J. Johnson - Moriah Tolliver
 *  Date          :  2017-01-19   - 2018-01-27
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  No Main Class. StringStuffTester.java used for testing
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 *  @version 2.0.0  2018-02-25  M.T. Tolliver Fill in methods so they perform specified function
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   * NOTE: inlcudes 'y' and 'Y' as vowels
   */
   public static boolean containsVowel( String s ) {

     char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
     boolean outputString = false;
     s = s.toLowerCase();

     for (char vowel : vowels ) {
       outputString = ( s.indexOf(vowel) > -1 ) ? true : outputString;
     }
     return outputString;
   }

   /**
    * Method to return the reverse of a string passed as an argument
    *
    * @param s String containing the data to be reversed
    * @return  String containing the reverse of the input string
    */
    public static String reverse( String s ) {
      String outputString = "";

      for (int characterIndex = s.length()-1; characterIndex >= 0; characterIndex--) {
        outputString += s.charAt(characterIndex);
      }
       return outputString;
    }

    /**
     * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
     * the first and last, second and last-but-one, etc. against each other.  If something doesn't
     * match that way, returns false, otherwise returns true.
     *
     * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
     * @return  boolean which is true if this a palindrome, or false otherwise
     * NOTE: does not account for letter case so isPalindrome(Racecar) returns true
     */
   public static boolean isPalindrome( String s ) {
     s = s.toLowerCase();
     return ( s.equals(reverse(s)) );
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {
      String outputString = "";
      String sLower = s.toLowerCase();

      for (int charIndex = 0; charIndex < s.length(); charIndex++) {
        outputString += ( (sLower.substring(charIndex, charIndex+1).hashCode() % 2 == 0) && ( sLower.substring(charIndex, charIndex+1).hashCode() > 97 ) && ( sLower.substring(charIndex, charIndex+1).hashCode() < 123) ) ? s.charAt(charIndex) : "";
      }

      return outputString;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
     String outputString = "";
     String sLower = s.toLowerCase();

     for (int charIndex = 0; charIndex < s.length(); charIndex++) {
       outputString += ( (sLower.substring(charIndex, charIndex+1).hashCode() % 2 == 1) && ( sLower.substring(charIndex, charIndex+1).hashCode() >= 97 ) && ( sLower.substring(charIndex, charIndex+1).hashCode() <= 123) ) ? s.charAt(charIndex) : "";
     }

     return outputString;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
     return evensOnly(removeDupes(s));
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
      return oddsOnly(removeDupes(s));
   }

   /**
    * Method to return the characters in a string but with no duplicate characters in the resulting string.
    *
    * @param s String containing the data to be parsed for &quot;odd&quot; letters
    * @return  String containing the letters from the input without duplicates
    */
   public static String removeDupes( String s ) {
     String outputString = "";

     for (int charIndex = 0; charIndex < s.length(); charIndex++ ) {
       outputString += ( outputString.indexOf(s.charAt(charIndex)) == -1 ) ? s.charAt(charIndex) : "";
       }

     return outputString;
   }


}
