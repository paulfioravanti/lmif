// ORIG: jmport cs1.Keyboard;
import java.util.Scanner;

public class ComputeMortgage{
	public static void main(String [] args){

    // ORIG: None
    Scanner sc = new Scanner(System.in);

		double annualInterestRate;

		int numberOfYears;
		
		double loanAmount;

		System.out.print("Enter your yearly interest rate:");

		// ORIG: annualInterestRate = Keyboard.readDouble();
		annualInterestRate = sc.nextDouble();

		double monthlyInterestRate = annualInterestRate/1200;

		System.out.print("Enter number of years as an integer:");

		// ORIG: numberOfYears = Keyboard.readInt();
		numberOfYears = sc.nextInt();

		System.out.print("Enter the loan amount:");

		// ORIG: loanAmount = Keyboard.readDouble();
		loanAmount = sc.nextDouble();

		double monthlyPayment = loanAmount * monthlyInterestRate/(1 - 1/(Math.pow(1 + monthlyInterestRate,numberOfYears*12)));

		double totalPayment = monthlyPayment * numberOfYears *12;
		
		//Display results

		System.out.println("The monthly payment is:" + monthlyPayment);

		System.out.println("The total payment is:" + totalPayment);

	}//end Main

}//end class ComputeMortage
