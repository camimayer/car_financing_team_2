package view;

import DAO.InvestissementDAO;
import DAO.InvestissementDAOImpl;
import model.Investissement;
import model.Investor;
import model.LoggedUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InvestorView extends JPanel {
    CardLayout cardLayout;
    JPanel cardPanel;
    JFrame main;


    //    private JLabel investmentInfoLabel = new JLabel("Investment Information:");
//    private JLabel investmentInfoLabel;
    private JLabel totalInvestedLabel;
    private JLabel transactionListLabel = new JLabel("Transaction List:");
    private JTextArea transactionTextArea = new JTextArea("",10, 30);
    private JLabel balanceLabel;
    public int idInvestor;
    private double totalInvested = 0;
    public static double balance = 0;
    private String transactionText = "";
    public InvestorView(CardLayout cardLayout, JPanel cardPanel, JFrame main) {
        idInvestor = new Investor().getIdInvestor();
        balance = 0;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.main = main;
        JLabel investmentInfoLabel;

        LoggedUser loggedUser = LoggedUser.getInstance();

        InvestissementDAO investissementDAO = new InvestissementDAOImpl();
        List<Investissement> listFromInvestissement = investissementDAO.getAllInvestissement(loggedUser.getLoggedUserId());

        System.out.println(listFromInvestissement.size());
        System.out.println(loggedUser.getLoggedUserId());

        if(listFromInvestissement.size() == 0){
            investmentInfoLabel = new JLabel("Investment Information: ");
        }else {
            Investissement currentInvestissement = listFromInvestissement.get(listFromInvestissement.size() - 1);
            investmentInfoLabel = new JLabel("Investment Information: " +
                    currentInvestissement.getNomBanque() + " / " +
                    currentInvestissement.getNumTransit() + " / " +
                    currentInvestissement.getNumInstituition() + " / " +
                    currentInvestissement.getNumCompte());
        }
        for (Investissement investissement : listFromInvestissement){

            if ("INVESTISSEMENT".equalsIgnoreCase(investissement.getInvestissementType())){
                totalInvested += investissement.getMontant();
                transactionText = transactionText + ("Investi + " + investissement.getMontant() + "$ le " + investissement.getInvestissementDate() + "\n");
                balance = balance + investissement.getMontant();
            }else {
                transactionText = transactionText + ("Retiré - " + investissement.getMontant() + "$ le " + investissement.getInvestissementDate() + "\n");
                balance = balance - investissement.getMontant();
            }
        }
        transactionTextArea = new JTextArea(transactionText);
        totalInvestedLabel = new JLabel("Montant total investi: " + totalInvested + "$");
        balanceLabel = new JLabel("Current Balance: " + balance + "$");

        JLabel title = new JLabel("Page d'Investissement");
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(4, 1));
        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(title);
        topPanel.add(investmentInfoLabel);
        topPanel.add(totalInvestedLabel);
        topPanel.add(transactionListLabel);
        JPanel transactionPanel = new JPanel(new BorderLayout());
        transactionPanel.add(new JScrollPane(transactionTextArea), BorderLayout.CENTER);
        transactionPanel.add(balanceLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton investorButton = new JButton("Investir");
        JButton retirerButton = new JButton("Retirer");
        JButton backButton = new JButton("Page principal");

        investorButton.addActionListener(e -> cardLayout.show(cardPanel, "Investissement"));
        retirerButton.addActionListener(e -> cardLayout.show(cardPanel, "Retrait"));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setSize(600, 400);
                cardLayout.show(cardPanel, "Main");
                cardPanel.add(new InvestorView(cardLayout, cardPanel, main), "Investor");
            }
        });

        buttonPanel.add(investorButton);
        buttonPanel.add(retirerButton);
        buttonPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);
        add(transactionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


}