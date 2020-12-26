import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
	A class to provide a frame for the text editor
	
	@author Paul Fioravanti
	@see TextEditor
	@see AboutTextEditor
	@version 1.0 8/3/05
*/
public class TextEditorFrame extends JFrame
{	
	JPanel contentPane;
	BorderLayout borderLayout = new BorderLayout();
	JToolBar toolBar = new JToolBar();
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	
	ImageIcon image1;
	ImageIcon image2;
	ImageIcon image3;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu file = new JMenu();
	JMenuItem open = new JMenuItem();
	JMenuItem saveAs = new JMenuItem();
	JMenuItem exit = new JMenuItem();
	JMenu help = new JMenu();
	JMenuItem about = new JMenuItem();

	final JFileChooser chooser = new JFileChooser();
	
	JLabel statusBar = new JLabel();
	
	JTextArea textArea = new JTextArea();
	
	String currFileName = "";

	/**
		Constructor for TextEditorFrame
	*/
	public TextEditorFrame()
	{
		this.setSize(new Dimension(400,300));
		this.setTitle("Text Editor");
		
		contentPane = (JPanel)this.getContentPane();
		contentPane.setLayout(borderLayout);
		
		try
		{
			image1 = new ImageIcon(TextEditorFrame.class.getResource("Open16.gif"));
			button1.setIcon(image1); 
		}
		catch (Exception e)
		{
			button1.setText("Open File");
		}
		
		try
		{
			image2 = new ImageIcon(TextEditorFrame.class.getResource("Save16.gif"));
			button2.setIcon(image2);
		}
		catch (Exception e)
		{
			button2.setText("Save File");
		}
		
		try
		{
			image3 = new ImageIcon(TextEditorFrame.class.getResource("Help16.gif"));
			button3.setIcon(image3);
		}
		catch (Exception e)
		{
			button3.setText("Help");
		}
		
		button1.setToolTipText("Open File");
		button1.addActionListener(new OpenActionAdapter(this));
		toolBar.add(button1);
		
		button2.setToolTipText("Save File");
		toolBar.add(button2);
		
		button3.setToolTipText("Help");
		toolBar.add(button3);
		
		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(statusBar, BorderLayout.SOUTH);
		contentPane.add(textArea, BorderLayout.CENTER);
		
		file.setText("File");
		open.setText("Open");
		saveAs.setText("Save As...");
		exit.setText("Exit");
		help.setText("Help");
		about.setText("About");
		
		open.addActionListener(new OpenActionAdapter(this));
		file.add(open);
		saveAs.addActionListener(new SaveAsActionAdapter(this));
		file.add(saveAs);
		exit.addActionListener(new ExitActionAdapter(this));
		file.add(exit);
		about.addActionListener(new AboutActionAdapter(this));
		help.add(about);
		menuBar.add(file);
		menuBar.add(help);
		this.setJMenuBar(menuBar);
	}
	
	public void exitActionPerformed(ActionEvent e)
	{
		System.exit(0);
	}
	
	public void aboutActionPerformed(ActionEvent e)
	{
		AboutTextEditor dialog = new AboutTextEditor(this);
		dialog.setLocationRelativeTo(this);
		dialog.setModal(true);
		dialog.show();	
	}
	
	public void openActionPerformed(ActionEvent e)
	{
		int returnVal = chooser.showOpenDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			//System.out.println(chooser.getSelectedFile().getName());
			openFile(chooser.getSelectedFile().getName());
		}
		
		repaint();		
	}
	
	public void saveAsActionPerformed(ActionEvent e)
	{
		int returnVal = chooser.showSaveDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			//System.out.println(chooser.getSelectedFile().getName());
			saveFile(chooser.getSelectedFile().getName());
		}
		
		repaint();
	}
	
	public void openFile(String fileName)
	{
		try
		{
			// Open a file of the given name.
			File file = new File(fileName);
			// Get the size of the opened file.
			int size = (int)file.length();
			// Set # character read to zero
			int chars_read = 0;
			// Create an input reader based from the file
			FileReader in = new FileReader((File)file);
			// Create a character array of the size of the file
			char[] data = new char[size];
			// Now read the characters into the array
			while(in.ready()) 
			{
				chars_read += in.read(data, chars_read, size - chars_read);
			}
			in.close();
			// Create a temporary string from the array
			// and add to the JTextArea
			// (Your variable name may be different)
			textArea.setText(new String(data, 0, chars_read));
			// Display the file name in the status bar.
			statusBar.setText("Opened "+ fileName); 
			
			currFileName = fileName;
		}
		catch (Exception e)
		{
			statusBar.setText("Failed to open file");
		}	
	}
	
	public void saveFile(String currFileName)
	{
		// Open a file of the current name.
		File file = new File (currFileName);
		// Create an output writer.
		try
		{
			FileWriter out = new FileWriter(file);
			String text = textArea.getText();
			out.write(text);
			out.close();
			statusBar.setText("Saved "+ currFileName); 
		}
		catch (Exception e)
		{
			statusBar.setText("Failed to save file");
		}
	}
	
	class ExitActionAdapter implements ActionListener
	{
		TextEditorFrame adaptee;
		
		ExitActionAdapter(TextEditorFrame adaptee)
		{
			this.adaptee = adaptee;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			adaptee.exitActionPerformed(e);
		}
	}
	
	class AboutActionAdapter implements ActionListener
	{
		TextEditorFrame adaptee;
	
		AboutActionAdapter(TextEditorFrame adaptee)
		{
			this.adaptee = adaptee;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			adaptee.aboutActionPerformed(e);
		}
	}
	
	class OpenActionAdapter implements ActionListener
	{
		TextEditorFrame adaptee;
		
		OpenActionAdapter(TextEditorFrame adaptee)
		{
			this.adaptee = adaptee;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			adaptee.openActionPerformed(e);
		}
	}
	
	class SaveAsActionAdapter implements ActionListener
	{
		TextEditorFrame adaptee;
		
		SaveAsActionAdapter(TextEditorFrame adaptee)
		{
			this.adaptee = adaptee;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			adaptee.saveAsActionPerformed(e);
		}
	}
}