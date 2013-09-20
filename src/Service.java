import java.util.concurrent.ConcurrentLinkedQueue;
import java.lang.Runnable;


class Service implements Runnable
{
  ConcurrentLinkedQueue<Message> inbox;
  ConcurrentLinkedQueue<Message> outbox;
  boolean alive = true;
  int myid = -1;
  
  
  public void run()
  {
    while(alive)
    {
      Message m = inbox.poll();
      if(m != null)
      {
        handleMessage(m);
      }
    }
  }
  
  public void sendMessage(int dest, String message)
  {
    Message m = new Message(myid, dest, message);
    outbox.offer(m);
  }
  
  public void handleMessage(Message m)
  {
    String msg = m.text;
    System.out.println(msg);
  }
  
  public Service()
  {
    inbox = new ConcurrentLinkedQueue<Message>();
    outbox = new ConcurrentLinkedQueue<Message>();
  }
  
}