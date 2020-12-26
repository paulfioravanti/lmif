   import java.util.*;

    public class BufferQueue
   {
      String[] buffer;
		int in = 0;
		int out = 0;
		int count = 0;
      int size;
   	  
       public BufferQueue(int size)
      {
         this.size = size;
			buffer = new String[size];
      }
   
       synchronized void enter(String string) throws InterruptedException
      {
			while (count == size)
				wait();
         buffer[in] = string;
			System.out.println("Producer: creating string at " + in);
         count++;
			in = (in + 1) % size;
         if (count > 0)
            notify();			
      }
   
       synchronized String remove() throws InterruptedException
      {
         while (count == 0)
            wait();
         Object string = buffer[out];
         buffer[out] = null;
			System.out.println("Consumer: reading string at " + out);
         count--;
			out = (out + 1) % size;
         if (count < size)
            notify();
         return (String)string;		
      }
   	
   }