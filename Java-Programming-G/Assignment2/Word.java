/**===============================================================
		JPG Assignment 2 Word.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

public class Word
{
	private String word;
	
	public Word()
	{
		word = new String();
	}
	
	public Word(String wordInput)
	{
		word = new String(wordInput);
	}
	
	public String getWord()
	{
		return(word);
	}
	
	/*public void setWord(String wordInput)
	{
		word = new String(wordInput);
	}*/

}//end class
