import stamp.core.*;
/*
  METHODS:
    - recordRoute()
    - isBytesAvailable()
    - getRouteString()
*/
public class Bluetooth
{

        final static int SERIAL_RX_PIN  = CPU.pin2;
        static Uart rxUart = new Uart(Uart.dirReceive,SERIAL_RX_PIN,Uart.dontInvert,Uart.speed9600,Uart.stop1);
        private String routeString;
        private boolean recording;

        public Bluetooth()
        {
                recording = false;
                routeString = "";
        }

        public void recordRoute()
        {
                while(rxUart.byteAvailable())
                {
                        char receivedChar = (char)rxUart.receiveByte();
                        System.out.println(receivedChar);
                        if(receivedChar == 's')
                        {
                                //Start of route.
                                recording = true;
                                continue;
                        }
                        else if(receivedChar == 'e')
                        {
                                //End of route.
                                recording = false;
                                continue;
                        }
                        if(recording)
                        {
                                routeString += receivedChar;
                        }
                        //System.out.println(routeString);

                }
        }

        public boolean isBytesAvailable()
        {
                return rxUart.byteAvailable();
        }

        public String getRouteString()
        {
                return routeString;
        }
}