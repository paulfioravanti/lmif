import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
	Java Bean that fires events periodically in accordance to how it hears incoming events
	from its own Timer
*/
public class Ticker extends JLabel implements ActionListener
{	
		private ActionListener[] actionArray = new ActionListener[1];
		private int delay = 500;
		private Timer t;
		
		/**
			No argument constructor that simply adds the Ticker class itself to the
			Timer's actionListeners.
		*/
		public Ticker()
		{	
			t = new Timer(delay, null);
			t.addActionListener(this);
		}
		
		/**
			Method to return the ticker's delay setting.
			@return delay the length of time (ms) before another event is fired
		*/
		public int getDelay()
		{
			return delay;
		}
		
		/**
			Method to set the delay.
			@param delay the length of time (ms) before another event is fired 
		*/
		public void setDelay(int delay)
		{ 
			this.delay = delay;
			t.setDelay(delay);
		}
		
		/**
			Method to add an actionListener to the Ticker (limit of 1)
			@param al the actionListener to add
		*/
		public void addActionListener(ActionListener al)
		{
			actionArray[0] = al;
		}
		
		/**
			Method to remove an actionListener from the Ticker.
			@param al the actionListener to remove
		*/
		public void removeActionListener(ActionListener al)
		{
			actionArray[0] = null;
		}
		
		/**
			Method to return the actionListers of the Ticker.
			@return actionArray the array of actionListeners of Ticker
		*/
		public ActionListener[] getActionListeners()
		{
			return actionArray;
		}
		
		/**
			Method to 'fire' events by calling each of the listeners registered
			with the Ticker.
			@param e the ActionEvent passed in to the Ticker
		*/	
		public void actionPerformed(ActionEvent e)
		{
			actionArray[0].actionPerformed(e);
		}
		
		/**
			Method to call the start() method of Ticker's Timer object.
		*/
		public void start()
		{
			t.start();
		}
		
		/**
			Method to call the stop() method of Ticker's Timer object
		*/
		public void stop()
		{
			t.stop();
		}	
}