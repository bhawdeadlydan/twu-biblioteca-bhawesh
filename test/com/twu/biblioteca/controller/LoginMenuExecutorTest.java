package com.twu.biblioteca.controller;

import com.twu.biblioteca.action.MenuAction;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginMenuExecutorTest {
    @Mock
    HashMap<Integer, MenuAction> menuActionMap;
    ConsoleView consoleView;


    @Test
    public void setUp() {
        consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
    }

    @Test
    public void shouldRemoveLogoutOperation() {
        LibraryMenuExecutor libraryMenuExecutor = new LibraryMenuExecutor(menuActionMap, consoleView);
        libraryMenuExecutor.buildMenu();
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(menuActionMap, times(1)).remove(integerArgumentCaptor.capture());

        List<Integer> capturedIntegers = integerArgumentCaptor.getAllValues();
        assertThat(capturedIntegers.get(0), is(9));
    }

}