public class Notes
{
	public static void main(String [] args)
	{
		int amount = 789;
		int num100, num50, num20, num10, num5, num2, num1;
		int rem;
		
		num100 = amount/100;
		rem = amount%100;
		num50 = rem/50;	rem = rem%50;
		num20 = rem/20;	rem = rem%20;
		num10 = rem/10;	rem = rem%10;
		num5 = rem/5;		rem = rem%5;
		num2 = rem/2;		rem = rem%2;
		num1 = rem;
		
		System.out.println(num100 + " 100s, " + num50 + " 50s, " + num20 + " 20s, " +
		num10 + " 10s, " + num5 + " 5s, " + num2 + " 2s, " + num1 + " 1s. ");
	}
} 
		