package view;

import DAO.InvestissementDAO;
import DAO.InvestissementDAOImpl;
import model.Investissement;
import model.Investor;
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
    private JLabel bankInfoLabel = new JLabel("Nom de la banque: ");
    private JTextField bankNameField = new JTextField(10);
    private JTextField transitNumberField = new JTextField(10);
    private JTextField institutionNumberField = new JTextField(10);
    private JTextField accountNumberField = new JTextField(10);
    private JButton submitButton = new JButton("Submit");
    private JButton backButton = new JButton("Page principal");
    int idInvestor = new Investor().getIdInvestor();
    public InvestissementForm(CardLayout cardLayout, JPanel cardPanel, JFrame main) {
        setLayout(new GridLayout(0, 1));
        JPanel amountPanel = new JPanel();
        amountPanel.add(amountLabel);
        amountPanel.add(amountField);
        JPanel bankPanel = new JPanel();
        bankPanel.add(bankInfoLabel);
        bankPanel.add(bankNameField);
        bankPanel.add(new JLabel("Numéro de Transit: "));
        bankPanel.add(transitNumberField);
        bankPanel.add(new JLabel("Numéro d'Institution: "));
        bankPanel.add(institutionNumberField);
        bankPanel.add(new JLabel("Numéro de compte: "));
        bankPanel.add(accountNumberField);
        JPanel buttonPanel = new JPanel();

        submitButton.addActionListener(e -> {
            if(validateInvestissement(amountField.getText())){
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

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        add(amountPanel);
        add(bankPanel);
        add(buttonPanel);
    }

    public void addInvestissementToDB(){
        Investissement investissement = prepareInvestissement();
        InvestissementDAO investissementDAO = new InvestissementDAOImpl();
        investissementDAO.addInvestissement(investissement);
    }

    public Investissement prepareInvestissement(){
        Investissement investissement = new Investissement();
        investissement.setNomBanque(bankNameField.getText());
        investissement.setNumTransit(transitNumberField.getText());
        investissement.setNumInstituition(institutionNumberField.getText());
        investissement.setNumCompte(accountNumberField.getText());
        if (!Objects.isNull(amountField.getText()) && !amountField.getText().isEmpty()) {
            investissement.setMontant(Double.valueOf(amountField.getText()));
        }
        investissement.setIdInvestor(idInvestor);
        investissement.setInvestissementDate(dateConverter("25/03/2024"));
        investissement.setInvestissementType("INVESTISSEMENT");
        return investissement;
    }

    public boolean validateInvestissement(String amount) {
        if(amount.isEmpty()){
            JOptionPane.showMessageDialog(null, "Vous devez remplir le champ Montant." , "Amount empty", WARNING_MESSAGE);
            return false;
        }else{
            try{
                double amountDouble = Double.valueOf(amount);
                if (amountDouble < 100) {
                    JOptionPane.showMessageDialog(null, "La valeur minimale d'investissement est de 100 $." , "Investissement", WARNING_MESSAGE);
                    return false;
                }
            }catch (Exception err){
                JOptionPane.showMessageDialog(null, "La valeur saisie doit être numérique." , "Loan Amount", WARNING_MESSAGE);
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
