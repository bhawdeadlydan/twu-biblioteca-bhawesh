package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MenuExecutorTest {

        @Mock
        ConsoleView consoleViewStub1;

        @Mock
        ConsoleView consoleViewStub2;


        private MenuExecutor menuExecutor;

        private HashMap<Integer, MenuAction> menuItemMap;

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);

            ArrayList<String[]> bookList = new ArrayList<String[]>();
            bookList.add(new String[]{"Book 1", "JK Rowling", "2003"});
            bookList.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
            bookList.add(new String[]{"Book 3", "Agatha Christie", "1800"});

            Books books = new Books(bookList);
            menuItemMap = new HashMap<Integer, MenuAction>();
            menuItemMap.put(1, new ListBooks(books, consoleViewStub1));
            menuExecutor = new MenuExecutor(menuItemMap, consoleViewStub2);
        }

        @Test
        public void shouldTakeUserChoice() {
            menuExecutor.executeUserCommand();

            verify(consoleViewStub2).read();
        }



    }