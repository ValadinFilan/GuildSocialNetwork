package client.Dialogs;

import FileSystem.Message;
import QueryManager.RequestManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogPanel extends JPanel {
    int dialogID = 1, userId = 1, thisUserId = 1;
    String userName = "Valery";
    QueryManager.RequestManager requestManager = new RequestManager(this);
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
                    Message message = new Message((new SimpleDateFormat("hh:mm")).format(new Date()), userId, textField.getText());
                    newMessage(message, true);
                    textField.setText("Введите сообщение");
                    requestManager.SEND_MSG(message, dialogID);
                }
            }
        });
        footer.setLayout(new FlowLayout());
        footer.add(textField);
        footer.add(send);
        add(footer, BorderLayout.SOUTH);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message m = requestManager.LOAD_MSG(userName, dialogID);
                while(m != null){
                    newMessage(m, m.getUserID() == thisUserId);
                    m = requestManager.LOAD_MSG(userName, dialogID);
                }
                while (true){
                    try {
                        Thread.sleep(1000);
                        m = requestManager.RENEW_MSG(userName, dialogID, m.getTime());
                        while(m != null){
                            newMessage(m, m.getUserID() == thisUserId);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
    public void newMessage(Message message, boolean right){
        MessagePanel mp = new MessagePanel(0, 0, 300, 50, right, message.getText(), message.getTime());
        p.add(mp, BorderLayout.CENTER);
        pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getModel().getMaximum());
        repaint();
        revalidate();
    }
}
