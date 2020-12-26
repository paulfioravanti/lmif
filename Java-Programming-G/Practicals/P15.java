// ORIG: import cs1.Keyboard;
import java.util.Scanner;

public class P15
{
	public static void main (String [] args)
	{
		System.out.println("How much tax should you pay?");
		System.out.print("Enter your income: ");
    // ORIG: None
    Scanner sc = new Scanner(System.in);
		
		// double income = Keyboard.readDouble();
		double income = sc.nextDouble();
		double tax1 = (income - 6000)*0.15;
		double tax1a = (6000 * 0.15);
		double tax2 = (income - 12000)*0.2;
		double tax2a = (12000 * 0.2);
		double tax3 = (income - 24000)*0.25;
		double tax3a = (24000 * 0.25);
		double tax4 = (income - 36000)*0.3;
		double tax4a = (36000 * 0.3);
		double tax5 = (income - 48000)*0.35;
		double tax5a = (48000 * 0.35);
		double tax6 = (income - 60000)*0.4;
		double totalTax = 0;
									
		if (income < 6000)
		{
			System.out.println("No tax is payable.");
		}
			else
			if (income >= 6000 && income <12000) 
			{	
				totalTax = tax1;
				System.out.println("Tax payable is: " + totalTax);
			}
				else
				if (income >=12000 && income <24000)
				{
					totalTax = tax1a + tax2;
					System.out.println("Tax payable is: " + totalTax);
				}
					else
					if (income >=24000 && income <36000)
					{
						totalTax = tax1a + tax2a + tax3;
						System.out.println("Tax payable is: " + totalTax);
					}
						else
						if (income >=36000 && income <48000)
						{
							totalTax = tax1a + tax2a + tax3a + tax4;
							System.out.println("Tax payable is: " + totalTax);
						}
							else
							if (income >=48000 && income <60000)
							{
								totalTax = tax1a + tax2a + tax3a + tax4a + tax5;
								System.out.println("Tax payable is: " + totalTax);
							}
								else
								if (income >= 60000)
								{
									totalTax = tax1a + tax2a + tax3a + tax4a + tax5a + tax6;
									System.out.println("Tax payable is: " + totalTax);
								}
								
		System.out.println("Fortnightly Pay is: " + (income - totalTax)/26);
		System.out.println("Fortnightly Tax is: " + totalTax /26);
		System.out.println("Percentage of income paid in tax is: " + (totalTax / income) *100 + " %");
	}
}
