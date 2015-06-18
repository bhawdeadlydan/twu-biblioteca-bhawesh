package com.twu.biblioteca.view;

import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleViewTest {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private ByteArrayInputStream bookName = new ByteArrayInputStream("Book 1".getBytes());
    private Books books;
    private ConsoleView consoleView;
    Scanner scanner;

    @Rule
    public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Before
    public void setUp() {
        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        availableBookList.add(new Book("Book 3", "Agatha Christie", 1800));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        books = new Books(availableBookList, checkedOutBookList);
        scanner = new Scanner(System.in);

        System.setOut(new PrintStream(outputStream));
        consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
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
    public void shouldBeAbleToTakeUserInput() throws IOException {
        systemInMock.provideText(String.valueOf(1));
        int actualUserInput = consoleView.read();
        int expectedUserInput = 1;

        assertThat(actualUserInput, is(expectedUserInput));
    }

    @Test
    public void shouldBeAbleToGetBookName() throws IOException {
        systemInMock.provideText("Book 1");

        consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
        String actualBookName = consoleView.getName();
        String expectedBookName = "Book 1";
        assertThat(actualBookName, is(expectedBookName));
    }


    @After
    public void tearDown() {
        System.setOut(null);
    }

}