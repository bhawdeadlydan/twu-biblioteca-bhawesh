package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleViewTest {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private Books books;

    @Before
    public void setUp() {
        ArrayList<String[]> bookList = new ArrayList<String[]>();
        bookList.add(new String[]{"Book 1", "JK Rowling", "2003"});
        bookList.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        bookList.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        Books books =new Books(bookList);
        System.setOut(new PrintStream(outputStream));

    }

    @Test
    public void shouldShowTheWelcomeMessage() {
        ConsoleView consoleView = new ConsoleView();

        consoleView.print(Messages.WELCOME_MESSAGE);
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(Messages.WELCOME_MESSAGE+'\n'));
    }

    @Test
    public void shouldShowListOfBooksWithDetails() {
        ConsoleView consoleView = new ConsoleView();
        consoleView.print(books.toString());
        String actualBookListWithDetails = outputStream.toString();
        String expectedBookListWithDetails = "\nBook 1....JK Rowling....2003\nBook 2....Arthur Conan Doyle....1886\nBook 3....Agatha Christie....1800\n";
        ;

        assertThat(actualBookListWithDetails, is(expectedBookListWithDetails));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

}