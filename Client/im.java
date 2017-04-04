import java.awt.Rectangle.*;
import java.awt.image.BufferedImage;
import java.awt.GraphicsDevice.*;
import java.awt.GraphicsEnvironment.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.*;
import java.net.*;
/*this class is used to display the images recived from other device*/
public class im extends Thread
{
    static Frame frm=new Frame("Frame");
    static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    static GraphicsDevice dev= ge.getDefaultScreenDevice();
    static Image img=null;
    ObjectInputStream input;
    Socket socket;

    public im(Socket socket)throws IOException
    {
        this.socket=socket;
     	frm.setUndecorated(true);   
        start();
    }
    public void run()
    {
        try
        {
	    
            input=new ObjectInputStream(socket.getInputStream());
            while(true)
            {
                ImageIcon img1=(ImageIcon)input.readObject();
                img=img1.getImage();
                //painting the image that is recived
                frm.add(new Component(){
                    public void paint(Graphics g){
                            super.paint(g);g.drawImage(img,0,0,frm.getWidth(),frm.getHeight(),this);}});
                dev.setFullScreenWindow(frm);
                frm.validate();
            }


        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Classnotfound");
        }
        catch(IOException e)
        {
		System.exit(0);            
        }

    }
    public static Frame getFrame()
    {
        return frm;
    }
}
