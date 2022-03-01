package javaphase1.lockedmeApp;
import javaphase1.lockedmeApp.appoperations.MainMenu;
import javaphase1.lockedmeApp.appoperations.IAppOperation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import java.util.InputMismatchException;
public class LockedMeApp {
    private Path ROOT_DIR_PATH;
    private final EnumMap<MainMenu, IAppOperation> mainMenu;
    private final Scanner scanner;


    public LockedMeApp(final EnumMap<MainMenu, IAppOperation> mainMenu, final Scanner scanner) throws IOException {
        init();
        this.mainMenu = mainMenu;
        this.scanner = scanner;
    }


    private void init() throws IOException {
        readPropertiesFile();
    }

    private void readPropertiesFile() throws IOException {
        try (InputStream input = getClass().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (Objects.isNull(input)) {
                System.out.println("Sorry, not able to launch the app");
                return;
            }
            prop.load(input);
            ROOT_DIR_PATH = FileSystems.getDefault().getPath(prop.getProperty("ROOT_DIR_PATH"));

        }
    }

    public void run() {
        try {
            displayWelcomeScreen();
            while (true) {
                displayMainMenu();
                String option = scanner.nextLine();
                try {
                    MainMenu selectedMenuItem = MainMenu.getBySymbol(option);
                    IAppOperation operation = mainMenu.get(selectedMenuItem);
                    if (operation.execute(ROOT_DIR_PATH)) {
                        return;
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void displayWelcomeScreen() {
        System.out.println("\n###################################");
        System.out.println("\tApplication Name: LockedMe.com");
        System.out.println("\tDeveloper: Remya Pillai ");
        System.out.println("####################################");
    }

    private void displayMainMenu() {
        System.out.println("\nPlease select an option:");
        for (MainMenu option : mainMenu.keySet()) {
            System.out.println(option.getSymbol() + "> " + option.getDescription());
        }
    }

}