package javaphase1.lockedmeApp;

import javaphase1.lockedmeApp.appoperations.IAppOperation;
import javaphase1.lockedmeApp.appoperations.MainMenu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Scanner;


import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LockedMeAppTest {
    private LockedMeApp lockedMeApp;
    private Scanner scanner;
    private IAppOperation appOperation;
    private IAppOperation exitOperation;
    private EnumMap<MainMenu, IAppOperation> mainMenuOperationMap;


    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Rule
    public TemporaryFolder appRootDir = new TemporaryFolder();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before

    public void setup() throws IOException {
        scanner = new Scanner(System.in);
        appOperation = mock(IAppOperation.class);
        exitOperation = mock(IAppOperation.class);
        mainMenuOperationMap  = new EnumMap<>(MainMenu.class);
        mainMenuOperationMap.put(MainMenu.LIST, appOperation);
        mainMenuOperationMap.put(MainMenu.QUIT, exitOperation);
        lockedMeApp = new LockedMeApp(mainMenuOperationMap, scanner);

    }


    @Test
    public void testRunWhenExecutedForValidOption()throws IOException{
        systemInMock.provideLines("1","3");
        when(appOperation.execute(any(Path.class))).thenReturn(false);
        when(exitOperation.execute(any(Path.class))).thenReturn(true);
        lockedMeApp.run();
        verify(appOperation, times(1))
                .execute(any(Path.class));
        verify(exitOperation, times(1))
                .execute(any(Path.class));

    }

    @Test
    public void testRunWhenExecutedForInValidOption()throws IOException{
        systemInMock.provideLines("4","3");
        when(appOperation.execute(any(Path.class))).thenReturn(false);
        when(exitOperation.execute(any(Path.class))).thenReturn(true);
        lockedMeApp.run();
        verify(appOperation, times(0))
                .execute(any(Path.class));
        Assert.assertTrue(systemOutRule.getLog().contains("Invalid Input"));
        verify(exitOperation, times(1))
                .execute(any(Path.class));

    }

}
