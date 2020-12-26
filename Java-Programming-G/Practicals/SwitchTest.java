// ORIG: import cs1.Keyboard;
import java.util.Scanner;

public class SwitchTest
{
	public static void main(String [] args)
	{
    // ORIG: None
    Scanner sc = new Scanner(System.in);
		// ORIG: int x = Keyboard.readInt();
		int x = sc.nextInt();
		switch (x)
		{
			case 6:
				System.out.println("You chose 6"); break;
			case 7:
				System.out.println("You chose 7"); break;
			case 8:
				System.out.println("You chose 8"); break;
			default:
				System.out.println("Bad choice");
		}
	}
}
