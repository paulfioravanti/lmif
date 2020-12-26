/**===============================================================
		JPG Assignment 4 Q2 NoElementPresentException.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

/**
	This NoElementPresentException class is a user-defined exception for use
	with the MyVector class.  It is thrown when a method in the MyVector
	class refers to an element that does not exist.
	@author Paul Fioravanti
*/
public class NoElementPresentException extends Exception
{
	/**
		Initialises the NoElementPresentException class.
	*/
	public NoElementPresentException()
	{
		super("No Element Present");
	}
}//end NoElementPresentException 
