// ORIG: import cs1.Keyboard;
import java.util.Scanner;

public class SimpleInterest2{
	public static void main(String [] args){
    // ORIG: None
    Scanner sc = new Scanner(System.in);
		//Enter Principal, Rate and Time from the keyboard
		System.out.print("Enter the Principal:");
		// ORIG: double principal = Keyboard.readDouble();
		double principal = sc.nextDouble();
		System.out.print("Enter the Rate:");
		// ORIG: double rate = Keyboard.readDouble();
		double rate = sc.nextDouble();
		System.out.print("Enter the Time:");
		// ORIG: double time = Keyboard.readDouble();
		double time = sc.nextDouble();

		double interest = principal * rate * time;

		System.out.println("Interest:" + interest);
	}//end main
}//end class SimpleInterest2
