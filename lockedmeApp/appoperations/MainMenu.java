package javaphase1.lockedmeApp.appoperations;


import java.util.InputMismatchException;

public enum MainMenu {
    LIST("1", "List current file names in repository"),
    SUB_MENU("2", "Enter the sub menu"),
    QUIT("3", "Exit App" );

    MainMenu(final String symbol, final String description){
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

    public static MainMenu getBySymbol(final String symbol) {
        for (MainMenu value : MainMenu.values()) {
            if (value.getSymbol().equals(symbol)) {
                return value;
            }
        }
        throw new InputMismatchException("Invalid Input");
    }
}
