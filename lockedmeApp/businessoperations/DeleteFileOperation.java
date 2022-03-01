package javaphase1.lockedmeApp.businessoperations;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;


public class DeleteFileOperation implements IBusinessOperation {


    /**
     * @param rootDirPath
     * @param scanner
     * @return
     */
    @Override
    public boolean execute(final Path rootDirPath, Scanner scanner) throws IOException {


            /*Files.walk() returns a Stream of Path that we sort in reverse order.
            This places the paths denoting the contents of directories before directories itself.
            Thereafter it maps Path to File and deletes each File.*/

            System.out.println("Enter file to be removed from the App :");
            Path path = Paths.get(scanner.nextLine());

            Path usrPath = rootDirPath.resolve(path);

            if (Objects.isNull(usrPath)|| !Files.exists(usrPath)) {
                System.out.println("File does not exist in app");
                return false;
            }
             else {
                Files.walk(usrPath)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }

        System.out.println("File deletion was successful");
        return false;
    }
}
