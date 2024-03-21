package view;

import javax.swing.*;
import java.awt.*;

public class InvestorView extends JPanel {
    CardLayout cardLayout;
    JPanel cardPanel;
    JFrame main;

    public InvestorView(CardLayout cardLayout, JPanel cardPanel, JFrame main) {

        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.main = main;

        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JPanel totalPanel = new JPanel(new GridLayout(0, 1, 30, 30));
        JLabel title = new JLabel("Page d'Investissement'");
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        totalPanel.add(title);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton investorButton = new JButton("Investir");

        JButton retirerButton = new JButton("Retirer");







        buttonPanel.add(investorButton);
        buttonPanel.add(retirerButton);
        totalPanel.add(buttonPanel);


        add(totalPanel);
    }

    }

