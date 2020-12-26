   import java.lang.Thread;

    public class ProducerConsumer extends Thread
   {
      int type;
      BufferQueue bq;
   	
       public ProducerConsumer(BufferQueue bq, int type)
      {
         this.bq = bq;
         this.type = type;
      }
   
       public void run()
      {
         if (type == 0)
         {
            try
            {
               while (true)
               {
                  bq.enter("abc");
                  //Thread.sleep(500);
               }
            }
                catch (InterruptedException e)
               {
                  System.out.println("Enter Interrupted");
               }
         }
         else
         {
            try
            {
               while (true)
               {
                  bq.remove();
                  //Thread.sleep(500);
               }
            }
                catch (InterruptedException e)
               {
                  System.out.println("Remove Interrupted");
               }
         
         }
      }
       public static void main(String[] args)
      {
         BufferQueue bq = new BufferQueue(20);
         ProducerConsumer producer = new ProducerConsumer(bq, 0);
         ProducerConsumer consumer = new ProducerConsumer(bq, 1);	
         producer.start();
         consumer.start();	 
      }
   }
