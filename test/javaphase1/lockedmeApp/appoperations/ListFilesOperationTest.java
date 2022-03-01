package javaphase1.lockedmeApp.appoperations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.TreeSet;

public class ListFilesOperationTest {
    private ListFilesOperation operation;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    TreeSet<String> expectedOutput;

    @Before
    public void setup() {
        operation = new ListFilesOperation();
        expectedOutput = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        File f = null;
        try {
            f = folder.newFile( "a.txt" );
            expectedOutput.add(f.getAbsolutePath());
            f = folder.newFile( "c.txt" );
            expectedOutput.add(f.getAbsolutePath());

            File subDirectory = folder.newFolder("b");
            f = new File(subDirectory, "d.txt");
            f.createNewFile();
            expectedOutput.add(f.getAbsolutePath());
            f = new File(subDirectory, "e.txt");
            f.createNewFile();
            expectedOutput.add(f.getAbsolutePath());
        }
        catch( IOException ioe ) {
            System.err.println(
                    "Error creating temporary test file in " +
                            this.getClass().getSimpleName() );
        }
    }

    @Test
    public void testExecuteReturnsFalse() throws IOException {
        Assert.assertFalse(operation.execute(Paths.get(folder.getRoot().toURI())));
    }

    @Test
    public void testGetPathsReturnsFilesInAscendingOrder() throws IOException {

        Assert.assertEquals(expectedOutput, operation.getPaths(Paths.get(folder.getRoot().toURI())));

    }
}
