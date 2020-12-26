/**===============================================================
		JPG Assignment 4 Q1 TrafficFrame.java, 2004
		I declare this to be my own work as defined by the
		University of South Australia's Academic misconduct policy.
		Paul Fioravanti
===============================================================*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TrafficFrame extends JFrame implements ActionListener
{
	//Declare components
	JPanel buttonPanel = new JPanel();
	JButton stopButton = new JButton("Stop");
	JButton cautionButton = new JButton("Caution");
	JButton goButton = new JButton("Go");
	JButton exit = new JButton("Exit");
	TrafficLightPanel tlp = new TrafficLightPanel();
	Color darkGray = Color.DARK_GRAY;
	
	public static void main(String[] args)
	{
		//Create frame
		TrafficFrame frame = new TrafficFrame();
		frame.setTitle("Java G Traffic Lights");
		frame.setSize(600,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		//Center frame within screen
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fs = frame.getSize();
		int x = (ss.width - fs.width)/2;
		int y = (ss.height - fs.height)/2;
		frame.setLocation(x,y);
		
	}//end main

//------------------------------------------------------------------
	
	public TrafficFrame() //defines properties of frame
	{
		Container frm = getContentPane();
		frm.setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(1,4));
		buttonPanel.add(stopButton);
		buttonPanel.add(cautionButton);
		buttonPanel.add(goButton);
		buttonPanel.add(exit); 
		frm.add(buttonPanel,BorderLayout.SOUTH);
		frm.add(tlp);
		
		//add ActionListeners to buttons
		stopButton.addActionListener(this);
		cautionButton.addActionListener(this);
		goButton.addActionListener(this);
		exit.addActionListener(this);
	}//end TrafficFrame()
	
//-----------------------------------------------------------
	
	public class TrafficLightPanel extends JPanel
	{
		private Color top, middle, bottom;
		
		public TrafficLightPanel()
		{
			top = Color.RED;
			middle = Color.YELLOW;
			bottom = Color.GREEN;
			setLayout(new FlowLayout());
		}
		
		public void changeColors(Color top, Color middle, Color bottom)
		{
			this.top = top;
			this.middle = middle;
			this.bottom = bottom;
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillRect(225,50,150,350);
			g.setColor(top);
			g.fillOval(235,55,130,110);
			g.setColor(middle);
			g.fillOval(235,170,130,110);
			g.setColor(bottom);
			g.fillOval(235,285,130,110);
		}
	}//end TrafficLightPanel

//-------------------------------------------------------------

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==exit)
			System.exit(0);
		if (e.getSource()==stopButton)
			tlp.changeColors(Color.RED, darkGray, darkGray);
		if (e.getSource()==cautionButton)
			tlp.changeColors(darkGray, Color.YELLOW, darkGray);
		if (e.getSource()==goButton)
			tlp.changeColors(darkGray, darkGray, Color.GREEN);
		tlp.repaint();
	}	
}//end class TrafficFrame
