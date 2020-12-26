import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AboutTextEditor extends JDialog implements ActionListener
{
	JButton buttonOK = new JButton();
	JPanel contentPane;
	JPanel buttonPane = new JPanel();
	BorderLayout contentLayout = new BorderLayout();
	FlowLayout buttonLayout = new FlowLayout();
	
	ImageIcon aboutIcon;
	JLabel graphicLabel = new JLabel();
	
	String title = "TextEditor";
	String description = "A simple text editor using Swing";
	String version = "Version 1.0";
	String author = "Author: Paul Fioravanti";
	String copyright = "Copyright (c) 2005";
	JPanel infoPane = new JPanel();
	GridLayout infoLayout = new GridLayout(0,1);
	
	AboutTextEditor(Frame parent)
	{
		super(parent);
		
		setTitle("About TextEditor");
		setResizable(false);
		
		try
		{
			aboutIcon = new ImageIcon(AboutTextEditor.class.getResource("about.gif"));
			graphicLabel.setIcon(aboutIcon);
		}
		catch (Exception e)
		{
			graphicLabel.setText("TextEditor");
		}
		
		buttonOK.setText("OK");
		
		contentPane = (JPanel)this.getContentPane();
		contentPane.setLayout(contentLayout);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		contentPane.add(graphicLabel, BorderLayout.WEST);
		
		infoPane.setLayout(infoLayout);
		JLabel titleLable = new JLabel(title);
		JLabel descriptionLable = new JLabel(description);
		JLabel versionLable = new JLabel(version);
		JLabel authorLable = new JLabel(author);
		JLabel copyrightLable = new JLabel(copyright);
		infoPane.add(titleLable);
		infoPane.add(descriptionLable);
		infoPane.add(versionLable);
		infoPane.add(authorLable);
		infoPane.add(copyrightLable);
		
		contentPane.add(infoPane, BorderLayout.CENTER);
		
		graphicLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		infoPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		buttonPane.setLayout(buttonLayout);
		buttonPane.add(buttonOK);
		
		buttonOK.addActionListener(this);
		
		pack();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		dispose();
	}
}