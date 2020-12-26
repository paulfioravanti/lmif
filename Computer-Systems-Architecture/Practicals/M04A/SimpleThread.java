class SimpleThread implements Runnable
{
	//entry point for second thread
	public void run()
	{
		int i = 0;

		while (true)
		{
			System.out.println("Thread Two - Count=" + i);
			i++;
		}
	}

	public static void main(String[] args)
	{
		SimpleThread threadTwo = new SimpleThread(); //create a new thread
		new Thread(threadTwo).start(); //start second thread
		
		//first (main) thread starts
		int i = 0;

		while (true)
		{
			System.out.println("Thread One - Count=" + i);
			i++;
		}
	}
}