   import java.io.*;
   import java.awt.*;
   import java.awt.BorderLayout;
   import java.awt.Color;
   import java.awt.Graphics;
   import java.awt.event.*;
   import java.awt.image.*;
   import javax.swing.*;
   import java.util.*;

/**
	Class to represent a whiteboard upon which straignt lines can be drawn in 5 colours.
	
	@author Paul Fioravanti
	@version 15/5/05
*/
    public class ClientWhiteboard extends JPanel implements MouseListener, MouseMotionListener
   {
   //declare variables
      private int x1, y1, x2, y2; //x1, y1 are the start points, x2,y2 are mouse end points
      private boolean dragging;
      private static int WIDTH = 500, HEIGHT = 500;
      private BufferedImage bi;
      private Graphics g;
      private ClientUI ui;
   
   /**
     Constructor to create a whitboard
     @param ui an object of the UI class
   */
       public ClientWhiteboard(ClientUI ui)
      {
      //set passed in ui to this class' ui.
         this.ui = ui;
      //Add listeners for the interfaces
         addMouseMotionListener(this);
         addMouseListener(this);
      
      //set size of whiteboard and its visibility
         setSize(WIDTH, HEIGHT);
         setVisible(true);
      //create a buffered image for the whiteboard so lines won't be lost if windows are
      //layed on top of it.
         bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
         g = bi.getGraphics();
      //set whiteboard's color to grey so white lines will be visible
         g.setColor(Color.LIGHT_GRAY);
         g.fillRect(0, 0, WIDTH, HEIGHT);
         g.setColor(ui.getColor()); //needs to be changed for when color button pressed
      }
   
   /**
   	Method to draw a line on the whiteboard.
   	@param x1 the x coordinate of the line's start point
   	@param y1 the y coordinate of the line's start point
   	@param x2 the x coordinate of the line's end point
   	@param y2 the y coordinate of the line's end point
   */
       public void drawLine(int x1, int y1, int x2, int y2)
      {	
         if (ui.isLoggedIn())
         {
         	//get the colour from the UI class.
            g.setColor(ui.getColor());
            g.drawLine(x1, y1, x2, y2);
            repaint();
         }
      }
   	
   /**
   	Method to draw a line on the whiteboard that is 'drawn' from
   	another user on the server.
   	@param x1 the x coordinate of the line's start point
   	@param y1 the y coordinate of the line's start point
   	@param x2 the x coordinate of the line's end point
   	@param y2 the y coordinate of the line's end point
   	@param color the color to draw the line
   */
       public void drawLine(int x1, int y1, int x2, int y2, Color color)
      {	
         if (ui.isLoggedIn())
         {
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);
            repaint();
         }
      }
   	
   /**
   	Method to draw up the whiteboard
   	@param g the graphics to draw.
   */
       protected void paintComponent(Graphics g)
      {
         super.paintComponent(g);
      	//draw up the BufferedImage
         g.drawImage(bi, 0, 0, this);
      }
   
   /**
   	Method to handle MouseEvents when the mouse is pressed
   	@param e the MouseEvent generated
   */
       public void mousePressed(MouseEvent e)
      {
      //make sure to ignore accidental mouse presses while dragging ie right-click
         if (dragging == true)
            return;  
      
      //sets x1 and y1 at the point where the mouse clicks	
         x1 = e.getX();
         y1 = e.getY();
      
      //set dragging to true as the mouse will be dragging while its held down
         dragging = true;
      }
   
   /**
   	Method to handle MouseEvents generated when mouse is dragged
   	@param e the MouseEvent
   */
       public void mouseDragged(MouseEvent e)
      {
      //checks whether user is dragging, if not, return
         if (dragging == false)
            return;
      
      //sets x2 and y2 at the end of the formed line
         x2 = e.getX();
         y2 = e.getY();
      }
   
   /**
   	Method to handle MouseEvents generated when the mouse button is released.
   	@param e the MouseEvent
   */
       public void mouseReleased(MouseEvent e)
      {
      //check to see whether user is dragging, if not return as user hasn't started
      //a line
         if (dragging == false)
            return;
      	//once mouse is depressed, user is no longer dragging to set to false	
         dragging = false;
      	
      //set final x2 and y2 values
         x2 = e.getX();
         y2 = e.getY();
      
         if (ui.isLoggedIn() == true)
         {
         //get the name of the colour to draw from the UI
            String color = ui.getColorString();
         //Had problems with sending " " empty strings to represent spaces
         //so am using ascii value of space
            char s = (char)32;
         
         //draw the line once mouse is released only if it is more than zero length; no debug message
         //needed.
         //If line starts or ends outside the whiteboard, do not draw the line and instead print
         //a message to the debugOutput prompting user to stay within the lines.	
            if (x1 == x2 && y1 == y2)
            {
               return;
            }
            else if ((x2 != x1 || y2 != y1) && (x1 >= 0 && x1 < 500) && (y1 >= 0 && y1 < 500) && (x2 >= 0 && x2 < 500) && (y2 >= 0 && y2 < 500))
            {
               ClientUI.debugOutput.append("DRAW " + x1 + s + y1 + s + x2 + s + y2 + s + color + "\n");
               ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
               ui.getToServer().println("DRAW " + x1 + s + y1 + s + x2 + s + y2 + s + color);
               ui.getToServer().flush();
               drawLine(x1, y1, x2, y2);
            }
            else
            {
               ClientUI.debugOutput.append("ERROR: Line must start and end within whiteboard boundaries." + "\n");
               ClientUI.debugOutput.setText(ClientUI.debugOutput.getText());
               return;
            }
         }
      }
   
   //methods needed for the implemented interfaces but
   //are unused in this application
       public void mouseClicked(MouseEvent e) {}
       public void mouseEntered(MouseEvent e) {}
       public void mouseExited(MouseEvent e) {}
       public void mouseMoved(MouseEvent e) {}
   }
