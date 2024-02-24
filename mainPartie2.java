// Importation des classes nécessaires de la bibliothèque Swing
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField jobInfoField;
    private JTextField annualIncomeField;
    private JTextField creditNoteField;
    private JTextField birthDateField;
    private JTextField maritalStatusField;
    private JTextField yearsInCanadaField;

    // Champs pour les utilisateurs de type "Investisseur"
    private JTextField bankNameField;
    private JTextField accountDetailsField;
    private JTextField riskLevelField;
    private JTextField investmentEducationField;

    // Constructeur de la classe RegisterView
    public RegisterView() {
        // Configuration du layout en grille pour organiser les composants
        setLayout(new GridLayout(0, 2));

        // Initialisation de la JComboBox pour le type d'utilisateur
        String[] userTypes = {"Client", "Investisseur"};
        userTypeComboBox = new JComboBox<>(userTypes);

        // Initialisation des champs de mot de passe
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        // Initialisation des champs pour les utilisateurs de type "Client"
        fullNameField = new JTextField();
        emailField = new JTextField();
        phoneNumberField = new JTextField();
        jobInfoField = new JTextField();
        annualIncomeField = new JTextField();
        creditNoteField = new JTextField();
        birthDateField = new JTextField();
        maritalStatusField = new JTextField();
        yearsInCanadaField = new JTextField();

        // Initialisation des champs pour les utilisateurs de type "Investisseur"
        bankNameField = new JTextField();
        accountDetailsField = new JTextField();
        riskLevelField = new JTextField();
        investmentEducationField = new JTextField();

        // Ajout des labels et des champs au panneau
        add(new JLabel("Type d'Utilisateur: "));
        add(userTypeComboBox);

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

        add(new JLabel("Informations sur l'Emploi: "));
        add(jobInfoField);

        add(new JLabel("Revenu Annuel: "));
        add(annualIncomeField);

        add(new JLabel("Note de Crédit: "));
        add(creditNoteField);

        add(new JLabel("Date de Naissance: "));
        add(birthDateField);

        add(new JLabel("Statut Marital: "));
        add(maritalStatusField);

        add(new JLabel("Établi au Canada depuis: "));
        add(yearsInCanadaField);

        add(new JLabel("Nom de la Banque: "));
        add(bankNameField);

        add(new JLabel("Détails du Compte Bancaire: "));
        add(accountDetailsField);

        add(new JLabel("Niveau de Risque Souhaité: "));
        add(riskLevelField);

        add(new JLabel("Niveau d'Éducation en Investissement: "));
        add(investmentEducationField);

        // Ajout d'un bouton pour l'inscription avec un gestionnaire d'événements
        JButton registerButton = new JButton("S'inscrire");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vérification des champs avant l'inscription
                if (areAllFieldsFilled() && isPasswordValid()) {
                    // Création d'une instance de Client ou Investisseur selon le type sélectionné
                    if ("Client".equals(userTypeComboBox.getSelectedItem())) {
                        Client client = new Client(fullNameField.getText(), emailField.getText(), new String(passwordField.getPassword()), phoneNumberField.getText(),
                                jobInfoField.getText(), annualIncomeField.getText(), creditNoteField.getText(),
                                birthDateField.getText(), maritalStatusField.getText(), yearsInCanadaField.getText());
                        System.out.println(client.toString());
                    } else {
                        Investisseur investor = new Investisseur(fullNameField.getText(), emailField.getText(), new String(passwordField.getPassword()), phoneNumberField.getText(),
                                bankNameField.getText(), accountDetailsField.getText(), riskLevelField.getText(), investmentEducationField.getText());
                        System.out.println(investor.toString());
                    }
                } else {
                    // Affichage d'un message d'avertissement si les champs ne sont pas correctement remplis
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs correctement.", "Champs manquants ou invalide", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Ajout d'espacements pour l'esthétique
        add(new JLabel());
        add(registerButton);
    }

    // Méthode pour vérifier si tous les champs sont remplis
    private boolean areAllFieldsFilled() {
        return !fullNameField.getText().isEmpty() &&
                !emailField.getText().isEmpty() &&
                !new String(passwordField.getPassword()).isEmpty() &&
                new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword())) &&
                !phoneNumberField.getText().isEmpty();
        
    }

    // Méthode pour vérifier si le mot de passe est valide
    private boolean isPasswordValid() {
        String password = new String(passwordField.getPassword());

        // Validation du mot de passe
        // Le mot de passe doit avoir au moins 8 caractères
        // Doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial

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
