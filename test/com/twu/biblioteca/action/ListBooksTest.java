package com.twu.biblioteca.action;

import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ListBooksTest {
    private ListBooks listBooks;
    private ByteArrayOutputStream outputStream;
    private Books books;
    private ArrayList<String[]> bookList;
    private ConsoleView consoleView;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        availableBookList.add(new Book("Book 3", "Agatha Christie", 1800));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        Books books = new Books(availableBookList, checkedOutBookList);
        consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
        listBooks = new ListBooks(books, consoleView);

    }

    @Test
    public void shouldDisplayListOfAllLibraryBooksWithNameAuthorYearOfPublication() {
        listBooks.performAction();
        String actualListOfBooks = outputStream.toString();
        String expectedListOfBooks = "\nName\tAuthor\tPublication Year\nBook 1\tJK Rowling\t2003\nBook 2\tArthur Conan Doyle\t1886\nBook 3\tAgatha Christie\t1800\n";

        assertThat(actualListOfBooks, is(expectedListOfBooks));
    }
}