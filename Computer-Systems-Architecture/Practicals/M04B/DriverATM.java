public class DriverATM
{
	public static void main(String[] args)
	{
		GlobalCustomer gc = new GlobalCustomer();
		ThreadATM t1 = new ThreadATM(0,100,gc);
		ThreadATM t2 = new ThreadATM(1,100,gc);
		new Thread(t1).start();
		new Thread(t2).start();
	}
}