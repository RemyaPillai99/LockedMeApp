package javaphase1.lockedmeApp.businessoperations;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchFileOperation implements IBusinessOperation {
    Pattern pattern;
    boolean searchFlag = false;

    @Override
    public boolean execute(final Path rootDirPath, Scanner scanner) throws IOException {

        System.out.println("Enter file name to be searched");
        pattern = Pattern.compile(scanner.nextLine().trim(), Pattern.CASE_INSENSITIVE);
        FileVisitor<Path> findFileVisitor = new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {

                Matcher matcher = pattern.matcher(dir.getFileName().toString());
                if (matcher.matches()) {
                    System.out.println(dir);

                    searchFlag = true;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

                Matcher matcher;
                String extension = pattern.toString().substring(pattern.toString().lastIndexOf(".") + 1);
                if(!extension.isEmpty()) {
                    matcher = pattern.matcher(file.getFileName().toString());
                }else {
                    String fileNameWithOutExt = file.getFileName().toString().replaceFirst("[.][^.]+$", "");
                    matcher = pattern.matcher(fileNameWithOutExt);
                }

                if (matcher.matches()) {
                    System.out.println(file);
                    searchFlag = true;
                }
                return FileVisitResult.CONTINUE;
            }
        };
        try {
            Files.walkFileTree(rootDirPath, findFileVisitor);
        } catch (IOException e) {
            throw new IOException();
        }
        if (!searchFlag) {
            System.out.println("No Files found matching the pattern, please modify your search criteria");

        }
        return false;

    }
}
