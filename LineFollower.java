import stamp.core.*;
/*
  METHODS:
    - getRoute(char[] routeArray)
    - handleRoute()
    - qtiHandler(Transmission trans)
    - getRightFollower()
    - getCenterFollower()
    - getLeftFollower()
*/
public class LineFollower{

    private boolean  rLineFollower;
    private boolean  cLineFollower;
    private boolean  lLineFollower;

    private char direction;
    private char test;
    int locationRobot =0;
    public char[] routeArray = {'w','a','d','d','w','e'};   //testArray
    private int routeSize;      // de lengte van de route
    private boolean isFollowingRoute;

    /*
    *Deze methode haalt de richting uit de array met characters die meegegeven wordt om de robot de route te laten lopen
    */

   public LineFollower()
   {
      isFollowingRoute = true;
   }

   public void getRoute(char[] routeArray)
    {
        this.routeArray = routeArray;
        routeSize = routeArray.length;


        while(routeArray[locationRobot] != 'e' &&locationRobot <routeSize)
        {
            locationRobot ++;
            return;
        }
    }

    /*
     *Deze methode haalt de huidige richting uit de route
     */
    public  void handleRoute()
    {
        routeSize = routeArray.length;
        if(routeSize >= 0 && locationRobot <= routeSize)
        {
            direction = routeArray[locationRobot];
            locationRobot ++;
        }
        else
        {
           isFollowingRoute = false;
          direction = 'e';
        }
        //System.out.println("In loop");
    }

    /*
    follow the line according to the states of the QTI's
  */
  public void qtiHandler(Transmission trans)
  {
      //get values of qti's
      getLeftFollower();
      getCenterFollower();
      getRightFollower();

      //act in an appropriate way to values of the qti's
      if(isFollowingRoute)
      {
       if(!rLineFollower && !lLineFollower)
       {
            trans.forward();
       }
       else if(!lLineFollower && rLineFollower)
       {
               trans.rotate(5, true);
       }
       else if(!rLineFollower && lLineFollower)
       {
               trans.rotate(5,false);
       }
       else if(rLineFollower && lLineFollower &&cLineFollower)
       {
         handleRoute();
         trans.forward();
         CPU.delay(3500);

        if(direction == 'd')                     //wanneer de richting d is dus rechts
         {
           trans.rotate(90, true);
           return;
         }

         else if(direction == 'a')                //wanneer de richting a is dus links
         {
           trans.rotate(90, false);
           return;
         }

         else if(direction == 'w')                //wanneer de richting w is dus rechtdoor
         {
           trans.forward();
           return;
         }

         else if(direction == 'b')                //wanneer de richting b is dus achteruit
         {
           trans.rotate(140,true);
           trans.forward();
           return;
         }

          else if(direction == 'e')               //wanneer de richting e stopt de robot
          {
             trans.pause();
             isFollowingRoute = false;
         }
         else
           trans.pause();

        }
       }
       else
          trans.pause();

      }


      /*
    *deze methode update de allerRechtse lijvolger
    */
    public boolean getRightFollower()
    {
        CPU.writePin(CPU.pin9,true);                          // zet spanning op QTI
        CPU.delay(1);                                         // wacht even
        rLineFollower = CPU.readPin(CPU.pin4);                // lees input: 0 of 1?
        CPU.writePin(CPU.pin9,false);
        return(rLineFollower);                         // haal spanning van QTI
    }
   /*
   *deze methode update de middelste lijvolger
   */
    public boolean getCenterFollower()
    {
        CPU.writePin(CPU.pin10,true);                          // zet spanning op QTI
        CPU.delay(1);                                         // wacht even
        cLineFollower = CPU.readPin(CPU.pin5);                // lees input: 0 of 1?
        CPU.writePin(CPU.pin10,false);
        return cLineFollower;                     // haal spanning van QTI
    }

   /*
   *deze methode update de allerLinkse lijvolger
   */
    public boolean getLeftFollower()
    {
        CPU.writePin(CPU.pin11,true);                         // zet spanning op QTI
        CPU.delay(1);                                         // wacht even
        lLineFollower = CPU.readPin(CPU.pin6);                // lees input: 0 of 1?
        CPU.writePin(CPU.pin11,false);
        //System.out.println(lLineFollower);                   // haal spanning van QTI
        return(lLineFollower);
    }
}