import stamp.core.*;
/*
  METHODS:
    - parseA(boolean collisionLA, boolean collisionRA, Transmission trans)
    - parseIR(boolean collisionLIR, boolean collisionRIR, Transmission trans)
    - checkCollision(Transmission trans)
*/

public class CollisionHandler
{
        private boolean collisionLA;
        private boolean collisionRA;
        private boolean collisionLIR;
        private boolean collisionRIR;
        private boolean collisionLLF;
        private boolean collisionRLF;
        private boolean collisionMLF;

        private CollisionDetector collision;
        private LineFollower lineFollower;

        public CollisionHandler()
        {
                collision = new CollisionDetector();
                lineFollower = new LineFollower();
        }

        public void parseA(boolean collisionLA, boolean collisionRA, Transmission trans)
        {
                if(!collisionLA || !collisionRA)
                {
                        CPU.delay(1000);
                        collision.detectLA();
                        collision.detectRA();

                        collisionLA = collision.getLeftA();
                        collisionRA = collision.getRightA();


                        if(!collisionRA && !collisionLA)
                        {
                                //make a turn of 180 degrees
                                System.out.println("Double antenna");
                                trans.reverse();
                                CPU.delay(5000);
                                trans.pause();
                                trans.rotate(180, false);
                        }
                        else if(!collisionLA)
                        {
                                //turn right
                                System.out.println("Left antenna");
                                trans.reverse();
                                CPU.delay(5000);
                                trans.pause();
                                trans.rotate(90, true);
                        }
                        else if(!collisionRA)
                        {
                                //turn left
                                System.out.println("Right antenna");
                                trans.reverse();
                                CPU.delay(5000);
                                trans.pause();
                                trans.rotate(90, false);
                        }
                }
        }

        public void parseIR(boolean collisionLIR, boolean collisionRIR, Transmission trans)
        {
                if(collisionLIR || !collisionRIR)
                {
                        CPU.delay(1000);
                        collision.detectLIR();
                        collision.detectRIR();

                        collisionLIR = collision.getLeftIR();
                        collisionRIR = collision.getRightIR();
                        //detected hole in ground or end of map
                        if(collisionRIR)
                        {
                                //trans.rotate(180, false);
                                System.out.println("Detected hole in IR");
                                trans.pause();
                                trans.reverse();
                                CPU.delay(5000);
                                trans.pause();

                        }
                        //detected object
                        if(!collisionLIR)
                        {
                                //trans.rotate(180, false);
                                System.out.println("Detecter object in IR");
                                trans.pause();
                                trans.reverse();
                                CPU.delay(5000);
                                trans.pause();
                        }
                }
        }

        public void checkCollision(Transmission trans)
        {
                collision.updateSensors();                                        //get IR- and antenna values
                //trans.forward();                                                //drive forward
                //respond to potential colission of IR sensors
                //parseIR(collision.getLeftIR(), collision.getRightIR(), trans);
                //parseA(collision.getLeftA(), collision.getRightA(), trans);     //respond to potential colission of antenna sensors
                lineFollower.qtiHandler(trans);                                 //keep following the line
                parseA(collision.getLeftA(), collision.getRightA(), trans);


        }
}