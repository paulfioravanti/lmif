class ExtendedThread implements Runnable
{
	int name;

	ExtendedThread(int name)
	{
		this.name = name;
	}
	//entry point for second thread
	public void run()
	{
		int i = 0;

		while (true)
		{
			System.out.println("Thread " + name + " - Count " + i);
			i++;
		}
	}

	public static void main(String[] args)
	{
		//Display message for when less than 1 args is entered at command line
		if (args.length < 1)
		{
			System.out.println("Usage: java ExtendedThread <numberOfThreads>");
			System.out.println("Enter integer value for <numberOfThreads>");
			System.exit(1);
		}		
		
		for (int i = Integer.parseInt(args[0]); i > 1; i--)
		{
			ExtendedThread newThread = new ExtendedThread(i); //create a new thread
			new Thread(newThread).start(); //start second thread
		}

		//first (main) thread starts
		int i = 0;

		while (true)
		{
			System.out.println("Thread 1 - Count " + i);
			i++;
		}
	}
}