// ORIG: import cs1.Keyboard;
import java.util.Scanner;

public class Menu
{
	public static void main(String [] args)
	{
		System.out.println(" Acme Bank");
		System.out.println("----------------------");
		System.out.println("   a. Deposit");
		System.out.println("   b. Withdrawal");
		System.out.println("   c. Enquire");
		System.out.println("   d. Quit");
		System.out.println(" ");
		System.out.print("   Please Select: ");
		
    // ORIG: None
    Scanner sc = new Scanner(System.in);
		// ORIG: char input = Keyboard.readChar();
		char input = sc.next().charAt(0);
		
		if (input=='d')
			{
			System.exit(0);
			}
		
		while (input!='d')
		{		
			if (input=='a')
			{
				System.out.println("You have selected Deposit");
				System.out.print("   Please Select: ");
				// ORIG: input = Keyboard.readChar();
		    input = sc.next().charAt(0);
			}
			else if (input=='b')
			{
				System.out.println("You have selected Withdrawal");
				System.out.print("   Please Select: ");
				// ORIG: input = Keyboard.readChar();
		    input = sc.next().charAt(0);
			}
			else if (input=='c')
			{
				System.out.println("You have selected Enquire");
				System.out.print("   Please Select: ");
				// ORIG: input = Keyboard.readChar();
		    input = sc.next().charAt(0);
			}
			
			else
			{
			System.out.println("\nInvalid choice, please re-enter.");
			System.out.print("Please Select: ");
      // ORIG: input = Keyboard.readChar();
      input = sc.next().charAt(0);
			}
		
		}
	}
}
