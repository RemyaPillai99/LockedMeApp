package javaphase1.lockedmeApp.appoperations;

import org.junit.Assert;
import org.junit.Test;

import java.util.InputMismatchException;

public class MainMenuTest {
    private static String VALID_SYMBOL = "1";
    private static String INVALID_SYMBOL = "?";

    @Test
    public void testGetBySymbolForValidSymbolReturnsMainMenuItem() {
        Assert.assertEquals(MainMenu.LIST, MainMenu.getBySymbol(VALID_SYMBOL));
    }

    @Test(expected = InputMismatchException.class)
    public void testGetBySymbolForInValidSymbolThrowsException() {
        MainMenu.getBySymbol(INVALID_SYMBOL);
    }
}
