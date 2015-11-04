import stamp.core.*;
/*
  METHODS:
    - detectLIR()
    - detectRIR()
    - detectRA()
    - detectLA()
    - updateSensors()
    - getLeftA()
    - getRightA()
    - getLeftIR()
    - getRightIR()
*/

public class CollisionDetector
{
        private boolean leftIR;
        private boolean rightIR;
        private boolean rightA;
        private boolean leftA;

        private PWM irSenderL;
        private PWM irSenderR;

        public CollisionDetector()
        {
                irSenderL = new PWM(CPU.pin14);
                irSenderL.update(1,2);
                irSenderR = new PWM(CPU.pin3);
                irSenderR.update(1,2);
        }

        public void detectLIR()
        {
                irSenderL.start();
                irSenderL.update(1,2);
                CPU.delay(5);
                leftIR = CPU.readPin(CPU.pin15);
                irSenderL.stop();
        }

        public void detectRIR()
        {
                irSenderR.start();
                irSenderR.update(1,2);
                CPU.delay(5);
                rightIR = CPU.readPin(CPU.pin1);
                irSenderR.stop();
        }

        public void detectRA()
        {
                rightA = CPU.readPin(CPU.pin8);
        }

        public void detectLA()
        {
                leftA = CPU.readPin(CPU.pin7);
        }

        public void updateSensors()
        {
                this.detectLA();
                this.detectRA();
                //this.detectRIR();
                //this.detectLIR();
                //print values

                /*System.out.println("Left Antenna");
                System.out.println(leftA ? leftA : leftA);
                System.out.println("Right Antenna");
                System.out.println(rightA ? rightA : rightA);
                System.out.println("Left IR");
                System.out.println(leftIR ? leftIR : leftIR);
                System.out.println("Right IR");
                System.out.println(rightIR ? rightIR : rightIR); */

                CPU.delay(20);
        }

        public boolean getLeftA()
        {
                return leftA;
        }

        public boolean getRightA()
        {
                return rightA;
        }

        public boolean getLeftIR()
        {
                return leftIR;
        }

        public boolean getRightIR()
        {
                return rightIR;
        }
}