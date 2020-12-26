import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.*;

/**
	A simple text editor created for User Interfaces Practical 2
	@author Paul Fioravanti
	@version 1.0 8/3/05 
*/
public class TextEditor
{
	/**
		Constructor for the objects of class TextEditor
	*/
	public TextEditor()
	{
		TextEditorFrame frame = new TextEditorFrame();
		frame.validate();
		frame.addWindowListener(new ShutDown());
		frame.setVisible(true);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		
		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;
		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;
		
		frame.setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
		 
	}
	
	/**
		Inner class to TextEditor for handling window-closing events
	*/
	class ShutDown extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
	
	/**
		The main method to start the application
		@param args command line parameters
		@return void
	*/
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		new TextEditor();
	}
}
