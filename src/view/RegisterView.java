package view;
import DAO.ClientDAO;
import DAO.ClientDAOImpl;
import DAO.InvestorDAO;
import DAO.InvestorDAOImpl;
import helper.Helper;
import model.Client;
import model.Investor;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static helper.Helper.dateConverter;
import static view.LoginView.SALT;
import static view.LoginView.hashPasswordWithSalt;

// Définition de la classe RegisterView qui étend JPanel
public class RegisterView extends JPanel {
    // Déclaration des composants GUI

    // JComboBox pour sélectionner le type d'utilisateur (Client ou Investisseur)
    private JComboBox<String> userTypeComboBox;

    // Champs de mot de passe
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    // Champs pour les utilisateurs de type "Client"
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JLabel jobInfoLabel;
    private JTextField jobInfoField;
    private JLabel annualIncomeLabel;
    private JTextField annualIncomeField;
    private JLabel creditNoteLabel;
    private JTextField creditNoteField;
    private JLabel birthDateLabel;
    private JTextField birthDateField;
    private JLabel maritalStatusLabel;
    private JTextField maritalStatusField;
    private JLabel yearsInCanadaLabel;
    private JTextField yearsInCanadaField;

    // Champs pour les utilisateurs de type "Investisseur"
    private JLabel bankNameLabel;
    private JTextField bankNameField;
    private JLabel accountDetailsLabel;
    private JTextField accountDetailsField;
    private JLabel riskLevelLabel;
    private JTextField riskLevelField;
    private JLabel investmentEducationLabel;
    private JTextField investmentEducationField;

    CardLayout cardLayout;
    JPanel cardPanel;
    JFrame main;


    public RegisterView(CardLayout cardLayout, JPanel cardPanel, JFrame main) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.main = main;

        // Configuration du layout en grille pour organiser les composants
        setLayout(new GridLayout(0, 2));

        // Initialisation de la JComboBox pour le type d'utilisateur
        String[] userTypes = {"Choisi un type", "Client", "Investisseur"};
        userTypeComboBox = new JComboBox<>(userTypes);

        // Initialisation des champs de mot de passe
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        // Initialisation des champs commun
        fullNameField = new JTextField();
        emailField = new JTextField();
        phoneNumberField = new JTextField();
        // Initialisation des champs pour les utilisateurs de type "Client"
        jobInfoField = new JTextField();
        jobInfoLabel = new JLabel("Informations sur l'Emploi: ");
        annualIncomeField = new JTextField();
        annualIncomeLabel = new JLabel("Revenu Annuel: ");
        creditNoteField = new JTextField();
        creditNoteLabel = new JLabel("Note de Crédit: ");
        birthDateField = new JTextField();
        birthDateLabel = new JLabel("Date de Naissance: ");
        maritalStatusField = new JTextField();
        maritalStatusLabel = new JLabel("Statut Marital: ");
        yearsInCanadaField = new JTextField();
        yearsInCanadaLabel = new JLabel("Établi au Canada depuis: ");

        // Initialisation des champs pour les utilisateurs de type "Investisseur"
        bankNameLabel = new JLabel("Nom de la Banque: ");
        bankNameField = new JTextField();
        accountDetailsLabel = new JLabel("Détails du Compte Bancaire: ");
        accountDetailsField = new JTextField();
        riskLevelLabel = new JLabel("Niveau de Risque Souhaité: ");
        riskLevelField = new JTextField();

        investmentEducationLabel = new JLabel("Niveau d'Éducation en Investissement: ");
        investmentEducationField = new JTextField();

        // Ajout d'un bouton pour l'inscription avec un gestionnaire d'événements
        JButton registerButton = new JButton("S'inscrire");

        JButton backButton = new JButton("Page principal");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.setSize(600, 400);
                cardLayout.show(cardPanel, "Main");
                cardPanel.add(new RegisterView(cardLayout, cardPanel, main), "Register");
            }
        });

        // Ajout des labels et des champs au panneau
        add(new JLabel("Type d'Utilisateur: "));
        add(userTypeComboBox);
        userTypeComboBox.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selectedItem = (String) combo.getSelectedItem();
