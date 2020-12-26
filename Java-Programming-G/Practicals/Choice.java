// ORIG: import cs1.Keyboard;
import java.util.Scanner;

public class Choice
{
	public static void main(String [] args)
	{
    // ORIG: None
    Scanner sc = new Scanner(System.in);
		// ORIG: int x = Keyboard.readInt();
		int x = sc.nextInt();
		if (x==6)
			System.out.print("You chose 6");
			else
			if (x==7)
				System.out.print("You chose 7");
				else
				if (x==8)
					System.out.print("You chose 8");
					else
						System.out.print("Bad Choice");
	}
}
			
