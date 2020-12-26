// ORIG: import cs1.Keyboard;
import java.util.Scanner;


public class Bingo
{
	public static void main(String [] args)
	{
    // ORIG: None
    Scanner sc = new Scanner(System.in);
		int r = (int) (Math.random()*10);
		System.out.print("Enter a number between 1 and 10: ");
		// ORIG: int random = Keyboard.readInt();
		int random = sc.nextInt();
		if (random == r)
			System.out.println("Bingo");
		while (random != r)
		{		
			if (random > r)
			{
			System.out.print("Too high, try again: ");
			// ORIG: random = Keyboard.readInt();
		  random = sc.nextInt();
			}
			if (random < r)
			{
			System.out.print("Too low, try again: ");
			// ORIG: random = Keyboard.readInt();
		  random = sc.nextInt();
			}		
			if (random == r)
			System.out.print("Bingo!");
		}
	}
}
