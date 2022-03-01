package javaphase1.lockedmeApp.businessoperations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class NavigateBackOperationTest {
    private NavigateBackOperation operation;

    @Mock
    private Path path;

    @Mock
    private Scanner scanner;

    @Before
    public void setup() {
        operation = new NavigateBackOperation();
        scanner = new Scanner(System.in);
    }

    @Test
    public void testExecuteReturnsTrue() throws IOException {

        Assert.assertTrue(operation.execute(path, scanner));

    }
}
