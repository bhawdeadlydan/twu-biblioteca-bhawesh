package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

        ArrayList<String[]> bookList = new ArrayList<String[]>();
        bookList.add(new String[]{"Book 1", "JK Rowling", "2003"});
        bookList.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        bookList.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        Books books = new Books(bookList);

        quitMenuActionStub = mock(Quit.class);
        menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleViewStub1));
        menuActionMap.put(2, quitMenuActionStub);
        menuExecutor = new MenuExecutor(menuActionMap, consoleViewStub2);
    }

    @Test
    public void shouldTakeUserChoice() {
        menuExecutor.executeUserCommand();

        verify(consoleViewStub2).read();
    }

    @Test
    public void shouldDisplayListOfBooksIfUserInputIsOne() {
        when(consoleViewStub2.read()).thenReturn(1);
        menuExecutor.executeUserCommand();

        verify(consoleViewStub1).print("\nName\tAuthor\tPublication Year\nBook 1\tJK Rowling\t2003\nBook 2\tArthur Conan Doyle\t1886\nBook 3\tAgatha Christie\t1800");
    }

    @Test
    public void shouldExitWhenQuitIsSelected() {
        when(consoleViewStub2.read()).thenReturn(2);
        Boolean actualValue = menuExecutor.executeUserCommand();

        assertThat(false, is(actualValue));

    }
}