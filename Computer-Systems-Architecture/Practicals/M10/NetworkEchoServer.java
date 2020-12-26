import java.net.*;
import java.io.*;

public class NetworkEchoServer extends Thread
{
	BufferedReader br;
	PrintStream os;
	Socket clientSock;
	String line;
	
	public NetworkEchoServer(Socket clientSock)
	{
		this.clientSock = clientSock;
	}

	public void run()
	{
		try
		{
			br = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
			os = new PrintStream(clientSock.getOutputStream());
			os.println("Enter 'exit' to exit");
			
			while (true)
			{
				line = br.readLine();
				if (line == null || line.trim().equalsIgnoreCase("exit"))
					break;
				else
					os.println("From Server: " + line);
			}
			clientSock.close();
		}
			catch (IOException e)
			{
				System.err.println("IO Exception");
			}
	}

	public static void main(String[] args)
	{
		try 
		{
			ServerSocket helloServer = new ServerSocket(9999);
			while (true)
			{
				Socket clientSock = helloServer.accept();
				new NetworkEchoServer(clientSock).start();
			}	 
		}
			catch (IOException e)
			{	
				System.err.println("IO Exception");
			}
	}
}