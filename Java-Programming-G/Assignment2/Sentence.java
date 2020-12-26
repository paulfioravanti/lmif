/**===============================================================
		JPG Assignment 2 Sentence.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

import java.util.StringTokenizer;

public class Sentence
{	
	private String sentence;
	private int numWords;
	public Word[] wordArray;
	
	public Sentence()
	{
		sentence = new String();
	}
	
	public Sentence(String sentenceInput)
	{
		sentence = new String(sentenceInput);
	
		int wordIndex = 0;
		numWords = 0;
		
		StringTokenizer wordTokenizer = new StringTokenizer (sentence,";:, ");
	
		while (wordTokenizer.hasMoreTokens())
		{
			String wordToken = wordTokenizer.nextToken();
			numWords++;
		}
		wordArray = new Word[numWords];
				
		StringTokenizer word = new StringTokenizer(sentence,";:, ");
		wordArray[0] = new Word(word.nextToken());
		//System.out.println(wordArray[0].getWord()); //debugging code
		
		while (word.hasMoreTokens())
		{
			String wordToken = word.nextToken();
			wordIndex++;
			wordArray[wordIndex] = new Word(wordToken);
			//System.out.println(wordArray[wordIndex].getWord());
		}
	}
	
	public String getSentenceString()
	{
		return(sentence);
	}
	
	/*public void setSentence(String sentenceInput)
	{
		sentence = new String(sentenceInput);
	}*/
	
	public int getNumWords()
	{
		return(numWords);
	}

}// end class
