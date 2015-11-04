import stamp.core.*;
/*
  METHODS:
    - getSpeed()
    - setSpeed(int speed)
    - stop()
    - update(int pulselength)
    - forwardUsingSpeed()
    - start()
*/

public class Motor
{
        private PWM motor;
        private int speed;
        private int regulator;

        public Motor(boolean isRight)
        {
                speed = 0;
                if(isRight == true)
                {
                        motor = new PWM(CPU.pin12, 173, 2304);
                        regulator = 4;
                }
                else if(isRight == false)
                {
                        motor = new PWM(CPU.pin13, 173, 2304);
                        regulator = -4;
                }

        }
        public int getSpeed()
        {
                return speed;
        }

        //dit kan tot max 5
        public void setSpeed(int speed)
        {
                if(speed >= -5 && speed <= 5)
                {
                  this.speed = speed;
                }

        }

        public void stop()
        {
                motor.stop();
        }

        public void update(int pulselength)
        {
                motor.update(pulselength, 2304);
        }

        public void forwardUsingSpeed()
        {
                this.update(173+speed*regulator);
        }

                public void reverseUsingSpeed()
                {
                                this.update(173+negate(speed)*regulator);
                }

                private int negate(int number)
                {
                        return 0 - number;
                }

        public void start()
        {
                motor.start();
        }


}