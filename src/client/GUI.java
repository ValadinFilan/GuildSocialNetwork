package client;

import client.Dialogs.DialogPanel;
import client.Dialogs.MessagePanel;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(){
        super();
        setSize(400, 400);
        setLayout(new BorderLayout());
        DialogPanel p = new DialogPanel();
        p.setBounds(0, 0, 300, 300);
        add(p, BoxLayout.X_AXIS);
        this.revalidate();
        setVisible(true);

        setBackground(Color.getHSBColor(.45F,.25f,.61f));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
