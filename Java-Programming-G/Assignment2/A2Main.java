/**===============================================================
		JPG Assignment 2 A2Main.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

// ORIG: import cs1.Keyboard;
import java.util.Scanner;
import java.util.StringTokenizer;

public class A2Main
{
	public static void main(String [] args)
	{
		System.out.println("\n=================================================================");
      System.out.println("|| I declare this to be my own work as defined by the          ||");		
      System.out.println("|| University of South Australia's Academic misconduct policy. ||");
      System.out.println("|| Paul Fioravanti                                             ||");
      System.out.println("=================================================================");
	
    // ORIG: None
    Scanner sc = new Scanner(System.in);
    sc.useDelimiter("\n");

		String input = "";
					
		while (!(input.equals("N") && input.equals("n")))
		{
						
			while (!(input.equals("Y") && input.equals("y")))
			{
				System.out.print("\nDo you want to enter a paragraph (Y or y for yes. N or n for no)? ");
				// ORIG: input = Keyboard.readString();
				input = sc.next();
			
				if (input.equals("N") || input.equals("n"))
				{
					System.out.println("\nGoodbye");
					System.exit(0);
				}
				else if (input.equals("Y") || input.equals("y"))
					break;
				else
					continue;
			}//end while
		
			System.out.print("\nPlease enter a paragraph:\n> ");
	  		// ORIG: String paragraphInput = Keyboard.readString();
	  		String paragraphInput = sc.next();
			
			ParagraphAnalyzer paragraph = new ParagraphAnalyzer(paragraphInput);
		
			System.out.println("\nNumber of sentences in the paragraph: " + paragraph.getNumSentences());
			
			System.out.println("\nFrequency distribution of sentences with given number of set words:\n");
		
			System.out.print(paragraph.getHistogram());
				
			System.out.println("\nNumber of words in the paragraph: " + paragraph.getNumWords());
		
			String menuInput = "";
		
			while (!(menuInput.equals("Q") && menuInput.equals("q")))
			{
				System.out.println("\n*************************************************************");
				System.out.println("\nA. Display the frequency of a word in the paragraph?");
				System.out.println("B. Display a list of sentences with a given count of words in a sentence?");
				System.out.println("C. Search for a word in the paragraph?");
				System.out.println("Q. Quit this menu?");
				System.out.print("\nPlease enter your selection (A, B, C, or Q): ");
		
				// menuInput = Keyboard.readString();
				menuInput = sc.next();
			
				if (menuInput.equals("Q") || menuInput.equals("q"))
				{
					break;
				}
				else if (menuInput.equals("A") || menuInput.equals("a"))
				{
					System.out.print("\nPlease enter the word: ");
					// ORIG: String frequencyInput = Keyboard.readString();
					String frequencyInput = sc.next();
					System.out.println("\nThe word '" + frequencyInput + "' occurs " + paragraph.getFrequency(frequencyInput) + " times in the paragraph.");
					continue;
				}
				else if (menuInput.equals("B") || menuInput.equals("b"))
				{
					System.out.print("\nPlease enter the frequency of words in a sentence: ");
					// ORIG: int sentenceFrequencyInput = Keyboard.readInt();
					int sentenceFrequencyInput = sc.nextInt();
					System.out.println(paragraph.getSentenceFrequency(sentenceFrequencyInput));
					continue;
				}
				else if (menuInput.equals("C") || menuInput.equals("c"))
				{
					System.out.print("\nPlease enter the word: ");
					// String wordSearchInput = Keyboard.readString();
					String wordSearchInput = sc.next();
					System.out.println(paragraph.getSearchWord(wordSearchInput)); 
					continue;
				}
				else
				{
					System.out.println("\nInvalid input.  Please enter A, B, C, or Q.");
					continue;
				}
			}//end while
		
		}//end first while
	}//end main
}//end class
