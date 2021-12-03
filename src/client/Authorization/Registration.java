package client.Authorization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Registration extends JPanel
{
    private static final long serialVersionUID = 1L;

    private JTextField tfLogin, tfPassword;
    private JButton btnBack, btnCancel, btnReg;
    private JFrame parentFrame;
    private Authorization authorization;
    private JPanel mainPanel = this;

    public Registration(JFrame parentFrame, Authorization authorization)
    {
        super();
        this.parentFrame = parentFrame;
        this.authorization = authorization;
        createGUI();
    }
    // этот метод будет возвращать панель с созданным расположением
    private void createGUI()
    {
        // Определение отступов от границ ранели. Для этого используем пустую рамку
        setBorder (BorderFactory.createEmptyBorder(12,12,12,12));
        // Создание панели для размещения метки и текстового поля логина
        JPanel name = BoxLayoutUtils.createHorizontalPanel();
        JLabel nameLabel = new JLabel("Login:");
        name.add(nameLabel);
        name.add(Box.createHorizontalStrut(12));
        tfLogin = new JTextField(15);
        name.add(tfLogin);
        // Создание панели для размещения метки и текстового поля пароля
        JPanel password = BoxLayoutUtils.createHorizontalPanel();
        JLabel passwrdLabel = new JLabel("Password:");
        password.add(passwrdLabel);
        password.add(Box.createHorizontalStrut(12));
        tfPassword = new JTextField(15);
        password.add(tfPassword);
        // Создание label для размещения информации
        JLabel infoLabel = new JLabel(">~<>~<>~<>~<>~<>~<>~<>~<>~<>~<>~<>~<>~<>~<");
        infoLabel.add(Box.createHorizontalStrut(12));
        // Создание панели для размещения кнопок управления
        JPanel flow = new JPanel( new FlowLayout( FlowLayout.RIGHT, 0, 0) );
        JPanel grid = new JPanel( new GridLayout( 1,2,5,0) );
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                if(authorization != null)
                    authorization.setVisible(true);
            }
        });
        btnReg = new JButton("Sign up");
        btnReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**/
                mainPanel.setVisible(false);
                if(authorization != null)
                    authorization.setVisible(true);
                System.out.println("Register: " + tfLogin.getText());
                System.out.println("Register: " + tfPassword.getText());
            }
        });
        grid.add(btnReg);
        grid.add(btnBack);
        flow.add(grid);
        // Выравнивание вложенных панелей по горизонтали
        BoxLayoutUtils.setGroupAlignmentX(new JComponent[] { name, password, this, flow },
                Component.LEFT_ALIGNMENT);
        // Выравнивание вложенных панелей по вертикали
        BoxLayoutUtils.setGroupAlignmentY(new JComponent[] { tfLogin, tfPassword, nameLabel, passwrdLabel },
                Component.CENTER_ALIGNMENT);
        // Определение размеров надписей к текстовым полям
        GUITools.makeSameSize(new JComponent[] { nameLabel, passwrdLabel } );
        // Определение стандартного вида для кнопок
        GUITools.createRecommendedMargin(new JButton[] {btnBack, btnReg} );
        // Устранение "бесконечной" высоты текстовых полей
        GUITools.fixTextFieldSize(tfLogin   );
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
            System.out.println(getWidth() + 15);
            System.out.println(getHeight() + 50);
        }
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }
}