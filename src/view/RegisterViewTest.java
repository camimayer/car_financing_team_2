package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterViewTest {

    private RegisterView registerView;

    @BeforeEach
    void setUp() {
        registerView = new RegisterView(null, null, null);
    }


    @Test
    void testInvalidPassword_Length() {
        String invalidPassword = "Cam1@";

        assertFalse(registerView.isPasswordValid());
    }

    @Test
    void testInvalidPassword_NoUppercase() {
        String invalidPassword = "camila1@";

        assertFalse(registerView.isPasswordValid());
    }

    @Test
    void testInvalidPassword_NoLowercase() {
        String invalidPassword = "CAMILA1@";

        assertFalse(registerView.isPasswordValid());
    }

    @Test
    void testInvalidPassword_NoDigit() {
        String invalidPassword = "Camilaa@";

        assertFalse(registerView.isPasswordValid());
    }

    @Test
    void testInvalidPassword_NoSpecialChar() {
        String invalidPassword = "Camila11";

        assertFalse(registerView.isPasswordValid());
    }

    @Test
    void testInvalidPassword_Null() {
        String invalidPassword = null;

        assertFalse(registerView.isPasswordValid());
    }
}



