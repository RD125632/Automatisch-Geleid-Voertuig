import stamp.core.*;
/*
  METHODS:
    -reverse()
    -forward()
    -pause()
    -slowDown(boolean isDirectionForward)
    -rotate(int degrees, boolean clockwise)
    -rotateSlow(int degrees, boolean clockwise)
 */
public class Transmission
{
        Motor motorL;
        Motor motorR;

        public Transmission()
        {
                motorL = new Motor(false);
                motorR = new Motor(true);
        }
        /*
          drive forward without stopping
         */
        public void forward()
        {
                motorR.update(193);
                motorL.update(153);
        }
        /*
          drive back without stopping
         */
        public void reverse()
        {
                motorR.update(149);
                motorL.update(195);
        }

        /*
          stops the motors (puts a frequency on the motors that causes them to stand still)
         */
        public void pause()
        {
                motorR.update(173);
                motorL.update(173);
        }

        /*
         * Updates both motors using the set speed in both motors.
         */
        public void forwardUsingSpeed()
        {
                motorR.forwardUsingSpeed();
                motorL.forwardUsingSpeed();
        }

        public void reverseUsingSpeed()
        {
                motorR.reverseUsingSpeed();
                motorL.reverseUsingSpeed();
        }
        /*
         * Sets speed in both motors using parameter speed.
         */
        public void setSpeed(int speed)
        {
                motorR.setSpeed(speed);
                motorL.setSpeed(speed);
        }

        /*
          Slows the bot down to speed 0.
          @param boolean isDirectionForward : If the bot is driving forward give true, if the bot is driving backwards give false
         */
        public void slowDown(boolean isDirectionForward)
        {
                if(!isDirectionForward)
                {
                        for (int i=11; i>=0; i--)
                        {
                                motorL.update(173+i*2);
                                motorR.update(173-i*2);
                                CPU.delay(1000);
                        }
                }
                else
                {
                        for (int i=11; i>=0; i--)
                        {
                                motorR.update(173+i*2);
                                motorL.update(173-i*2);
                                CPU.delay(1000);
                        }
                }
        }

 /*
         rotates the robot for the given degrees.
         @param int degrees       : degrees the bot has to rotate
         @param boolean clockwise : true lets the bot rotate clockwise, flase lets the bot rotate counterclockwise
         */
        public void rotate(int degrees, boolean clockwise)
        {
                if(!clockwise)
                {
                        switch(degrees){
                        case 90 :
                                motorR.update(149);
                                motorL.update(149);
                                CPU.delay(5800); //3450
                                break;
                        default :
                                motorR.update(149);
                                motorL.update(149);
                                CPU.delay(39*degrees);
                                break;
                        }
                }
                else if(clockwise)
                {
                        switch(degrees){
                        case 90 :
                                motorR.update(195);
                                motorL.update(195);
                                CPU.delay(5800);
                                break;
                        default :
                                motorR.update(195);
                                motorL.update(195);
                                CPU.delay(39*degrees);
                                break;
                        }
                }
        }


        /*
         rotates the robot for the given degrees while driving forward.
         In this way we try to achieve a better and faster way of making turns
         @param int degrees       : degrees the bot has to rotate
         @param boolean clockwise : true lets the bot rotate clockwise, flase lets the bot rotate counterclockwise
         */
        public void rotateSlow(int degrees, boolean clockwise)
        {
                if(!clockwise)
                {
                        motorL.update(160);
                        CPU.delay(degrees*20);
                }
                else if(clockwise)
                {
                        motorR.update(186);
                        CPU.delay(degrees*20);
                }
        }
}