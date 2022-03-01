package javaphase1.lockedmeApp.appoperations;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.TreeSet;

public class ListFilesOperation implements IAppOperation {

    @Override
    public boolean execute(final Path rootDirPath) throws IOException {
        TreeSet<String> paths = getPaths(rootDirPath);
        paths.forEach(System.out::println);
        return false;
    }


    protected TreeSet<String> getPaths(final Path rootDirPath) throws IOException {
        TreeSet<String> paths = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        Files.walkFileTree(rootDirPath, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path visitedFile, BasicFileAttributes attrs) {
                paths.add(visitedFile.toAbsolutePath().toString());
                return FileVisitResult.CONTINUE;
            }
        });

        return paths;
    }

}
