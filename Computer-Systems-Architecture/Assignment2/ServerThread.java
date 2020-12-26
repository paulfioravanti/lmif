   import java.net.*;
   import java.io.*;
	import java.util.*;
	
	/**
		A class to represent a thread spawned off by the Server class.  Each client on the server
		has its own unique thread.
		
		@author Paul Fioravanti
		@version 25/5/05
	*/
    public class ServerThread extends Thread
   {
	//declare variables
      protected Socket s;
		protected int id;
		protected PrintWriter out;
		protected BufferedReader in;
   
	/**
		Constructor for a ServerThread object.
		@param s the client's socket
		@id the clients assigned id number.
	*/
       public ServerThread(Socket s, int id) 
      {
         this.s = s;
			this.id = id;
			try
			{
				out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			}
			catch (Exception e)
			{
				System.err.println("Could not create input or output stream");
			}
      }
		
	/**
		Method to return the ServerThread's PrintWriter
		@return out the ServerThread's PrintWriter
	*/
		public synchronized PrintWriter getOut()
		{
			return out;
		}
		
		/**
			Method used to broadcast that a DRAW message has been performed.
			Method is synchronized so that no race condition will exist between threads that want to 
			fire their DRAW messages at all the other clients.
			Although the list of active users is static, it may only be accessed
			through this method, and only by one thread at a time.
			The method calls an iterator down on the list of active users and sends each of
			them the DRAWMSG.
			@param toUsers the message to send to all clients connected to the server.
		*/
		public synchronized void broadcast(String toUsers)
		{
			Iterator i = Server.getActiveClients().iterator();
			while (i.hasNext())
			{
				if (getOut() != null)
				{
					ServerThread t = (ServerThread)i.next();
					t.getOut().println(toUsers);
					t.getOut().flush();
				}
			}
		}
		
		public synchronized void logoff()
		{
			Server.getActiveClients().remove(this);
		}
		
		/**
			Method to start the running of a ServerThread.
		*/
       public void run() 
      {
         try 
         {
            String inputLine, outputLine;
				//create new ServerProtocol object to deal with messages on this thread
            ServerProtocol sp = new ServerProtocol();
				//toUser output lines are generated from parsing the input
				//line through the protocol to decide what the server should do.
            outputLine = sp.processInput(null);
            out.println(outputLine);
         
            while ((inputLine = in.readLine()) != null) 
            {
               outputLine = sp.processInput(inputLine);
					//Tokenize the outputLine returned from the protocol
					//and deal with the results appropriately:
					//1. WELCOME and LOGIN_OK messages do not need to be
					//broadcast to all clients
					//2. DRAWMSGs are broadcast to all clients.
					//3. ERROR messages cause the server to break and exit.
					StringTokenizer st = new StringTokenizer(outputLine);
					String fromServer = st.nextToken();
					if (fromServer.equals("DRAWMSG"))
					{
						try
						{
							broadcast(outputLine);
						}
							catch (ConcurrentModificationException e)
							{
							
							}
					}
               else if (fromServer.equals("WELCOME") || fromServer.equals("LOGIN_OK"))
					{
						out.println(outputLine);
					}	
               else if (fromServer.equals("ERROR:"))
					{	
						out.println(outputLine);
                  break;
					}
            }
				//if server breaks, remove this thread from the list of activeClients and 
				//close all connections.
				logoff();
				Server.getUsernames().remove(sp.getUsername());
            out.close();
            in.close();
            s.close();
         
         } 
             catch (IOException e) 
            {
               
            }
      }
   }
