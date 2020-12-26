public class SimpleInterest{
	public static void main(String [] args){
		//Calculate the Simple Interest on a loan of $1325.76
		//at an interest rate of 6.25% per year
		//for a period of 6.75 years

		double principal = 1325.76;
		double rate = 0.0625;
		double time = 6.75;

		double interest = principal * rate * time;

		System.out.println("Interest: " + interest);
	}//end main
}//end class SimpleInterest