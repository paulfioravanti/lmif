/**===============================================================
		JPG Assignment 4 Q2 Node.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

/**
	This Node class is for use inside a linked list with the MyVector class.
	@author Paul Fioravanti
*/
public class Node
{
	Object o;
	Node next;
	Node prev;
	
	/**
		Initialises the Node class and the Object passed into it.
		@param o the object passed into the method
	*/
	public Node(Object o)
	{
		this.o = o;
	}
}//end Node
