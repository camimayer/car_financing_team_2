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

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validatePassword(String.valueOf(passwordField.getPassword()));
            }
        });

        buttonPanel.add(loginButton);
        totalPanel.add(buttonPanel);

        add(totalPanel);

    }
    private static void validatePassword(String password) {
//      String password = "12345678";
        byte[] salt = getSalt();

        byte[] hashedPassword = hashPasswordWithSalt(password.getBytes(), salt);

        //verification
        byte[] verificationHash = hashPasswordWithSalt("12345678".getBytes(), salt);

        System.out.println("Password verification successful: " + Arrays.equals(hashedPassword, verificationHash));
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
