package javaphase1.lockedmeApp.businessoperations;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class AddFileOperation implements IBusinessOperation {

    @Override
    public boolean execute(final Path rootDirPath, Scanner scanner) throws IOException {
        System.out.println("Enter the absolute file path to be locked :");

        Path srcPath = Paths.get(scanner.nextLine());

        Path destPath = rootDirPath.resolve(srcPath.getFileName());

        try {
            Files.move(srcPath, destPath);
        } catch (NoSuchFileException e) {
            System.out.println("Please enter valid file path");
            return false;

        } catch (FileAlreadyExistsException fae) {
            System.out.println("The file already exists in the repository . \n Do you want to overwrite the existing file/directory Y/N");
            if (scanner.nextLine().toUpperCase().equals("Y")) {
                handleOverwriteFile(srcPath, destPath);
            } else {
                return false;
            }
        }
        System.out.println("File added successfully");
        return false;
    }

    public void handleOverwriteFile(Path srcPath, Path destPath) throws IOException {
        Files.move(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
