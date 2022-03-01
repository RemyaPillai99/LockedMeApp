package javaphase1.lockedmeApp.businessoperations;


import org.junit.Assert;
import org.junit.Test;

import java.util.InputMismatchException;

public class SubMenuTest {
    private static String VALID_SYMBOL = "1";
    private static String INVALID_SYMBOL = "?";

    @Test
    public void testGetBySymbolForValidSymbolReturnsSubmenuItem(){
        Assert.assertEquals(SubMenu.ADD, SubMenu.getBySymbol(VALID_SYMBOL));

    }

    @Test(expected = InputMismatchException.class)
    public void testGetBySymbolForInvalidSymbolThrowsException(){
        SubMenu.getBySymbol(INVALID_SYMBOL);

    }
}
