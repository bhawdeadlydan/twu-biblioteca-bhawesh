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
    private ByteArrayInputStream bookName = new ByteArrayInputStream("Book 1".getBytes());
    private Books books;
    private ConsoleView consoleView;

    @Before
    public void setUp() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Book 1", "JK Rowling", 2003));
        bookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        bookList.add(new Book("Book 3", "Agatha Christie", 1800));
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        books = new Books(bookList);

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

    @Test
    public void shouldBeAbleToGetBookName() {
        consoleView = new ConsoleView(new Scanner(System.in));
        System.setIn(null);
        System.setIn(bookName);
        String actualBookName = consoleView.getBookName();
        String expectedBookName = "1";
        assertThat(actualBookName, is(expectedBookName));
    }


    @After
    public void tearDown() {
        System.setOut(null);
    }

}