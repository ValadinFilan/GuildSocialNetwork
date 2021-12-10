package client;

import client.Dialogs.MessagePanel;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(){
        super();
        setSize(400, 400);
        JPanel p = new JPanel();
        BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);

        p.setLayout(b);
        setLayout(/*new FlowLayout()*/null);
        p.setBounds(0, 0, 300, 300);
        setLayout(new BorderLayout());



        //Registration registration = new Registration(this, null);
        //Authorization authorization = new Authorization(this,registration);
        //registration.setAuthorization(authorization);
        //add(authorization);
        //add(registration);
        setVisible(true);
        //authorization.setVisible(true);
        for (int i = 0; i < 100; i++){
            MessagePanel mp = new MessagePanel(0, 0, 300, 50, i % 2 == 0, "Hello" + i, "12:30");
            p.add(mp, BorderLayout.CENTER);
        }

        JScrollPane pane = new JScrollPane(p);
        pane.setMaximumSize(new Dimension(300, 200));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(pane, BoxLayout.X_AXIS);
        this.revalidate();


        setBackground(Color.getHSBColor(.45F,.25f,.61f));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
