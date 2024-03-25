package view;

import DAO.ClientDAO;
import DAO.ClientDAOImpl;
import DAO.InvestissementDAO;
import DAO.InvestissementDAOImpl;
import model.Client;
import model.Investissement;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

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

        InvestissementDAO investissementDAO = new InvestissementDAOImpl();
        List<Investissement> listFromInvestissement = investissementDAO.getAllInvestissement();
        for(int i =0; i< listFromInvestissement.size(); i++){
            if(listFromInvestissement.get(i).getIdInvestor() == 1){
                JLabel valeurInvestissement = new JLabel("Investissement:" + listFromInvestissement.get(i).getMontant());
                totalPanel.add(valeurInvestissement);
            }
        }



        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton investorButton = new JButton("Investir");

        JButton retirerButton = new JButton("Retirer");







        buttonPanel.add(investorButton);
        buttonPanel.add(retirerButton);
        totalPanel.add(buttonPanel);


        add(totalPanel);
    }

    }

