   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.*;
   import java.util.*;
   import java.io.*;
   import java.text.*;

/**
	Computer Systems Architecture Assignment 1 - CPU Emulator
	A CPU Emulation program for a simple imaginary assembly language.
	@version 1.0 14/4/2005
	@author Paul Fioravanti
*/
    public class Emulator extends JFrame implements ActionListener
   {
   //===BEGIN USER INTERFACE COMPONENTS===
   
   //JPanel on top of the JFrame 
      JPanel contentPane;
   	
   //JLabels for the user interface
      JLabel debugOutputLabel;
      JLabel sourceCodeLabel;
      JLabel CPURegisterNamelabel;
      JLabel CPURegisterValuelabel;
   	
   //JButtons for the user interface and the panel on which they reside
      JPanel buttonPanel;
      JButton pauseButton;
      JButton stepButton;
      JButton runButton;
      JButton quitButton;
      JButton resetButton;
      JButton loadButton;
   	
   //JLists to pass the source code and output into and JScrollPanes to put each list in
      JPanel mainPanel = new JPanel(new GridBagLayout());
      JTextArea debugOutput;
      JList sourceCodeList;
      JScrollPane debugOutputScrollPane;
      JScrollPane sourceCodeScrollPane;
   	
   //JTextFields to display the names of each CPU Register and their values
      JPanel textFieldPanel;
      JTextField AX;
      JTextField AXValue;
      JTextField BX;
      JTextField BXValue;
      JTextField CX;
      JTextField CXValue;
      JTextField DX;
      JTextField DXValue;
      JTextField IP;
      JTextField IPValue;
      JTextField FLAGS;
      JTextField FLAGSValue;
   	
   //Create the JLists, JScrollPanes, JLabels for mainPanel 
      GridBagConstraints c = new GridBagConstraints();
   	
   //===BEGIN LOGICAL COMPONENTS===
   	
   	//The string array to store each line of source code present
      String[] sourceCode = {};
   	
   	//Static integers for all of the registers
      static int regAX = 0;
      static int regBX = 0;
      static int regCX = 0;
      static int regDX = 0;
      static int regIP = 0;
      static int regFLAGS = 0;
   	
   	//64 kb of memory; set everything to zero
      byte[] memory = new byte[65536];
   	
   	//boolean values for the following purposes:
   	//loadCalled so that run cannot be executed without an input file
   	//haltCalled so that once HALT has been called for any reason, operation cannot continue   	
      boolean loadCalled = false;
      boolean haltCalled = false;
		
		//Timer for the run and pause buttons.
		//Specifically called from the javax.swing library to avoid ambiguity.
		javax.swing.Timer t;
   	
   	/**
   		Constructor for objects of class Assign1
   	*/
       public Emulator()
      {
      	//Set program to close when shut down button clicked
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	//sets title of Assign1 frame
         this.setTitle("CSA Assignment 1");
      	//Retrieve window's content pane and assign it to a JPanel
         contentPane = (JPanel)this.getContentPane();
      	
      	//Create JButtons for user interface and the panel they reside on
         buttonPanel = new JPanel(new FlowLayout());
      		
         pauseButton = new JButton("Pause");
         pauseButton.setOpaque(true);
         pauseButton.addActionListener(this);
         buttonPanel.add(pauseButton);
      		
         stepButton = new JButton("Step");
         stepButton.setOpaque(true);
         stepButton.addActionListener(this);
         buttonPanel.add(stepButton);
      		
         runButton = new JButton("Run");
         runButton.setOpaque(true);
         runButton.addActionListener(this);
         buttonPanel.add(runButton);
      		
         quitButton = new JButton("Quit");
         quitButton.setOpaque(true);
         quitButton.addActionListener(this);
         buttonPanel.add(quitButton);
      		
         resetButton = new JButton("Reset");
         resetButton.setOpaque(true);
         resetButton.addActionListener(this);
         buttonPanel.add(resetButton);
      		
         loadButton = new JButton("Load");
         loadButton.setOpaque(true);
         loadButton.addActionListener(this);
         buttonPanel.add(loadButton);
      		
      	//add buttonPanel to the JPanel content pane
         contentPane.add(buttonPanel, BorderLayout.SOUTH);
      		
      	//Create JLabels for textFieldPanel
         CPURegisterNamelabel = new JLabel("Register Name");
         CPURegisterValuelabel = new JLabel("Register Value");
      
      	//add GridBagLayout constraints to components for main panel and add them to mainPanel
      	//These include the JLabels indicating 'DEBUG OUTPUT' and 'SOURCE CODE' windows,
      	//plus the 2 windows that contain the source code and the debug output		
         debugOutputLabel = new JLabel("Debug Output");
         c.fill = GridBagConstraints.BOTH;
         c.weightx = 1;
         c.weighty = 0;
         c.gridx = 0;
         c.gridy = 0;
         mainPanel.add(debugOutputLabel, c);
      		
         debugOutput = new JTextArea();
         debugOutputScrollPane = new JScrollPane(debugOutput);
         debugOutput.setEditable(false);
         c.weightx = 1;
         c.weighty = 3;
         c.gridx = 0;
         c.gridy = 1;
         mainPanel.add(debugOutputScrollPane, c);
      	
      	//feeds the sourceCode array into the sourceCodeList 	
         sourceCodeList = new JList(sourceCode);
         sourceCodeScrollPane = new JScrollPane(sourceCodeList);
         c.weightx = 1;
         c.weighty = 1;
         c.gridx = 0;
         c.gridy = 2;
         mainPanel.add(sourceCodeScrollPane, c);
      
         sourceCodeLabel = new JLabel("Source Code");
         c.weightx = 1;
         c.weighty = 0;
         c.gridx = 0;
         c.gridy = 3;
         mainPanel.add(sourceCodeLabel, c);
      		
      	//add mainPanel to the JPanel content pane
         contentPane.add(mainPanel, BorderLayout.CENTER);
      		
      	//Create the JTextFields to display each of the CPU registers and their values
      	//and make the text fields uneditable
      	//Note the values for the FLAGS register:
      	//'0' means equals, '1' means greater-than, '-1' means less-than
         textFieldPanel = new JPanel(new GridLayout(7,2));
         AX = new JTextField("AX");
         AX.setEditable(false);
         AXValue = new JTextField(String.valueOf(regAX));
         AXValue.setEditable(false);
         BX = new JTextField("BX");
         BX.setEditable(false);
         BXValue = new JTextField(String.valueOf(regBX));
         BXValue.setEditable(false);
         CX = new JTextField("CX");
         CX.setEditable(false);
         CXValue = new JTextField(String.valueOf(regCX));
         CXValue.setEditable(false);
         DX = new JTextField("DX");
         DX.setEditable(false);
         DXValue = new JTextField(String.valueOf(regDX));
         DXValue.setEditable(false);
         IP = new JTextField("IP");
         IP.setEditable(false);
         IPValue = new JTextField(String.valueOf(regIP));
         IPValue.setEditable(false);
         FLAGS = new JTextField("FLAGS (EQ:0/GT:1/LT:-1)");
         FLAGS.setEditable(false);
         FLAGSValue = new JTextField(String.valueOf(regFLAGS));
         FLAGSValue.setEditable(false);
      	
      	//add each textField to the textFieldPanel	
         textFieldPanel.add(CPURegisterValuelabel);
         textFieldPanel.add(CPURegisterNamelabel);
         textFieldPanel.add(AXValue);
         textFieldPanel.add(AX);
         textFieldPanel.add(BXValue);
         textFieldPanel.add(BX);
         textFieldPanel.add(CXValue);
         textFieldPanel.add(CX);
         textFieldPanel.add(DXValue);
         textFieldPanel.add(DX);
         textFieldPanel.add(IPValue);
         textFieldPanel.add(IP);
         textFieldPanel.add(FLAGSValue);
         textFieldPanel.add(FLAGS);
      		
      	//add textFieldPanel to the JPanel content pane
         contentPane.add(textFieldPanel, BorderLayout.EAST);
			
			//ActionListener initialized for the start/stop buttons
			ActionListener task = new ActionListener()
			{
				public void actionPerformed(ActionEvent ev)
				{
					//calls the step method
					step(sourceCode[regIP]);
				}
			};
			//Initializes the Timer and sets its delay to ha;f a second
			t = new javax.swing.Timer(500, task);
	
		
      }//end constructor
   	
		/**
			Method to call the start method in a Timer if certain conditions are fulfilled.
		*/
		public void run()
		{
			//place same constraints as on the step button 
			if ((!t.isRunning()) && loadCalled == true && regIP < sourceCode.length && haltCalled == false)
			{
				t.start();
			}
			else
			{
				stop();
			} 
		}
		
		/**
			Method to call the stop method in a Timer.
		*/
		public void stop()
		{
			if (t.isRunning())
			{
				t.stop();
			}
		}
		
   	/**
   		Method to load a CSASM file into the CPU after having opened up the program.
   		@throws FileNotFoundException if the file does not exist.
   		@throws IOException if there is an error writing the file.
   	*/
       public void load()
      {
      //declare variables
         File selectedFile;
         BufferedReader in;
         String sourceCodeLine;
         int i = 0;  //generic counter
         int length = 0; //to be length of the array to store each line of source code
      	
      	//Bring up a dialog to choose which file to load in
         try
         {
            JFileChooser chooser = new JFileChooser();
         	
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
               selectedFile = chooser.getSelectedFile();
               in = new BufferedReader(new FileReader(selectedFile));
               sourceCodeLine = in.readLine();
            	
            //open file and run through it to get the necessary length for source code array
               while (sourceCodeLine != null)
               {
                  length++;
                  sourceCodeLine = in.readLine();	
               }
               in.close();
            	
            //make source code array big enough to hold source code
               sourceCode = new String[length];
            	
            //read in file again	
               in = new BufferedReader(new FileReader(selectedFile));
               sourceCodeLine = in.readLine();
            	
            //store each line of source code in an element of sourceCode array
               while (sourceCodeLine != null)
               {
                  sourceCode[i] = sourceCodeLine;
                  i++;
                  sourceCodeLine = in.readLine();	
               }
               in.close();	
            	
            //create a new JList to put sourceCode array in and a new JScroll Pane to
            //store the JList in
               sourceCodeList = new JList(sourceCode);
               sourceCodeScrollPane = new JScrollPane(sourceCodeList);
            //add constraints
               c.weighty = 1;
               c.gridx = 0;
               c.gridy = 2;
            //remove the original sourceCode window component
               mainPanel.remove(2);
            //insert new sourceCode window in exactly the same position and validate
               mainPanel.add(sourceCodeScrollPane, c, 2);
               contentPane.validate();
            	
            	//allows step() to be executed
               loadCalled = true;
					haltCalled = false;
            regAX = 0;
            AXValue.setText(String.valueOf(regAX));
            regBX = 0;
            BXValue.setText(String.valueOf(regBX));
            regCX = 0;
            CXValue.setText(String.valueOf(regCX));
            regDX = 0;
            DXValue.setText(String.valueOf(regDX));
            regIP = 0;
            IPValue.setText(String.valueOf(regIP));
            regFLAGS = 0;
            FLAGSValue.setText(String.valueOf(regFLAGS));
            debugOutput.setText("");
            debugOutput.repaint();
            sourceCodeList.setSelectedIndex(regIP);
            }
         } 
             catch (FileNotFoundException exception)
            {
               JOptionPane.showMessageDialog(this, "Requested File Not Found", "File Error", JOptionPane.ERROR_MESSAGE); 
               System.out.println("\nFile Not Found"); //debug
               return;
            }
         
             catch (IOException exception)
            {
               JOptionPane.showMessageDialog(this, "Error Reading In File", "File Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("\nI/O error"); //debug
               return;
            }
      }
   	
   	/**
   		Method to load a CSASM file into the CPU from the command line without first opening
   		the program.
   		@throws FileNotFoundException if the file does not exist.
   		@throws IOException if there is an error writing the file.
   	*/
       public void load(String selectedFile)
      {
         BufferedReader in;
         String sourceCodeLine;
         int i = 0;  //generic counter
         int length = 0; //to be length of the array to store each line of source code
      	
      	//standard file read in
         try
         {
            in = new BufferedReader(new FileReader(selectedFile));
            sourceCodeLine = in.readLine();
            	
            //open file and run through it to get the necessary length for source code array
            while (sourceCodeLine != null)
            {
               length++;
               sourceCodeLine = in.readLine();	
            }
            in.close();
            	
            //make source code array big enough to hold source code
            sourceCode = new String[length];
            	
            in = new BufferedReader(new FileReader(selectedFile));
            sourceCodeLine = in.readLine();
            	
            //store each line of source code in an element of sourceCode array
            while (sourceCodeLine != null)
            {
               sourceCode[i] = sourceCodeLine;
               i++;
               sourceCodeLine = in.readLine();	
            }
            in.close();	
            	
            //create a new JList to put sourceCode array in and a new JScroll Pane to
            //store the JList in
            sourceCodeList = new JList(sourceCode);
            sourceCodeScrollPane = new JScrollPane(sourceCodeList);
            //add constraints
            c.weighty = 1;
            c.gridx = 0;
            c.gridy = 2;
            //remove the original sourceCode window component
            mainPanel.remove(2);
            //insert new sourceCode window in exactly the same position and validate
            mainPanel.add(sourceCodeScrollPane, c, 2);
            contentPane.validate();
            
         	//allows step() to be called	
            loadCalled = true;
         }
         
             catch (FileNotFoundException exception)
            {
            	//JOptionPane.showMessageDialog(this, "Requested File Not Found", "File Error", JOptionPane.ERROR_MESSAGE); 
               System.out.println("\nFile Not Found"); //debug
               return;
            }
         
             catch (IOException exception)
            {
            	//JOptionPane.showMessageDialog(this, "Error Reading In File", "File Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("\nError reading in file"); //debug
               return;
            }
      		
      	//highlights index according to value of regIP so user understands which line will
      	//be executed	
         sourceCodeList.setSelectedIndex(regIP);
      }
   	
   	/**
   		Method to tokenize a line of CSASM code into its respective instructions and 
   		operands and then execute that line of code.
   	*/
       public void step(String line)
      {	
      	//highlights index according to value of regIP so user understands which line will
      	//be executed
         sourceCodeList.setSelectedIndex(regIP);
      	
      	//initialize variables
         String instruction = "";
         boolean operand1Exists = false;
         boolean commaExists = false;
         boolean operand2Exists = false;
         String operand1 = "";
         String operand2 = "";
         String temp = "";
         int tokenCount = 0;
      	
      	//Create string tokenizer to parse for spaces and tabs
         StringTokenizer st = new StringTokenizer(line, "\t ", false);
      	
         temp = st.nextToken();
         if (temp != null && temp.charAt(0) != '#')
         {
         	//If the first token is not a comment it is assigned as the instruction
            instruction = temp;
            System.out.println("Instruction = " + instruction); //debug
            tokenCount++;
            if (st.hasMoreTokens())
            {
               temp = st.nextToken();
               System.out.println(temp); //debug
               if (temp != null && temp.charAt(0) != '#')
               {
               	//If the next token is not a comment it is assigned as operand 1
                  operand1 = temp;
                  if (operand1.charAt(operand1.length()-1) == ',')
                  {
                  	//checks for comma at the end of operand 1. If present, deletes it
                  	//but keeps a value to know that a comma exists and to expect a second
                  	//operand
                     operand1 = operand1.substring(0, operand1.length()-1);
                     commaExists = true;
                  }
                  operand1Exists = true;
                  System.out.println("Operand1 = " + operand1); //debug
                  tokenCount++;
               }
               if (st.hasMoreTokens())
               {	
                  temp = st.nextToken();
               	
                  if (temp.equals(","))
                  {
                  	//If the next token is a comma, stores that it exists and moves on
                  	//to the next token
                     commaExists = true;
                     temp = st.nextToken();
                  }
                                 	
                  if (temp != null && temp.charAt(0) != '#')
                  {
                     operand2 = temp;
                     if (operand2.charAt(0) == ',')
                     {
                     	//checks for comma at the beginning of operand 2. If present, deletes it
                     	//but keeps a value to know that a comma exists.
                        operand2 = operand2.substring(1);
                        commaExists = true;
                     }
                  	
                     if (commaExists == true) //debug
                        System.out.println("CommaExists = true"); //debug
                  	
                  	//keeps a record of whether a second operand exists for appropriate
                  	//exception handing
                     operand2Exists = true;
                     System.out.println("Operand2 = " + operand2); //debug
                     tokenCount++;
                  }
               }
            }
         }
      	
      	//No more than 3 tokens are ever needed in a CSASM line of code, so an
      	//exception is thrown and handled if there is.
         if (tokenCount > 3)
         {
            JOptionPane.showMessageDialog(this, "Line contains too many elements to execute valid instruction.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error in code formatting - more than 3 tokens");
            debugOutput.append("HALT\n");
            haltCalled = true;
            return;
         }
      	//If the tokenizer detects no tokens, it is considered a NOP instruction
         if (tokenCount == 0)
         {
            instruction = "NOP";	
         }
      	//adds 'NOP' to the debug output window if instruction is NOP
         if (instruction.equalsIgnoreCase("NOP"))
         {
            debugOutput.append("NOP\n");
            System.out.println("NOP"); //debug
         }
         //adds 'HALT' to the debug output window if instruction is HALT
         //as well as sets haltCalled to true, meaning no more operations can
         //be performed until the CPU is reset.
         else if (instruction.equalsIgnoreCase("HALT"))
         {
            debugOutput.append("HALT\n");
            System.out.println("HALT"); //debug
            haltCalled = true;
				stop();
            return;
         }
         else if (instruction.equalsIgnoreCase("MOV"))
         {
            if (operand1Exists == false)
            {
            	//MOV requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	//Condition 1: operand1 is a constant
            if (operand1.charAt(0) == '$')
            {
            	//make a substring containing just the int.
            	//If operand1 starts with a $ yet is not an int, bring up error window
               try
               {
                  operand1 = operand1.substring(1);
                  Integer.parseInt(operand1);
                  System.out.println("Parsed Operand1 = " + operand1); //debug
               }
                   catch (NumberFormatException e)
                  {
                     JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand1 is not a constant value"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
            	
            	//Condition 1.1: operand2 is a register
               if (operand2.charAt(0) == '%')
               {
               	//Make substring out of operand 2 and check its value
               	//against value of all 4 possible valid registers
               	//If register is valid, put constant value into register
               	//If not valid, bring up error window
                  operand2 = operand2.substring(1);
               
                  if (operand2.equalsIgnoreCase("AX"))
                  {
                     regAX = Integer.parseInt(operand1);
                     AXValue.setText(String.valueOf(regAX));
                     System.out.println("RegAX = " + regAX); //debug
                  }
                  else if (operand2.equalsIgnoreCase("BX")) 
                  {
                     regBX = Integer.parseInt(operand1);
                     BXValue.setText(String.valueOf(regBX));
                     System.out.println("RegBX = " + regBX); //debug
                  }
                  else if (operand2.equalsIgnoreCase("CX")) 
                  {
                     regCX = Integer.parseInt(operand1);
                     CXValue.setText(String.valueOf(regCX));
                     System.out.println("RegCX = " + regCX); //debug
                  }
                  else if (operand2.equalsIgnoreCase("DX")) 
                  {
                     regDX = Integer.parseInt(operand1);
                     DXValue.setText(String.valueOf(regDX));
                     System.out.println("RegDX = " + regDX); //debug
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.print("Operand2 is not a valid register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               //Condition 1.2: operand2 is a memory allocation
               else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '%' && operand2.charAt(operand2.length()-1) == ')')
               {	
               	//Make substring out of operand 2 and check its value
               	//against value of all 4 possible valid registers
               	//If register is valid, put value into memory at position
               	//indicated by register.
               	//memory[] only accepts bytes, so must parse operand 1
               	//into byte form before storing.
               	//If memory allocation not valid, bring up error window 
                  operand2 = operand2.substring(2, operand2.length()-1);
               
                  if (operand2.equalsIgnoreCase("AX"))
                  {
                     if (regAX > 0 && regAX < memory.length)
                     {
                        memory[regAX] = Byte.parseByte(operand1);
                        System.out.println("Memory["+regAX+"] = " + operand1); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Allocation made to non-existant memory."); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else if (operand2.equalsIgnoreCase("BX")) 
                  {
                     if (regBX > 0 && regBX < memory.length)
                     {
                        memory[regBX] = Byte.parseByte(operand1);
                        System.out.println("Memory["+regAX+"] = " + operand1); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Allocation made to non-existant memory."); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else if (operand2.equalsIgnoreCase("CX")) 
                  {
                     if (regCX > 0 && regCX < memory.length)
                     {
                        memory[regCX] = Byte.parseByte(operand1);
                        System.out.println("Memory["+regAX+"] = " + operand1); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Allocation made to non-existant memory."); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else if (operand2.equalsIgnoreCase("DX")) 
                  {
                     if (regDX > 0 && regDX < memory.length)
                     {
                        memory[regDX] = Byte.parseByte(operand1);
                        System.out.println("Memory["+regAX+"] = " + operand1); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Allocation made to non-existant memory."); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.print("Operand2 is not a valid register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               //Condition 1.3: operand2 is a memory allocation denoted by a constant
               else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '$' && operand2.charAt(operand2.length()-1) == ')')
               {	
               	//Make substring out of operand 2 and 
               	//parse to see if it is an integer
               	//If integer is valid, put value into memory at position
               	//indicated by integer.
               	//memory[] only accepts bytes, so must parse operand 1
               	//into byte form before storing.
               	//If memory allocation not valid, bring up error window
                  try
                  { 
                     operand2 = operand2.substring(2, operand2.length()-1);
                     Integer.parseInt(operand2);
                     System.out.println("Parsed Operand2 = " + operand2); //debug
                  }
                      catch (NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a constant value"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
               
                  if (Integer.parseInt(operand2) > 0 && Integer.parseInt(operand2) < memory.length)
                  {
                     memory[Integer.parseInt(operand2)] = Byte.parseByte(operand1);
                     System.out.println("Memory["+Integer.parseInt(operand2)+"] = " + operand1); //debug
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                     System.out.print("Allocation made to non-existant memory."); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 2 is not a register or memory allocation.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            //Condition 2: operand1 is a register
            else if (operand1.charAt(0) == '%')
            {
            	//make a substring containing just the regsiter name.
            	//If regsiter name does not match the possible valid registers
            	//bring up error window
               operand1 = operand1.substring(1);
            	
               if (operand1.equalsIgnoreCase("AX"))
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                  	//Make substring out of operand 2 and check its value
                  	//against value of all 4 possible valid registers
                  	//If register is valid, put value of register 1 into register 2
                  	//If not valid, bring up error window
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX = regAX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX = regAX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegBX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX = regAX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX = regAX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  //Condition 2.2: operand2 is a memory allocation
                  else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '%' && operand2.charAt(operand2.length()-1) == ')')
                  {
                  	//Make substring out of operand 2 and check its value
                  	//of its register against the memory to ensure value 
                  	//is part of memory 
                  	//If register is valid, put value of register 1 into memory
                  	//If not valid, bring up error window
                     operand2 = operand2.substring(2, operand2.length()-1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        if (regAX > 0 && regAX < memory.length)
                        {
                           memory[regAX] = (byte)regAX;
                           System.out.println("Memory["+regAX+"] = " + regAX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        if (regBX > 0 && regBX < memory.length)
                        {
                           memory[regBX] = (byte)regAX;
                           System.out.println("Memory["+regBX+"] = " + regAX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        if (regCX > 0 && regCX < memory.length)
                        {
                           memory[regCX] = (byte)regAX;
                           System.out.println("Memory["+regCX+"] = " + regAX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        if (regDX > 0 && regDX < memory.length)
                        {
                           memory[regDX] = (byte)regAX;
                           System.out.println("Memory["+regDX+"] = " + regAX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  //Condition 2.3: operand2 is a memory allocation denoted by a constant
                  else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '$' && operand2.charAt(operand2.length()-1) == ')')
                  {	
                  //Make substring out of operand 2 and 
                  //parse to see if it is an integer
                  //If integer is valid, put value into memory at position
                  //indicated by integer.
                  //memory[] only accepts bytes, so must parse operand 1
                  //into byte form before storing.
                  //If memory allocation not valid, bring up error window
                     try
                     { 
                        operand2 = operand2.substring(2, operand2.length()-1);
                        Integer.parseInt(operand2);
                        System.out.println("Parsed Operand2 = " + operand2); //debug
                     }
                         catch (NumberFormatException e)
                        {
                           JOptionPane.showMessageDialog(this, "Operand 2 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                           System.out.println("Operand2 is not a constant value"); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                  
                     if (Integer.parseInt(operand2) > 0 && Integer.parseInt(operand2) < memory.length)
                     {
                        memory[Integer.parseInt(operand2)] = (byte)regAX;
                        System.out.println("Memory["+Integer.parseInt(operand2)+"] = " + regAX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Allocation made to non-existant memory."); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register or memory allocation.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else if (operand1.equalsIgnoreCase("BX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX = regBX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX = regBX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegBX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX = regBX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX = regBX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register");  //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  //Condition 2.2: operand2 is a memory allocation
                  else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '%' && operand2.charAt(operand2.length()-1) == ')')
                  {
                     operand2 = operand2.substring(2, operand2.length()-1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        if (regAX > 0 && regAX < memory.length)
                        {
                           memory[regAX] = (byte)regBX;
                           System.out.println("Memory["+regAX+"] = " + regBX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        if (regBX > 0 && regBX < memory.length)
                        {
                           memory[regBX] = (byte)regBX;
                           System.out.println("Memory["+regBX+"] = " + regBX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        if (regCX > 0 && regCX < memory.length)
                        {
                           memory[regCX] = (byte)regBX;
                           System.out.println("Memory["+regCX+"] = " + regBX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        if (regDX > 0 && regDX < memory.length)
                        {
                           memory[regDX] = (byte)regBX;
                           System.out.println("Memory["+regDX+"] = " + regBX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
						//Condition 2.3: operand2 is a memory allocation denoted by a constant
                  else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '$' && operand2.charAt(operand2.length()-1) == ')')
                  {	
                  //Make substring out of operand 2 and 
                  //parse to see if it is an integer
                  //If integer is valid, put value into memory at position
                  //indicated by integer.
                  //memory[] only accepts bytes, so must parse operand 1
                  //into byte form before storing.
                  //If memory allocation not valid, bring up error window
                     try
                     { 
                        operand2 = operand2.substring(2, operand2.length()-1);
                        Integer.parseInt(operand2);
                        System.out.println("Parsed Operand2 = " + operand2); //debug
                     }
                         catch (NumberFormatException e)
                        {
                           JOptionPane.showMessageDialog(this, "Operand 2 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                           System.out.println("Operand2 is not a constant value"); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                  
                     if (Integer.parseInt(operand2) > 0 && Integer.parseInt(operand2) < memory.length)
                     {
                        memory[Integer.parseInt(operand2)] = (byte)regBX;
                        System.out.println("Memory["+Integer.parseInt(operand2)+"] = " + regBX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Allocation made to non-existant memory."); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register or memory allocation.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }	
               else if (operand1.equalsIgnoreCase("CX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX = regCX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX = regCX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegBX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX = regCX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX = regCX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  //Condition 2.2: operand2 is a memory allocation
                  else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '%' && operand2.charAt(operand2.length()-1) == ')')
                  {
                     operand2 = operand2.substring(2, operand2.length()-1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        if (regAX > 0 && regAX < memory.length)
                        {
                           memory[regAX] = (byte)regCX;
                           System.out.println("Memory["+regAX+"] = " + regBX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        if (regBX > 0 && regBX < memory.length)
                        {
                           memory[regBX] = (byte)regCX;
                           System.out.println("Memory["+regBX+"] = " + regCX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        if (regCX > 0 && regCX < memory.length)
                        {
                           memory[regCX] = (byte)regCX;
                           System.out.println("Memory["+regCX+"] = " + regCX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        if (regDX > 0 && regDX < memory.length)
                        {
                           memory[regDX] = (byte)regCX;
                           System.out.println("Memory["+regDX+"] = " + regCX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
						//Condition 2.3: operand2 is a memory allocation denoted by a constant
                  else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '$' && operand2.charAt(operand2.length()-1) == ')')
                  {	
                  //Make substring out of operand 2 and 
                  //parse to see if it is an integer
                  //If integer is valid, put value into memory at position
                  //indicated by integer.
                  //memory[] only accepts bytes, so must parse operand 1
                  //into byte form before storing.
                  //If memory allocation not valid, bring up error window
                     try
                     { 
                        operand2 = operand2.substring(2, operand2.length()-1);
                        Integer.parseInt(operand2);
                        System.out.println("Parsed Operand2 = " + operand2); //debug
                     }
                         catch (NumberFormatException e)
                        {
                           JOptionPane.showMessageDialog(this, "Operand 2 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                           System.out.println("Operand2 is not a constant value"); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                  
                     if (Integer.parseInt(operand2) > 0 && Integer.parseInt(operand2) < memory.length)
                     {
                        memory[Integer.parseInt(operand2)] = (byte)regCX;
                        System.out.println("Memory["+Integer.parseInt(operand2)+"] = " + regCX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Allocation made to non-existant memory."); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register or memory allocation.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else if (operand1.equalsIgnoreCase("DX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX = regDX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX = regDX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegDX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX = regDX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX = regDX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  //Condition 2.2: operand2 is a memory allocation
                  else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '%' && operand2.charAt(operand2.length()-1) == ')')
                  {
                     operand2 = operand2.substring(2, operand2.length()-1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        if (regAX > 0 && regAX < memory.length)
                        {
                           memory[regAX] = (byte)regDX;
                           System.out.println("Memory["+regAX+"] = " + regDX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        if (regBX > 0 && regBX < memory.length)
                        {
                           memory[regBX] = (byte)regDX;
                           System.out.println("Memory["+regBX+"] = " + regDX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        if (regCX > 0 && regCX < memory.length)
                        {
                           memory[regCX] = (byte)regDX;
                           System.out.println("Memory["+regCX+"] = " + regDX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        if (regDX > 0 && regDX < memory.length)
                        {
                           memory[regDX] = (byte)regDX;
                           System.out.println("Memory["+regDX+"] = " + regDX); //debug
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                           System.out.print("Allocation made to non-existant memory."); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
						//Condition 2.3: operand2 is a memory allocation denoted by a constant
                  else if (operand2.charAt(0) == '(' && operand2.charAt(1) == '$' && operand2.charAt(operand2.length()-1) == ')')
                  {	
                  //Make substring out of operand 2 and 
                  //parse to see if it is an integer
                  //If integer is valid, put value into memory at position
                  //indicated by integer.
                  //memory[] only accepts bytes, so must parse operand 1
                  //into byte form before storing.
                  //If memory allocation not valid, bring up error window
                     try
                     { 
                        operand2 = operand2.substring(2, operand2.length()-1);
                        Integer.parseInt(operand2);
                        System.out.println("Parsed Operand2 = " + operand2); //debug
                     }
                         catch (NumberFormatException e)
                        {
                           JOptionPane.showMessageDialog(this, "Operand 2 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                           System.out.println("Operand2 is not a constant value"); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                  
                     if (Integer.parseInt(operand2) > 0 && Integer.parseInt(operand2) < memory.length)
                     {
                        memory[Integer.parseInt(operand2)] = (byte)regDX;
                        System.out.println("Memory["+Integer.parseInt(operand2)+"] = " + regCX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Allocation made to non-existant memory.\n" + "Please press the reset button.", "Memory Error", JOptionPane.ERROR_MESSAGE);
                        System.out.print("Allocation made to non-existant memory."); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register or memory allocation.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            else 
            {
               JOptionPane.showMessageDialog(this, "Operand 1 is not a register or constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.print("Operand1 is not a valid register or constant"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;	
            }
         }
         else if (instruction.equalsIgnoreCase("ADD"))
         {
            if (operand1Exists == false)
            {
            	//ADD requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	//Condition 1: operand1 is a constant
            if (operand1.charAt(0) == '$')
            {
            	//make a substring containing just the int.
            	//If operand1 starts with a $ yet is not an int, bring up error window
               try
               {
                  operand1 = operand1.substring(1);
                  Integer.parseInt(operand1);
                  System.out.println("Parsed Operand1 = " + operand1); //debug
               }
                   catch (NumberFormatException e)
                  {
                     JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand1 is not a constant value"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
            	
            	//Condition 1.1: operand2 is a register
               if (operand2.charAt(0) == '%')
               {
               	//Make substring out of operand 2 and check its value
               	//against value of all 4 possible valid registers
               	//If register is valid, add constant value to register
               	//If not valid, bring up error window
                  operand2 = operand2.substring(1);
               
                  if (operand2.equalsIgnoreCase("AX"))
                  {
                     regAX += Integer.parseInt(operand1);
                     AXValue.setText(String.valueOf(regAX));
                     System.out.println("RegAX = " + regAX); //debug
                  }
                  else if (operand2.equalsIgnoreCase("BX")) 
                  {
                     regBX += Integer.parseInt(operand1);
                     BXValue.setText(String.valueOf(regBX));
                     System.out.println("RegBX = " + regBX); //debug
                  }
                  else if (operand2.equalsIgnoreCase("CX")) 
                  {
                     regCX += Integer.parseInt(operand1);
                     CXValue.setText(String.valueOf(regCX));
                     System.out.println("RegCX = " + regCX); //debug
                  }
                  else if (operand2.equalsIgnoreCase("DX")) 
                  {
                     regDX += Integer.parseInt(operand1);
                     DXValue.setText(String.valueOf(regDX));
                     System.out.println("RegDX = " + regDX); //debug
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.print("Operand2 is not a valid register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            //Condition 2: operand1 is a register
            else if (operand1.charAt(0) == '%')
            {
            	//make a substring containing just the regsiter name.
            	//If regsiter name does not match the possible valid registers
            	//bring up error window
               operand1 = operand1.substring(1);
            	
               if (operand1.equalsIgnoreCase("AX"))
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                  	//Make substring out of operand 2 and check its value
                  	//against value of all 4 possible valid registers
                  	//If register is valid, add value of register 1 into register 2
                  	//If not valid, bring up error window
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX += regAX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX += regAX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegBX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX += regAX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX += regAX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else if (operand1.equalsIgnoreCase("BX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX += regBX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX += regBX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegBX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX += regBX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX += regBX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
               }
               else if (operand1.equalsIgnoreCase("CX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX += regCX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX += regCX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegBX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX += regCX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX += regCX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
               }
               else if (operand1.equalsIgnoreCase("DX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX += regDX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX += regDX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegDX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX += regDX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX += regDX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            else 
            {
               JOptionPane.showMessageDialog(this, "Operand 1 is not a register or constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.print("Operand1 is not a valid register or constant"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;	
            }
         }
         else if (instruction.equalsIgnoreCase("SUB"))
         {
            if (operand1Exists == false)
            {
            	//SUB requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	//Condition 1: operand1 is a constant
            if (operand1.charAt(0) == '$')
            {
            	//make a substring containing just the int.
            	//If operand1 starts with a $ yet is not an int, bring up error window
               try
               {
                  operand1 = operand1.substring(1);
                  Integer.parseInt(operand1);
                  System.out.println("Parsed Operand1 = " + operand1); //debug
               }
                   catch (NumberFormatException e)
                  {
                     JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand1 is not a constant value"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
            	
            	//Condition 1.1: operand2 is a register
               if (operand2.charAt(0) == '%')
               {
               	//Make substring out of operand 2 and check its value
               	//against value of all 4 possible valid registers
               	//If register is valid, subtract constant value to register
               	//If not valid, bring up error window
                  operand2 = operand2.substring(1);
               
                  if (operand2.equalsIgnoreCase("AX"))
                  {
                     regAX -= Integer.parseInt(operand1);
                     AXValue.setText(String.valueOf(regAX));
                     System.out.println("RegAX = " + regAX); //debug
                  }
                  else if (operand2.equalsIgnoreCase("BX")) 
                  {
                     regBX -= Integer.parseInt(operand1);
                     BXValue.setText(String.valueOf(regBX));
                     System.out.println("RegBX = " + regBX); //debug
                  }
                  else if (operand2.equalsIgnoreCase("CX")) 
                  {
                     regCX -= Integer.parseInt(operand1);
                     CXValue.setText(String.valueOf(regCX));
                     System.out.println("RegCX = " + regCX); //debug
                  }
                  else if (operand2.equalsIgnoreCase("DX")) 
                  {
                     regDX -= Integer.parseInt(operand1);
                     DXValue.setText(String.valueOf(regDX));
                     System.out.println("RegDX = " + regDX); //debug
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.print("Operand2 is not a valid register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            //Condition 2: operand1 is a register
            else if (operand1.charAt(0) == '%')
            {
            	//make a substring containing just the regsiter name.
            	//If regsiter name does not match the possible valid registers
            	//bring up error window
               operand1 = operand1.substring(1);
            	
               if (operand1.equalsIgnoreCase("AX"))
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                  	//Make substring out of operand 2 and check its value
                  	//against value of all 4 possible valid registers
                  	//If register is valid, subtract value of register 1 into register 2
                  	//If not valid, bring up error window
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX -= regAX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX -= regAX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegBX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX -= regAX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX -= regAX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else if (operand1.equalsIgnoreCase("BX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX -= regBX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX -= regBX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegBX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX -= regBX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX -= regBX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
               }
               else if (operand1.equalsIgnoreCase("CX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX -= regCX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX -= regCX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegBX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX -= regCX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX -= regCX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
               }
               else if (operand1.equalsIgnoreCase("DX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        regAX -= regDX;
                        AXValue.setText(String.valueOf(regAX));
                        System.out.println("RegAX = " + regAX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        regBX -= regDX;
                        BXValue.setText(String.valueOf(regBX));
                        System.out.println("RegDX = " + regBX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        regCX -= regDX;
                        CXValue.setText(String.valueOf(regCX));
                        System.out.println("RegCX = " + regCX); //debug
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        regDX -= regDX;
                        DXValue.setText(String.valueOf(regDX));
                        System.out.println("RegDX = " + regDX); //debug
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }	
            }
            else 
            {
               JOptionPane.showMessageDialog(this, "Operand 1 is not a register or constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.print("Operand1 is not a valid register or constant"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;	
            }
         }
         else if (instruction.equalsIgnoreCase("INC"))
         {
            if (operand1Exists == false)
            {
            	//INC requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
            //Sole condition: operand1 is a register
            if (commaExists == true || operand2Exists == true)
            {
            	//INC only requires one operand.  Any more and it is considered invalid code.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Second operand or comma present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	
            if (operand1.charAt(0) == '%')
            {
            	//Make substring out of operand 1 and check its value
            	//against value of all 4 possible valid registers
            	//If register is valid, add value of 1 to register
            	//If not valid, bring up error window
               operand1 = operand1.substring(1);
            	
               if (operand1.equalsIgnoreCase("AX"))
               {
                  regAX++;
                  AXValue.setText(String.valueOf(regAX));
                  System.out.println("RegAX = " + regAX); //debug
               }
               else if (operand1.equalsIgnoreCase("BX")) 
               {
                  regBX++;
                  BXValue.setText(String.valueOf(regBX));
                  System.out.println("RegBX = " + regBX); //debug
               }
               else if (operand1.equalsIgnoreCase("CX")) 
               {
                  regCX++;
                  CXValue.setText(String.valueOf(regCX));
                  System.out.println("RegCX = " + regCX); //debug
               }
               else if (operand1.equalsIgnoreCase("DX")) 
               {
                  regDX++;
                  DXValue.setText(String.valueOf(regDX));
                  System.out.println("RegDX = " + regDX); //debug
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.println("Operand1 is not a valid register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            else 
            {
               JOptionPane.showMessageDialog(this, "Operand 1 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.print("Operand1 is not a valid register or constant"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;	
            }
         }
         else if (instruction.equalsIgnoreCase("DEC"))
         {
            if (operand1Exists == false)
            {
            	//DEC requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	//Sole condition: operand1 is a register
            if (commaExists == true || operand2Exists == true)
            {
            	//DEC only requires one operand.  Any more and it is considered invalid code.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Second operand or comma present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	
            if (operand1.charAt(0) == '%')
            {
            	//Make substring out of operand 1 and check its value
            	//against value of all 4 possible valid registers
            	//If register is valid, subtract value of 1 from register
            	//If not valid, bring up error window
               operand1 = operand1.substring(1);
            	
               if (operand1.equalsIgnoreCase("AX"))
               {
                  regAX--;
                  AXValue.setText(String.valueOf(regAX));
                  System.out.println("RegAX = " + regAX); //debug
               }
               else if (operand1.equalsIgnoreCase("BX")) 
               {
                  regBX--;
                  BXValue.setText(String.valueOf(regBX));
                  System.out.println("RegBX = " + regBX); //debug
               }
               else if (operand1.equalsIgnoreCase("CX")) 
               {
                  regCX--;
                  CXValue.setText(String.valueOf(regCX));
                  System.out.println("RegCX = " + regCX); //debug
               }
               else if (operand1.equalsIgnoreCase("DX")) 
               {
                  regDX--;
                  DXValue.setText(String.valueOf(regDX));
                  System.out.println("RegDX = " + regDX); //debug
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.println("Operand1 is not a valid register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            else 
            {
               JOptionPane.showMessageDialog(this, "Operand 1 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.print("Operand1 is not a valid register or constant"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;	
            }
         }
         else if (instruction.equalsIgnoreCase("JMP"))
         {
            if (operand1Exists == false)
            {
            	//JMP requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	//Sole condition: operand1 is a constant
            if (commaExists == true || operand2Exists == true)
            {
            	//JMP only requires one operand.  Any more and it is considered invalid code.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Second operand or comma present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	
         	//Condition 1: operand1 is a constant
            if (operand1.charAt(0) == '$')
            {
            	//make a substring containing just the int.
            	//If operand1 starts with a $ yet is not an int, bring up error window
               try
               {
                  operand1 = operand1.substring(1);
                  int jumpValue = Integer.parseInt(operand1);
                  System.out.println("Parsed Operand1 = " + operand1); //debug
               	
               	//check that JMP is not trying to access non-existent memory
                  if (jumpValue < 0 || jumpValue > sourceCode.length -1)
                  {
                     JOptionPane.showMessageDialog(this, "Attempted jump to non-existent line of code.\n" + "Please press the reset button.", "Access Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Error in code formatting - Second operand or comma present"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;	
                  }
                  else
                  {
                  	//make jumpValue equal regIP and hence move execution line to jumpValue
                     regIP = jumpValue;
                     IPValue.setText(String.valueOf(regIP));
                     sourceCodeList.setSelectedIndex(regIP);
                  	//return from call as do not want to increment regIP
                     return;
                  }
               }
                   catch (NumberFormatException e)
                  {
                     JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand1 is not a constant value"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
            }
            else 
            {
               JOptionPane.showMessageDialog(this, "Operand 1 is not a constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.print("Operand1 is not a valid register or constant"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;	
            }	
         }
         else if (instruction.equalsIgnoreCase("CMP"))
         {
            if (operand1Exists == false)
            {
            	//CMP requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	//temp int to store result of comparison
            int result;
         	
         	//reset regFLAGS to zero everytime a comparison is made
            regFLAGS = 0;
            FLAGSValue.setText(String.valueOf(regFLAGS));
         	
         	//Condition 1: operand1 is a constant
            if (operand1.charAt(0) == '$')
            {
            	//make a substring containing just the int.
            	//If operand1 starts with a $ yet is not an int, bring up error window
               try
               {
                  operand1 = operand1.substring(1);
                  Integer.parseInt(operand1);
                  System.out.println("Parsed Operand1 = " + operand1); //debug
               }
                   catch (NumberFormatException e)
                  {
                     JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand1 is not a constant value"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
            	
            	//Condition 1.1: operand2 is a register
               if (operand2.charAt(0) == '%')
               {
               	//Make substring out of operand 2 and check its value
               	//against value of all 4 possible valid registers
               	//If register is valid, subtract constant value from register
               	//and store appropriate value of EQ/GT/LT into regFLAGS.
               	//If not valid, bring up error window
                  operand2 = operand2.substring(1);
               
                  if (operand2.equalsIgnoreCase("AX"))
                  {
                     result = regAX - Integer.parseInt(operand1);
                     if (result < 0)
                     {
                        regFLAGS = -1;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                     if (result == 0)
                     {
                        regFLAGS = 0;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                     if (result > 0)
                     {
                        regFLAGS = 1;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                  }
                  else if (operand2.equalsIgnoreCase("BX")) 
                  {
                     result = regBX - Integer.parseInt(operand1);
                     if (result < 0)
                     {
                        regFLAGS = -1;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                     if (result == 0)
                     {
                        regFLAGS = 0;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                     if (result > 0)
                     {
                        regFLAGS = 1;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                  }
                  else if (operand2.equalsIgnoreCase("CX")) 
                  {
                     result = regBX - Integer.parseInt(operand1);
                     if (result < 0)
                     {
                        regFLAGS = -1;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                     if (result == 0)
                     {
                        regFLAGS = 0;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                     if (result > 0)
                     {
                        regFLAGS = 1;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                  }
                  else if (operand2.equalsIgnoreCase("DX")) 
                  {
                     result = regDX - Integer.parseInt(operand1);
                     if (result < 0)
                     {
                        regFLAGS = -1;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                     if (result == 0)
                     {
                        regFLAGS = 0;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                     if (result > 0)
                     {
                        regFLAGS = 1;
                        FLAGSValue.setText(String.valueOf(regFLAGS));
                        System.out.println("RegFLAGS = " + regFLAGS); //debug
                     }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.print("Operand2 is not a valid register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 2 is not a register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            //Condition 2: operand1 is a register
            else if (operand1.charAt(0) == '%')
            {
            	//Make substring out of operand 1 and check its value
            	//against value of all 4 possible valid registers
            	//If not valid, bring up error window
               operand1 = operand1.substring(1);
            	
               if (operand1.equalsIgnoreCase("AX"))
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                  	//Make substring out of operand 2 and check its value
                  	//against value of all 4 possible valid registers
                  	//If register is valid, subtract register 1 value from register 2
                  	//and store appropriate value of EQ/GT/LT into regFLAGS.
                  	//If not valid, bring up error window
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        result = regAX - regAX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        result = regBX - regAX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        result = regCX - regAX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        result = regDX - regAX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else if (operand2.charAt(0) == '$')
                  {
                  	//make a substring containing just the int.
                  	//If operand2 starts with a $ yet is not an int, bring up error window
                  	//If constant is valid, subtract register 1 value from constant
                  	//and store appropriate value of EQ/GT/LT into regFLAGS.
                  	//If not valid, bring up error window
                     try
                     {
                        operand2 = operand2.substring(1);
                        Integer.parseInt(operand2);
                        System.out.println("Parsed Operand2 = " + operand2); //debug
                        result = Integer.parseInt(operand2) - regAX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                         catch (NumberFormatException e)
                        {
                           JOptionPane.showMessageDialog(this, "Operand 2 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                           System.out.println("Operand2 is not a constant value"); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register or constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }
               }
               else if (operand1.equalsIgnoreCase("BX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        result = regAX - regBX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        result = regBX - regBX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        result = regCX - regBX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        result = regDX - regBX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register");  //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else if (operand2.charAt(0) == '$')
                  {
                     try
                     {
                        operand2 = operand2.substring(1);
                        Integer.parseInt(operand2);
                        System.out.println("Parsed Operand2 = " + operand2); //debug
                        result = Integer.parseInt(operand2) - regBX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                         catch (NumberFormatException e)
                        {
                           JOptionPane.showMessageDialog(this, "Operand 2 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                           System.out.println("Operand2 is not a constant value"); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register or constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
               }
               else if (operand1.equalsIgnoreCase("CX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        result = regAX - regCX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        result = regBX - regCX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        result = regCX - regCX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        result = regDX - regCX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else if (operand2.charAt(0) == '$')
                  {
                     try
                     {
                        operand2 = operand2.substring(1);
                        Integer.parseInt(operand2);
                        System.out.println("Parsed Operand2 = " + operand2); //debug
                        result = Integer.parseInt(operand2) - regCX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                         catch (NumberFormatException e)
                        {
                           JOptionPane.showMessageDialog(this, "Operand 2 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                           System.out.println("Operand2 is not a constant value"); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register or constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
               }
               else if (operand1.equalsIgnoreCase("DX")) 
               {
               		//Condition 2.1: operand2 is a register
                  if (operand2.charAt(0) == '%')
                  {
                     operand2 = operand2.substring(1);
                  
                     if (operand2.equalsIgnoreCase("AX"))
                     {
                        result = regAX - regDX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("BX")) 
                     {
                        result = regBX - regDX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("CX")) 
                     {
                        result = regCX - regDX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else if (operand2.equalsIgnoreCase("DX")) 
                     {
                        result = regDX - regDX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(this, "Operand 2 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand2 is not a valid register"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }
                  }
                  else if (operand2.charAt(0) == '$')
                  {
                     try
                     {
                        operand2 = operand2.substring(1);
                        Integer.parseInt(operand2);
                        System.out.println("Parsed Operand2 = " + operand2);
                        result = Integer.parseInt(operand2) - regAX;
                        if (result < 0)
                        {
                           regFLAGS = -1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result == 0)
                        {
                           regFLAGS = 0;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                        if (result > 0)
                        {
                           regFLAGS = 1;
                           FLAGSValue.setText(String.valueOf(regFLAGS));
                           System.out.println("RegFLAGS = " + regFLAGS); //debug
                        }
                     }
                         catch (NumberFormatException e)
                        {
                           JOptionPane.showMessageDialog(this, "Operand 2 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                           System.out.println("Operand2 is not a constant value"); //debug
                           debugOutput.append("HALT\n");
                           haltCalled = true;
                           return;
                        }
                  }
                  else
                  {
                     JOptionPane.showMessageDialog(this, "Operand 2 is not a register or constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Operand2 is not a register"); //debug
                     debugOutput.append("HALT\n");
                     haltCalled = true;
                     return;
                  }	
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a valid register.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            else 
            {
               JOptionPane.showMessageDialog(this, "Operand 1 is not a register or constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.print("Operand1 is not a valid register or constant"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;	
            }
         }
         else if (instruction.equalsIgnoreCase("JE"))
         {
            if (operand1Exists == false)
            {
            	//JE requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
            if (regFLAGS == 0)
            {
            	//Sole condition: operand1 is a constant
               if (commaExists == true || operand2Exists == true)
               {
               	//JE only requires one operand.  Any more and it is considered invalid code.
                  JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.println("Error in code formatting - Second operand or comma present"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            
            //Condition 1: operand1 is a constant
               if (operand1.charAt(0) == '$')
               {
               	//make a substring containing just the int.
               	//If operand1 starts with a $ yet is not an int, bring up error window
                  try
                  {
                     operand1 = operand1.substring(1);
                     int jumpValue = Integer.parseInt(operand1);
                     System.out.println("Parsed Operand1 = " + operand1); //debug
                  	
                  	//check that JE is not trying to access non-existent memory
                     if (jumpValue < 0 || jumpValue > sourceCode.length -1)
                     {
                        JOptionPane.showMessageDialog(this, "Attempted jump to non-existent line of code.\n" + "Please press the reset button.", "Access Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Error in code formatting - Second operand or comma present"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;	
                     }
                     else
                     {
                     	//make jumpValue equal regIP and hence move execution line to jumpValue
                        regIP = jumpValue;
                        IPValue.setText(String.valueOf(regIP));
                        sourceCodeList.setSelectedIndex(regIP);
                     	//return from call as do not want to increment regIP
                        return;
                     }
                  }
                      catch (NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand1 is not a constant value"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }	
               }
               else 
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a constant." + "Please press the reset button.\n", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register or constant"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;	
               }	
            }
         }
         else if (instruction.equalsIgnoreCase("JNE"))
         {
            if (operand1Exists == false)
            {
            	//JNE requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
            if (regFLAGS < 0 || regFLAGS > 0)
            {
            	//Sole condition: operand1 is a constant
               if (commaExists == true || operand2Exists == true)
               {
               	//JNE only requires one operand.  Any more and it is considered invalid code.
                  JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.println("Error in code formatting - Second operand or comma present"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            
            //Condition 1: operand1 is a constant
               if (operand1.charAt(0) == '$')
               {
               	//make a substring containing just the int.
               	//If operand1 starts with a $ yet is not an int, bring up error window
                  try
                  {
                     operand1 = operand1.substring(1);
                     int jumpValue = Integer.parseInt(operand1);
                     System.out.println("Parsed Operand1 = " + operand1); //debug
                  	
                  	//check that JNE is not trying to access non-existent memory
                     if (jumpValue < 0 || jumpValue > sourceCode.length -1)
                     {
                        JOptionPane.showMessageDialog(this, "Attempted jump to non-existent line of code.\n" + "Please press the reset button.", "Access Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Error in code formatting - Second operand or comma present"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;	
                     }
                     else
                     {
                     	//make jumpValue equal regIP and hence move execution line to jumpValue
                        regIP = jumpValue;
                        IPValue.setText(String.valueOf(regIP));
                        sourceCodeList.setSelectedIndex(regIP);
                     	//return from call as do not want to increment regIP
                        return;
                     }
                  }
                      catch (NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand1 is not a constant value"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }	
               }
               else 
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register or constant"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;	
               }	
            }
         }
         else if (instruction.equalsIgnoreCase("JL"))
         {
            if (operand1Exists == false)
            {
            	//JL requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
            if (regFLAGS < 0)
            {
            	//Sole condition: operand1 is a constant
               if (commaExists == true || operand2Exists == true)
               {
               	//JL only requires one operand.  Any more and it is considered invalid code.
                  JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.println("Error in code formatting - Second operand or comma present"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            
            //Condition 1: operand1 is a constant
               if (operand1.charAt(0) == '$')
               {
               	//make a substring containing just the int.
               	//If operand1 starts with a $ yet is not an int, bring up error window
                  try
                  {
                     operand1 = operand1.substring(1);
                     int jumpValue = Integer.parseInt(operand1);
                     System.out.println("Parsed Operand1 = " + operand1); //debug
                  	
                  	//check that JNE is not trying to access non-existent memory
                     if (jumpValue < 0 || jumpValue > sourceCode.length -1)
                     {
                        JOptionPane.showMessageDialog(this, "Attempted jump to non-existent line of code.\n" + "Please press the reset button.", "Access Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Error in code formatting - Second operand or comma present"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;	
                     }
                     else
                     {
                     	//make jumpValue equal regIP and hence move execution line to jumpValue
                        regIP = jumpValue;
                        IPValue.setText(String.valueOf(regIP));
                        sourceCodeList.setSelectedIndex(regIP);
                     	//return from call as do not want to increment regIP
                        return;
                     }
                  }
                      catch (NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand1 is not a constant value"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }	
               }
               else 
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register or constant"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;	
               }	
            }	
         }
         else if (instruction.equalsIgnoreCase("JG"))
         {
            if (operand1Exists == false)
            {
            	//JG requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
            if (regFLAGS > 0)
            {
            	//Sole condition: operand1 is a constant
               if (commaExists == true || operand2Exists == true)
               {
               	//JG only requires one operand.  Any more and it is considered invalid code.
                  JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.println("Error in code formatting - Second operand or comma present"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            
            //Condition 1: operand1 is a constant
               if (operand1.charAt(0) == '$')
               {
               	//make a substring containing just the int.
               	//If operand1 starts with a $ yet is not an int, bring up error window
                  try
                  {
                     operand1 = operand1.substring(1);
                     int jumpValue = Integer.parseInt(operand1);
                     System.out.println("Parsed Operand1 = " + operand1); //debug
                  	
                  	//check that JG is not trying to access non-existent memory
                     if (jumpValue < 0 || jumpValue > sourceCode.length -1)
                     {
                        JOptionPane.showMessageDialog(this, "Attempted jump to non-existent line of code.\n" + "Please press the reset button.", "Access Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Error in code formatting - Second operand or comma present"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;	
                     }
                     else
                     {
                     	//make jumpValue equal regIP and hence move execution line to jumpValue
                        regIP = jumpValue;
                        IPValue.setText(String.valueOf(regIP));
                        sourceCodeList.setSelectedIndex(regIP);
                     	//return from call as do not want to increment regIP
                        return;
                     }
                  }
                      catch (NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand1 is not a constant value"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }	
               }
               else 
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register or constant"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;	
               }	
            }
         }
         else if (instruction.equalsIgnoreCase("JLE"))
         {
            if (operand1Exists == false)
            {
            	//JLE requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
            if (regFLAGS <= 0)
            {
            	//Sole condition: operand1 is a constant
               if (commaExists == true || operand2Exists == true)
               {
               	//JLE only requires one operand.  Any more and it is considered invalid code.
                  JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.println("Error in code formatting - Second operand or comma present"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            
            //Condition 1: operand1 is a constant
               if (operand1.charAt(0) == '$')
               {
               	//make a substring containing just the int.
               	//If operand1 starts with a $ yet is not an int, bring up error window
                  try
                  {
                     operand1 = operand1.substring(1);
                     int jumpValue = Integer.parseInt(operand1);
                     System.out.println("Parsed Operand1 = " + operand1); //debug
                  	
                  	//check that JLE is not trying to access non-existent memory
                     if (jumpValue < 0 || jumpValue > sourceCode.length -1)
                     {
                        JOptionPane.showMessageDialog(this, "Attempted jump to non-existent line of code.\n" + "Please press the reset button.", "Access Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Error in code formatting - Second operand or comma present"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;	
                     }
                     else
                     {
                     	//make jumpValue equal regIP and hence move execution line to jumpValue
                        regIP = jumpValue;
                        IPValue.setText(String.valueOf(regIP));
                        sourceCodeList.setSelectedIndex(regIP);
                     	//return from call as do not want to increment regIP
                        return;
                     }
                  }
                      catch (NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand1 is not a constant value"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }	
               }
               else 
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register or constant"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;	
               }	
            }
         }
         else if (instruction.equalsIgnoreCase("JGE"))
         {
            if (operand1Exists == false)
            {
            	//JGE requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
            if (regFLAGS >= 0)
            {
            	//Sole condition: operand1 is a constant
               if (commaExists == true || operand2Exists == true)
               {
               	//JGE only requires one operand.  Any more and it is considered invalid code.
                  JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.println("Error in code formatting - Second operand or comma present"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            
            //Condition 1: operand1 is a constant
               if (operand1.charAt(0) == '$')
               {
               	//make a substring containing just the int.
               	//If operand1 starts with a $ yet is not an int, bring up error window
                  try
                  {
                     operand1 = operand1.substring(1);
                     int jumpValue = Integer.parseInt(operand1);
                     System.out.println("Parsed Operand1 = " + operand1); //debug
                  	
                  	//check that JGE is not trying to access non-existent memory
                     if (jumpValue < 0 || jumpValue > sourceCode.length -1)
                     {
                        JOptionPane.showMessageDialog(this, "Attempted jump to non-existent line of code.\n" + "Please press the reset button.", "Access Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Error in code formatting - Second operand or comma present"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;	
                     }
                     else
                     {
                     	//make jumpValue equal regIP and hence move execution line to jumpValue
                        regIP = jumpValue;
                        IPValue.setText(String.valueOf(regIP));
                        sourceCodeList.setSelectedIndex(regIP);
                     	//return from call as do not want to increment regIP
                        return;
                     }
                  }
                      catch (NumberFormatException e)
                     {
                        JOptionPane.showMessageDialog(this, "Operand 1 is not a constant value.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Operand1 is not a constant value"); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;
                     }	
               }
               else 
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a constant.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.print("Operand1 is not a valid register or constant"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;	
               }	
            }
         }
         else if (instruction.equalsIgnoreCase("CALL"))
         {
            if (operand1Exists == false)
            {
            	//CALL requires operand1.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Operand not present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Operand1 not present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	//Sole condition: operand1 is a register
            if (commaExists == true || operand2Exists == true)
            {
            	//CALL only requires one operand.  Any more and it is considered invalid code.
               JOptionPane.showMessageDialog(this, "Error in code formatting - Second operand or comma present.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.println("Error in code formatting - Second operand or comma present"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;
            }
         	
            if (operand1.charAt(0) == '_' && operand1.charAt(operand1.length()-1) == '_')
            {
            	//Check that operand 1 is a valid subroutine, and if so,
            	//create a new substring with just its name
            	//If not valid, bring up error window
               operand1 = operand1.substring(1, operand1.length()-1);
            	
               if (operand1.equalsIgnoreCase("printchar"))
               {
               	//prints out ascii value of regAX to the debugOutput
               	//unless regAX has a value of more than 127, in which
               	//case it prints a blank character
                  if (regAX < 128)
                  {
                     debugOutput.append(String.valueOf((char)regAX));
                     debugOutput.append("\n");
                     System.out.println("RegAX Char value = " + (char)regAX); //debug
                  }
                  else
                  {
                     debugOutput.append("\n");
                  }
               }
               else if (operand1.equalsIgnoreCase("printdec")) 
               {
               	//prints out integer value of regAX to the debugOutput
                  debugOutput.append(String.valueOf(regBX));
                  debugOutput.append("\n");
                  System.out.println("RegBX String value = " + regBX); //debug
               }
               else if (operand1.equalsIgnoreCase("printstr")) 
               {
               	//reads register AX to find the address which contains the string to 
               	//print out, and register BX indicates how many characters to print
                  for (int i = 0; i <= regBX; i++)
                  {
                  	//check that JMP is not trying to access non-existent memory
                     if (regAX < 0 || regAX > memory.length)
                     {
                        JOptionPane.showMessageDialog(this, "Attempted read character from non-existent memory.\n" + "Please press the reset button.", "Access Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Attempted read character from non-existent memory."); //debug
                        debugOutput.append("HALT\n");
                        haltCalled = true;
                        return;	
                     }
                     debugOutput.append(String.valueOf((char)memory[regAX]));
                     regAX++;
                     System.out.println("memory["+regAX+"] = " + memory[regAX]); //debug
                  }
                  debugOutput.append("\n");
               }
               else
               {
                  JOptionPane.showMessageDialog(this, "Operand 1 is not a valid subroutine.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                  System.out.println("Operand1 is not a valid register"); //debug
                  debugOutput.append("HALT\n");
                  haltCalled = true;
                  return;
               }
            }
            else 
            {
               JOptionPane.showMessageDialog(this, "Operand 1 is not a subroutine.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
               System.out.print("Operand1 is not a valid register or constant"); //debug
               debugOutput.append("HALT\n");
               haltCalled = true;
               return;	
            }
         }
         else 
         {
            JOptionPane.showMessageDialog(this, "Instruction is not valid.\n" + "Please press the reset button.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
            System.out.print("Invalid instruction."); //debug
            debugOutput.append("HALT\n");
            haltCalled = true;
            return;	
         }
      	
         //Increment regIP for instructions that need it
         regIP++;
         IPValue.setText(String.valueOf(regIP));
      	//set next selected line of code in source code window
         sourceCodeList.setSelectedIndex(regIP);
			//if the run button is going, set it to stop if at end of code and no HALT present
			if (regIP == sourceCode.length)
			{
				stop();
			}
         
      }
   	
   	/**
   		Describes the action each button should do when pressed depending on
   		what button is pressed.
   	*/
       public void actionPerformed(ActionEvent e)
      {
         Object source = e.getSource();
      	
         if (source == pauseButton)
         {
				//call the stop method in the timer
            stop();
         }
         //Before step() can be called, 3 main conditions must be met
         //1. A file has been loaded in.
         //2. HALT has not been called.
         //3. regIP is pointing to a valid line of code ie not outside its scope.
         else if (source == stepButton && loadCalled == true && regIP < sourceCode.length && haltCalled == false)
         {
            step(sourceCode[regIP]);
            System.out.println("RegIP = " + regIP); //debug				
         }
         else if (source == runButton)
         {
				//call the run method in the timer
				run();
         }
         //Quits the program
         else if (source == quitButton)
         {
            JOptionPane.showMessageDialog(this, "Thanks for using this Emulator.");
            System.exit(0);			
         }
         //resets all values in the program to zero, clears the debugOutput window
         //and highlights the first line of code again as well as clears the 
         //haltCalled flag.
         //If HALT is called, the reset button MUST be pushed before any futher operations
         //can take place
         else if (source == resetButton)
         {
            haltCalled = false;
            regAX = 0;
            AXValue.setText(String.valueOf(regAX));
            regBX = 0;
            BXValue.setText(String.valueOf(regBX));
            regCX = 0;
            CXValue.setText(String.valueOf(regCX));
            regDX = 0;
            DXValue.setText(String.valueOf(regDX));
            regIP = 0;
            IPValue.setText(String.valueOf(regIP));
            regFLAGS = 0;
            FLAGSValue.setText(String.valueOf(regFLAGS));
            debugOutput.setText("");
            debugOutput.repaint();
            sourceCodeList.setSelectedIndex(regIP);
         }
         //Calls the load method
         else if (source == loadButton)
         {
            load();			
         }
      }
   	
   	/**
   		Main method to start the application.
   		@param args the command line parameters
   		@return void
   	*/
       public static void main(String[] args)
      {
      	//Display message for when more than 1 args are entered at command line
         if (args.length > 1)
         {
            System.out.println("Usage: java Emulator [asmFile]");
            System.out.println(" - where [asmFile] is a CSASM Assembly Language plain text file");
            System.exit(1);
         }
      	
      //Attempts to set the look and feel of the application to the system look and feel
         try
         {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         }
             catch (Exception e)
            {
               e.printStackTrace();
            }
      
      //Initialises new instance of Assign1 class
         Emulator myProgram = new Emulator();
      //validate frame and all of its components
         myProgram.validate();
      //sets initial frame size to 800x400
         myProgram.setSize(800, 400);
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
      //if an arg is present, loads in the CSASM file as the program opens.
         if (args.length == 1)
         {
            myProgram.load(args[0]);	
         }
      }		
   }
