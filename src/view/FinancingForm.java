package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            }
        });

        soumettreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Demande soumise." , "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(soumettreButton);
        add(backButton);
    }
}
