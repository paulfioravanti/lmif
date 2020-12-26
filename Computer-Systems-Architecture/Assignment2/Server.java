import java.net.*;
import java.io.*;
import java.util.*;

/**
	A class to represent a server for a networked whiteboard.  For every client that connects to
	the server, it spawns off a seperate thread to handle it.
	
	@author Paul Fioravanti
	@version 25/5/05
*/
public class Server
{
//Vector created to hold active client objects
//this Vector is accessed from any class that needs to broadcast
//a message to all clients connected to the server.
	static private Vector activeClients = new Vector();
	static private Vector usernames = new Vector();

/**
	Method to return the Set containing the list of active clients.
	Method is synchronized to make sure only one thread may access this 
	method at a time.
	@return activeClients the list of activeClients
*/
public static synchronized Vector getActiveClients()
{
	return activeClients;
}

/**
	Method to return the Vector containing the list of valid usernames.
	Method is synchronized to make sure only one thread may access this 
	method at a time.
	@return usernames the list of usernames
*/
public static synchronized Vector getUsernames()
{
	return usernames;
}

/**
	Method to add a username to the list of valid usernames.
	Method is synchronized to make sure only one thread may access this 
	method at a time.
	@param username the username to add to the list.
*/
public static synchronized void setUsername(String username)
{
	usernames.add(username);
}

/**
	Main method to start the server running.
	@param args the command line arguments
*/
	public static void main(String[] args)
	{
		int i = 0;
		//check for number of args; must be 1 only, be an integer, and be in the valid range.
		try
		{
			if (args.length != 1)
				throw new IllegalArgumentException("ERROR: Parameters must consist of one argument");
			//ensure port is an integer
			int port = Integer.parseInt(args[0]);
			//ensure port is over 1024
			if (port < 1024)
				throw new NumberFormatException();
			//create a new ServerSocket
			ServerSocket ss = new ServerSocket(port);
			
			boolean listening = true;
			//create a new ServerThread for every client that connects,
			//add it to the activeClients list, and start the thread.
			while (listening)
			{
				Socket s = ss.accept();
				ServerThread newClient = new ServerThread(s, i++);
				activeClients.add(newClient);
				newClient.start();
			}
         ss.close();
		}
			catch (NumberFormatException e)
         {
            System.err.println("ERROR: Requested server port is invalid.");
				System.err.println("Port must be an integer and be over 1024");
				System.err.println("Usage: java Server <port>");
				System.err.println("\t - where <port> is the TCP port number to listen for incoming connections on");
            System.exit(1);
         }
			
			catch (IllegalArgumentException e)
         {
            System.err.println(e.getMessage());
				System.err.println("Usage: java Server <port>");
				System.err.println("\t - where <port> is the TCP port number to listen for incoming connections on");
            System.exit(1);
         }
			
			catch (IOException e)
			{
				System.err.println("ERROR: Could not listen on desired port.");
				System.err.println("Usage: java Server <port>");
				System.err.println("\t - where <port> is the TCP port number to listen for incoming connections on");
            System.exit(1);
			}
		
			catch (Exception e)
			{
				System.err.println("Usage: java Server <port>");
				System.err.println("\t - where <port> is the TCP port number to listen for incoming connections on");
				System.exit(1);
			}
		
	}
}
