package javaphase1.lockedmeApp.appoperations;

import java.io.IOException;
import java.nio.file.Path;

@FunctionalInterface
public interface IAppOperation {
    /**
     *
     * @param rootDirPath
     * @return return true to exit app
     * @throws IOException
     */
    boolean execute(Path rootDirPath) throws IOException;

}
