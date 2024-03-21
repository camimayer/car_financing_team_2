package view;

import DAO.*;
import com.sun.tools.javac.Main;
import model.Client;
import model.Financement;
import model.Investor;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoginView extends JPanel {
    CardLayout cardLayout;
    JPanel cardPanel;
    JFrame main;

    public static final byte[] SALT = {
            (byte) 0x5e, (byte) 0xa1, (byte) 0x9b, (byte) 0x2c,
            (byte) 0xd3, (byte) 0xf8, (byte) 0x7e, (byte) 0x0f
    };

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

        JCheckBox checkInvestor = new JCheckBox("I'm a investor");
        usernamePanel.add(checkInvestor);

        totalPanel.add(usernamePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Connexion");

        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setSize(600, 400);
                cardLayout.show(cardPanel, "Main");
                cardPanel.add(new LoginView(cardLayout, cardPanel, main), "Login");

            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(validatePassword(usernameField.getText(), passwordField.getText(), checkInvestor.isSelected())){
                    main.setSize(500, 300);
                    cardLayout.show(cardPanel, "Financing");
                    cardPanel.add(new LoginView(cardLayout, cardPanel, main), "Login");
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
    private static boolean validatePassword(String user, String password, boolean checkInvestor) {
        boolean validation;
        // Password saisi par l'utilisateur
//        byte[] hashedPassword = hashPasswordWithSalt(password.getBytes(), SALT);
//        System.out.println(password);
        InvestorDAO investorDAO = new InvestorDAOImpl();
        List<Investor> listFromInvestor = investorDAO.getAllInvestor();

        ClientDAO clientDAO = new ClientDAOImpl();
        List<Client> listFromClient = clientDAO.getAllClients();

        if(!checkInvestor){
            for(int i =0; i< listFromClient.size(); i++){
                if(Objects.equals(listFromClient.get(i).getEmail(), user) && Objects.equals(listFromClient.get(i).getPassword(), password)){
                    return true;
                }
            }
        }

        else{
            for(int i =0; i< listFromInvestor.size(); i++){
                if(Objects.equals(listFromInvestor.get(i).getEmail(), user) && Objects.equals(listFromInvestor.get(i).getPassword(), password)){
                    return true;
                }
            }
        }

        byte[] verificationHash = hashPasswordWithSalt("12345678".getBytes(), SALT);

        return false;
    }

    static byte[] hashPasswordWithSalt(byte[] password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            return md.digest(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing failed");
        }
    }

    static byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return SALT;
    }
}
