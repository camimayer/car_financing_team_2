package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvestissementFormTest {

    private InvestissementForm investissementForm;

    @BeforeEach
    void setUp() {
        investissementForm = new InvestissementForm(null, null, null);
    }

    @Test
    void testValidateInvestissement_ValidAmount() {
        String validAmount = "150";

        assertTrue(investissementForm.validateInvestissement(validAmount));
    }

    @Test
    void testValidateInvestissement_EmptyAmount() {
        String emptyAmount = "";

        assertFalse(investissementForm.validateInvestissement(emptyAmount));
    }

    @Test
    void testValidateInvestissement_LowerThanMinimum() {
        String lowerThanMinimumAmount = "50";

        assertFalse(investissementForm.validateInvestissement(lowerThanMinimumAmount));
    }

    @Test
    void testValidateInvestissement_NonNumericAmount() {
        String nonNumericAmount = "abc";

        assertFalse(investissementForm.validateInvestissement(nonNumericAmount));
    }
}
