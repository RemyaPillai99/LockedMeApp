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
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeSet;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class SearchFilesOperationTest {
    private SearchFileOperation operation;
    private Scanner scanner;
    TreeSet<String> expectedOutput;
    String expectedOutputString1,expectedOutputString2;


    @Rule
    public TemporaryFolder appRootDir = new TemporaryFolder();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    @Before

    public void setup(){
        operation = new SearchFileOperation();
        //expectedOutput = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        scanner = new Scanner(System.in);
        File f = null;
        try {
            f = appRootDir.newFile( "a.txt" );
            expectedOutputString1 = f.getAbsolutePath();
            f = appRootDir.newFile( "c.txt" );

            File subDirectory = appRootDir.newFolder("B");
            f = new File(subDirectory, "d.txt");
            f.createNewFile();

            f = new File(subDirectory, "A.txt");
            f.createNewFile();
            expectedOutputString2 = f.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testExecuteReturnsFalse() throws IOException {

        systemInMock.provideLines("a.txt");
        Assert.assertFalse(operation.execute(Paths.get(appRootDir.getRoot().toURI()),scanner));

    }

    @Test
    public void testExecuteWhereFileDoesNotExist() throws IOException {
        systemInMock.provideLines("dummy.txt");
        Assert.assertFalse(operation.execute(Paths.get(appRootDir.getRoot().toURI()),scanner));
        Assert.assertTrue(systemOutRule.getLog().contains("No Files found matching the pattern, please modify your search criteria"));

    }

    @Test
    public void testExpectedSearchResult()throws IOException{
        systemInMock.provideLines("a.txt");
        Assert.assertFalse(operation.execute(Paths.get(appRootDir.getRoot().toURI()),scanner));
        Assert.assertTrue(systemOutRule.getLog().contains(expectedOutputString1));
        Assert.assertTrue(systemOutRule.getLog().contains(expectedOutputString2));

    }

}
