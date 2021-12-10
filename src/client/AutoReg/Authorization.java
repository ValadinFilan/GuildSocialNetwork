package client.AutoReg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Authorization extends JPanel
{
    private static final long serialVersionUID = 1L;
    private Authorization mainPanel = this;
    private JTextField tfLogin, tfPassword;
    private JButton    btnOk, btnCancel, btnRegister;
    private Registration registration;
    JFrame parentFrame;
    public Authorization(JFrame parentFrame, Registration registration)
    {
        super();
        this.parentFrame = parentFrame;
        this.registration = registration;
        createGUI();
    }
    private void createGUI()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        // Создание панели для размещения метки и текстового поля логина
        JPanel name = BoxLayoutUtils.createHorizontalPanel();
        JLabel nameLabel = new JLabel("Login:");
        name.add(nameLabel);
        name.add(Box.createHorizontalStrut(12));
        tfLogin = new JTextField(15);
        name.add(tfLogin);
        // Создание панели для размещения метки и текстового поля пароля
        JPanel password = BoxLayoutUtils.createHorizontalPanel();
        JLabel passwordLabel = new JLabel("Password:");
        password.add(passwordLabel);
        password.add(Box.createHorizontalStrut(12));
        tfPassword = new JTextField(15);
        password.add(tfPassword);
        // Создание label для размещения информации
        JLabel infoLabel = new JLabel(">_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<");
        infoLabel.add(Box.createHorizontalStrut(12));
        // Создание панели для размещения кнопок управления
        JPanel flow = new JPanel( new FlowLayout( FlowLayout.RIGHT, 0, 0) );
        JPanel grid = new JPanel( new GridLayout( 1,2,5,0) );
        btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*HERE WILL BE AUTHORISATION REQUEST*/
                System.out.println("Autho: " + tfLogin.getText());
                System.out.println("Autho: " + tfPassword.getText());
            }
        });
        btnRegister = new JButton("Sign up");
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                if(registration != null)
                    registration.setVisible(true);
            }
        });
        grid.add(btnRegister);
        grid.add(btnOk);
        flow.add(grid);
        // Выравнивание вложенных панелей по горизонтали
        BoxLayoutUtils.setGroupAlignmentX(new JComponent[] { name, password, infoLabel, this, flow },
                Component.LEFT_ALIGNMENT);
        // Выравнивание вложенных панелей по вертикали
        BoxLayoutUtils.setGroupAlignmentY(new JComponent[] { tfLogin, tfPassword, nameLabel, passwordLabel},
                Component.CENTER_ALIGNMENT);
        // Определение размеров надписей к текстовым полям
        GUITools.makeSameSize(new JComponent[] { nameLabel, passwordLabel } );
        // Определение стандартного вида для кнопок
        GUITools.createRecommendedMargin(new JButton[] { btnOk, btnRegister } );
        // Устранение "бесконечной" высоты текстовых полей
        GUITools.fixTextFieldSize(tfLogin);
        GUITools.fixTextFieldSize(tfPassword);

        // Сборка интерфейса
        add(name);
        add(Box.createVerticalStrut(12));
        add(password);
        add(Box.createVerticalStrut(17));
        add(infoLabel);
        add(Box.createVerticalStrut(17));
        add(flow);
        setBackground(Color.getHSBColor(.45F,.25f,.61f));
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if(aFlag) {
            parentFrame.setSize(getWidth() + 15, getHeight() + 50);
            parentFrame.repaint();
        }
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
