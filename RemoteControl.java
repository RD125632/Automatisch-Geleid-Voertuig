import stamp.core.*;
/*
  METHODS:
    - getButtonValue()
    - decodeMsg()
    - decodeRawMsg(int rawMsg)
*/

public class RemoteControl
{

        private int sensorPin = -1;
        private int buttonValue;

        public RemoteControl( int sensorPin )
        {
                this.sensorPin = sensorPin;
        }

        public int getButtonValue()
        {
                return buttonValue;
        }

        public boolean decodeMsg()
        {
                int result = 0;
                boolean err = true;
                int pulseLengte;

                if( CPU.pulseIn (30000, this.sensorPin, false) > 200 )
                {
                        err = false;
                        for( int idx = 0; (idx < 12) && (!err); idx++ )
                        {
                                pulseLengte = CPU.pulseIn (10000, this.sensorPin, false );

                                if (pulseLengte == -1 )
                                {
                                        err = true;
                                }
                                else
                                {
                                        if( pulseLengte > 100 )
                                        {
                                                result |= 0x01;
                                        }
                                        result <<=1;
                                }
                        }
                }

                decodeRawMsg((result>>1));

                return !err;
        }

        public void decodeRawMsg(int rawMsg)
        {

                //System.out.println(rawMsg>>5&0x7F);//Print raw waarde knop
                switch( (rawMsg>>5)&0x7F )
                {
                // 000 0000
                case 0x00: buttonValue = 0; break;
                // 100 0000
                case 0x40: buttonValue = 1; break;
                // 010 0000
                case 0x20: buttonValue = 2; break;
                // 110 0000
                case 0x60: buttonValue = 3; break;
                // 001 0000
                case 0x10: buttonValue = 4; break;
                // 101 0000
                case 0x50: buttonValue = 5; break;
                // 011 0000
                case 0x30: buttonValue = 6; break;
                // 111 0000
                case 0x70: buttonValue = 7; break;
                // 000 1000
                case 0x08: buttonValue = 8; break;
                // 100 1000
                case 0x48: buttonValue = 9; break;
                //ch-
                case 0x4D: buttonValue = 10; break;
                //ch+
                case 0x0D: buttonValue = 11; break;
                //vol+
                case 0x24: buttonValue = 12; break;
                //vol-
                case 0x64: buttonValue = 13; break;
                //poweroffknop
                case 0x54: buttonValue = 14; break;
                default: buttonValue = -1; break;
                }
        }
}