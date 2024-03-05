import view.FinancingForm;
import view.ImagePanel;
import view.LoginView;
import view.RegisterView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JPanel mainPanel = new ImagePanel();
    private LoginView loginView = new LoginView(cardLayout, cardPanel, this);
    private RegisterView registerView = new RegisterView();

    private FinancingForm financingForm = new FinancingForm(cardLayout, cardPanel, this);

    private Image backgroundImage;

    public Main() {
        setTitle("Financement Automobile XYZ");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel labelDesc = new JLabel("Nos services de financement offrent des solutions pour répondre à vos besoins.");
        labelDesc.setFont(new Font("Arial", Font.PLAIN, 17));
        JLabel labelContact = new JLabel("Contact: 514-233-3333");
        labelContact.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton loginButton = new JButton("Connexion");
        JButton registerButton = new JButton("Inscription");
        JButton financeButton = new JButton("Status demande financement");

        JPanel labelPanel = new JPanel(new GridLayout(0, 1));
        labelDesc.setHorizontalAlignment(SwingConstants.CENTER);
        labelPanel.add(labelDesc);
        labelContact.setHorizontalAlignment(SwingConstants.CENTER);
        labelPanel.add(labelContact);
        labelPanel.setBackground(new Color(255, 255, 255, 100));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(financeButton);
        buttonPanel.setBackground(new Color(255, 255, 255, 100));

        JPanel mainContentPanel = new JPanel(new GridLayout(0, 1));
        mainContentPanel.setOpaque(false);
        mainContentPanel.add(labelPanel);
        mainContentPanel.add(buttonPanel);
        mainContentPanel.setBackground(new Color(255, 255, 255, 100));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(mainContentPanel, BorderLayout.CENTER);

        cardPanel.add(mainPanel, "Main");
        cardPanel.add(loginView, "Login");
        cardPanel.add(registerView, "Register");
        cardPanel.add(financingForm, "Financing");

        add(cardPanel);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSize(300, 400);
                cardLayout.show(cardPanel, "Login");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Register");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });

    }
}
