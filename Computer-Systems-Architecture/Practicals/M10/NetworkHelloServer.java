import java.net.*;
import java.io.*;

public class NetworkHelloServer
{
	public static void main(String[] args)
	{
		ServerSocket helloServer;
		Socket clientSock = null;
		BufferedReader br;
		PrintStream os;
		
		try 
		{
			helloServer = new ServerSocket(9999);
			clientSock = helloServer.accept();
			br = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
			os = new PrintStream(clientSock.getOutputStream());
				
				if (true)
				{
					os.println("hello world");
					os.close();
				} 
		}
			catch (IOException e)
			{	
				System.err.println("IO Exception");
			}
	}
}