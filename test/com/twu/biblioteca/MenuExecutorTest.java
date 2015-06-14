package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

public class MenuExecutorTest {
        @Mock
        Books books;

        @Mock
        ConsoleView consoleView;

        @Mock
        ListBooks listBooks;

        private MenuExecutor menuExecutor;

        private HashMap<Integer, MenuAction> menuItemMap;

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);
            menuItemMap = new HashMap<Integer, MenuAction>();
            menuItemMap.put(1, listBooks);
            menuExecutor = new MenuExecutor(menuItemMap, consoleView);
        }

        @Test
        public void shouldTakeUserChoice() {
            menuExecutor.executeUserCommand();

            Mockito.verify(consoleView).read();
        }


    }