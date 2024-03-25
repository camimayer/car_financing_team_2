package view;
import DAO.InvestissementDAO;
import DAO.InvestissementDAOImpl;
import model.Investissement;
import model.Investor;
import model.LoggedUser;
import view.InvestorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static helper.Helper.dateConverter;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class InvestissementForm extends JPanel {
    private JLabel amountLabel = new JLabel("Montant à investir: ");
    private JTextField amountField = new JTextField(10);
    private JLabel bankInfoLabel = new JLabel();
    private JTextField bankNameField = new JTextField(10);
    private JTextField transitNumberField = new JTextField(10);
    private JTextField institutionNumberField = new JTextField(10);
    private JTextField accountNumberField = new JTextField(10);
    private JButton submitButton = new JButton("Soumettre");
    private JButton backButton = new JButton("Retour à la page principale");
    int idInvestor = new Investor().getIdInvestor();


//layout
    public InvestissementForm(CardLayout cardLayout, JPanel cardPanel, JFrame main) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(amountLabel, gbc);
        gbc.gridx = 1;
        add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(bankInfoLabel, gbc);

        gbc.gridy = 2;
        add(new JLabel("Nom de la banque: "), gbc);
        gbc.gridx = 1;
        add(bankNameField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(new JLabel("Numéro de transit: "), gbc);
        gbc.gridx = 1;
        add(transitNumberField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(new JLabel("Numéro d'institution: "), gbc);
        gbc.gridx = 1;
        add(institutionNumberField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        add(new JLabel("Numéro de compte: "), gbc);
        gbc.gridx = 1;
        add(accountNumberField, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        gbc.gridy = 7;
        add(backButton, gbc);

        submitButton.addActionListener(e -> {
            if (validateInvestissement(amountField.getText())) {
                addInvestissementToDB();
                cardLayout.show(cardPanel, "Main");
                cardPanel.add(new InvestissementForm(cardLayout, cardPanel, main), "Investissement");
                revalidate();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setSize(600, 400);
                cardLayout.show(cardPanel, "Main");
                cardPanel.add(new InvestorView(cardLayout, cardPanel, main), "Investor");
            }
        });
    }

    public void addInvestissementToDB() {
        Investissement investissement = prepareInvestissement();
        InvestissementDAO investissementDAO = new InvestissementDAOImpl();
        investissementDAO.addInvestissement(investissement);
    }

    public Investissement prepareInvestissement() {
        LoggedUser loggedUser = LoggedUser.getInstance();
        System.out.println(loggedUser.getLoggedUserId());

        Investissement investissement = new Investissement();
        investissement.setNomBanque(bankNameField.getText());
        investissement.setNumTransit(transitNumberField.getText());
        investissement.setNumInstituition(institutionNumberField.getText());
        investissement.setNumCompte(accountNumberField.getText());
        if (!Objects.isNull(amountField.getText()) && !amountField.getText().isEmpty()) {
            investissement.setMontant(Double.valueOf(amountField.getText()));
        }
        investissement.setIdInvestor(loggedUser.getLoggedUserId());
        investissement.setInvestissementDate(dateConverter("25/03/2024"));
        investissement.setInvestissementType("INVESTISSEMENT");
        return investissement;
    }

    public boolean validateInvestissement(String amount) {
        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vous devez remplir le champ Montant.", "Amount empty", WARNING_MESSAGE);
            return false;
        } else {
            try {
                double amountDouble = Double.valueOf(amount);
                if (amountDouble < 100) {
                    JOptionPane.showMessageDialog(null, "La valeur minimale d'investissement est de 100 $.", "Investissement", WARNING_MESSAGE);
                    return false;
                }
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "La valeur saisie doit être numérique.", "Loan Amount", WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JTextField getBankNameField() {
        return bankNameField;
    }

    public JTextField getTransitNumberField() {
        return transitNumberField;
    }

    public JTextField getInstitutionNumberField() {
        return institutionNumberField;
    }

    public JTextField getAccountNumberField() {
        return accountNumberField;
    }
}

