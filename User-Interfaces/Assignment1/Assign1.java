//package unisa.cis;

   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.*;
   import java.util.*;

/**
	User Interfaces Assignment 1
	@version 1.0 22nd March, 2005
	@author Paul Fioravanti
*/
    public class Assign1 extends JFrame implements ActionListener
   {
   //class Globals
      static String[] clowns = {"Krusty", "BoBo", "Pennywise", "John Howard", "Stan Laurel", "Groucho Marx", "Krusty's Brother", "Sideshow Bob", "Yet Another", "Bill Gates", "Steve Jobs", "Thomas Watson", "Thomas Ford", "Bart Simpson", "Lisa Simpson", "Homer Simpson", "Weasel Holden", "Outtof Names", "Macy Parade", "Rock Hudson"};
   
   //gets size of frame -- used to set defined middle column of step1 frame
      Dimension frameSize1 = this.getSize();
   
   //JPanel on top of the JFrame
      JPanel contentPane;
   
   //Previous, Next buttons and the buttonPanel they're on
      JPanel buttonPanel;
      JButton prevButton;
      JButton nextButton;
   
   //Step 1 panel with its GridBagLayout
      JPanel step1Panel;
      GridBagLayout gridBagLayout = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
   
   //Step 1 top labels - Step1 and Pick Your Location
      JLabel stepLabel;
      JLabel locLabel;
   
   //Step 1 buttons x5
      JRadioButton backyardButton;
      JRadioButton fastFoodButton;
      JRadioButton parkButton;
      JRadioButton hallButton;
      JRadioButton pubButton;
		
	//Step 2 panel with its GridBagLayout
      JPanel step2Panel;
      GridBagLayout gridBagLayout2 = new GridBagLayout();
      GridBagConstraints c2 = new GridBagConstraints();
		
	//Step 2 top labels - Step2 and Pick Your Options
      JLabel step2Label;
      JLabel pickLabel;
	
	//Step2 buttons x10
		JCheckBox balloonsBox;
		JCheckBox streamersBox;
		JCheckBox dwarfTossBox;
		JCheckBox bouncersYellowBox;
		JCheckBox cakeBox;
		JCheckBox boozeBox;
		JCheckBox bouncersRedBox;
		JCheckBox strippersBox;
		JCheckBox fizzyWaterBox;
		JCheckBox armaliteBox;

	//Bonus marks components
		JLabel bonusLabel;
		JSpinner bonusSpinner;
		SpinnerDateModel bonusDateModel;
		//JSpinner.DateEditor bonusDateSpinner;
	
	//Step 3 panel with its GridBagLayout
   	JPanel step3Panel;
     	GridBagLayout gridBagLayout3 = new GridBagLayout();
      GridBagConstraints c3 = new GridBagConstraints();
		
	//Step 3 top labels - Step3 and Select your Clown
      JLabel step3Label;
      JLabel clownLabel;
		
	//Step 3 JList for the clown list, JScrollPanel to put the list in, and Clown image
		JList list;
		JScrollPane scrollPane;
		JLabel clownPicLabel;
		Icon clownImage;
   
   //these are the only two specified fonts used in the solution
      Font largeFont = new Font("Default", Font.BOLD, 20);
      Font smallFont = new Font("Default", Font.BOLD, 14);
   
   /**
   	Constructor for objects of class Assign1
   */
       public Assign1()
      {
      //Set program to close when shut down button clicked
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //sets title of Assign1 frame
         this.setTitle("Kids Party Planner");
      //Retrieve window's content pane and assign it to a JPanel
         contentPane = (JPanel)this.getContentPane();
 
      //Assign FlowLayout on the buttonPanel
         buttonPanel = new JPanel(new FlowLayout());
      //set its background to orange
         buttonPanel.setBackground(Color.ORANGE);
			
      //set properties for Previous and Next buttons and add them to the buttonPanel 
         prevButton = new JButton("Previous");
         prevButton.setOpaque(true);
         prevButton.setBackground(Color.PINK);
		//register prevButton
			prevButton.addActionListener(this);
         buttonPanel.add(prevButton);
			
         nextButton = new JButton("Next");
         nextButton.setOpaque(true);
         nextButton.setBackground(Color.PINK);
      //register nextButton 
         nextButton.addActionListener(this);
         buttonPanel.add(nextButton);
      
      //add buttonPanel to the JPanel content pane
         contentPane.add(buttonPanel, BorderLayout.SOUTH);
      
      //assign a GridLayout to the JPanel which will contain the JRadioButtons
         step1Panel = new JPanel(gridBagLayout);
      
      //Create JLabel for 'Step 1' area, set its properties, and add it to the step1 panel
         stepLabel = new JLabel("STEP 1", JLabel.LEFT);
         stepLabel.setOpaque(true);
         //stepLabel.setBackground(Color.LIGHT_GRAY);  //no need as is default JVM colour
         stepLabel.setFont(largeFont); //sets assignment specific font
			c.fill = GridBagConstraints.BOTH;
         c.weightx = 1;
         c.weighty = 0;
      //add to position (0,0)
         c.gridx = 0;
         c.gridy = 0;
         step1Panel.add(stepLabel, c);
      
      //Create JLabel for 'Pick Your Location' area, set its properties, and add it to the step1 panel
         locLabel = new JLabel("Pick your location", JLabel.RIGHT);
         locLabel.setOpaque(true);
         //locLabel.setBackground(Color.LIGHT_GRAY);  //no need as is default JVM colour
         locLabel.setFont(smallFont); //sets assignment specific font
			c.fill = GridBagConstraints.BOTH;
         c.weightx = 1;
         c.weighty = 0;
      //add to position (1,0)
         c.gridx = 1;
         c.gridy = 0;
         step1Panel.add(locLabel, c);
      
      //Create JRadio Buttons for the other 5 components and add them to the step1 panel
         backyardButton = new JRadioButton("Backyard");
         backyardButton.setOpaque(true);
         backyardButton.setBackground(Color.BLUE);
         c.fill = GridBagConstraints.BOTH; 
         c.weightx = 0;
         c.weighty = 1;
         c.gridx = 0;
         c.gridy = 1;
         step1Panel.add(backyardButton, c);
      
         fastFoodButton = new JRadioButton("Fast Food Joint");
         fastFoodButton.setOpaque(true);
         fastFoodButton.setBackground(Color.RED);
         c.fill = GridBagConstraints.BOTH; 
         c.weightx = 0;
         c.weighty = 1;
         c.gridx = 1;
         c.gridy = 1;
         step1Panel.add(fastFoodButton, c);
      
         parkButton = new JRadioButton("Park");
         parkButton.setOpaque(true);
         parkButton.setBackground(Color.PINK);
         c.fill = GridBagConstraints.BOTH; 
         c.weightx = 0;
         c.weighty = 1;
         c.gridx = 0;
         c.gridy = 2;
         step1Panel.add(parkButton, c);
      
         hallButton = new JRadioButton("Local Hall");
         hallButton.setOpaque(true);
         hallButton.setBackground(Color.YELLOW);
         c.fill = GridBagConstraints.BOTH; 
         c.weightx = 0;
         c.weighty = 1;
         c.gridx = 1;
         c.gridy = 2;
         step1Panel.add(hallButton, c);
      
         pubButton = new JRadioButton("Local Pub Behind the Dumpster");
         pubButton.setOpaque(true);
         pubButton.setBackground(Color.ORANGE);
         //this component is the longest string on the left, so set a length contraint on it
         //to make a line down the middle
         pubButton.setPreferredSize(new Dimension((frameSize1.width + 250) /2, pubButton.getPreferredSize().height)); //put this line in other buttons if have time
         c.fill = GridBagConstraints.BOTH; 
         c.weightx = 0;
         c.weighty = 1;
         c.gridx = 0;
         c.gridy = 3;
         step1Panel.add(pubButton, c);
			
			JPanel p = new JPanel();
			p.setBackground(Color.WHITE);
			c.gridx = 1;
			c.gridy = 3;
			step1Panel.add(p, c);
      
      //Create a ButtonGroup and add all the JRadioButtons to it so that 2 butttons cannot be 
      //selected at once
         ButtonGroup group = new ButtonGroup();
         group.add(backyardButton);
         group.add(fastFoodButton);
         group.add(parkButton);
         group.add(hallButton);
         group.add(pubButton);
      	
      //assign a GridLayout to the JPanel which will contain the JCheckBoxes
         step2Panel = new JPanel(gridBagLayout2);
			//step2Panel.setBackground(Color.LIGHT_GRAY);
      
      //Create JLabel for 'Step 2' area, set its properties, and add it to the step2 panel
         step2Label = new JLabel("STEP 2", JLabel.LEFT);
         step2Label.setOpaque(true);
         //step2Label.setBackground(Color.LIGHT_GRAY); //no need as is default JVM colour
         step2Label.setFont(largeFont); //sets assignment specific font
         c2.fill = GridBagConstraints.BOTH;
         c2.weightx = 2;
			c2.gridwidth = 2;
      //add to position (0,0)
         c2.gridx = 0;
         c2.gridy = 0;
         step2Panel.add(step2Label, c2);
      
      //Create JLabel for 'Pick Your Options' area, set its properties, and add it to the step2 panel
         pickLabel = new JLabel("Pick your options", JLabel.RIGHT);
         pickLabel.setOpaque(true);
         //pickLabel.setBackground(Color.LIGHT_GRAY); //no need as is default JVM colour
         pickLabel.setFont(smallFont); //sets assignment specific font
         c2.weightx = 1;
			c2.gridwidth = 2;
      //add to position (1,0)
         c2.gridx = 2;
         c2.gridy = 0;
         step2Panel.add(pickLabel, c2);
      
      //Create JCheckBoxes for the other 10 components and add them to the step2 panel
         balloonsBox = new JCheckBox("Balloons");
         balloonsBox.setOpaque(true);
         balloonsBox.setBackground(Color.BLUE);
         c2.fill = GridBagConstraints.VERTICAL; 
         c2.weightx = 1;
         c2.weighty = 1;
			c2.gridwidth = 1;
         c2.gridx = 0;
         c2.gridy = 1;
         step2Panel.add(balloonsBox, c2);
			
			streamersBox = new JCheckBox("Streamers");
         streamersBox.setOpaque(true);
         streamersBox.setBackground(Color.YELLOW);
         c2.fill = GridBagConstraints.NONE; 
         c2.gridx = 1;
         c2.gridy = 1;
         step2Panel.add(streamersBox, c2);
			
			dwarfTossBox = new JCheckBox("Dwarf Toss Machine");
         dwarfTossBox.setOpaque(true);
         dwarfTossBox.setBackground(Color.CYAN);
         c2.fill = GridBagConstraints.NONE; 
         c2.gridx = 2;
         c2.gridy = 1;
         step2Panel.add(dwarfTossBox, c2);
			
			bouncersYellowBox = new JCheckBox("Bouncers");
         bouncersYellowBox.setOpaque(true);
         bouncersYellowBox.setBackground(Color.YELLOW);
			c2.gridheight = 2;
         c2.gridx = 3;
         c2.gridy = 1;
         step2Panel.add(bouncersYellowBox, c2);
			
			cakeBox = new JCheckBox("Cake");
         cakeBox.setOpaque(true);
         cakeBox.setBackground(Color.RED);
         c2.fill = GridBagConstraints.BOTH; 
			c2.gridheight = 1;
         c2.gridx = 0;
         c2.gridy = 2;
         step2Panel.add(cakeBox, c2);
			
			boozeBox = new JCheckBox("Booze");
         boozeBox.setOpaque(true);
         boozeBox.setBackground(Color.ORANGE);
         c2.gridx = 1;
         c2.gridy = 2;
         step2Panel.add(boozeBox, c2);
			
			bouncersRedBox = new JCheckBox("Bouncers");
         bouncersRedBox.setOpaque(true);
         bouncersRedBox.setBackground(Color.RED);
         c2.gridx = 2;
         c2.gridy = 2;
         step2Panel.add(bouncersRedBox, c2);
			
			strippersBox = new JCheckBox("Strippers");
         strippersBox.setOpaque(true);
         strippersBox.setBackground(Color.GREEN);
         c2.gridx = 0;
         c2.gridy = 3;
         step2Panel.add(strippersBox, c2);
			
			fizzyWaterBox = new JCheckBox("Fizzy Water");
         fizzyWaterBox.setOpaque(true);
         fizzyWaterBox.setBackground(Color.WHITE);
         c2.gridx = 1;
         c2.gridy = 3;
         step2Panel.add(fizzyWaterBox, c2);
			
			armaliteBox = new JCheckBox("Armalite AR10");
         armaliteBox.setOpaque(true);
         armaliteBox.setBackground(Color.BLUE);
         c2.gridx = 2;
         c2.gridy = 3;
			c2.gridwidth = 2;
         step2Panel.add(armaliteBox, c2);
			
			//Bonus components
			bonusLabel = new JLabel("Implement component below for bonus marks", JLabel.LEFT);
         pickLabel.setOpaque(true);
         c2.gridx = 0;
         c2.gridy = 4;
			c2.gridwidth = 4;
         step2Panel.add(bonusLabel, c2);
			
			bonusDateModel = new SpinnerDateModel();
			bonusSpinner = new JSpinner(bonusDateModel);
			//bonusDateSpinner = new JSpinner.DateEditor(bonusSpinner);
			c2.gridx = 0;
         c2.gridy = 5; 
			step2Panel.add(bonusSpinner, c2);
			
			//assign a GridLayout to the JPanel which will contain the JCheckBoxes
         step3Panel = new JPanel(gridBagLayout3);
			step3Panel.setBackground(Color.YELLOW);
      
      //Create JLabel for 'Step3' area, set its properties, and add it to the step3 panel
         step3Label = new JLabel("STEP 3", JLabel.LEFT);
         step3Label.setOpaque(true);
         //step3Label.setBackground(Color.LIGHT_GRAY); //no need as is default JVM colour
         step3Label.setFont(largeFont); //sets assignment specific font
         c3.fill = GridBagConstraints.BOTH; //necessary
			c3.gridwidth = GridBagConstraints.RELATIVE;
         c3.weightx = 1; //necessary
			c3.weighty = 0;
         c3.gridx = 0;
         c3.gridy = 0;
         step3Panel.add(step3Label, c3);
      
      //Create JLabel for 'Select Your Clown' area, set its properties, and add it to the step3 panel
         clownLabel = new JLabel("Select Your Clown", JLabel.RIGHT);
         clownLabel.setOpaque(true);
         //clownLabel.setBackground(Color.LIGHT_GRAY); //no need as is default JVM colour
         clownLabel.setFont(smallFont); //sets assignment specific font
			c3.gridwidth = GridBagConstraints.REMAINDER;
			c3.weightx = 0;
         c3.gridx = 1;
         c3.gridy = 0;
         step3Panel.add(clownLabel, c3);
			
		//Create JScrollPane and place JScrollPane on top of that, and add it to the step3 panel
			list = new JList(clowns);
			scrollPane = new JScrollPane(list);
			c3.weighty = 1; //necessary
			c3.weightx = 1;
			c3.gridwidth = GridBagConstraints.RELATIVE;
         c3.gridx = 0;
         c3.gridy = 1;
         step3Panel.add(scrollPane, c3);
			
		//Create Image for clown image and add it to the step3 panel.
			try
			{	
				clownImage = new ImageIcon("it.gif");
				//clownPicLabel = new JLabel(clownImage);
			}
			catch (Exception e)
			{
				clownLabel.setText("Clown Image");
			}
			c3.fill = GridBagConstraints.VERTICAL;
			c3.gridwidth = GridBagConstraints.REMAINDER;
			c3.weighty = 0;
			c3.weightx = 0;
			c3.gridx = 1;
      	c3.gridy = 1;
			if (clownImage != null)
				clownPicLabel = new JLabel(clownImage);
			else
				clownPicLabel = new JLabel("Clown Image");
			step3Panel.add(clownPicLabel, c3);
      
      //adds the step1 panel to the contentPane for initial startup
         contentPane.add(step1Panel, BorderLayout.CENTER);
			step1Panel.setVisible(true);
			step2Panel.setVisible(false);
			step3Panel.setVisible(false);
      }
   /**
		Describes the action each button should do when pressed depending on
		what button is pressed and what panel is currently visible
	*/
       public void actionPerformed(ActionEvent e)
      {
         Object source = e.getSource();
      	
         if (source == nextButton)
         {
            if (step1Panel.isVisible() == true)
            {
					step1Panel.setVisible(false);
               contentPane.remove(step1Panel);
					contentPane.add(step2Panel, BorderLayout.CENTER);
					step2Panel.setVisible(true);
            }
				else if (step2Panel.isVisible() == true)
				{
					step2Panel.setVisible(false);
					contentPane.remove(step2Panel);
					contentPane.add(step3Panel, BorderLayout.CENTER);
					step3Panel.setVisible(true);
				}
         }
			else if (source == prevButton)
			{
				if (step2Panel.isVisible() == true)
				{
					step2Panel.setVisible(false);
					contentPane.remove(step2Panel);
					contentPane.add(step1Panel, BorderLayout.CENTER);
					step1Panel.setVisible(true);
            }
				else if (step3Panel.isVisible() == true)
				{
					step3Panel.setVisible(false);
					contentPane.remove(step3Panel);
					contentPane.add(step2Panel, BorderLayout.CENTER);
					step2Panel.setVisible(true);
				}			
			}
      }
   
   /**
   	Main method to start the application.
   	@param args the command line parameters
   	@return void
   */
       public static void main(String[] args)
      {
		
      //Initialises new instance of Assign1 class
         Assign1 myProgram = new Assign1();
      //validate frame and all of its components
         myProgram.validate();
      //sets initial frame size to 400x400
         myProgram.setSize(400, 400);
      //sets frame to be visible on screen
         myProgram.setVisible(true);
      
      //initialises Dimension objects to contain size of the myProgram frame and size of computer screen
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         Dimension frameSize = myProgram.getSize();
      
      //ensures that the frame size cannot be bigger than the screen it is on
         if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
         if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
      
      //sets myProgram frame to the middle of the screen
         myProgram.setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
      
      }
   }
