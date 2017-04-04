import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.InputEvent;
/*this class is used to listen to the events and to process on the input events*/
class EventsListener extends Thread
{
    Socket socket;
    ObjectInputStream input;
    Robot robot;//this robot class will be used to handle the events that is sent from the other device 
    final static int  KEY_PRESSED=0;
    final static int KEY_RELEASED=1;
    final static int MOUSE_PRESSED=2;
    final static int MOUSE_RELEASED=3;
    final static int MOUSE_MOVED=4;


    EventsListener(Socket socket,Robot robot)
    {
        this.socket=socket;
        this.robot=robot;
        start();

    }
    public void run()
    {
        try
        {

            input=new ObjectInputStream(socket.getInputStream());
            while(true)
            {
                int eventtype=(int)input.readObject();
                System.out.println(eventtype);
                /*this will first detect the actual event that is to be handle that is defined at the top of the class 
                then it will detect the button or the cordinates accordingly and will use the robot class' methods to implement it*/
                switch(eventtype)
                {
                    case 0 :int keycode1=(int)input.readObject();
                    robot.keyPress(keycode1);
                    break;
                    case 1 : int keycode2=(int)input.readObject();
                    robot.keyRelease(keycode2);
                    break;
                        /*For cases 2 and 3 we have to change the values of the buttons that has been sent because when you manually press the button 
                        the value sent will be without masking and when we want to automatically press the button that is sent we have to use masked butttons*/
                    case 2 : int butt1=(int)input.readObject();
                        if(butt1==3)
                        {
                            butt1=4;
                        }

                            else
                            {
                                butt1=16;
                            }

                        System.out.println("Button pressed"+butt1);
                    robot.mousePress(butt1);
                    break;
                    case 3 : int butt2=(int)input.readObject();
                        System.out.println(butt2);
                        if(butt2==3)
                        {
                            butt2=4;
                        }

                            else
                            {
                                butt2=16;
                            }
                    robot.mouseRelease(butt2);
                    break;
                    case 4 :  int x=(int)input.readObject();
                        System.out.println(x);

                        int y=(int)input.readObject();

                        System.out.println(y);
                       robot.mouseMove(x,y);
                        break;
                    default :System.out.println("default case is executed :something is wrong");
                }
            }

        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException w)
        {
            System.out.println("io eventlis");
        }
        catch(IllegalArgumentException w)
        {
            System.out.println(w.getMessage());
        }



    }
}
