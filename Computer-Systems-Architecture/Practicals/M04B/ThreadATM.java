public class ThreadATM extends Thread
{
	int type;
	int amount;
	GlobalCustomer gc;

	public ThreadATM(int type, int amount, GlobalCustomer gc) //type: 0=withdraw 1=deposit
	{
		this.type = type;
		this.amount = amount;
		this.gc = gc;
	}

	public void run()
	{
			while (true)
			{
				if (type == 0)
				{
					try
					{
						//gc.setBalance(gc.getBalance() - amount);
						gc.update(0,amount);
						System.out.println(amount + " withdrawn.");
						System.out.println("Current account balance: " + gc.getBalance());
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						System.out.println("Withdrawal Interrupted");
					}
				}
				else if (type == 1)
				{
					try
					{
						//gc.setBalance(gc.getBalance() + amount);
						gc.update(1,amount);
						System.out.println(amount + " deposited.");
						System.out.println("Current account balance: " + gc.getBalance());
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						System.out.println("Deposit Interrupted");
					}
				}
			}
	}
}