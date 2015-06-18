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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuExecutorTest {

    @Mock
    ConsoleView consoleViewStub1;

    @Mock
    ConsoleView consoleViewStub2;

    private Quit quitMenuActionStub;
    private MenuExecutor menuExecutor;
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
        menuExecutor = new MenuExecutor(menuActionMap, consoleViewStub2);
    }

    @Test
    public void shouldTakeUserChoice() throws IOException {
        menuExecutor.executeUserCommand();

        verify(consoleViewStub2).read();
    }

    @Test
    public void shouldDisplayListOfBooksIfUserInputIsOne() throws IOException {
        when(consoleViewStub2.read()).thenReturn(1);
        menuExecutor.executeUserCommand();

        verify(consoleViewStub1).print("\nName\tAuthor\tPublication Year\nBook 1\tJK Rowling\t2003\nBook 2\tArthur Conan Doyle\t1886\nBook 3\tAgatha Christie\t1800");
    }

    @Test
    public void shouldDisplayInvalidOptionWhenInvalidOptionIsEntered() throws IOException {
        when(consoleViewStub2.read()).thenReturn(-1);
        menuExecutor.executeUserCommand();

        verify(consoleViewStub2).print(Messages.INVALID_OPTION_MESSAGE);
    }

    @Test
    public void shouldExitWhenQuitIsSelected() throws IOException {
        when(consoleViewStub2.read()).thenReturn(2);
        Boolean actualValue = menuExecutor.executeUserCommand();

        assertThat(false, is(actualValue));

    }
}