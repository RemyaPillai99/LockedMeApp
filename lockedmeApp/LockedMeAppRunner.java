package javaphase1.lockedmeApp;

import javaphase1.lockedmeApp.appoperations.MainMenu;
import javaphase1.lockedmeApp.businessoperations.SubMenu;
import javaphase1.lockedmeApp.appoperations.IAppOperation;
import javaphase1.lockedmeApp.appoperations.ListFilesOperation;
import javaphase1.lockedmeApp.appoperations.QuitOperation;
import javaphase1.lockedmeApp.appoperations.SubMenuOperation;
import javaphase1.lockedmeApp.businessoperations.*;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Scanner;

public class LockedMeAppRunner {
    private  EnumMap<MainMenu, IAppOperation> mainMenu;
    private EnumMap<SubMenu, IBusinessOperation> subMenu;
    private Scanner scanner;

    public LockedMeAppRunner() {

        this.mainMenu = new EnumMap<>(MainMenu.class);
        this.subMenu = new EnumMap<>(SubMenu.class);

        this.scanner = new Scanner(System.in);
        subMenu.put(SubMenu.ADD, new AddFileOperation());
        subMenu.put(SubMenu.DELETE, new DeleteFileOperation());
        subMenu.put(SubMenu.SEARCH, new SearchFileOperation());
        subMenu.put(SubMenu.NAVIGATE_BACK, new NavigateBackOperation());

        mainMenu.put(MainMenu.LIST, new ListFilesOperation());
        mainMenu.put(MainMenu.SUB_MENU,  new SubMenuOperation(subMenu, scanner));
        mainMenu.put(MainMenu.QUIT, new QuitOperation());

    }


    public static void main(String[] args) throws IOException {
        LockedMeAppRunner runner = new LockedMeAppRunner();
        LockedMeApp app = new LockedMeApp(runner.mainMenu, runner.scanner);
        app.run();
    }
}
