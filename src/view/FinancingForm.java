package view;

import jdk.internal.access.JavaSecurityAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.WARNING_MESSAGE;


public class FinancingForm extends JPanel {

    private JLabel vinLabel;
    private JTextField vinField;
    private JLabel marqueLabel;
    private JTextField marqueField;
    private JLabel modeleLabel;
    private JTextField modeleField;
    private JLabel lanneeLabel;
    private JTextField lanneeField;
    private JLabel montantPretLabel;
    private JTextField montantPretField;
    private JLabel dureePretLabel;
    private JTextField dureePretField;
    private JLabel kilometrageLabel;
    private JTextField kilometrageField;

    private JComboBox<String> typeVehiculeComboBox;

    CardLayout cardLayout;
    JPanel cardPanel;
    JFrame main;
    public FinancingForm(CardLayout cardLayout, JPanel cardPanel, JFrame main) {

        // Configuration du layout en grille pour organiser les composants
        setLayout(new GridLayout(0, 2, 10, 10));

        vinField = new JTextField();
        vinLabel = new JLabel("Numéro d'identification du véhicule (NIV): ");
        marqueField = new JTextField();
        marqueLabel = new JLabel("Marque du véhicule: ");
        modeleField = new JTextField();
        modeleLabel = new JLabel("Modèle du véhicule: ");
        lanneeField = new JTextField();
        lanneeLabel = new JLabel("Année du véhicule: ");
        montantPretField = new JTextField();
        montantPretLabel = new JLabel("Montant du prêt désiré: ");
        dureePretField = new JTextField();
        dureePretLabel = new JLabel("Durée du prêt: ");
        kilometrageField = new JTextField();
        kilometrageLabel = new JLabel("Kilométrage: ");

        String[] typeVehicule = {"Neuf", "d'Occasion"};
        typeVehiculeComboBox = new JComboBox<>(typeVehicule);

        // Ajouter des boutons pour demander un financement ou revenir à la page d'accueil
        JButton soumettreButton = new JButton("Soumettre");
        JButton backButton = new JButton("Retour");

        // Ajout des labels et des champs au panneau
        add(vinLabel);
        add(vinField);
        add(marqueLabel);
        add(marqueField);
        add(modeleLabel);
        add(modeleField);
        add(lanneeLabel);
        add(lanneeField);
        add(montantPretLabel);
        add(montantPretField);
        add(dureePretLabel);
        add(dureePretField);
        add(kilometrageLabel);
        add(kilometrageField);
        add(new JLabel("Type de véhicule: "));
        add(typeVehiculeComboBox);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setSize(600, 400);
                cardLayout.show(cardPanel, "Main");
                cardPanel.add(new FinancingForm(cardLayout, cardPanel, main), "Financing");

            }
        });

        soumettreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                areAllFieldsFilled(vinField.getText(), montantPretField.getText(), dureePretField.getText(), kilometrageField.getText());
                if(validateVIN(vinField.getText())){
                    if(validateLoanAmount(montantPretField.getText())){
                        if(validateLoanDuration(dureePretField.getText())){
                            if ("d'Occasion".equals(typeVehiculeComboBox.getSelectedItem())) {
                                if(validateMileage(kilometrageField.getText())){
                                    // Chama o BD
                                }
                            }else{
                                // Chama BD
                            }
                        }
                    }
                };

            }
        });

        add(soumettreButton);
        add(backButton);

        
    }
//    private void areAllFieldsFilled(String vin, String amount, String duration, String mileage){
//
////        if(vin.isEmpty() || amount.isEmpty() || duration.isEmpty() || mileage.isEmpty()){
////            JOptionPane.showMessageDialog(null, "Il faut remplir le champs" , "VIN NULL", WARNING_MESSAGE);
////        }
//    }
    public boolean validateVIN(String vin) {
        if(vin.isEmpty()){
            JOptionPane.showMessageDialog(null, "Il faut remplir le champs" , "VIN empty", WARNING_MESSAGE);
            return false;
        }else{
            if (vin.length() != 17) {
                JOptionPane.showMessageDialog(null, "Le numéro d'identification du véhicule doit contenir exactement 17 caractères." , "VIN", WARNING_MESSAGE);
                return false;
            }
        }
        return true;

    }


    public boolean validateLoanAmount(String amount) {
        if(amount.isEmpty()){
            JOptionPane.showMessageDialog(null, "Il faut remplir le champs" , "Amount empty", WARNING_MESSAGE);
            return false;
        }else{
            try{
                double amountDouble = Double.valueOf(amount);
                if (amountDouble <= 0 || amountDouble > 60000) {
                    JOptionPane.showMessageDialog(null, "La valeur maximale du prêt est de 60 000 $." , "Loan Amount", WARNING_MESSAGE);
                    return false;
                }
            }catch (Exception err){
                JOptionPane.showMessageDialog(null, "La valeur du prêt est numerique." , "Loan Amount", WARNING_MESSAGE);
                return false;
            }

        }
        return true;

    }

    public boolean validateLoanDuration(String duration) {
        if(duration.isEmpty()){
            JOptionPane.showMessageDialog(null, "Il faut remplir le champs" , "Duration empty", WARNING_MESSAGE);
            return false;
        }else{
            try{
                int durationInt = Integer.valueOf(duration);
                if (durationInt <= 0 || durationInt > 48) {
                    JOptionPane.showMessageDialog(null, "La durée maximale du prêt est de 4 ans.", "Loan Duration", WARNING_MESSAGE);
                    return false;
                }
            }catch (Exception err){
                JOptionPane.showMessageDialog(null, "La valeur durée maximale du prêt est numerique." , "Loan Duration", WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public boolean validateMileage(String mileage) {
        if(mileage.isEmpty()){
            JOptionPane.showMessageDialog(null, "Il faut remplir le champs" , "Mileage empty ", WARNING_MESSAGE);
            return false;
        }else{
            try{
                int mileageInt = Integer.valueOf(mileage);
                if (mileageInt > 230000) {
                    JOptionPane.showMessageDialog(null, "Le kilométrage ne doit pas excéder 230 000 pour un véhicule d'occasion.", "Loan Duration", WARNING_MESSAGE);
                    return false;
                }
            }catch (Exception err){
                JOptionPane.showMessageDialog(null, "Le kilométrage est numerique." , "Loan Amount", WARNING_MESSAGE);
                return false;
            }

        }
        return true;
    }
}
