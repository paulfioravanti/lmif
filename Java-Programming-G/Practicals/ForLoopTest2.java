public class ForLoopTest2
{
	public static void main(String [] args)
	{
		int n, c;
		
		for (c=0; c<1000; c++)
		{
			n = (int)(Math.random()*1000);
			if (n==291) break;
		}
		
		System.out.print("It took " + c + " tries.");
	}
}