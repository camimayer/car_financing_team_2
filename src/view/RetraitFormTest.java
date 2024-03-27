package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RetraitFormTest {

    private RetraitForm retraitForm;

    @BeforeEach
    void setUp() {
        retraitForm = new RetraitForm(null, null, null);
    }


    @Test
    void testValidateRetrait_EmptyAmount() {
        String emptyAmount = "";
        assertFalse(retraitForm.validateRetrait(emptyAmount));
    }

    @Test
    void testValidateRetrait_AmountExceedsBalance() {
        String amountExceedsBalance = "1000";
        assertFalse(retraitForm.validateRetrait(amountExceedsBalance));
    }

    @Test
    void testValidateRetrait_NonNumericAmount() {
        String nonNumericAmount = "abc";
        assertFalse(retraitForm.validateRetrait(nonNumericAmount));
    }
}
