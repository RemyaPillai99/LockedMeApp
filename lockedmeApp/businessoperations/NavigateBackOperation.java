package javaphase1.lockedmeApp.businessoperations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class NavigateBackOperation implements IBusinessOperation {

    @Override
    public boolean execute(Path rootDirPath, Scanner scanner) throws IOException {
        return true;
    }
}
