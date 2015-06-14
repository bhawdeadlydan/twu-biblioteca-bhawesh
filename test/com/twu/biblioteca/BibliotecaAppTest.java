package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;


public class BibliotecaAppTest {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private BibliotecaApp bibliotecaApp;
    private HashMap<Integer, String> menuMap;
    private Menu menu;
    private Books books;
    MenuExecutor menuExecutor;

    @Mock
    ConsoleView consoleView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArrayList<String[]> bookList = new ArrayList<String[]>();
        bookList.add(new String[]{"Book 1", "JK Rowling", "2003"});
        bookList.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        bookList.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        books = new Books(bookList);
        menuMap = new HashMap<Integer, String>();
        menuMap.put(1, Messages.LIST_BOOKS);
        menu = new Menu(menuMap);
        ConsoleView consoleView = new ConsoleView(new Scanner(System.in));
        HashMap<Integer, MenuAction> menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleView));
        menuActionMap.put(2, new Quit());
        menuExecutor = new MenuExecutor(menuActionMap, consoleView);
        bibliotecaApp = new BibliotecaApp(consoleView, menu, menuExecutor);
        System.setOut(new PrintStream(outputStream));

    }

    @Test
    public void shouldDisplayWelcomeMessageWhenBibliotecaAppStarts() {
        ConsoleView consoleViewStub = mock(ConsoleView.class);
        Books booksStub = mock(Books.class);
        HashMap<Integer, MenuAction> menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(booksStub, consoleView));
        MenuExecutor menuExecutor = new MenuExecutor(menuActionMap, consoleView);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleViewStub, menu, menuExecutor);
        bibliotecaApp.start();

        verify(consoleViewStub).print(Messages.WELCOME_MESSAGE);
    }

    @Test
    public void shouldDisplayListOfAllLibraryBooksWithNameAuthorYearOfPublication() {
        ConsoleView consoleViewStub1 = mock(ConsoleView.class);
        ConsoleView consoleViewStub2 = mock(ConsoleView.class);
        HashMap<Integer, MenuAction> menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleViewStub2));
        MenuExecutor menuExecutor = new MenuExecutor(menuActionMap, consoleViewStub1);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleView, menu, menuExecutor);

        when(consoleViewStub1.read()).thenReturn((int)1);

        bibliotecaApp.start();
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleViewStub2, times(1)).print(stringArgumentCaptor.capture());

        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String expectedString = "\nName\tAuthor\tPublication Year\nBook 1\tJK Rowling\t2003\nBook 2\tArthur Conan Doyle\t1886\nBook 3\tAgatha Christie\t1800";
        assertThat(capturedStrings.get(0), is(expectedString));
    }

    @Test
    public void shouldDisplayMenuOptions() {
        ConsoleView consoleViewStub1 = mock(ConsoleView.class);
        HashMap<Integer, MenuAction> menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleView));
        MenuExecutor menuExecutor = new MenuExecutor(menuActionMap, consoleView);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleViewStub1, menu, menuExecutor);

        bibliotecaApp.start();

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleViewStub1, times(3)).print(stringArgumentCaptor.capture());

        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMenu = capturedStrings.get(1);
        String expectedMenu = "\n1 " + Messages.LIST_BOOKS;

        assertThat(expectedMenu, is(actualMenu));
    }

    @Test
    public void shouldQuitWhenOptionIsSelected() {
        Quit quitAction = mock(Quit.class);
        ConsoleView consoleViewStub1 = mock(ConsoleView.class);
        HashMap<Integer, MenuAction> menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleView));
        menuActionMap.put(2, quitAction);
        MenuExecutor menuExecutor = new MenuExecutor(menuActionMap, consoleViewStub1);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleView, menu, menuExecutor);
        when(consoleViewStub1.read()).thenReturn(2);

        bibliotecaApp.start();

        verify(quitAction).performAction();

    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}
