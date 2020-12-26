/**===============================================================
		JPG Assignment 3 A3.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

// ORIG: import cs1.Keyboard;
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class A3
{
	private Employee[] employeeArray = new Employee[1];
   private int employeeArraySize = 0;
   
	DecimalFormat compPaycheckFormat = new DecimalFormat("$" + "#####.00");
  // ORIG: None
  Scanner sc = new Scanner(System.in);
   
   public void start()
   {
   	int employeeID;
      String surname, givenName, employeeType;
      double wage;
      	
      BufferedReader in = null;
      StringTokenizer employeeToken;
      String employeeDetails;
            				
      try
      {
    		in = new BufferedReader(new FileReader("employee.dat"));
         employeeDetails = in.readLine();
         	
         while (employeeDetails != null)
         {	
         	employeeToken = new StringTokenizer(employeeDetails,",$/	");
            	
            if (employeeToken.countTokens() != 5)
            	throw new NumberFormatException();
            						
            employeeID = Integer.parseInt(employeeToken.nextToken());
            surname = employeeToken.nextToken();
            givenName = employeeToken.nextToken();
            wage = Double.parseDouble(employeeToken.nextToken());
            employeeType = employeeToken.nextToken();
            
            addEmployee(employeeID, surname, givenName, employeeType, wage);
            	
            employeeDetails = in.readLine();
            	
         }//end while
         	
         in.close();
         
		}//end try
         
      catch (FileNotFoundException exception)
      {
      	System.out.println("\nFile Not Found");
			return;
      }
         
      catch (IOException exception)
      {
         System.out.print("\nI/O error");
			return;
      }
         
      catch (NumberFormatException exception)
      {
         System.out.println("\nA Number in File is of Incorrect Format");
			return;
      }	
      		
      System.out.println("\n=================================================================");
      System.out.println("|| I declare this to be my own work as defined by the          ||");		
      System.out.println("|| University of South Australia's Academic misconduct policy. ||");
      System.out.println("|| Paul Fioravanti\t  100036883\t  Room MLK-GP1-02          ||");
      System.out.println("=================================================================");
      	
      String selectionInput = "";
      	
      while (!selectionInput.equals("4"))
      {
      	System.out.println("\n*******************************");
         System.out.println("* 1. New Employee             *");
         System.out.println("* 2. Compute paychecks        *");
         System.out.println("* 3. Change wages             *");
         System.out.println("*                             *");
         System.out.println("* 4. Quit                     *");
         System.out.println("*******************************");

         System.out.print("\nEnter your selection (1-4): ");
         // ORIG: selectionInput = Keyboard.readString().trim();
         selectionInput = sc.next().trim();
         							
         if (selectionInput.equals("1"))
       	  newEmployee();
         else if (selectionInput.equals("2"))
           computePay();
         else if (selectionInput.equals("3"))
           changeWage();
         else if (selectionInput.equals("4"))
         {
        		writeFile();
            System.exit(0);
         }
         else
            System.out.println("\nIncorrect Input.");
      }//end while
   }//end start
   	
//=================================================================================
   	
	private void newEmployee()
	{
   	int employeeID;
    	String surname, givenName, employeeType;
      double wage;
      	
      int employeeIDParsed = 0;
         
      try
      {
      	System.out.print("\nEnter the employee ID (5 digits): ");
         // ORIG: employeeIDParsed = Integer.parseInt(Keyboard.readString().trim());
         employeeIDParsed = Integer.parseInt(sc.next().trim());
         	
         if (employeeIDParsed <= 0 || employeeIDParsed > 99999)
         {
         	System.out.println("\nIncorrect Input: Employee Number Out of Range.");
            return;
         }
      }//end try
         
      catch (NumberFormatException exception)
      {
      	System.out.println("\nIncorrect input.");
			return;
      }
      	
      for (int i = 0; i < employeeArraySize; i++)
      {
      	if (employeeIDParsed == employeeArray[i].getEmployeeID())
         {
         	System.out.println("\nError: Employee Already on Record.");
            return;
         }
      }
      	
      employeeID = employeeIDParsed;
      	
      employeeType = "";
      System.out.print("\nEnter employee type ('H' for HourlyEmployee, 'S' for SalariedEmployee): ");
      // ORIG: String employeeTypeInput = Keyboard.readString().trim();
      String employeeTypeInput = sc.next().trim();
      	      	      		
      if (employeeTypeInput.equalsIgnoreCase("H"))
      	employeeType = "hour";
      else if (employeeTypeInput.equalsIgnoreCase("S"))
         employeeType = "year";
      else
      {
      	System.out.println("\nIncorrect Input: Invalid Employee Type.");
         return;
      }
      		
      surname = "";
      System.out.print("\nEnter surname: ");
      // ORIG: surname = Keyboard.readString().trim();
      surname = sc.next().trim();
		
		for (int i = 0; i < surname.length(); i++)
		{	
			if (Character.isDigit(surname.charAt(i)) == true)
			{
				System.out.println("\nInput error: Not a name.");
				return;
			}
		}
      			
      givenName = "";
      System.out.print("\nEnter given name: ");
      // ORIG: givenName = Keyboard.readString().trim();
      givenName = sc.next().trim();
		
		for (int i = 0; i < givenName.length(); i++)
		{	
			if (Character.isDigit(givenName.charAt(i)) == true)
			{
				System.out.println("\nInput error: Not a name.");
				return;
			}
		}
      			
      wage = 0;
      System.out.print("\nEnter the wage: ");
			
		try
		{
        	// ORIG: wage = Double.parseDouble(Keyboard.readString().trim());
        	wage = Double.parseDouble(sc.next().trim());
		}
			
		catch (NumberFormatException exception)
		{
			System.out.println("\nIncorrect Input:  Number not of Currency Format.");
			return;
		}
      			
      addEmployee(employeeID, surname, givenName, employeeType, wage);
		
   }//end newEmployee
   
//======================================================================================================================		
   	
   private void addEmployee(int employeeID, String surname, String givenName, String employeeType, double wage)
   {
      			
   	if (employeeArraySize >= employeeArray.length)
      {
      	Employee[] tempEmployeeArray = new Employee[2 * employeeArray.length];
         System.arraycopy(employeeArray, 0, tempEmployeeArray, 0, employeeArray.length);
         employeeArray = tempEmployeeArray;
      }         
                             
      if (employeeType.equals("hour"))
      {
         double hourlyWage = wage;
         employeeArray[employeeArraySize] = new HourlyEmployee(employeeID, surname, givenName, wage);
      }
      else if (employeeType.equals("year"))
      {
         double salary = wage;
         employeeArray[employeeArraySize] = new SalariedEmployee(employeeID, surname, givenName, wage);
      }
      else
		{
         System.out.println("\nIncorrect Input: Invalid Employee Type. Data not entered.");
			return;
		}
               
      employeeArraySize++;
          
   }//end addEmployee
   	
//===============================================================================================================
   	
   public void computePay()
   {
   	int index = -1;
      int employeeIDParsed;
      	
      System.out.print("\nEnter the employee ID: ");
     
	   try
      {
      	// ORIG: employeeIDParsed = Integer.parseInt(Keyboard.readString().trim());
      	employeeIDParsed = Integer.parseInt(sc.next().trim());
      }
         
      catch (NumberFormatException exception)
      {
      	System.out.println("\nIncorrect Input: Illegal Employee Number.");
         return;
      }
      	
      if (employeeIDParsed <= 0 || employeeIDParsed > 99999)
      {
         System.out.println("\nIncorrect Input: Illegal Employee Number - Out of Range.");
         return;
      }
			
		boolean employeeExists = false;
			      		
      for (int i = 0; i < employeeArraySize; i++)
      {
			if (employeeIDParsed == employeeArray[i].getEmployeeID())
         {
         	double wage;
				employeeExists = true;
            	
            if (employeeArray[i] instanceof HourlyEmployee)
            {
            	System.out.print("\nHow many hours worked? ");
               int hour = 0;         	
              	
               try
               {
               	// ORIG: hour = Integer.parseInt(Keyboard.readString().trim());
               	hour = Integer.parseInt(sc.next().trim());
               }
                  
               catch (NumberFormatException exception)
               {
               	System.out.println("\nInvalid Input.");
						break;	
               }
                  wage = ((HourlyEmployee)employeeArray[i]).computePay(hour);
            }//end if
               
            else 
            {
            	wage = ((SalariedEmployee)employeeArray[i]).computePay();	
            }	
            		
            System.out.println("\nThis week's payment is: " + compPaycheckFormat.format(wage));
				break;
            
         }//end if
		}//end for 
			
		if (employeeExists == false)
		{
			System.out.println("\nIncorrect Input: Employee not on Record.");
			return;
		}
			
	}//end computePay
   	
//===========================================================================================
   	
   public void changeWage()
   {
   	System.out.print("\nEnter employee type ('H' for HourlyEmployee, 'S' for SalariedEmployee): ");
      // ORIG: String employeeTypeInput = Keyboard.readString().trim();
      String employeeTypeInput = sc.next().trim();
      
      double amount = 0.0;  
      double percent = 0.0;
      String employeeType = "";
      		
      if (employeeTypeInput.equalsIgnoreCase("H"))
      {
      	employeeType = "hour";
         
         System.out.print("\nEnter the amount: ");
         
         	
         try
         {
         	// ORIG: amount = Double.parseDouble(Keyboard.readString().trim());
         	amount = Double.parseDouble(sc.next().trim());
         	if (amount < 0)
				System.out.println("\nIncorrect Input: Please Enter Non-negative Amount");
         }
            
         catch (NumberFormatException exception)
         {
         	System.out.println("\nError: Cannot Process changeWage - Invalid Number Type.");
            return;
         }
      }//end if
         	
         	
      else if (employeeTypeInput.equalsIgnoreCase("S"))
      {
      	employeeType = "year";
         System.out.print("\nEnter the percentage: ");
         	
         try
         {
         	// ORIG: percent = Double.parseDouble(Keyboard.readString().trim());
         	percent = Double.parseDouble(sc.next().trim());
				if (percent < 0)
					System.out.println("\nIncorrect Input: Please Enter Non-negative Amount");
         }
            
         catch (NumberFormatException exception)
         {
         	System.out.println("\nError: Cannot Process changeWage - Invalid Number Type.");
            return;
         }
      }//end if
         
      else
      {
      	System.out.println("\nError: Cannot Process changeWage - Invalid Employee Type.");
         return;
      }
      	
      for (int i = 0; i < employeeArraySize; i++)
      {
      	if (employeeArray[i] instanceof HourlyEmployee && amount > 0.0)
         	employeeArray[i].changeWage(amount);
         else if (employeeArray[i] instanceof SalariedEmployee && percent > 0.0)
            employeeArray[i].changeWage(percent);
      }
      
   }//end changeWage
   	
//======================================================================================
   	
   public void writeFile()
   {	
   	File file1 = new File("employee.dat");
      	
      if (!(file1.renameTo(new File("employee.old"))))
      {
      	System.out.println("\nError: Could not Rename File.");
         System.exit(1);
      }
      				
      try
      {
         PrintWriter pw = new PrintWriter(new FileWriter("employee.dat"));
         	
         for (int i = 0; i < employeeArraySize; i++)
         {
         	pw.println(employeeArray[i]);
            System.out.println(employeeArray[i]);
         }
         	
            pw.close();
      }
         
      catch (IOException exception)
      {
            
      }
   }//end writeFile
}//end class
