package view;
import DAO.RetraitDAO;
import DAO.RetraitDAOImpl;
import model.Investor;
import model.Retrait;
import view.InvestorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static helper.Helper.dateConverter;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class RetraitForm extends JPanel {
    private JLabel amountLabel = new JLabel("Montant à retirer: ");
    private JTextField amountField = new JTextField(10);
    private JLabel bankInfoLabel = new JLabel();
    private JTextField bankNameField = new JTextField(10);
    private JTextField transitNumberField = new JTextField(10);
    private JTextField institutionNumberField = new JTextField(10);
    private JTextField accountNumberField = new JTextField(10);
    private JButton submitButton = new JButton("Soumettre");
    private JButton backButton = new JButton("Retour à la page principale");
    int idInvestor = new Investor().getIdInvestor();

    public RetraitForm(CardLayout cardLayout, JPanel cardPanel, JFrame main) {
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
            if (validateRetrait(amountField.getText())) {
                addRetraitToDB();
                cardLayout.show(cardPanel, "Main");
                cardPanel.add(new RetraitForm(cardLayout, cardPanel, main), "Retrait");
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

    public void addRetraitToDB() {
        Retrait retrait = prepareRetrait();
        RetraitDAO retraitDAO = new RetraitDAOImpl();
        retraitDAO.addRetrait(retrait);
    }

    public Retrait prepareRetrait() {
        Retrait retrait = new Retrait();
        retrait.setNomBanque(bankNameField.getText());
        retrait.setNumTransit(transitNumberField.getText());
        retrait.setNumInstituition(institutionNumberField.getText());
        retrait.setNumCompte(accountNumberField.getText());
        if (!Objects.isNull(amountField.getText()) && !amountField.getText().isEmpty()) {
            retrait.setMontant(Double.valueOf(amountField.getText()));
        }
        retrait.setIdInvestor(idInvestor);
        retrait.setInvestissementDate(dateConverter("25/03/2024"));
        retrait.setInvestissementType("RETRAIT");
        return retrait;
    }

    public boolean validateRetrait(String amount) {
        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vous devez remplir le champ Montant.", "Amount empty", WARNING_MESSAGE);
            return false;
        } else {
            try {
                double amountRetrait = Double.valueOf(amount);
                if (InvestorView.balance < amountRetrait) {
                    JOptionPane.showMessageDialog(null, "La valeur du retrait est supérieure à la valeur du solde.", "Retrait", WARNING_MESSAGE);
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
}

