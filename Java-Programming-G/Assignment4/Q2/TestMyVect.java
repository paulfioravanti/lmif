/**===============================================================
		JPG Assignment 4 Q2 TestMyVect.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

import java.util.*;
import java.io.*;

public class TestMyVect
{
	public static void main(String[] args)
	{
		MyVector mv = new MyVector();
		
		//Demonstrates that the list is initially empty using size() method
		System.out.println("\n-- size() method used to return the number of elements in the list");
		System.out.println("MyVector Initial size: " + mv.size() + "\n");
		
		System.out.println("**Begin lower null boundary testing for methods that throw exceptions**\n");
		
		//Attempts to remove the last element with removeElement() method
		//but since no elements are yet in the list, exception is thrown.
		System.out.println("-- removeElement() method attempt to remove at null");
		try
		{
			mv.removeElement();
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot remove element.\n");
		}
		
		//Attempts to remove the element located at 0, which would be the
		//first element if a list was present.  No elements are yet in the
		//list, so an exception is thrown
		System.out.println("-- removeElementAt() method attempt to remove element at 0");
		try
		{
			mv.removeElementAt(0);
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot remove element at that location.\n");
		}
		
		//Attempts to return the next element with nextElement() method
		//but since no elements are yet in the list, exception is thrown.
		System.out.println("-- nextElement() method attempt to return next element");
		try
		{
			mv.nextElement();
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot return next element.\n");
		}
		
		//Attempts to return the element located at 0, which would be the
		//first element if a list was present.  No elements are yet in the
		//list, so an exception is thrown
		System.out.println("-- elementAt() method attempt to return element at 0");
		try
		{
			mv.elementAt(0);
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot return element at that location.\n");
		}
		
		//Attempts to return the element located at 0, which would be the
		//first element if a list was present, then remove it.  No elements are yet in the
		//list, so an exception is thrown
		System.out.println("-- takeElementAt() method attempt to return and delete element at 0");
		try
		{
			mv.takeElementAt(0);
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot return and delete element at that location.\n");
		} 
		
		System.out.println("**End lower null boundary testing**\n");
		
		System.out.println("**Begin inner boundary method testing**\n");
		
		System.out.println("-- addElement() method used to add 7 objects of different types to the list\n");
		
		//store 7 objects of various types via addElement() method
		mv.addElement(new Integer(1));
		mv.addElement(new Double(2.0));
		mv.addElement(new Character('A'));
		mv.addElement(new String("ABC"));
		mv.addElement(new String("DEF"));
		mv.addElement(new Double(3.0));
		mv.addElement(new Character('C'));
		
		System.out.println("-- size() method used to return the number of elements in the list");
		System.out.println("MyVector size after 7 additions: " + mv.size() + "\n");
		
		//user-defined displayList() method returns list after 7 additions
		mv.displayList();
		
		System.out.println("\n-- insertElementAt() method used to add 3 more objects of different types to positions 1, 3, and 5\n");
		
		//adds another 3 elements at specific places in the vector using insertElementAt() method
		mv.insertElementAt(new Integer(3),1);
		mv.insertElementAt(new Double(4.0),3);
		mv.insertElementAt(new Character('B'),5);
		
		//Shows that there are now 10 elements in the vector using size() method
		System.out.println("-- size() method used to return the number of elements in the list");
		System.out.println("MyVector size after 10 additions: " + mv.size() + "\n");
		
		//user-defined displayList() method returns list after 10 additions
		mv.displayList();
		
		//demonstrates removeElementAt() method which in this case removes elements 2 and 7
		System.out.println("\n-- removeElementAt() method used to delete elements at positions 2 and 7\n"); 
		try
		{
			mv.removeElementAt(2);
			mv.removeElementAt(7);			
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot remove element at that location.\n");
		} 
		
		//Shows that there are now 8 elements in the vector using size() method
		System.out.println("-- size() method used to return the number of elements in the list");
		System.out.println("MyVector size after 2 deletions via removeElementAt() method: " + mv.size() + "\n");
		
		//user-defined displayList() method returns list after 2 deletions via removeElementAt() method
		mv.displayList();
		
		//demonstrates the removeElement() method which removes the last two elements on the list
		System.out.println("\n-- removeElement() method used to delete last two elements on the list\n");
		try
		{
			mv.removeElement();
			mv.removeElement();			
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot remove next element.\n");
		}
		
		//Shows that there are now 6 elements in the vector using size() method
		System.out.println("-- size() method used to return the number of elements in the list");
		System.out.println("MyVector size after 2 more deletions via removeElement() method: " + mv.size() + "\n");
		
		//user-defined displayList() method returns list after 2 more deletions via removeElement() method
		mv.displayList();
		
		//demonstrates takeElementAt() method by returning elements at 2 and 3 and deleting them
		System.out.println("\n-- takeElementAt() method used to return elements 2 and 3 and delete them\n");
		try
		{
			System.out.println("Element taken is: " + mv.takeElementAt(2));
			System.out.println("Element taken is: " + mv.takeElementAt(3) + "\n");
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot return and delete element at that location.\n");
		}
		
		//System.out.println("**Note that due to the takeElementAt() method returning after it executes, Character 'B' becomes the object at position 3 after the first deletion**\n");
		
		//Shows that there are now 4 elements in the vector using size() method
		System.out.println("-- size() method used to return the number of elements in the list");
		System.out.println("MyVector size after 2 more deletions via takeElementAt() method: " + mv.size() + "\n");
		
		//user-defined displayList() method returns list after 2 more deletions via takeElementAt() method
		mv.displayList();
		
		//Demonstrates elementAt() method by returning the element at positions 3 and 1
		System.out.println("\n-- elementAt() method used to return elements 3 and 1");
		try
		{
			System.out.println("\nElement 3 is: " + mv.elementAt(3));
			System.out.println("Element 1 is: " + mv.elementAt(1));			
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot return element at that location.\n");
		}
		
		//Demonstrates the nextElement() method by returning the element at
		//position 2
		System.out.println("\n-- nextElement() method used to return the next element from last elementAt() method call, which left us at element 1 (3)");
		try
		{
			System.out.println("\nNext element is: " + mv.nextElement());			
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot return next element.\n");
		}
		
		System.out.println("\n**End inner boundary method testing**");
		
		System.out.println("\n**Begin higher boundary testing for methods that throw exceptions except removeElement(),\nwhich needs none as it cannot go past the end of the list**\n");	
		
		//Demonstrates exception handling of removeElementAt() method by attempting
		//to remove element 4, an element that does not exist
		System.out.println("-- removeElementAt() method attempt to remove element at position 4");
		try
		{
			mv.removeElementAt(4);
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot remove element.\n");
		} 
		
		//Shows that there are still 4 elements in the vector using size() method
		System.out.println("-- size() method used to return the number of elements in the list");
		System.out.println("MyVector size after attempt to remove element 4 via removeElementAt() method: " + mv.size() + "\n");
		
		//user-defined displayList() method returns list showing no elements removed
		mv.displayList();
		
		//Demonstrates exception handling of nextElement() method by first using the elementAt() method
		//to move to element 3 (the last element in the list), and then attempting to return element
		//4, and element that does not exist.
		System.out.println("\n-- nextElement() method attempt to return element at position 4");
		System.out.println("**Note: uses elementAt() method to initially get to position 3, the last element of the list**");
		try
		{
			mv.elementAt(3);
			mv.nextElement();
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot return next element.\n");
		}
		
		//Demonstrates exception handling of elementAt() method by attempting
		//to return element 10, an element that does not exist 
		System.out.println("-- elementAt() method attempt to return element at position 10");
		try
		{
			mv.elementAt(10);
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot return element at that location.\n");
		}
		
		//Demonstrates exception handling of takeElementAt() method by attempting
		//to remove element 6, an element that does not exist 
		System.out.println("-- takeElementAt() method attempt to return and delete element at position 6");
		try
		{
			mv.takeElementAt(6);
		}
		catch (NoElementPresentException e)
		{
			System.out.println(e + " - cannot return and delete element at that location.\n");
		}
		
		//user-defined displayList() method returns final element list
		mv.displayList();
		
		System.out.println("\nMyVector size: " + mv.size());
		
		System.out.println("\n**End higher boundary testing**");
	}//end main
	
}//end TestMyVect
