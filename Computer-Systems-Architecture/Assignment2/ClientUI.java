import javax.swing.*;
import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
	A class to represent a user interface for a shared whiteboard client
	
	@author Paul Fioravanti
	@version 15/5/05
*/
public class ClientUI extends JFrame implements ActionListener
{
	//declare variables
	private Client c;
	private ClientWhiteboard whiteboard;
	private Color color = Color.BLACK;
	private String colorString = "black";
	
	private JButton redButton;
	private JButton blueButton;
	private JButton greenButton;
	private JButton whiteButton;
	private JButton blackButton;
	
	//disconnectButton is static so it can be easily accessed from Client class
	//as that's where ActionEvents generated from it are handled.
	static JButton disconnectButton;
	
	//debugOutput is static much like System.out is static so it can be accessed
	//from any of the classes.
	static JTextArea debugOutput; 
	
	/**
		Constructor to initialize the user interface.
		Client is passed in so the UI can act as an intermediary between messages the 
		whiteboard wants to send to the client and vice versa
		@param c the Client
		@throws IOException
	*/
	public ClientUI(Client c) throws IOException
	{
		this.c = c;
	//Set program to close when shut down button clicked
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //sets title of ClientUI frame
      this.setTitle("Whiteboard Client v1.0");
	//Set size of the UI	
		this.setSize(new Dimension(650, 750));
	//Make this JFrame unresizeable
		this.setResizable(false);
	//Set the UI to be in the middle of the user's computer screen.	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		
		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;
		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;
		
		this.setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
	
	//JPanel on top of the JFrame
		JPanel contentPane = (JPanel)this.getContentPane();	
		
	//create JPanel to go in the north of the UI.
		JPanel northPanel = new JPanel(new GridLayout(2,1));
	//create JPanels for each row of the northPanel	
		JPanel northTopPanel = new JPanel(new FlowLayout());
		JPanel northBottomPanel = new JPanel(new FlowLayout());
		
	//Create the color label and color buttons and add them to the northTopPanel
	//set their background colours, tooltiptext, and add an actionListener to each
	//of the buttons
		JLabel colourChoiceLabel = new JLabel("Choose your line colour:");
		northTopPanel.add(colourChoiceLabel);
		
		redButton = new JButton("Red");
		redButton.setBackground(Color.RED);
		redButton.setToolTipText("Red");
		redButton.setOpaque(true);
      redButton.addActionListener(this);
		northTopPanel.add(redButton);
		
		greenButton = new JButton("Green");
		greenButton.setBackground(Color.GREEN);
		greenButton.setToolTipText("Green");
		greenButton.setOpaque(true);
      greenButton.addActionListener(this);
		northTopPanel.add(greenButton);
		
		blueButton = new JButton("Blue");
		blueButton.setBackground(Color.BLUE);
		blueButton.setToolTipText("Blue");
		blueButton.setOpaque(true);
      blueButton.addActionListener(this);
		northTopPanel.add(blueButton);
		
		whiteButton = new JButton("White");
		whiteButton.setBackground(Color.WHITE);
		whiteButton.setToolTipText("White");
		whiteButton.setOpaque(true);
      whiteButton.addActionListener(this);
		northTopPanel.add(whiteButton);
		
		blackButton = new JButton("Black");
		blackButton.setForeground(Color.WHITE);
		blackButton.setBackground(Color.BLACK);
		blackButton.setToolTipText("Black");
		blackButton.setOpaque(true);
      blackButton.addActionListener(this);
		northTopPanel.add(blackButton);
		
	//Add northTopPanel to the northPanel
		northPanel.add(northTopPanel);
	
	//Create JLabels and JTextFields to display username, hostname, port number
	//and add them to the northBottomPanel
	//set restraints on fields which have text, add tooltiptext, and set
	//them to be uneditable	
		JTextField userNameField = new JTextField(c.getUsername()); //set value to logged in username
		userNameField.setEditable(false);
		userNameField.setColumns(10);
		userNameField.setToolTipText("Your username");
		northBottomPanel.add(userNameField);
		
		JLabel atLabel = new JLabel("@");
		northBottomPanel.add(atLabel);
		
		JTextField hostNameField = new JTextField(c.getHost()); //set value to hostname
		hostNameField.setEditable(false);
		hostNameField.setColumns(20);
		hostNameField.setToolTipText("Foreign hostname");
		northBottomPanel.add(hostNameField);
		
		JLabel colonLabel = new JLabel(":");
		northBottomPanel.add(colonLabel);
		
		JTextField portNameField = new JTextField(String.valueOf(c.getPort())); //set value to portname
		portNameField.setEditable(false);
		portNameField.setColumns(4);
		portNameField.setToolTipText("Port number");
		northBottomPanel.add(portNameField);
		
		disconnectButton = new JButton("Disconnect");
		disconnectButton.setToolTipText("Disconnect from host");
		disconnectButton.addActionListener(c);
		northBottomPanel.add(disconnectButton);
		
	//Add the northBottomPanel to the northPanel
		northPanel.add(northBottomPanel);
	//Add the northPanel to the NORTH of the UI
		contentPane.add(northPanel, BorderLayout.NORTH);
		
	//Create debug output screen JTextArea and add it to the southPanel
		debugOutput = new JTextArea();
		JScrollPane debugOutputScrollPane = new JScrollPane(debugOutput);
      debugOutput.setEditable(false);
		debugOutput.setToolTipText("Debug Output");
		debugOutput.setRows(7);
	//Add the southPanel to the SOUTH of the UI.	
		contentPane.add(debugOutputScrollPane, BorderLayout.SOUTH);
		
		//create a new whiteboard object and pass an object of the UI to it for easy access
		//and add the whitboard to the center of the UI
		whiteboard = new ClientWhiteboard(this);
		contentPane.add(whiteboard, BorderLayout.CENTER);
	}
	
