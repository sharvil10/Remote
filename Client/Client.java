import java.net.*;
import javax.swing.*;
import java.awt.BorderLayout.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
/*this class is used to display the gui and this is the main class for the device that is going to control the other device*/
public class Client extends JFrame
{
    public static Socket socket=null;
    public JTextField enterip=new JTextField(25);
    Rectangle rect;
    public Client()throws UnknownHostException
    {
        //JTextField enterip=new JTextField(15);
        JButton connect=new JButton("Connect");


        setTitle("Server");
        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        setLayout(null);
        add(enterip);
        enterip.setBounds(10,10,300,30);
        add(connect);
        connect.setBounds(100,100,100,50);
        cnnct butt=new cnnct();
        connect.addActionListener(butt);

    }
    public static void main(String[] args)throws UnknownHostException
    {
        Client ser=new Client();
    }
    public class cnnct implements ActionListener
    {
        public void actionPerformed(ActionEvent x)
        {
                String ip=enterip.getText();
                try{
                socket = new Socket(ip, 8000);
                System.out.println("connected");
                ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                rect=(Rectangle)ois.readObject();
                System.out.println("blah");
                im img_display=new im(socket);
                new Events(socket,img_display.getFrame(),rect);
                }
                catch(UnknownHostException e)
                {
                    System.out.println("Wrong ip");
                }
                catch(IOException f)
                {
                    System.out.println("IOEx in Server");
                }
                catch(ClassNotFoundException w)
                {

                }
		
        }
    }
}
