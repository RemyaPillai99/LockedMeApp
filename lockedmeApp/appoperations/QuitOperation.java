package javaphase1.lockedmeApp.appoperations;

import java.io.IOException;
import java.nio.file.Path;

public class QuitOperation implements IAppOperation {
    @Override
    public boolean execute(Path rootDirPath) throws IOException {
        return true;
    }
}
