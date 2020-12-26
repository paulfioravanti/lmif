/**===============================================================
		JPG Assignment 3 HourlyEmployee.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

import java.text.*;

public class HourlyEmployee extends Employee
{
	DecimalFormat compPaycheckFormat = new DecimalFormat("$" + "#####.##");
	DecimalFormat empIDFormat = new DecimalFormat("00000");

	public HourlyEmployee(int employeeID, String surname, String givenName, double wage)
	{
		super(employeeID, surname, givenName);
		this.wage = wage;
	}
	
	public double computePay(int hour)
	{
		double weeklyWage;
			
		if (hour <= 40)
			weeklyWage = wage * hour;
		else
			weeklyWage = 40 * wage + ((hour - 40) * (wage * 0.8));
		return weeklyWage;
	}
		
	public double changeWage(double amount)
	{
		if (amount <= 0)
			System.out.println("\nIncorrect Input: Please Enter Positive Amount");
		else
			wage+= amount;
		return wage;
	}
	
	public String toString()
	{
		return empIDFormat.format(employeeID) + "," + surname + "," + givenName + "," + compPaycheckFormat.format(wage) + "/hour";
	}
}//end class
