  import stamp.core.*;
  /*
    METHODS:
      - main()
      - priorityCheck()
  */

  public class PriorityHandler
  {
          private Transmission trans;
          private Connection connection;
          private CollisionHandler collhandler;

          public static void main()
          {
                  new PriorityHandler();
          }

          public PriorityHandler()
          {
                  trans = new Transmission();
                  connection = new Connection();
                  collhandler = new CollisionHandler();
                  //CPU.writePin(CPU.pins[2],true);
                  while(true)
                  {
                          priorityCheck();

                  }
          }

          public void priorityCheck()
          {
                  //get remote priority
                  connection.updatePriority(trans);
                 // if remote has priority
                 //System.out.println(connection.getPriority());
                 if(connection.getPriority())
                  {;
                          connection.parseIR(trans);
                  }
                 // otherwise, collision has priority
                  else
                  {
                         collhandler.checkCollision(trans);
                  }

          }
  }
