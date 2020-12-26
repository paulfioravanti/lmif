public class LabelForLoop
{
	public static void main(String [] args)
	{
		OUTER:
		for (int x=0; x<100; x++)
		{
			INNER:
			for (int y=0; y<100; y++)
			{
				System.out.println(x*y);
				if (x==73 && y==81) break OUTER;
			}
		}
	}
}