package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleViewTest {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private Books books;
    ConsoleView consoleView;
    private ByteArrayInputStream byteArrayInputStream;

    @Before
    public void setUp() {
        ArrayList<String[]> bookList = new ArrayList<String[]>();
        bookList.add(new String[]{"Book 1", "JK Rowling", "2003"});
        bookList.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        bookList.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        books = new Books(bookList);
        byteArrayInputStream = new ByteArrayInputStream("1".getBytes());
        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(outputStream));
        consoleView = new ConsoleView(new Scanner(System.in));

    }

    @Test
    public void shouldShowTheWelcomeMessage() {
        consoleView.print(Messages.WELCOME_MESSAGE);
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(Messages.WELCOME_MESSAGE + '\n'));
    }

    @Test
    public void shouldShowListOfBooksWithDetails() {
        consoleView.print(books.toString());

        String actualBookListWithDetails = outputStream.toString();
        String expectedBookListWithDetails = "\nName\tAuthor\tPublication Year\n"
                + "Book 1\tJK Rowling\t2003\n"
                + "Book 2\tArthur Conan Doyle\t1886\n"
                + "Book 3\tAgatha Christie\t1800\n";

        assertThat(actualBookListWithDetails, is(expectedBookListWithDetails));
    }

    @Test
    public void shouldBeAbleToTakeUserInput() {
        int actualUserInput = consoleView.read();
        int expectedUserInput = 1;

        assertThat(actualUserInput, is(expectedUserInput));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

}