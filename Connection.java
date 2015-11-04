import stamp.core.*;
/*
  METHODS:
    - getPriotity()
    - refreshPriority()
    - updatePriority()
    - togglePriority()
    - parseIR(Transmission trans)
*/

public class Connection
{
        private RemoteControl irRX;
        //private Transmission trans;
        private boolean hasPriority;

        public Connection()
        {
                irRX = new RemoteControl(CPU.pin1);
                hasPriority = false;
                //this.trans = trans;
                //parseIR();
        }

        public boolean getPriority()
        {
                return hasPriority;
        }

        public void refreshPriority()
        {
                //System.out.println(irRX.decodeMsg());
                if(irRX.decodeMsg() == true)
                {
                        //this.updatePriority();
                        hasPriority = true;
                }
                else
                {
                        hasPriority = false;
                }
        }

        public void updatePriority(Transmission trans)
        {
                if(irRX.decodeMsg() == true)
                {
                        System.out.println(irRX.getButtonValue());
                        if(irRX.getButtonValue() == 9)
                        {
                                this.togglePriority();
                                trans.pause();
                                //System.out.println("Past TogglePrio");
                                CPU.delay(1000);
                        }

                }
        }

        public void togglePriority()
        {
                hasPriority = !hasPriority;
        }


        public void parseIR(Transmission trans)
        {
                if(irRX.decodeMsg() == true)
                {
                        //System.out.println(msg.getButtonValue());
                        //this.refreshPriority();
                        switch(irRX.getButtonValue())
                        {
                        case 0:
                                //go dir NW
                                trans.rotate(45,false);
                                trans.forward();
                                break;
                        case 1:
                                trans.forward();
                                break;
                        case 2:
                                //go dir NE
                                trans.rotate(45,true);
                                trans.forward();
                                break;
                        case 3:
                                trans.rotate(90, false);
                                trans.forward();
                                break;
                        case 4:
                                trans.pause();
                                break;
                        case 5:
                                trans.rotate(90,true);
                                trans.forward();
                                break;
                        case 6:
                                //go dir SW
                                trans.rotate(135, false);
                                trans.forward();
                                break;
                        case 7:
                                trans.reverse();
                                break;
                        case 8:
                                //go dir SE
                                trans.rotate(135, true);
                                trans.forward();
                                break;
                        case 9:
                                //Take priority, toggle
                                //this.togglePriority();
                                break;
                        case 10:
                                trans.reverse();
                                break;
                        case 11:
                                trans.forward();
                                break;
                        case 12:
                                trans.rotate(90, true);
                                trans.forward();
                                break;
                        case 13:
                                trans.rotate(90,false);
                                trans.forward();
                                break;
                        case 14:
                                trans.pause();
                                break;
                        }

                }

        }

}