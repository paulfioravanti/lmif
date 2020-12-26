public class Grade
{
	public static void main(String [] args)
	{
		int mark = 76;
		// ORIG: char grade;
    // Grade.java:16: error: variable grade might not have been initialized
		char grade = ' ';
		
		if (mark < 50) grade = 'F';
			else
			if (mark >= 50) grade = 'P';
				else
				if (mark >= 65) grade = 'C';
					else
					if (mark >- 75) grade = 'D';
		
		System.out.println(mark + " " + grade);
	}

}  
