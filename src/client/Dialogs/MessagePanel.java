package client.Dialogs;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    JLabel text, time;
    public MessagePanel(int x, int y, int width, int height, boolean right, String text, String time){
        super();
        setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        setBounds(x, y, width, height);
        this.text = new JLabel(text);
        this.time = new JLabel(time);
        this.text.setFont(new Font("Courier", Font.BOLD,14));
        this.time.setFont(new Font("Courier", Font.BOLD,8));
        setBackground(Color.getHSBColor(.45F,.25f,.61f));
        this.add(this.text);
        this.add(this.time);
    }
}
