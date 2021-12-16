package client.Dialogs;

import FileSystem.Message;
import QueryManager.RequestManager;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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
        //pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getModel().getMaximum());
        add(pane, BorderLayout.CENTER);
        setBackground(Color.getHSBColor(.45F,.25f,.61f));
        JPanel footer = new JPanel();
        JTextField textField = new JTextField("Enter text");
        textField.setSize(200, 50);
        JButton send = new JButton("Отправить");
        send.setBounds(0,0, 90, 40);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField.getText().trim().equals("")) {
                    Message message = new Message((new SimpleDateFormat("hh:mm")).format(new Date()), userId, textField.getText());
                    //newMessage(message, true);
                    textField.setText("Enter message");
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
                Message lastMessage = m;
                String LastTime = m.getTime();
                ArrayList<Message> messages = new ArrayList<>();
                while(m != null){
                    messages.add(m);
                    m = requestManager.LOAD_MSG(userName, dialogID);
                }
                for (int i = messages.size() - 1; i > 0; i--)
                    newMessage(messages.get(i), messages.get(i).getUserID() == thisUserId);
                m = lastMessage;
                while (true){
                    try {
                        Thread.sleep(1000);
                        String data = requestManager.RENEW_MSG(userName, dialogID, LastTime);
                        if(!Objects.equals(data, "NULL")){
                            System.out.println(1);
                            String[] Lines = data.split("\n");
                            System.out.println(Lines[Lines.length - 1]);
                            Message temp = (new Gson()).fromJson(Lines[Lines.length - 1], Message.class);
                            System.out.println(m.toString());
                            System.out.println(temp.toString());
                            if(m.getUserID() != temp.getUserID() || !m.getTime().equals(temp.getTime()) || !m.getText().equals(m.getText())) {
                                m = temp;
                                newMessage(m, m.getUserID() == thisUserId);
                                for (int i = Lines.length - 1; i > 0; i--) {
                                    System.out.println(Lines[i]);
                                    m = (new Gson()).fromJson(Lines[i], Message.class);
                                    newMessage(m, m.getUserID() == thisUserId);
                                }
                                LastTime = m.getTime();
                                System.out.println(LastTime);
                            }
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
