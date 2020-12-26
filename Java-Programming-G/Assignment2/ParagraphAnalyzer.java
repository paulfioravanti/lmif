/**===============================================================
		JPG Assignment 2 ParagraphAnalyzer.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

   import java.util.*;

    public class ParagraphAnalyzer
   {
   	//define private variables
      private String paragraph;
      private Sentence[] sentenceArray;
      private int numSentences;
      private String frequencyWord;
   
       public int getNumSentences()
      {
         return(numSentences);
      }
   
       public int getNumWords()
      {
         int totalNumWords = 0;
         for (int i = 0; i < numSentences; i++)
            totalNumWords += sentenceArray[i].getNumWords();
         return(totalNumWords);
      }
   	
       public ParagraphAnalyzer(String paragraphInput)
      {
         int sentenceIndex = 0;
         paragraph = new String(paragraphInput);
         numSentences = 0;
      
         StringTokenizer sentenceTokenizer = new StringTokenizer (paragraph,".?!");
      
         while (sentenceTokenizer.hasMoreTokens())
         {
            String sentenceToken = sentenceTokenizer.nextToken();
            numSentences++;
         }
         sentenceArray = new Sentence[numSentences];
      		
         StringTokenizer sentence = new StringTokenizer(paragraphInput,".?!");
         sentenceArray[0] = new Sentence(sentence.nextToken());
      //System.out.println(sentenceArray[0].getSentenceString()); //debugging code
      
         while (sentence.hasMoreTokens())
         {
            String sentenceToken = sentence.nextToken();
            sentenceIndex++;
            sentenceArray[sentenceIndex] = new Sentence(sentenceToken);
         //System.out.println(sentenceArray[sentenceIndex].getSentenceString()); //debugging code
         }
      } //end ParagraphAnalyzer(String paragraphInput)
   
   	//histogram
       public String getHistogram()
      {
         String[] histogram = new String[7];
         histogram[0] = "     1 - 4 | ";
         histogram[1] = "     5 - 8 | ";
         histogram[2] = "    9 - 12 | ";
         histogram[3] = "   13 - 16 | ";
         histogram[4] = "   17 - 20 | ";
         histogram[5] = "   21 - 24 | ";
         histogram[6] = "25 or over | ";
      
         for (int i = 0; i < numSentences; i++)
         {
            int numWords = sentenceArray[i].getNumWords();
            if (numWords < 5)
               histogram[0] = new String(histogram[0] + "*");
            else if (numWords < 9)
               histogram[1] = new String(histogram[1] + "*");
            else if (numWords < 13)
               histogram[2] = new String(histogram[2] + "*");
            else if (numWords < 17)
               histogram[3] = new String(histogram[3] + "*");
            else if (numWords < 21)
               histogram[4] = new String(histogram[4] + "*");
            else if (numWords < 25)
               histogram[5] = new String(histogram[5] + "*");
            else
               histogram[6] = new String(histogram[6] + "*");
         }
         return(histogram[0] + "\n" + histogram[1] + "\n" + histogram[2] + "\n" + histogram[3] + "\n" + histogram[4] + "\n" + histogram[5] + "\n" + histogram[6] + "\n");
      }
   
   //A
       public int getFrequency(String frequencyInput)
      {
         int frequencyNum = 0;
         for (int i = 0; i < sentenceArray.length; i++)
         {
            for (int j = 0; j < sentenceArray[i].wordArray.length; j++)
            {
               if (sentenceArray[i].wordArray[j].getWord().equalsIgnoreCase(frequencyInput))
                  frequencyNum++;
            }
         
         }
         return(frequencyNum);
      }
   
   //B
       public String getSentenceFrequency(int sentenceFrequencyInput)
      {
         String sentenceResult = "";
         for (int i = 0; i < sentenceArray.length; i++)
         {
            if (sentenceArray[i].getNumWords() == sentenceFrequencyInput)
               sentenceResult = new String(sentenceResult + "\n" + sentenceArray[i].getSentenceString());
         	
         }
         if (sentenceResult.equals(""))
            return("\nThe paragraph has no sentence that contains " + sentenceFrequencyInput + " words.");
         else
            return(sentenceResult);
      }
   
   //C
       public String getSearchWord(String wordSearchInput)
      {
         String searchWord = "";
      
         for (int i = 0; i < sentenceArray.length; i++)
         {
            for (int j = 0; j < sentenceArray[i].wordArray.length; j++)
            {
               if (sentenceArray[i].wordArray[j].getWord().equalsIgnoreCase(wordSearchInput))
                  searchWord = new String(searchWord + "\n" + sentenceArray[i].getSentenceString());
            }
         }
         if (searchWord.equals(""))
            return ("\n'" + wordSearchInput + "' not found.");
         else
            return(searchWord);
      }	
   
   
   }//end class
