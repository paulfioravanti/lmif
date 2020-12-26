// ORIG: import cs1.Keyboard;
import java.util.Scanner;

public class TestKeys
{
	public static void main(String [] args)
	{
    // ORIG: None
    Scanner sc = new Scanner(System.in);
		System.out.print("Enter an Integer: ");
		// ORIG: int n = Keyboard.readInt();
		int n = sc.nextInt();
		System.out.println("You entered: " + n);
		
		System.out.print("Enter a Double: ");
		// ORIG: double x = Keyboard.readDouble();
		double x = sc.nextDouble();
		System.out.println("You entered: " + x);
	}
}
