package com.twu.biblioteca.controller;

import com.twu.biblioteca.action.ListBooks;
import com.twu.biblioteca.action.MenuAction;
import com.twu.biblioteca.action.Quit;
import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MenuBuilderTest {
    @Mock
    ConsoleView consoleViewStub1;

    @Mock
    ConsoleView consoleViewStub2;

    private Quit quitMenuActionStub;
    private LibrarianMenuExecutor librarianMenuExecutor;
    private HashMap<Integer, MenuAction> menuActionMap;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        availableBookList.add(new Book("Book 3", "Agatha Christie", 1800));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        Books books = new Books(availableBookList, checkedOutBookList);

        quitMenuActionStub = mock(Quit.class);
        menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleViewStub1));
        menuActionMap.put(2, quitMenuActionStub);
        librarianMenuExecutor = new LibrarianMenuExecutor(menuActionMap, consoleViewStub2);
    }

    @Test
    public void shouldTakeUserChoice() throws IOException {
        librarianMenuExecutor.executeUserCommand();

        verify(consoleViewStub2).read();
    }

    @Test
    public void shouldDisplayInvalidOptionWhenInvalidOptionIsEntered() throws IOException {
        when(consoleViewStub2.read()).thenReturn(-1);
        librarianMenuExecutor.executeUserCommand();

        verify(consoleViewStub2).print(Messages.INVALID_OPTION_MESSAGE);
    }
}