//                System.out.println(selectedItem);
                viewControl(selectedItem, registerButton, backButton);
            }
        });

        add(new JLabel("Mot de Passe: "));
        add(passwordField);

        add(new JLabel("Confirmer le Mot de Passe: "));
        add(confirmPasswordField);

        add(new JLabel("Nom Complet: "));
        add(fullNameField);

        add(new JLabel("Adresse Email: "));
        add(emailField);

        add(new JLabel("Numéro de Téléphone: "));
        add(phoneNumberField);

        add(jobInfoLabel);
        add(jobInfoField);

        add(annualIncomeLabel);
        add(annualIncomeField);

        add(creditNoteLabel);
        add(creditNoteField);

        add(birthDateLabel);
        add(birthDateField);

        add(maritalStatusLabel);
        add(maritalStatusField);

        add(yearsInCanadaLabel);
        add(yearsInCanadaField);

        add(bankNameLabel);
        add(bankNameField);

        add(accountDetailsLabel);
        add(accountDetailsField);

        add(riskLevelLabel);
        add(riskLevelField);

        add(investmentEducationLabel);
        add(investmentEducationField);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ("Choisi un type".equals(userTypeComboBox.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Il fault choisir un type d'user.", "Type d'user", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Vérification des champs avant l'inscription
                if (areAllFieldsFilled()) {
                    // Vaidation du mot de passe
                    if (isPasswordValid()) {
                        // Création d'une instance de Client ou Investisseur selon le type sélectionné
                        if ("Client".equals(userTypeComboBox.getSelectedItem())) {
                            // Ajoute client dans la base de donées
                            addClientToDB();
                            System.out.println(prepareClient());
                            JOptionPane.showMessageDialog(null, "Client enregistré avec succès.", "Enregistrementr", JOptionPane.WARNING_MESSAGE);
                            cardLayout.show(cardPanel, "Register");
                            cardPanel.add(new RegisterView(cardLayout, cardPanel, main), "Register");
                            revalidate();
                        } else {
                            addInvestorToDB();
                            System.out.println(prepareInvestor());
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Le mot de passe ne correspond pas aux critires.", "Mot de passe non invalide", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    // Affichage d'un message d'avertissement si les champs ne sont pas correctement remplis
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs correctement.", "Champs manquants ou invalide", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        add(registerButton);
        add(backButton);
    }

    public void listClientFromDB(){
//        Client client = prepareClient();
//        ClientDAO clientDAO = new ClientDAOImpl();
//        clientDAO.getAllClients(client);
//        List<Client> retorno = clientDAO.getAllClients(client);
    }
    public void addClientToDB(){
        Client client = prepareClient();
        ClientDAO clientDAO = new ClientDAOImpl();
        clientDAO.addClient(client);
    }

    public void addInvestorToDB(){
        Investor investor = prepareInvestor();
        InvestorDAO investorDAO = new InvestorDAOImpl();
        investorDAO.addInvestor(investor);
    }

    private void viewControl(String typeView, JButton inscriptionButton, JButton retourButton) {
        var isInvestor = "Investisseur".equals(typeView);
        var isClient = "Client".equals(typeView);

        remove(inscriptionButton);
        remove(retourButton);

        if (isInvestor) {
            remove(jobInfoLabel);
            remove(jobInfoField);
            remove(creditNoteLabel);
            remove(creditNoteField);
            remove(birthDateLabel);
            remove(birthDateField);
            remove(maritalStatusLabel);
            remove(maritalStatusField);
            remove(yearsInCanadaLabel);
            remove(yearsInCanadaField);
            remove(annualIncomeLabel);
            remove(annualIncomeField);

            add(bankNameLabel);
            add(bankNameField);
            add(accountDetailsLabel);
            add(accountDetailsField);
            add(riskLevelLabel);
            add(riskLevelField);
            add(investmentEducationLabel);
            add(investmentEducationField);

        } else if (isClient){
            remove(bankNameLabel);
            remove(bankNameField);
            remove(accountDetailsLabel);
            remove(accountDetailsField);
            remove(riskLevelLabel);
            remove(riskLevelField);
            remove(investmentEducationLabel);
            remove(investmentEducationField);

            add(jobInfoLabel);
            add(jobInfoField);
            add(annualIncomeLabel);
            add(annualIncomeField);
            add(creditNoteLabel);
            add(creditNoteField);
            add(birthDateLabel);
            add(birthDateField);
            add(maritalStatusLabel);
            add(maritalStatusField);
            add(yearsInCanadaLabel);
            add(yearsInCanadaField);
        }

        add(inscriptionButton);
        add(retourButton);
        revalidate();
    }

    private Client prepareClient() {
        byte[] hashedPassword = hashPasswordWithSalt(passwordField.getText().getBytes(), SALT);

        Client client = new Client();
        client.setFullName( fullNameField.getText());
        client.setEmail(emailField.getText());
        //client.setPassword(hashedPassword.toString());
        client.setPassword(passwordField.getText());
        client.setPhoneNumber(phoneNumberField.getText());
        client.setJobInfo(jobInfoField.getText());
        if (!Objects.isNull(annualIncomeField.getText()) && !annualIncomeField.getText().isEmpty()) {
            client.setAnnualIncome(Double.valueOf(annualIncomeField.getText()));
        }

        if (!Objects.isNull(creditNoteField.getText()) && !creditNoteField.getText().isEmpty()) {
            client.setCreditNote(Integer.valueOf(creditNoteField.getText()));
        }

        if (!Objects.isNull(birthDateField.getText()) && !birthDateField.getText().isEmpty()) {
            client.setBirthDateField(dateConverter(birthDateField.getText()));
        }

        client.setMaritalStatus(maritalStatusField.getText());

        if (!Objects.isNull(yearsInCanadaField.getText()) && !yearsInCanadaField.getText().isEmpty()) {
            client.setYearsInCanada(Integer.valueOf(yearsInCanadaField.getText()));
        }

        return client;
    }

    private Investor prepareInvestor() {
        byte[] hashedPassword = hashPasswordWithSalt(passwordField.getText().getBytes(), SALT);
        var investor = new Investor();
        investor.setFullName( fullNameField.getText());
        investor.setEmail(emailField.getText());
//        investor.setPassword(Arrays.toString(hashedPassword));
        investor.setPassword(passwordField.getText());
        investor.setPhoneNumber(phoneNumberField.getText());
        investor.setBankName(bankNameField.getText());
        investor.setAccountDetails(accountDetailsField.getText());
        investor.setRiskLevel(riskLevelField.getText());
        investor.setLevelEducation(investmentEducationField.getText());

        return investor;
    }

    // Méthode pour vérifier si tous les champs sont remplis
    private boolean areAllFieldsFilled() {

         if ("Client".equals(userTypeComboBox.getSelectedItem())) {
             return !fullNameField.getText().isEmpty() &&
                    !emailField.getText().isEmpty() &&
                    !new String(passwordField.getPassword()).isEmpty() &&
                    !phoneNumberField.getText().isEmpty() &&
                    !jobInfoField.getText().isEmpty() &&
                    !annualIncomeField.getText().isEmpty() &&
                    !creditNoteField.getText().isEmpty() &&
                    !birthDateField.getText().isEmpty() &&
                    !maritalStatusField.getText().isEmpty() &&
                    !yearsInCanadaField.getText().isEmpty();
         } else {
             return !fullNameField.getText().isEmpty() &&
                     !emailField.getText().isEmpty() &&
                     !new String(passwordField.getPassword()).isEmpty() &&
                     !phoneNumberField.getText().isEmpty() &&
                     !bankNameField.getText().isEmpty() &&
                     !accountDetailsField.getText().isEmpty() &&
                     !riskLevelField.getText().isEmpty() &&
                     !investmentEducationField.getText().isEmpty();
         }

    }

    public boolean isPasswordValid() {
        var password = new String(passwordField.getPassword());
        var confirmPassword = new String(confirmPasswordField.getPassword());

        if (!password.equals(confirmPassword)) {
            return false;
        }


        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecialChar = true;
            }
        }

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }
}