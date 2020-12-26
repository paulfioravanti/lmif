/**===============================================================
		JPG Assignment 3 Employee.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti	100036883
===============================================================*/

abstract class Employee
{
	protected int employeeID;
	protected String surname;
	protected String givenName;
	protected double wage;
	
	public Employee(int employeeID, String surname, String givenName)
	{
		this.employeeID = employeeID;
		this.surname = surname;
		this.givenName = givenName;
	}
	
	abstract double changeWage(double change);
	
	public int getEmployeeID()
	{
		return employeeID;
	}
	
}//end class