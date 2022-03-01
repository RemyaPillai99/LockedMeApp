package javaphase1.lockedmeApp.businessoperations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public interface IBusinessOperation {

  /**
   *
   * @param rootDirPath
   * @param scanner
   * @return returns true for operation to navigate back to Main menu App
   * @throws IOException
   */
  boolean execute(final Path rootDirPath, Scanner scanner) throws IOException;

}
