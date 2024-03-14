import java.util.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.Callable;
import java.text.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client implements ActionListener {
    JTextField text;
    static JFrame f = new JFrame();
    static JPanel a1;
    static Box vertical = Box.createVerticalBox(); // it is ues to make line by line message
    static DataOutputStream dout;

    Client() {
        f.setLayout(null);
        // creating panel in display
        JPanel p1 = new JPanel(); // it is for green panel
        p1.setBackground(new Color(7, 94, 84)); // it gives color in panel object
        p1.setBounds(0, 0, 450, 70); // it set the boundry of panel
        p1.setLayout(null);
        f.add(p1); // it is add the panel
        // arrow image in panel
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("imgs\\left-512.png")); // it is object to pass image
                                                                                           // url
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25); // it set the bounbry of image
        p1.add(back); // p1 object is used in this to add image in panel

        // main image in panel
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("imgs\\cartoon2.jpg")); // it is object to pass image
                                                                                           // url
        Image i5 = i4.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50); // it set the bounbry of image
        p1.add(profile); // p1 object is used in this to add image in panel

        // third image in panel
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("imgs\\video.png")); // it is object to pass image
                                                                                        // url
        Image i8 = i7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30); // it set the bounbry of image
        p1.add(video);

        // forth image in panel
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("imgs\\Daco_4828072.png")); // it is object to pass
                                                                                                // image url
        Image i11 = i10.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 35, 30); // it set the bounbry of image
        p1.add(phone);

        // manu image in panel

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("imgs\\manu.png")); // it is object to pass image
                                                                                        // url
        Image i14 = i13.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420, 24, 10, 25); // it set the bounbry of image
        p1.add(morevert);

        // name plate in panel
        JLabel name = new JLabel("Mann");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE); // font color
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18)); // font family,type,size
        p1.add(name);

        // stauts in panel

        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE); // font color
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 13)); // font family,type,size
        p1.add(status);

        // massage panel
        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        f.add(a1);

        // text box panel

        text = new JTextField(); // this class is used to user can write any text
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SET_SERIF", Font.PLAIN, 16));
        f.add(text);
        // create Button
        JButton send = new JButton("Send");
        send.addActionListener(this);
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SET_SERIF", Font.BOLD, 16));
        f.add(send);
        // event of clicking in application
        back.addMouseListener(new MouseAdapter() { // back image click event class No OBJECT
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        f.setSize(450, 700); // size of application layout
        f.setLocation(800, 20); // change for displaying layout position in screen
        f.getContentPane().setBackground(Color.WHITE); // it is use to visible layout

        f.setUndecorated(true); // hader part is unvisible
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text.getText(); // it stor the text in variable in send click event

            JPanel p2 = formatLabel(out);

            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());

            right.add(p2, BorderLayout.LINE_END); // end of line left side alignment
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15)); // distance between two lines space
            a1.add(vertical, BorderLayout.PAGE_START); // starting display line
            dout.writeUTF(out);
            text.setText(""); // empty text box after sending messange
            f.repaint(); // to show message
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width:150px  \">" + out + "</p></html>"); // using html css
                                                                                               // properties with java
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50)); // margin padding of color
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;

    }

    public static void main(String[] args) {
        new Client();
        try {
            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream()); // to recive data
            dout = new DataOutputStream(s.getOutputStream()); // to send data

            while (true) {
                a1.setLayout(new BorderLayout());
                String msg = din.readUTF();

                JPanel panel = formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical, BorderLayout.PAGE_START);
                f.validate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
