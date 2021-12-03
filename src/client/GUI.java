package client;

import client.Authorization.Authorization;
import client.Authorization.Registration;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(){
        super();
        setSize(400, 400);
        setLayout(new FlowLayout());
        Registration registration = new Registration(this, null);
        Authorization authorization = new Authorization(this,registration);
        registration.setAuthorization(authorization);
        add(authorization);
        add(registration);
        setVisible(true);
        authorization.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.getHSBColor(.45F,.25f,.61f));
    }
}
