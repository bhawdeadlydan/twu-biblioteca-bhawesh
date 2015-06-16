package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;


public class BooksTest {
    private Books books;

    @Before
    public void setUp() {
        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        availableBookList.add(new Book("Book 3", "Agatha Christie", 1800));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        books = new Books(availableBookList, checkedOutBookList);
    }

    @Test
    public void shouldGiveAllBooksFromLibrary() {

        String actualBooksInLibrary = books.toString();
        String expectedBooksInLibrary = "\nName\tAuthor\tPublication Year\nBook 1\tJK Rowling\t2003\nBook 2\tArthur Conan Doyle\t1886\nBook 3\tAgatha Christie\t1800";

        assertThat(actualBooksInLibrary, is(expectedBooksInLibrary));
    }

    @Test
    public void shouldMatchTheBookBasedOnNameInBookList() {
        boolean actual = books.isBookInBookList("Book 1");

        assertThat(actual, is(true));
    }

    @Test
    public void shouldCheckAvaiblityOfBook() {
        boolean actual = books.isAvailable("Book 1");

        assertThat(actual, is(true));
    }

    @Test
    public void shouldCheckOutBook() {
        String bookName = "Book 1";
        boolean actual = books.checkout(bookName);

        assertTrue(actual);
    }

    @Test
    public void shouldReturnBook() {
        String bookName = "Book 1";
        books.checkout(bookName);
        Boolean actual = books.returnBook(bookName);

        assertTrue(actual);
    }
}