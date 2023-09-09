import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;
import java.io.*;
public class Server  implements ActionListener {
  JTextField text;
  JPanel a1;
   static Box vertical=  Box.createVerticalBox();
   static JFrame f= new JFrame();
    static DataOutputStream dout;
    Server(){
        f.setLayout(null);
        JPanel p1= new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
       f. add(p1);
        ImageIcon i1= new ImageIcon("3.png");
      Image i2= i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
      ImageIcon i3= new ImageIcon(i2);
        JLabel back= new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter(){
          public void mouseClicked(MouseEvent ae){
            System.exit(0);
          }
        });
        ImageIcon i4= new ImageIcon("AS2.jpg");
        Image i5= i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6= new ImageIcon(i5);
          JLabel profile= new JLabel(i6);
          profile.setBounds(40,6,50,50);
          p1.add(profile);

          ImageIcon i7= new ImageIcon("video.png");
          Image i8= i7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
          ImageIcon i9= new ImageIcon(i8);
            JLabel video= new JLabel(i9);
            video.setBounds(270,20,25,25);
            p1.add(video);

            ImageIcon i10= new ImageIcon("phone.png");
            Image i11= i10.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
            ImageIcon i12= new ImageIcon(i11);
              JLabel phone= new JLabel(i12);
              phone.setBounds(315 ,20,25,25);
              p1.add(phone);

              ImageIcon i13= new ImageIcon("3icon.png");
              Image i14= i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
              ImageIcon i15= new ImageIcon(i14);
                JLabel morevert= new JLabel(i15);
                morevert.setBounds(360,20,10,25);
                p1.add(morevert);

                JLabel name =new JLabel("Ayush");
                name.setBounds(110, 15, 100, 18);
                name.setForeground(Color.white);
                name.setFont(new Font("SAN SERIF",Font.BOLD,18));
                p1.add(name);

                JLabel status =new JLabel("Active Now");
                status.setBounds(110, 35, 100, 18);
                status.setForeground(Color.white);
                status.setFont(new Font("SAN SERIF",Font.BOLD,14));
                p1.add(status);
                 
                  a1= new JPanel();
                a1.setBounds(4, 70, 383, 480);
                f.add(a1);

                 text = new JTextField();
                text.setBounds(4, 550,260 ,40);
                text.setFont(new Font("SAN SERIF",Font.PLAIN,17));
                f.add(text);

                JButton send = new JButton("Send");
                send.setBounds(265, 550, 123, 40);
                send.setBackground(new Color(7,94,84));
                send.setFont(new Font("SAN SERIF",Font.PLAIN,17));
                send.addActionListener(this);
                send.setForeground(Color.white);
                f.add(send);

        f.setSize(390, 590);
       f. getContentPane().setBackground(Color.white);
       f. setLocation(200,50);
        f.setUndecorated(true);
        f.setVisible(true);
      
       }
       public void actionPerformed(ActionEvent ae){
        try{
         String out=text.getText();
         
         JPanel p2= formatLabel(out);
        
         a1.setLayout(new BorderLayout());
         JPanel right= new JPanel(new BorderLayout());
         right.add(p2,BorderLayout.LINE_END);
         vertical.add(right);
         vertical.add(Box.createVerticalStrut(10));
         a1.add(vertical,BorderLayout.PAGE_START);
         dout.writeUTF(out);
         text.setText("");
         f.repaint();
         f.invalidate();
         f.validate();

       }catch(Exception e){
        e.printStackTrace();
       }
      }
       public static JPanel formatLabel(String out){
         JPanel panel= new JPanel();
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         JLabel output= new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>");
         output.setFont(new Font("Tahoma",Font.PLAIN,17));
         output.setBackground(new Color(37,211,102));
         output.setOpaque(true);
         output.setBorder(new EmptyBorder(15,15,15,50));
         panel.add(output);
         
         Calendar cal =  Calendar.getInstance();
         SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
         JLabel time = new JLabel();
         time.setText(sdf.format(cal.getTime()));
         panel.add(time);
         return panel;
       }
       public static void main(String[] args){
       
           new Server();

           ServerSocket skt;
           try{
             skt = new ServerSocket(6001);
             while(true){
                Socket s=skt.accept();
                System.out.println("server is ready to accept connection");
                System.out.println("Waiting....");
                DataInputStream din= new DataInputStream(s.getInputStream());
               dout= new DataOutputStream(s.getOutputStream());
                while(true){
                 String msg= din.readUTF();
                 JPanel panel= formatLabel(msg);
                 JPanel left= new JPanel(new BorderLayout());
                 left.add(panel,BorderLayout.LINE_START);
                 vertical.add(left);
                 f.validate(); 
                }
              }
           
           }catch(Exception e){
             e.printStackTrace();
           }
       }
}
