package view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewTest {

    @Test
    void testValidatePassword_Client_Success() {
        String user = "cami@gmail";
        String password = "Camila1@";
        boolean checkInvestor = false;

        LoginView loginView = new LoginView(null, null, null);

        boolean result = loginView.validatePassword(user, password, checkInvestor);

        assertTrue(result, "La méthode doit renvoyer true pour un client valide.");
    }

    @Test
    void testValidatePassword_Investor_Success() {
        String user = "cami@gmail";
        String password = "Camila1@";
        boolean checkInvestor = true;

        LoginView loginView = new LoginView(null, null, null);

        boolean result = loginView.validatePassword(user, password, checkInvestor);

        assertTrue(result, "La méthode doit retourner vrai pour un investisseur valide.");
    }

    @Test
    void testValidatePassword_Incorrect_Password() {
        String user = "cami@gmail";
        String password = "Camila";
        boolean checkInvestor = false;

        LoginView loginView = new LoginView(null, null, null);

        boolean result = loginView.validatePassword(user, password, checkInvestor);

        assertFalse(result, "La méthode doit renvoyer false pour un mot de passe incorrect.");
    }

    @Test
    void testValidatePassword_Incorrect_User() {
        String user = "cami";
        String password = "mila1@";
        boolean checkInvestor = true;

        LoginView loginView = new LoginView(null, null, null);

        boolean result = loginView.validatePassword(user, password, checkInvestor);

        assertFalse(result, "La méthode doit renvoyer false pour un utilisateur incorrect.");
    }
}





