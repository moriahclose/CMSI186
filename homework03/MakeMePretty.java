import java.util.Scanner;

public class MakeMePretty {

  public static void main( String[] args ) {

    Scanner sc = new Scanner(System.in);
    int input = 1;

    String[] printMyArray = {"Hello there." , "\r", "My name is MoTo" , "\n", "this was overwritten"};
    for (String str : printMyArray ) {
      System.out.println((char)13);
      System.out.print( str );
    }
  }

}
