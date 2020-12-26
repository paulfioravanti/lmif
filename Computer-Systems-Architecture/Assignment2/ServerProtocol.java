  import java.net.*;
   import java.io.*;
   import java.util.*;

    public class ServerProtocol
   {
		//create the states that the server will be in over the course
		//of the thread's life
      private static final int WAITING = 0;
      private static final int SENTWELCOME = 1;
      private static final int SENTLOGIN_OK = 2;
   	//initialize its state to waiting.
      private int state = WAITING;
		//create username variable here so that it can be sent to the ServerThread
		//when valid DRAW commands are called. 
		private String username = null;
   
		//intialize string arrays of possible server messages, and legal line colours.
      String[] messages = {"WELCOME This is Paul's CSA Assign2 Whiteboard Server", "LOGIN_OK", "DRAWMSG"};
      String[] validColours = {"red", "green", "blue", "white", "black"};
		
		/**
			Method to return the username associated with this thread.
			@return username the username associated with this thread.
		*/
		public String getUsername()
		{
			return username;
		}
		
		/**
			Method to set the username associated with this thread.
			@param username the username associated with this thread.
		*/
		public void setUsername(String username)
		{
			this.username = username;
		}
   	
		/**
			A method to parse in a command from a client, and send messages back 
			depending on the type of message received, and what commands the client
			has sent thus far.
			@param fromUser the command from the client.
		*/
       public String processInput(String fromUser)
      {
         String toUser = null;
      //once a connection is established with the client, the server will send a 
		//WELCOME message to the client and change its state to SENTWELCOME, demonstrating
		//that it is now waiting for the first command from the client.
         if (state == WAITING)
         {
            toUser = messages[0];
            state = SENTWELCOME;
         }
		//If the server has sent a WELCOME message, it now expects the user to login
		//If the server receives anything different from a LOGIN <username> command, it assumes
		//the client is rogue and disconnects.
		//1. Parses for 2 string tokens
		//2. Makes sure first token is a LOGIN command
		//3. Makes sure username only contains alphanumeric characters and/or numbers.
		//4. Makes sure the client's username is not already in use.
		//If the client passes these checks, a LOGIN_OK message is sent to the client and it
		//is allowed to continue.
		//Server state is changed to SENTLOGIN_OK.
         else if (state == SENTWELCOME)
         {
            StringTokenizer st = new StringTokenizer(fromUser);
            if (st.countTokens() == 2)
            {
               if (st.nextToken().equals("LOGIN"))
               {
                  String clientUsername = st.nextToken();
                  String usernamecheck = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                  boolean valid = true;          
                  for (int i = 0; i < clientUsername.length(); i++)
                  {	
                     boolean check = false;
                     for (int j = 0; j < usernamecheck.length(); j++)
                     {
                        if (clientUsername.charAt(i) == usernamecheck.charAt(j))
                           check = true;
                     }
                     if (check == false)
                     {
                        valid = false;
                        break;
                     }
                  }                  
               	
                  if (valid == true)
                  {
                  	if (!Server.getUsernames().contains(clientUsername))
							{
                     	toUser = messages[1];
								setUsername(clientUsername);
								Server.getUsernames().add(getUsername());
                     	state = SENTLOGIN_OK;
							}
							else
							{
								toUser = "ERROR: Supplied username already in use. Connection terminated by server.";
								state = WAITING;
							}
                  }
                  else
                  {
                     toUser = "ERROR: Username must contain only the letters A-Z, a-z, or digits 0-9. Connection terminated by server.";
                     state = WAITING;
                  }
               }
               else
               {
                  toUser = "ERROR: Cannot accept any commands before LOGIN complete.  Connection terminated by server.";
                  state = WAITING;
               }
            }
            else
            {
               toUser = "ERROR: Incorrect LOGIN command format - Usage: LOGIN <username>.  Connection terminated by server.";
               state = WAITING;
            }
         }
			//If the server has sent a LOGIN_OK message, it now expects the user to issue DRAW commands.
		//If the server receives anything different from a DRAW <x1> <y1> <x2> <y2> <colourname>
		// command, it assumes
		//the client is rogue and disconnects.
		//1. Parses for 6 string tokens
		//2. Makes sure first token is a DRAW command
		//3. Makes sure x and y values are integers
		//4. Makes sure the colour is legal.
		//5. Makes sure x and y values are between 0-499 each
		//If the client passes these checks, a DRAWMSG message is sent to the client and it
		//is allowed to continue.
		//Server state remains at SENTLOGIN_OK.
         else if (state == SENTLOGIN_OK)
         {
            StringTokenizer st = new StringTokenizer(fromUser);
            if (st.countTokens() == 6)
            {
               if (st.nextToken().equals("DRAW"))
               {
                  try
                  {
                     int x1 = Integer.parseInt(st.nextToken());
                     int y1 = Integer.parseInt(st.nextToken());
                     int x2 = Integer.parseInt(st.nextToken());
                     int y2 = Integer.parseInt(st.nextToken());
                     String color = st.nextToken();
                  	
                     if (x1 >= 0 && x1 < 500 && y1 >= 0 && y1 < 500 && x2 >= 0 && x2 < 500 && y2 >= 0 && y2 < 500)
                     {
                        boolean check = false;
                        for (int i = 0; i < validColours.length; i++)
                        {
                           if (color.equals(validColours[i]))
                           {
                              check = true;
                           }
                        }
                        if (check == true)
                        {
                           toUser = messages[2] + " " + getUsername() + " " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + color;
                        }
                        else
                        {
                           toUser = "ERROR: colour must be either blue, red, green, black, or white.\n Connection terminated by server.";
                           state = WAITING;
                        }
                     }
                     else
                     {
                        toUser = "ERROR: x and y coordinates must be in the range of 0-499.\n Connection terminated by server.";
                        state = WAITING;
                     }
                  }
                      catch (NumberFormatException e)
                     {
                        toUser = "ERROR: x and y coordinate must be integers.\n Connection terminated by server.";
                        state = WAITING;	
                     }
               }
               else
               {
                  toUser = "ERROR: Only valid DRAW commands are legal.\n Connection terminated by server.";
                  state = WAITING;
               }
            }
            else
            {
               toUser = "ERROR: Incorrect DRAW command format - Usage: DRAW <x1> <y1> <x2> <y2> <colourname>.\n Connection terminated by server.";
               state = WAITING;
            }	
         }
      
         return toUser;
      }
   } 
