   import java.io.*;
   import java.net.*;
   import java.awt.event.*;
   import java.util.*;

/**
	A class to represent a client which connects to a server.
	
	@author Paul Fioravanti
	@version 23/5/05
*/
    public class Client implements ActionListener
   {
      private ClientUI ui;
   
      private String host;
      private int port;
      private String username;
      private boolean loggedIn = false;
		private boolean welcomeReceived = false;
		private boolean logInReceived = false;
   
      private Socket s;
      private PrintWriter toServer;
      private BufferedReader fromServer;
      private BufferedReader fromUser;
      private PrintWriter toUser;
		
		private String serverLine;
		
		/**
			Method to check whether a WELCOME message has been received by the client
			@return welcomeReceived - whether a WELCOME message has been received by the client
		*/
		public boolean welcomeReceived()
		{
			return welcomeReceived;
		}
		
		/**
			Method to set whether a WELCOME message has been received by the client
			@param welcomeReceived - whether a WELCOME message has been received by the client
		*/
		public void setWelcomeReceived(boolean welcomeReceived)
		{
			this.welcomeReceived = welcomeReceived;
		}
		
		/**
			Method to check whether a LOGIN_OK message has been received by the client
			@return logInReceived - whether a LOGIN_OK message has been received by the client
		*/
		public boolean logInReceived()
		{
			return logInReceived;
		}
		
		/**
			Method to set whether a LOGIN_OK message has been received by the client
			@param logInReceived - whether a LOGIN_OK message has been received by the client
		*/
		public void setLogInReceived(boolean logInReceived)
		{
			this.logInReceived = logInReceived;
		}
   
   /**
   	Method to see whether client has logged into server
   	@return loggedIn the status of the client
   */
       public boolean isLoggedIn()
      {
         return loggedIn;
      }
   
   /**
   	Method to set the login status of the client
   	@param loggedIn the state to change the client
   */
       public void setLoggedIn(boolean loggedIn)
      {
         this.loggedIn = loggedIn;
      }
   
   /**
   	Method to get the BufferedReader used to get messages from the user
   	@return fromUser the BufferedReader used to get messages from the user
   */
       public BufferedReader getFromUser()
      {
         return fromUser;
      }
   
   /**
   	Method to set the BufferedReader used to get messages from the user.
   	Method is used only once.
   */
       public void setFromUser()
      {
         fromUser = new BufferedReader(new InputStreamReader(System.in));
      }
   
   /**
   	Method to get the BufferedReader used to get messages from the server
   	@return fromServer the BufferedReader used to get messages from the server
   */
       public BufferedReader getFromServer()
      {
         return fromServer;
      }
   
   /**
   	Method to set the BufferedReader used to get messages from the server.
   	Method is used only once.
   	@throws IOException if BufferedReader cannot be set
   */
       public void setFromServer() throws IOException 
      {
         fromServer = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
      }
   
   /**
   	Method to get the PrintWriter used to send messages to the server.
   	@return toServer the PrintWriter used to send messages to the server.
   */
       public PrintWriter getToServer()
      {
         return toServer;
      }
   
   /**
   	Method to set the PrintWriter used to send messages to the server.
   	Method is used only once.
   	@throws IOException if PrintWriter cannot be set
   */
       public void setToServer() throws IOException
      {
         toServer = new PrintWriter(new OutputStreamWriter(getSocket().getOutputStream()));
      }
   
   /**
   	Method to get the socket used by the client.
   	@return s the socket
   */
       public Socket getSocket()
      {
         return s;
      }
   
   /**
   	Method to connect a socket to a port number belonging to a host.
   	@param host the server to connect to
   	@param port the port to connect to
   	@throws IOException if socket cannot be connected
   	@throws UnknownHostException if host cannot be found
   */
       public void setSocket(String host, int port) throws IOException
      {
         try
         {
            s = new Socket(host, port);
         }
             catch (UnknownHostException e) 
            {
               System.err.println("ERROR: Requested host is unavailable");
               System.err.println("Usage: java Client <hostname> <port> <username>");
               System.err.println("\t - where <hostname> is the internet hostname for the server");
               System.err.println("\t - where <port> is the TCP port number to connect to");
               System.err.println("\t - where <username> is the username to use");
               System.exit(1);
            }
      }
   
   /**
   	Method to get the username for this client.
   	@return username the username for this client
   */
       public String getUsername()
      {
         return username;
      }
   
   /**
   	Method to set the username for this client.
   	@param username the username to set for this client.
   */
       public void setUsername(String username)
      {
         this.username = username;
      }
   
   /**
   	Method to get the hostname this client is connected to.
   	@return host the hostname this client is connected to.
   */
       public String getHost()
      {
         return host;
      }
   
   /**
   	Method to set the hostname this client is connected to.
   	@param host the hostname to set
   */
       public void setHost(String host)
      {
         this.host = host;
      }
   
   /**
   	Method to get the port number this client is connected to.
   	@return port the port number this client is connected to.
   */
       public int getPort()
      {
         return port;
      }
   
   /**
   	Method to set the port number this client is connected to.
   	@param port the port number to set
   */
       public void setPort(int port)
      {
         this.port = port;
      }
   
   /**
   	Method to handle ActionEvents fired specifically from the Disconnect button.
   	Once Disconnect button is pressed, the client will close the socket connection
   	and disconnect.
   	@param e the ActionEvent fired from the Disconnect button.
   */
       public void actionPerformed(ActionEvent e)
      {
         Object source = e.getSource();
      
         if (source == ClientUI.disconnectButton)
         {
            try
            {
            //close as many connections as possible
            //most important being the actual socket
               getToServer().close();
               getSocket().close();
					setLoggedIn(false);
            }
                catch (IOException ex) 
               {
                  System.err.println("ERROR: IO Exception occured when attempting to close socket");
               //System.exit(1);
               }
         }
      }
   
   /**
   	Main method to start the program.
   	@param args the command line arguments
   	@throws IOException
   */
       public static void main(String[] args) throws IOException
      {
      //Test for incorrect arguments.
         try
         {
         	//test for incorrect number of arguments
            if (args.length != 3)
               throw new IllegalArgumentException("ERROR: Parameters must consist of three arguments");
         	
         	//port error checking
         	//1. ensure port is an integer
         	//2. ensure port number is over 1024
            int port = Integer.parseInt(args[1]);
            if (port < 1024)
               throw new NumberFormatException();
         		
         	//username checking
         	//1. ensure username contains only the letters A-Z, a-z, or digits 0-9
         	//compare against a check string
            String username = args[2];
            String usernamecheck = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            for (int i = 0; i < username.length(); i++)
            {	
               boolean check = false;
               for (int j = 0; j < usernamecheck.length(); j++)
               {
                  if (username.charAt(i) == usernamecheck.charAt(j))
                     check = true;
               }
               if (check == false)
                  throw new IllegalArgumentException("ERROR: Username must contain only the letters A-Z, a-z, or digits 0-9.");
            }
         	
         	//hostname checking doen when connection is attempted
            String host = args[0];
         	//Client is final so it can be accessed by inner thread class.
            final Client c = new Client();
         	//set the strings for the port, host, and username so they can be
         	//displayed in the UI.
            c.setHost(host);
            c.setPort(port);
            c.setUsername(username);
         	
         	//attempt to connect to the host and port
            c.setSocket(c.getHost(), c.getPort());
         
         	//Set up streams for reading to and writing from the server
         	//fromServer is declared final in order to be accessed by the inner class
            c.setToServer();
            c.setFromServer();
         					
         	//Set up streams for reading to the console
         	//Writing is unnecessary as it will be written directly to the UI debug output
            c.setFromUser();
         	
         	//UI is final so it can be accessed by the Thread inner class
            final ClientUI ui = new ClientUI(c);
            ui.setVisible(true);
         	
         	//Confirm on UI that server has been connected to
            ClientUI.debugOutput.append("Connected to " + c.getSocket().getInetAddress() + ": " + c.getSocket().getPort() + "\n");
         	
         	//Get output from server and display it to the user
         	//Use a seperate thread so output can be send and received simultaneously.
            Thread t = 
                   new Thread()
                  {	
		                public void run()
                     {
                        String serverLine;
								
                        try
                        {
                        //print server line to debugOutput on the UI when message recieved
                           while ((serverLine = c.getFromServer().readLine()) != null)
                           {
                              ClientUI.debugOutput.append(serverLine + "\n");
                              ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
                           
                              StringTokenizer st = new StringTokenizer(serverLine);
                           	
									//Checks to see if client has received WELCOME message
									//if it hasn't, and the client has not sent one, then server must
									//be corrupt so client exits.
									//If it has send a WELCOME message, welcomeReceived is set to true
									//and the process continues.
          							if (c.welcomeReceived() == false)
										{
											if (!st.nextToken().equals("WELCOME"))
											{
												try
                              		{
                                 		c.getToServer().close();
                                 		c.getSocket().close();
                                 		ClientUI.debugOutput.append("ERROR: Bad information from server: WELCOME message not received." + "\n");
                                 		ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
													c.setLoggedIn(false);
													return;
                              		}
                                  		catch (IOException ex)
                                 		{
                                    		System.err.println("ERROR: IO Exception occured when attempting to close socket");
                                 		}
											}
											else
											{
												c.setWelcomeReceived(true);
												continue;
											}
										}
										
									//Checks to see if client has received LOGIN_OK message
									//if it hasn't, and the client has not sent one, then server must
									//be corrupt so client exits.
									//If it has send a LOGIN_OK message, logInReceived is set to true
									//and the process continues.
										if (c.logInReceived() == false)
										{
											String fromServer = st.nextToken();
											if (fromServer.equals("ERROR:"))
											{
												return;
											}
											else if (!fromServer.equals("LOGIN_OK"))
											{
												try
                              		{
                                 		c.getToServer().close();
                                 		c.getSocket().close();
                                 		ClientUI.debugOutput.append("ERROR: Bad information from server: LOGIN_OK message not received." + "\n");
                                 		ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
													c.setLoggedIn(false);
													return;
                              		}
                                  		catch (IOException ex)
                                 		{
                                    		System.err.println("ERROR: IO Exception occured when attempting to close socket");
                                 		}
											}
											else
											{
												c.setLogInReceived(true);
												continue;
											}
										}                  
										//If a DRAWMSG is received from the server and the message was
                           	//not generated by this client, pass the information to the whiteboard
                           	//so it can draw it without affecting this client's settings.
										//Otherwise, message is from a corrupt server and client exits.
										if (st.nextToken().equals("DRAWMSG"))
                              {
											if (st.countTokens() != 6)
											{
												try
                              		{
                                 		c.getToServer().close();
                                 		c.getSocket().close();
                                 		ClientUI.debugOutput.append("ERROR: Bad information from server: DRAWMSG incorrectly formatted." + "\n");
                                 		ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
													c.setLoggedIn(false);
													return;
                              		}
                                  		catch (IOException ex)
                                 		{
                                    		System.err.println("ERROR: IO Exception occured when attempting to close socket");
                                 		}
											}
												
                                 String user = st.nextToken();
                                 int x1 = Integer.parseInt(st.nextToken());
                                 int y1 = Integer.parseInt(st.nextToken());
                                 int x2 = Integer.parseInt(st.nextToken());
                                 int y2 = Integer.parseInt(st.nextToken());
                                 String color = st.nextToken(); 
                              
                                 if (user.equals(c.getUsername()))
                                    continue;
                                 else
                                 {
                                    ui.drawLine(x1, y1, x2, y2, color);
                                 }
                              }
										else
										{
											try
                              		{
                                 		c.getToServer().close();
                                 		c.getSocket().close();
                                 		ClientUI.debugOutput.append("ERROR: Bad information from server: DRAWMSG message not received." + "\n");
                                 		ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
													c.setLoggedIn(false);
													return;
                              		}
                                  		catch (IOException ex)
                                 		{
                                    		System.err.println("ERROR: IO Exception occured when attempting to close socket");
                                 		}
										}
                           }
                        }
                            catch (NumberFormatException e)
                           {
                              try
                              {
                                 c.getToServer().close();
                                 c.getSocket().close();
                                 ClientUI.debugOutput.append("ERROR: Bad information from server: x and/or y coordinate(s) not of integer type." + "\n");
                                 ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
											c.setLoggedIn(false);
											return;
                              }
                                  catch (IOException ex)
                                 {
                                    System.err.println("ERROR: IO Exception occured when attempting to close socket");
                                 }
                           }
                        //this exception generated from pressing disconnect button.
                            catch (IOException e)
                           {
                              ClientUI.debugOutput.append("Connection closed by client." + "\n");
                              ClientUI.debugOutput.append("To reconnect or connect to another server, please quit this application and rerun." + "\n\n");
                              ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
										c.setLoggedIn(false);
                              return;
                           }
                     
                     //When server connection is closed, loop will end, append message,
                     //and exit along with the main thread.	
                        ClientUI.debugOutput.append("Connection closed by server." + "\n");
                        ClientUI.debugOutput.append("To reconnect or connect to another server, please quit this application and rerun." + "\n\n");
                        ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
								c.setLoggedIn(false);
                     	return;	
                     }
                  };
         	
         	//Priority of server-to-user thread is set one level higher than the main thread.
         	//This is done as a cautionary measure for running on logic's Solaris system.
            t.setPriority(Thread.currentThread().getPriority() + 1);
         	
         	//Start thread sending messages from server to user.
            t.start();

         	//checks to see if client is logged into server.  If not, LOGIN message
         	//is sent to server and loggedIn is set to true.
            if (c.isLoggedIn() == false)
            {
                  ClientUI.debugOutput.append("LOGIN " + c.getUsername() + "\n");
                  c.getToServer().println("LOGIN " + c.getUsername());
                  c.getToServer().flush();
                  c.setLoggedIn(true);
            }
         }
             catch (UnknownHostException e) 
            {
               System.err.println("ERROR: Requested host is unavailable");
               System.err.println("Usage: java Client <hostname> <port> <username>");
               System.err.println("\t - where <hostname> is the internet hostname for the server");
               System.err.println("\t - where <port> is the TCP port number to connect to");
               System.err.println("\t - where <username> is the username to use");
               System.exit(1);
            }
         
             catch (NumberFormatException e)
            {
               System.err.println("ERROR: Requested server port is invalid.");
               System.err.println("Port must be an integer and be over 1025");
               System.err.println("Usage: java Client <hostname> <port> <username>");
               System.err.println("\t - where <hostname> is the internet hostname for the server");
               System.err.println("\t - where <port> is the TCP port number to connect to");
               System.err.println("\t - where <username> is the username to use");
               System.exit(1);
            }
         	
             catch (IllegalArgumentException e) 
            {
               System.err.println(e.getMessage());
               System.err.println("Usage: java Client <hostname> <port> <username>");
               System.err.println("\t - where <hostname> is the internet hostname for the server");
               System.err.println("\t - where <port> is the TCP port number to connect to");
               System.err.println("\t - where <username> is the username to use");
               System.exit(1);
            }
				
				catch (ConnectException e)
				{
					System.err.println("ERROR: Unable to connect to desired socket.");
					System.err.println("Usage: java Client <hostname> <port> <username>");
               System.err.println("\t - where <hostname> is the internet hostname for the server");
               System.err.println("\t - where <port> is the TCP port number to connect to");
               System.err.println("\t - where <username> is the username to use");
               System.exit(1);
				}
         
             catch (Exception e)
            {
					e.printStackTrace(); //debug
               System.err.println("Usage: java Client <hostname> <port> <username>");
               System.err.println("\t - where <hostname> is the internet hostname for the server");
               System.err.println("\t - where <port> is the TCP port number to connect to");
               System.err.println("\t - where <username> is the username to use");
               System.exit(1);
            }  
      }
   }
