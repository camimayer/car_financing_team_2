package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class LoginView extends JPanel {
    CardLayout cardLayout;
    JPanel cardPanel;
    JFrame main;
    public LoginView(CardLayout cardLayout, JPanel cardPanel, JFrame main) {

        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.main = main;

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

        totalPanel.add(usernamePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Connexion");

        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setSize(600, 400);
                cardLayout.show(cardPanel, "Main");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(validatePassword(usernameField.getText(), String.valueOf(passwordField.getPassword()))){
                    main.setSize(500, 300);
                    cardLayout.show(cardPanel, "Financing");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalide password. Try again. " , "Welcome",
                                                    JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        totalPanel.add(buttonPanel);

        add(totalPanel);

    }
    private static boolean validatePassword(String user, String password) {
        boolean validation;
        byte[] salt = getSalt();

        byte[] hashedPassword = hashPasswordWithSalt(password.getBytes(), salt);

        //verification
        byte[] verificationHash = hashPasswordWithSalt("12345678".getBytes(), salt);

        if(Arrays.equals(hashedPassword, verificationHash)){
//            JOptionPane.showMessageDialog(null, "Login: " + user + "\nPassword: " + hashedPassword, "Welcome",
//                    JOptionPane.INFORMATION_MESSAGE);
            validation = true;
        }
        else{
//            JOptionPane.showMessageDialog(null, "Invalide password. Try again. " , "Welcome",
//                    JOptionPane.INFORMATION_MESSAGE);
            validation = false;
        }
        return validation;
    }

    private static byte[] hashPasswordWithSalt(byte[] password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            return md.digest(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing failed");
        }
    }

    private static byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
