// ORIG: import cs1.Keyboard;
import java.util.Scanner;
// ORIG: None
import java.io.*;

public class P1
{
	public static void main(String [] args)
	{
    // ORIG: None
    Scanner sc = new Scanner(System.in);
		System.out.print("Enter a string: ");
		// ORIG: String input = Keyboard.readString();
		String input = sc.next();
		// ORIG: String output = StringBuffer(input).reverse();
		StringBuffer output = new StringBuffer(input).reverse();
		System.out.print("The reversed string is: " + output);
	}
}
		
