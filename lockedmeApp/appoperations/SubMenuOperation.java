package javaphase1.lockedmeApp.appoperations;

import javaphase1.lockedmeApp.businessoperations.SubMenu;
import javaphase1.lockedmeApp.businessoperations.IBusinessOperation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SubMenuOperation implements IAppOperation {
    EnumMap<SubMenu, IBusinessOperation> subMenu ;
    final Scanner scanner;
    public SubMenuOperation(EnumMap<SubMenu, IBusinessOperation> subMenu, final Scanner scanner) {
        this.subMenu = subMenu;
        this.scanner = scanner;
    }

    @Override
    public boolean execute(Path rootDirPath) throws IOException {
        while (true) {
            displaySubMenu();
            String option = scanner.nextLine();

            try {
                SubMenu selectedMenuItem = SubMenu.getBySymbol(option);
                IBusinessOperation operation = subMenu.get(selectedMenuItem);
                if(operation.execute(rootDirPath, scanner)){
                    return false;
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void displaySubMenu() {
        System.out.println("\nPlease select an option:");
        for (SubMenu option : subMenu.keySet()) {
            System.out.println(option.getSymbol() + "> "+ option.getDescription());
        }

    }
}
