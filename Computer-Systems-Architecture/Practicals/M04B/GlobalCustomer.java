public class GlobalCustomer
{
	private int balance = 1000;

	public int getBalance()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			System.out.println("Balacnce Enquiry interrupted");
		}
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}
		
	public synchronized void update(int type,int amount)
	{	
		int balance = getBalance();
		
		if (type == 0)
		{
			setBalance(balance - amount);
		}
		else if (type == 1)
		{
			setBalance(balance + amount);
		}
	}	

}

