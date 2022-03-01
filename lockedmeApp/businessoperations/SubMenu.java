package javaphase1.lockedmeApp.businessoperations;

import java.util.InputMismatchException;

public enum SubMenu {

    ADD("1", "Add a file to be locked"),
    DELETE("2", "Delete file"),
    SEARCH("3", "Search for a file" ),
    NAVIGATE_BACK("4", "Navigate back to the Main menu");

    SubMenu(final String symbol, final String description){
        this.symbol = symbol;
        this.description = description;
    }

    private final String symbol;
    private final String description;

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public static SubMenu getBySymbol(final String symbol) {
        for (SubMenu value : SubMenu.values()) {
            if (value.getSymbol().equals(symbol)) {
                return value;
            }
        }
        throw new InputMismatchException("Invalid Input");
    }
}
