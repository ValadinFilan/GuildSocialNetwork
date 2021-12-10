package client.Dialogs;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogPanel extends JPanel {
    JPanel p;
    JScrollPane pane;
    DialogManagerPanel managerPanel;
    public DialogPanel(){
        super();
        p = new JPanel();
        BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(b);
        p.setBounds(0, 50, getWidth(), getHeight() - 100);
        setLayout(new BorderLayout());
        pane = new JScrollPane(p);
        pane.setMaximumSize(new Dimension(300, 200));
        pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getModel().getMaximum());
        add(pane, BorderLayout.CENTER);
        setBackground(Color.getHSBColor(.45F,.25f,.61f));
        JPanel footer = new JPanel();
        JTextField textField = new JTextField("Введите сообщение");
        textField.setSize(200, 50);
        JButton send = new JButton("Отправить");
        send.setBounds(0,0, 90, 40);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField.getText().trim().equals("")) {
                    newMessage(textField.getText(), (new SimpleDateFormat("hh:mm")).format(new Date()), true);
                    textField.setText("Введите сообщение");
                }
            }
        });
        footer.setLayout(new FlowLayout());
        footer.add(textField);
        footer.add(send);
        add(footer, BorderLayout.SOUTH);
    }
    public void newMessage(String text, String Time, boolean right){
        MessagePanel mp = new MessagePanel(0, 0, 300, 50, right, text, Time);
        p.add(mp, BorderLayout.CENTER);
        pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getModel().getMaximum());
        repaint();
        revalidate();
    }
}
