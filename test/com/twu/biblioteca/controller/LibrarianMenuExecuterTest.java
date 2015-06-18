package com.twu.biblioteca.controller;

import com.twu.biblioteca.action.MenuAction;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LibrarianMenuExecuterTest {

    @Mock
    HashMap<Integer, MenuAction> menuActionMap;
    ConsoleView consoleView;


    @Test
    public void setUp() {
        consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
    }

    @Test
    public void shouldRemoveLoginOperation() {
        LibraryMenuExecuter libraryMenuExecuter = new LibraryMenuExecuter(menuActionMap, consoleView);
        libraryMenuExecuter.buildMenu();

        verify(menuActionMap).remove(8);
    }

}