	/**
		Method to return the loggedIn status from the Client class
		@return the loggedIn status
	*/
	public boolean isLoggedIn()
	{
		return c.isLoggedIn();
	}
	
	/**
		Method to get the PrintWriter used to send messages to the server.
		Used by the whiteboard when it needs to send coordinates of a line
		to the server.
		@return c.getToServer the PrintWriter used to send messages to the server.
	*/
	public PrintWriter getToServer()
	{
		return c.getToServer();
	}
	
	/**
		Method to get the current color of the line.
		@return color the color of the line.
	*/
	public Color getColor()
	{
		return color;
	}
	
	/**
		Method to set the color of the line
		@param color the color of the line to set.
	*/
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	/**
		Method to get the string that represents the current color of the line.
		@return colorString the string that represents the current color of the line.
	*/
	public String getColorString()
	{
		return colorString;
	}
	
	/**
		Method to set the string that represents the current color of the line.
		@param colorString the string that represents the current color of the line to set.
	*/
	public void setColorString(String colorString)
	{
		this.colorString = colorString;
	}
	
	/**
		Method that passes information concerning lines drawn by other users on the server
		to the drawLine method on the whiteboard.
		If bad information from the server is received ie an illegal colour, the socket is
		closed and the client disconnects.
		@param x1 the start x coordinate of the line from the server
		@param y1 the start y coordinate of the line from the server
		@param x2 the end x coordinate of the line from the server
		@param y2 the end y coordinate of the line from the server
		@param color the color string from the server
		@throws IOException
	*/
	public void drawLine(int x1, int y1, int x2, int y2, String color) throws IOException
	{
		if (color.equals("red"))
			whiteboard.drawLine(x1, y1, x2, y2, Color.RED);
		else if (color.equals("blue"))
			whiteboard.drawLine(x1, y1, x2, y2, Color.BLUE);
		else if (color.equals("green"))
			whiteboard.drawLine(x1, y1, x2, y2, Color.GREEN);
		else if (color.equals("white"))
			whiteboard.drawLine(x1, y1, x2, y2, Color.WHITE);
		else if (color.equals("black"))
			whiteboard.drawLine(x1, y1, x2, y2, Color.BLACK);
		else
		{
			c.getToServer().close();
			c.getSocket().close();
			ClientUI.debugOutput.append("ERROR: Bad information from server: illegal colour attempted to be used." + "\n");
			ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
			c.setLoggedIn(false);
			return;
		}
	}
	
	/**
		Method to handle any events generated by the colour buttons.
		When a colour button is pressed, the colour of the line is changed
		and the string representing the color is changed appropriately
		@param e the ActionEvent generated from a colour button.
	*/
	public void actionPerformed(ActionEvent e)
	{
		//get the source of the ActionEvent
		Object source = e.getSource();
		
		if (source == redButton)
		{
			setColor(Color.RED);
			setColorString("red");
		}
		else if (source == blueButton)
		{
			setColor(Color.BLUE);
			setColorString("blue");
		}
		else if (source == greenButton)
		{
			setColor(Color.GREEN);
			setColorString("green");
		}
		else if (source == whiteButton)
		{
			setColor(Color.WHITE);
			setColorString("white");
		}
		else if (source == blackButton)
		{
			setColor(Color.BLACK);
			setColorString("black");
		}	
	}
}
