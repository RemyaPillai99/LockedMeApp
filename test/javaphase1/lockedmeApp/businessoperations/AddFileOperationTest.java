package javaphase1.lockedmeApp.businessoperations;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.TemporaryFolder;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;


public class AddFileOperationTest {
    private AddFileOperation operation;
    Scanner scanner;


    @Rule
    public TemporaryFolder appRootDir = new TemporaryFolder();

    @Rule
    public TemporaryFolder contentDir = new TemporaryFolder();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();



    @Before
    public void setup() {
        operation = new AddFileOperation();
        scanner = new Scanner(System.in);
    }

    @Test
    public void testExecuteReturnsFalse() throws IOException {
        File f = contentDir.newFile("b.txt");

        systemInMock.provideLines(f.getAbsolutePath());

        Assert.assertFalse(operation.execute(Paths.get(appRootDir.getRoot().toURI()),scanner));
        Assert.assertFalse(Files.exists(Paths.get(f.toURI())));
        Assert.assertTrue(Files.exists(Paths.get(appRootDir.getRoot()+"/b.txt")));

    }

    @Test
    public void testExecuteWhereFileDoesNotExist() throws IOException {

        systemInMock.provideLines(contentDir.getRoot()+"/dummyFile");
        Assert.assertFalse(operation.execute(Paths.get(appRootDir.getRoot().toURI()), scanner));
        Assert.assertTrue(systemOutRule.getLog().contains("Please enter valid file path"));
    }

    @Test
    public void testExecuteWhereFileAlreadyExistsAndOverwrite() throws IOException{
        File f = contentDir.newFile("b.txt");
        appRootDir.newFile("b.txt");
        systemInMock.provideLines(f.getAbsolutePath(), "Y");
        operation.execute(Paths.get(appRootDir.getRoot().toURI()),scanner);
        Assert.assertFalse(Files.exists(Paths.get(f.toURI())));
    }

    @Test
    public void testExecuteWhereFileAlreadyExistsAndDoNotOverwrite() throws IOException{
        File f = contentDir.newFile("b.txt");
        appRootDir.newFile("b.txt");
        systemInMock.provideLines(f.getAbsolutePath(), "N");
        operation.execute(Paths.get(appRootDir.getRoot().toURI()),scanner);
        Assert.assertTrue(Files.exists(Paths.get(f.toURI())));
    }
}
