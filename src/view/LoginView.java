package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {

    public LoginView() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JPanel totalPanel = new JPanel(new GridLayout(0, 1, 30, 30));
        JLabel title = new JLabel("Page de Login");
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        totalPanel.add(title);

        JPanel usernamePanel = new JPanel(new GridLayout(0, 1));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JPasswordField passwordField = new JPasswordField(15);
        usernamePanel.add(passwordLabel);
        usernamePanel.add(passwordField);

        //usernamePanel.setBackground(new Color(111, 222, 123, 70));

        totalPanel.add(usernamePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Connexion");
        buttonPanel.add(loginButton);
        totalPanel.add(buttonPanel);

        add(totalPanel);
    }
}
