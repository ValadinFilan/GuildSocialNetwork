package Authorization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Authorization extends JDialog
{
    private static final long serialVersionUID = 1L;

    public JTextField tfLogin, tfPassword;
    public JButton    btnOk, btnCancel, btnRegister;

    public Authorization(JFrame parent)
    {
        super(parent, "Sign in");
        // При выходе из диалогового окна работа заканчивается
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
        // добавляем расположение в центр окна
        getContentPane().add(createGUI());
        // задаем предпочтительный размер
        pack();
        // выводим окно на экран
        setVisible(true);
    }
    // этот метод будет возвращать панель с созданным расположением
    private JPanel createGUI()
    {
        // Создание панели для размещение компонентов
        JPanel panel = BoxLayoutUtils.createVerticalPanel();
        // Определение отступов от границ ранели. Для этого используем пустую рамку
        panel.setBorder (BorderFactory.createEmptyBorder(12,12,12,12));
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
        JLabel infoLabel = new JLabel(">_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<");
        infoLabel.add(Box.createHorizontalStrut(12));
        // Создание панели для размещения кнопок управления
        JPanel flow = new JPanel( new FlowLayout( FlowLayout.RIGHT, 0, 0) );
        JPanel grid = new JPanel( new GridLayout( 1,2,5,0) );
        btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Autho: " + tfLogin.getText());
                System.out.println("Autho: " + tfPassword.getText());
            }
        });
        btnRegister = new JButton("Sign up");
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Registration(new JFrame());
            }
        });
        grid.add(btnRegister);
        grid.add(btnOk    );
        flow.add(grid);
        // Выравнивание вложенных панелей по горизонтали
        BoxLayoutUtils.setGroupAlignmentX(new JComponent[] { name, password, infoLabel, panel, flow },
                Component.LEFT_ALIGNMENT);
        // Выравнивание вложенных панелей по вертикали
        BoxLayoutUtils.setGroupAlignmentY(new JComponent[] { tfLogin, tfPassword, nameLabel, passwrdLabel},
                Component.CENTER_ALIGNMENT);
        // Определение размеров надписей к текстовым полям
        GUITools.makeSameSize(new JComponent[] { nameLabel, passwrdLabel } );
        // Определение стандартного вида для кнопок
        GUITools.createRecommendedMargin(new JButton[] { btnOk, btnRegister } );
        // Устранение "бесконечной" высоты текстовых полей
        GUITools.fixTextFieldSize(tfLogin   );
        GUITools.fixTextFieldSize(tfPassword);

        // Сборка интерфейса
        panel.add(name);
        panel.add(Box.createVerticalStrut(12));
        panel.add(password);
        panel.add(Box.createVerticalStrut(17));
        panel.add(infoLabel);
        panel.add(Box.createVerticalStrut(17));
        panel.add(flow);
        panel.setBackground(Color.getHSBColor(.45F,.25f,.61f));
        // готово
        return panel;
    }
}
