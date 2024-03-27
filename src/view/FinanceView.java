package view;

import DAO.FinancementDAO;
import DAO.InvestorDAO;
import DAO.InvestorDAOImpl;
import model.Financement;
import DAO.FinancementDAOImpl;
import model.Investor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FinanceView extends JPanel {
    CardLayout cardLayout;
    JPanel cardPanel;
    JFrame main;
      public FinanceView(CardLayout cardLayout, JPanel cardPanel, JFrame main){
          this.cardLayout = cardLayout;
          this.cardPanel = cardPanel;
          this.main = main;

          FinancementDAO financementDAO = new FinancementDAOImpl();
          List<Financement> listFromFinancement = financementDAO.getAllFinancements();



          Financement currentFinancement = listFromFinancement.get(listFromFinancement.size() - 1);

          setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

          JPanel totalPanel = new JPanel(new GridLayout(0, 1, 20, 15));
          JLabel title = new JLabel("Page de Financement");
          title.setFont(new Font("Arial", Font.PLAIN, 25));
          title.setHorizontalAlignment(SwingConstants.CENTER);
          totalPanel.add(title);

          JPanel marquePanel = new JPanel(new GridLayout(0, 1));
          JLabel marqueLabel = new JLabel("Marque: " + currentFinancement.getMarque());
          marqueLabel.setHorizontalAlignment(SwingConstants.LEFT);
          marqueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
          marquePanel.add(marqueLabel);

          totalPanel.add(marquePanel);

          JPanel modelePanel = new JPanel(new GridLayout(0, 1));

          JLabel modeleLabel = new JLabel("Modele: " + currentFinancement.getModele());
          modeleLabel.setHorizontalAlignment(SwingConstants.LEFT);
          modeleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
          modelePanel.add(modeleLabel);

          totalPanel.add(modelePanel);

          JPanel anneePanel = new JPanel(new GridLayout(0, 1));

          JLabel anneeLabel = new JLabel("Annee: " + currentFinancement.getAnnee());
          anneeLabel.setHorizontalAlignment(SwingConstants.LEFT);
          anneeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
          anneePanel.add(anneeLabel);

          totalPanel.add(anneePanel);

          JPanel kilometragePanel = new JPanel(new GridLayout(0, 1));

          JLabel kilometrageLabel = new JLabel("Kilometrage du vehicule: " + currentFinancement.getKilometrage());
          kilometrageLabel.setHorizontalAlignment(SwingConstants.LEFT);
          kilometrageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//          JTextField kilometrageField = new JTextField(15);
          kilometragePanel.add(kilometrageLabel);
//          kilometragePanel.add(kilometrageField);
          totalPanel.add(kilometragePanel);

          totalPanel.add(Box.createVerticalStrut(3));

          JPanel demandePanel = new JPanel(new GridLayout(0, 1));
          JLabel demandeLabel = new JLabel("Statut de la demande: en cours...");
          demandeLabel.setHorizontalAlignment(SwingConstants.LEFT);
          demandeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
          demandePanel.add(demandeLabel);
          totalPanel.add(demandePanel);

          JProgressBar progressBar = new JProgressBar(0, 100);
          progressBar.setStringPainted(true);
          progressBar.setValue(20);
          progressBar.setPreferredSize(new Dimension(250, 30));
          totalPanel.add(progressBar);


          JButton modifierButton = new JButton("Modifier");
          modifierButton.setEnabled(false);
          modifierButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
//                  main.setSize(600, 400);
//                  cardLayout.show(cardPanel, "Main");
//                  cardPanel.add(new FinanceView(cardLayout, cardPanel, main), "Financement");
              }
          });



          JButton backButton = new JButton("Page principal");
          backButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  main.setSize(500, 300);
                  cardLayout.show(cardPanel, "Main");
                  cardPanel.add(new FinanceView(cardLayout, cardPanel, main), "Financement");
              }
          });

          totalPanel.add(modifierButton);
          totalPanel.add(backButton);
          add(totalPanel);


    }


}
