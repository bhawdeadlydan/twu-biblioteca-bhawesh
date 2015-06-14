package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class BibliotecaAppTest {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private BibliotecaApp bibliotecaApp;
    ConsoleView consoleView;
    HashMap<Integer, String> menuMap;
    Menu menu;


    @Before
    public void setUp() {
        ArrayList<String[]> bookList = new ArrayList<String[]>();
        bookList.add(new String[]{"Book 1", "JK Rowling", "2003"});
        bookList.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        bookList.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        Books books = new Books(bookList);
        consoleView = new ConsoleView(new Scanner(System.in));
        menuMap = new HashMap<Integer, String>();
        menuMap.put(1, Messages.LIST_BOOKS);
        menu = new Menu(menuMap);
        bibliotecaApp = new BibliotecaApp(books, consoleView, menu);
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldDispalyWelcomeMessageWhenBibliotecaAppStarts() {
        bibliotecaApp.welcomeMessage();

        String actualWelcomeMessage = outputStream.toString();

        assertThat(actualWelcomeMessage, is(Messages.WELCOME_MESSAGE+"\n"));
    }

    @Test
    public void shouldDisplayListOfAllLibraryBooksWithNameAuthorYearOfPublication() {
        bibliotecaApp.printListOfBooks();
        String actualListOfBooks = outputStream.toString();
        String expectedListOfBooks = "\nName\tAuthor\tPublication Year\nBook 1\tJK Rowling\t2003\nBook 2\tArthur Conan Doyle\t1886\nBook 3\tAgatha Christie\t1800\n";

        assertThat(actualListOfBooks, is(expectedListOfBooks));
    }

    @Test
    public void shouldDisplayMenuOptions() {
        bibliotecaApp.displayMenu();
        String actualMenu = outputStream.toString();
        String expectedMenu = "\n1 "+Messages.LIST_BOOKS+"\n";

        assertThat(expectedMenu, is(actualMenu));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}
