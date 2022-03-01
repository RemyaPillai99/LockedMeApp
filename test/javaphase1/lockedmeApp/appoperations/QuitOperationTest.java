package javaphase1.lockedmeApp.appoperations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.nio.file.Path;

public class QuitOperationTest {
    private QuitOperation operation;

    @Mock
    private Path path;

    @Before
    public void setup() {
        operation = new QuitOperation();
    }

    @Test
    public void testExecuteReturnsTrue() throws IOException {
        Assert.assertTrue(operation.execute(path));
    }
}
