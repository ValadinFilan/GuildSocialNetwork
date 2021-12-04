package client;

import client.Authorization.Authorization;
import client.Authorization.Registration;
import client.Dialogs.MessagePanel;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(){
        super();
        setSize(400, 400);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        //Registration registration = new Registration(this, null);
        //Authorization authorization = new Authorization(this,registration);
        //registration.setAuthorization(authorization);
        //add(authorization);
        //add(registration);
        setVisible(true);
        //authorization.setVisible(true);
        MessagePanel mp = new MessagePanel(0, 0, 400, 50, true, "Hello", "12:30");
        mp.setVisible(true);
        add(mp);
        setBackground(Color.getHSBColor(.45F,.25f,.61f));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
