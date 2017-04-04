import java.net.*;
import javax.swing.*;
import java.awt.BorderLayout.*;
import java.io.*;
import java.awt.GraphicsDevice.*;
import java.awt.GraphicsEnvironment.*;
import java.awt.Robot;
import java.awt.*;
import java.awt.event.*;
/*this class is used to display the gui and it is the main class for one device that is going to be controlled*/
public class Server extends JFrame
{
    public Server()throws UnknownHostException
    {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip=inetAddress.getHostAddress();//this two lines will detect the ip to tell the other user to enter
        JTextField yourip=new JTextField(25);//Text field to show the ip
        JButton terminate=new JButton("Terminate");//To close the application remotely
	terminate.addActionListener(new Terminateapp());

        setTitle("Client");
        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        yourip.setEditable(false);//The ip showing textfield should not be editable
        setLayout(null);
        add(yourip);
        yourip.setBounds(10,10,300,30);//to set the bounds to button size
        add(terminate);
        yourip.setText("Your ip is : "+ip);
        terminate.setBounds(100,100,100,50);
    }
    public static void main(String[] args)throws UnknownHostException
    {
        try{
        Server cli=new Server();//thegui of the application
        ServerSocket ser=new ServerSocket(8000);
        Socket socket=ser.accept();//to be ready for request from the other device 
        GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gDev=gEnv.getDefaultScreenDevice();//to get the defalut screen 
        Robot robot=new Robot(gDev);
        Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();//To get the screen dimension
        Rectangle rect=new Rectangle(screen);

        ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(rect);//first sending the screen size
	
        new ScreenCapture(robot,socket);
        new EventsListener(socket,robot);
        System.out.println("Connected");

        }
        catch(IOException f)
                {
                    System.out.println(f.getMessage());
                }
        catch(AWTException e)
        {
            System.out.println(e.getMessage());
        }

    }
	public class Terminateapp implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);//if the terminate button is pressed than it will close the application without any error			
		}
	}
}
