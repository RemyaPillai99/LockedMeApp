package javaphase1.lockedmeApp.appoperations;

import javaphase1.lockedmeApp.businessoperations.IBusinessOperation;
import javaphase1.lockedmeApp.businessoperations.SubMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Scanner;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.*;

public class SubMenuOperationTest {
    private SubMenuOperation operation;

    @Mock
    IBusinessOperation businessOperation, exitOperation;

    @Rule
    public TemporaryFolder appRootDir = new TemporaryFolder();

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    private Scanner scanner;

    @Before
    public void setup() {
        scanner = new Scanner(System.in);
        businessOperation = mock(IBusinessOperation.class);
        exitOperation = mock(IBusinessOperation.class);
        EnumMap<SubMenu, IBusinessOperation> submenuOperationMap = new EnumMap<>(SubMenu.class);
        submenuOperationMap.put(SubMenu.ADD, businessOperation);
        submenuOperationMap.put(SubMenu.NAVIGATE_BACK, exitOperation);
        operation = new SubMenuOperation(submenuOperationMap, scanner);
    }

    @Test
    public void testSubmenuOperationExecutedForValidSubmenuOption() throws IOException {
        systemInMock.provideLines("1", "4");
        when(businessOperation.execute(any(Path.class), any(Scanner.class))).thenReturn(false);
        when(exitOperation.execute(any(Path.class), any(Scanner.class))).thenReturn(true);
        Assert.assertFalse(operation.execute(Paths.get(appRootDir.getRoot().toURI())));
        verify(businessOperation, times(1))
                .execute(eq(Paths.get(appRootDir.getRoot().toURI())), eq(scanner));
        verify(exitOperation, times(1))
                .execute(eq(Paths.get(appRootDir.getRoot().toURI())), eq(scanner));
    }

    @Test
    public void testSubmenuOperationExecutedForInvalidSubmenuOption() throws IOException {
        systemInMock.provideLines("5", "4");
        when(businessOperation.execute(any(Path.class), any(Scanner.class))).thenReturn(false);
        when(exitOperation.execute(any(Path.class), any(Scanner.class))).thenReturn(true);
        Assert.assertFalse(operation.execute(Paths.get(appRootDir.getRoot().toURI())));
        verify(businessOperation, times(0))
                .execute(any(Path.class), any(Scanner.class));
        verify(exitOperation, times(1))
                .execute(eq(Paths.get(appRootDir.getRoot().toURI())), eq(scanner));
    }
}
