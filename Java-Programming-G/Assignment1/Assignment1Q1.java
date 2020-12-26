/**===============================================================
		JPG Assignment 1 Q1, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

   // ORIG: import cs1.Keyboard;
   import java.util.Scanner;

    public class Assignment1Q1
   {
       public static void main(String [] args)
      {
         // ORIG: None
         Scanner sc = new Scanner(System.in);
         char menuInput = ' ';
      	while (menuInput != 'Q' && menuInput != 'q')
         {
            System.out.println("\nJava Assignment 1, Q1 \n");
            System.out.println("A. Convert decimal to hexadecimal\n");
            System.out.println("B. Convert hexadecimal to decimal\n");
            System.out.println("Q. Quit\n");
            System.out.print("Please enter your choice: ");
         
            // ORIG: menuInput = Keyboard.readChar();
            menuInput = sc.next().charAt(0);
         
            if (menuInput == 'Q' || menuInput == 'q') 
            {
               System.out.println("\nThanks for using our number conversion program.");
               System.exit(0);
            }
            else
               if (menuInput == 'A' || menuInput == 'a')
               {
                  String decInput;
                  int decimal;
                  int remainder;
                  char digit; 
                  String hexOutput = "";
               																
                  System.out.print("\nPlease enter a decimal number: ");
                  // ORIG: decInput = Keyboard.readString();
                  decInput = sc.next();
                  decimal = Integer.parseInt(decInput);
               							
                  do
                  {
                     remainder = decimal % 16;
                     decimal = decimal / 16;	
                  	
                     switch(remainder)
                     {
                        case 0:
                           digit = '0'; 
                           break;
                        case 1:
                           digit = '1'; 
                           break;
                        case 2:
                           digit = '2'; 
                           break;
                        case 3:
                           digit = '3'; 
                           break;
                        case 4:
                           digit = '4'; 
                           break;
                        case 5:
                           digit = '5'; 
                           break;
                        case 6:
                           digit = '6'; 
                           break;
                        case 7:
                           digit = '7'; 
                           break;
                        case 8:
                           digit = '8'; 
                           break;
                        case 9:
                           digit = '9'; 
                           break;
                        case 10:
                           digit = 'A'; 
                           break;
                        case 11:
                           digit = 'B'; 
                           break;
                        case 12:
                           digit = 'C'; 
                           break;
                        case 13:
                           digit = 'D'; 
                           break;
                        case 14:
                           digit = 'E'; 
                           break;
                        case 15:
                           digit = 'F'; 
                           break;
                        default:
                           digit = ' '; 
                           break;
                     } // end switch
                  
                     hexOutput =  digit + hexOutput;
                  
                  } while (decimal > 0);
               
                  System.out.println("\nThe converted value of " + decInput + " in hexadecimal is " + hexOutput);
                  System.out.println("\n##################################################");
               } // end if
               else 
                  if (menuInput == 'B' || menuInput == 'b')
                  {	
                     System.out.print("\nPlease enter a hexadecimal number: ");
                     // ORIG: String hexInput = Keyboard.readString();
                     String hexInput = sc.next();
                     int digit;
                     int multiplier = 1;
                     int decOutput = 0;
                  
                     for (int i = hexInput.length() - 1; i >= 0; i--)
                     {
                        char hexChar = hexInput.charAt(i);
                        switch(hexChar)
                        {
                           case '0':
                              digit = 0; 
                              break;
                           case '1':
                              digit = 1; 
                              break;
                           case '2':
                              digit = 2; 
                              break;
                           case '3':
                              digit = 3; 
                              break;
                           case '4':
                              digit = 4; 
                              break;
                           case '5':
                              digit = 5; 
                              break;
                           case '6':
                              digit = 6; 
                              break;
                           case '7':
                              digit = 7; 
                              break;
                           case '8':
                              digit = 8; 
                              break;
                           case '9':
                              digit = 9; 
                              break;
                           case 'A':
                           case 'a':
                              digit = 10;	
                              break;
                           case 'B':
                           case 'b':
                              digit = 11;	
                              break;
                           case 'C':
                           case 'c':
                              digit = 12;	
                              break;
                           case 'D':
                           case 'd':
                              digit = 13;	
                              break;
                           case 'E':
                           case 'e':
                              digit = 14;	
                              break;
                           case 'F':
                           case 'f':
                              digit = 15;	
                              break;
                           default:
                              digit = 16;	
                              break;
                        }//end switch
                     
                        if (digit == 16)
                        {
                           System.out.println("\nIncorrect number for specified type.");
                        }
                        else
                        {
                        //System.out.println(digit);//temp line
                           decOutput += digit * multiplier;
                           multiplier *= 16;
                        }
                     }//end for
                  
                     System.out.println("The converted value of " + hexInput + " in decimal is " + decOutput);
                     System.out.println("\n##################################################");
                  }//end if        
                  else
                  {
                     System.out.println("\nInvalid selection.");
                  }
         }//end while 		
      }//end main
   }//end class
