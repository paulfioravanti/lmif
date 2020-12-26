/**===============================================================
		JPG Assignment 1 Q2, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/
// ORIG: import cs1.Keyboard;
import java.util.Scanner;

public class Assignment1Q2
{
	public static void main(String [] args)
   {
   	System.out.println("===============================================================");
      System.out.println("	I declare this to be my own work as defined by the");
      System.out.println("	University of South Australia's Academic misconduct policy.");
      System.out.println("	Paul Fioravanti");
      System.out.println("===============================================================");	
			
    // ORIG: None
    Scanner sc = new Scanner(System.in);
		String dayInput;
		int dayNumber = 1;
		String dayName = "";
		String rainInput;
		double rainFall = 0.0;
		double highestRainfall = 0.0;
		int numOfLoops = 0;
		int highestWeekday = dayNumber;
		String highestWeekdayName = "";
   
		while (dayNumber >= 1 && dayNumber <= 7)   
		{     
			System.out.print("\nPlease enter the day number: ");
         // ORIG: dayInput = Keyboard.readString();
         dayInput = sc.next();
        	dayNumber = Integer.parseInt(dayInput);
											
			switch(dayNumber)
			{
				case 1:
					dayName = "Monday"; break;
				case 2:
					dayName = "Tuesday"; break;
				case 3:
					dayName = "Wednesday"; break;
				case 4:
					dayName = "Thursday"; break;
				case 5:
					dayName = "Friday"; break;
				case 6:
					dayName = "Saturday"; break;
				case 7:
					dayName = "Sunday"; break;
				default:
					System.out.println("\nThe highest rainfall in one day was " + highestRainfall + " mm");
					System.out.println("\nThe weekday with the highest rainfall is on " + highestWeekdayName);
					System.out.println("\n" + numOfLoops + " rainy days have been entered");
					System.exit(0);
			}
			
			//System.out.println("You chose " + dayName); //temp line
			
			System.out.print("\nPlease enter the rainfall: ");
      	// ORIG: rainInput = Keyboard.readString();
      	rainInput = sc.next();
      	rainFall	= Double.parseDouble(rainInput);
			
			if (rainFall <= 0.01)
				rainFall = 0.0;
			else 
				rainFall = Double.parseDouble(rainInput);
				
			//System.out.println("rainFall value is " + rainFall); //temp line
			
			if (rainFall > highestRainfall)
			{
				highestRainfall = rainFall;
				if (dayNumber != 6 && dayNumber != 7)
				highestWeekday = dayNumber;
				else
					highestWeekday = highestWeekday;
			}
			else
			{
				highestRainfall = highestRainfall;
				highestWeekday = highestWeekday;
			}
			
			switch(highestWeekday)
			{
				case 1:
					highestWeekdayName = "Monday"; break;
				case 2:
					highestWeekdayName = "Tuesday"; break;
				case 3:
					highestWeekdayName = "Wednesday"; break;
				case 4:
					highestWeekdayName = "Thursday"; break;
				case 5:
					highestWeekdayName = "Friday"; break;
				case 6:
					highestWeekdayName = "Saturday"; break;
				case 7:
					highestWeekdayName = "Sunday"; break;
			}
			
			//System.out.println("highestRainfall value is " + highestRainfall); //temp line
			//System.out.println("highestWeekday is " + highestWeekdayName); //temp line
						
			numOfLoops++;
			
			//System.out.println("num of loops is " + numOfLoops); //temp line
					
		}//end while
			
   }// end main
		
}//end class
