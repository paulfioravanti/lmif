/**===============================================================
		JPG Assignment 3 SalariedEmployee.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

import java.text.*;

public class SalariedEmployee extends Employee
{
	DecimalFormat empIDFormat = new DecimalFormat("00000");
	DecimalFormat salEmpWageFormat = new DecimalFormat("$" + "000000");
	
	public SalariedEmployee(int employeeID, String surname, String givenName, double wage)
	{
		super(employeeID, surname, givenName);
		this.wage = wage;
	}
	
	public double computePay()
	{
		return wage/52.0;
	}

	public double changeWage(double percent)
	{
		if (percent <= 0)
		 	System.out.println("\nIncorrect Input: Please Enter Positive Amount");
		else
			wage *= (1 + (percent/100));
		return wage;
	}
	
	public String toString()
	{
		return empIDFormat.format(employeeID) + "," + surname + "," + givenName + "," + salEmpWageFormat.format(wage) + "/year";
	}
}//end class
		
