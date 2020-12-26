/**===============================================================
		JPG Assignment 4 Q2 MyVector.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

/**
	This MyVector class allows access to a position in a linked list. 
	It contains a subset of the main methods for a Vector class.
	@author Paul Fioravanti
*/
public class MyVector
{	
	private Node curr, head, prev = null;
	
	/**
		If list is empty, adds an Object and makes it the head.
		If list is not empty, steps through each Object 
		and adds an Object at the end.
		@param o the object to add to the end
	*/
	public void addElement(Object o)
	{
		Node curr = head;
		Node n = new Node(o);
		
		if (head == null)
			head = n;
		else
		{
			while (curr.next != null)
			{
				curr = curr.next;
			}
			curr.next = n;
		}
	}//end addElement()
	
	/**
		Inserts an Object at position i.
		@param o the object to insert at position i
		@param i the position at which to insert the object
	*/
	public void insertElementAt(Object o, int i)
	{
		Node prev, curr;
		int p = 0;
		prev = curr = head;
		Node n = new Node(o);
		
		if (head == null)
			head = n;
		else
		{
			while (curr.next != null)
			{
				prev = curr;
				curr = curr.next;
				p++;
				
				if (p == i)
				{
					n.next = curr;
					prev.next = n;
					break;
				}
			}//end while
		}//end else
	}//end insertElementAt()
	
	/**
		Removes the last element.
		@throws NoElementPresentException if no elements exist to be removed
	*/
	public void removeElement() throws NoElementPresentException
	{
		Node curr, prev;
		prev = curr = head;
		if (head == null)
			throw new NoElementPresentException();	
		else
		{
			while (curr.next != null)
			{
				prev = curr;
				curr = curr.next;
			}
			prev.next = curr.next;
		}
	}//end removeElement()
	
	/**
		Removes the i-th node. Traverses to the i-th
		Node then deletes it.
		@param i the position at which to delete the element
		@throws NoElementPresentException if no element exists at i to be removed
	*/
	public void removeElementAt(int i) throws NoElementPresentException
	{
		Node prev, curr;
		int p = 0;
		prev = curr = head;
		
		if (head == null)
			throw new NoElementPresentException();
		else
		{
			while (curr.next != null)
			{
				prev = curr;
				curr = curr.next;
				p++;
				
				if (p == i)
				{
					prev.next = curr.next;
					break;
				}
			}//end while
			if (i > p || i < 0)
				throw new NoElementPresentException();
		}//end else
	}//end removeElementAt()
	
	/**
		Returns the number of elements.
		@return the number of elements in the vector
	*/
	public int size()
	{
		Node curr;
		curr = head;
		int p = 0;

		while (curr != null)
		{
			curr = curr.next;
			p++;
		}
		return p;
	}//end size()
	
	/**
		Returns the element at the next location.
		@return the element at the next location
		@throws NoElementPresentException if no element exists in the next location
	*/
	public Object nextElement() throws NoElementPresentException
	{
		if (curr == null || curr.next == null)
			throw new NoElementPresentException();
		return curr.next.o;
	}//end nextElement()
	
	/**
		Returns the element at location n.
		@param n the location of the element
		@return the element at location n
		@throws NoElementPresentException if there is no element at location n
	*/
	public Object elementAt(int n) throws NoElementPresentException
	{
		Node prev;
		int p = 0;
		prev = curr = head;
		
		if (head == null)
			throw new NoElementPresentException();
		else
		{
			while (curr.next != null)
			{
				curr = curr.next;
				prev = curr;
				p++;
								
				if (p == n)
				{
					break;
				}
			}//end while
			if (n > p || n < 0)
				throw new NoElementPresentException();
			else
				return curr.o;
		}//end else		
	}//end elementAt()
	
	/**
		Returns the element at location n and
		removes it.
		@param n the location of the element
		@return the element at location n
		@throws NoElementPresentException if there is no element at location n to delete 
	*/
	public Object takeElementAt(int n) throws NoElementPresentException
	{
		Object delEle = null;
		Node prev, curr;
		int p = 0;
		prev = curr = head;
		
		if (head == null)
			throw new NoElementPresentException();
		else
		{
			while (curr.next != null)
			{
				prev = curr;
				curr = curr.next;
				p++;
				
				if (p == n)
				{
					break;
				}
			}//end while
			if (n > p || n < 0)
				throw new NoElementPresentException();
			else
			{
				delEle = curr.o;
				prev.next = curr.next;
				return delEle;
			}
				 
		}//end else		
	}//end TakeElementAt()
	
	/**
		Traverses the list and displays the objects stored in each Node.
		This method only to be used if the list is not empty.
	*/
	public void displayList()	
	{
		Node curr;
		curr = head;
		
		if (head == null)
			System.out.println("The list is empty.");
		else
		{
			while (curr != null)
			{
				System.out.println(curr.o);
				curr = curr.next;
			}
		}//end else
	}//end displayList()
	
}//end MyVector
