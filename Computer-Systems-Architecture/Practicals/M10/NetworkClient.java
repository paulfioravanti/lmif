import java.net.*;
import java.io.*;

public class NetworkClient
{
	public static void main(String[] args)
	{
		Socket sock;
		BufferedReader br;
		DataOutputStream os;
		try 
		{
			// ORIG: sock = new Socket("www.cis.unisa.edu.au", 80);
			sock = new Socket("localhost", 9999);
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			os = new DataOutputStream(sock.getOutputStream());
			if (sock != null && br != null && os != null)
			{
				os.writeBytes("GET /" + "\n\n");
				String response;
				
				while ((response = br.readLine()) != null)
				{
					System.out.println(response);
					if (response.indexOf("Ok") != -1)
						break;
				} 
			}
			os.close();
			br.close();
			sock.close();
		}
			catch (UnknownHostException e)
			{
				System.err.println("Unknown Host");
			}
			catch (IOException e)
			{	
				System.err.println("IO Exception");
			}
	}
}
