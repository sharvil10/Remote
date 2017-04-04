import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
/*this class will listen to the events on the frame that is created by im.java class and send it to other device */
public class Events implements MouseListener,MouseMotionListener,KeyListener
{
    final static int  KEY_PRESSED=0;
    final static int KEY_RELEASED=1;
    final static int MOUSE_PRESSED=2;
    final static int MOUSE_RELEASED=3;
    final static int MOUSE_MOVED=4;
    Rectangle clientScreen;
    Socket socket;
    Frame frm;
    ObjectOutputStream out;
    //we will need the frame that is used by im.java to listen to the events
    Events(Socket socket,Frame frm,Rectangle rect)
    {
        this.socket=socket;
        this.frm=frm;
        this.clientScreen=rect;
        try{
            out=new ObjectOutputStream(socket.getOutputStream());
        }
        catch(IOException e)
        {

        }
            frm.addKeyListener(this);
            frm.addMouseListener(this);
            frm.addMouseMotionListener(this);

        
    }
    //Some methods are not implemented beacause it was unnecessary but we have to write it beacause it implemets the interface
    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e)
    {
        try{/*We have to divide the two device's resolution because both will be different size thus the point in the other will be from that is in the
            other it will give the ratio then we have to multiply it by the according co-ordinate to send */
        double xScale = clientScreen.getWidth()/frm.getWidth();
        System.out.println("xScale: " + xScale);
        double yScale = clientScreen.getHeight()/frm.getHeight();
        System.out.println("yScale: " + yScale);
        System.out.println("Mouse Moved");
        out.writeObject(this.MOUSE_MOVED);
        out.writeObject((int)(e.getX()*xScale));
        out.writeObject((int)(e.getY()*yScale));
        System.out.println((int)(e.getX()*xScale));
        System.out.println("out write");
        out.flush();}
        catch(IOException x)
        {

        }
    }

    //this is not implemented
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        try{
        System.out.println("Mouse Pressed");
        out.writeObject(this.MOUSE_PRESSED);
        int button = e.getButton();
        System.out.println(button);

        out.writeObject(button);
        out.flush();
        }
        catch(IOException x)
        {

        }
    }

    public void mouseReleased(MouseEvent e) {
        try{
        System.out.println("Mouse Released");
        out.writeObject(this.MOUSE_RELEASED);
        int button = e.getButton();
        out.writeObject(button);
        out.flush();
        }
        catch(IOException x)
        {

        }
    }

    //no need to be implemented
    public void mouseEntered(MouseEvent e) {
    }

    //not need to be implemented
    public void mouseExited(MouseEvent e) {

    }

    //not need to be implemented
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        try{
        System.out.println("Key Pressed");
        out.writeObject(this.KEY_PRESSED);
        out.writeObject(e.getKeyCode());
        out.flush();
        }
        catch(IOException x)
        {

        }
    }

    public void keyReleased(KeyEvent e) {
        try{
        System.out.println("Mouse Released");
        out.writeObject(this.KEY_RELEASED);
        out.writeObject(e.getKeyCode());
        out.flush();
        }
        catch(IOException x)
        {

        }
    }

}
