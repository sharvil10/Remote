import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import java.awt.GraphicsDevice.*;
import java.awt.GraphicsEnvironment.*;
import java.awt.*;
/*this class is used to capture the screen and then send it to the other device*/
public class ScreenCapture extends Thread
{
    static Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    static Rectangle rect=new Rectangle(screen);//to get the screensize of current device
    Robot robot;//Robot is default class from java it provides method to takethe screenshots
    Socket socket;
    static ObjectOutputStream output;
    ScreenCapture(Robot robot,Socket socket)
    {
        this.robot=robot;
        this.socket=socket;
        start();
    }
    public void run()
    {
        try
        {
           output=new ObjectOutputStream(socket.getOutputStream());//to send the screenshot to other device
           

        }
        catch(IOException e)
        {
           System.out.println("IOexception in ScreenCapture class");
        }
        while(true)
        {
            
           BufferedImage bfimg=robot.createScreenCapture(rect);
           //robot class captures the image and saves it as bufferedimage that can not be send with server so we have to convert it in ImageIcon and then send it
           ImageIcon image=new ImageIcon(bfimg); 
           try
           {
               output.writeObject(image);
               output.reset();
               sleep(100);
           }
           catch(IOException e)
           {
               System.out.println("ioexscreencap");
           }
           catch(InterruptedException e)
           {
               System.out.println("ioexscreencap");
           }
           

        }
    }
}
