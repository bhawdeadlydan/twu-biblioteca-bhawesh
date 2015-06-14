package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class BooksTest {
    private Books books;

    @Before
    public void setUp() {
        ArrayList<String[]> bookList = new ArrayList<String[]>();
        bookList.add(new String[]{"Book 1", "JK Rowling", "2003"});
        bookList.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        bookList.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        Books books =new Books(bookList);
    }

    @Test
    public void shouldReturnAllBooksFromLibrary() {

        ArrayList<String[]> actualBooksInLibrary = books.books();
        ArrayList<String[]> expectedBooksInLibrary = new ArrayList<String[]>();
        expectedBooksInLibrary.add(new String[]{"Book 1", "JK Rowling", "2003"});
        expectedBooksInLibrary.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        expectedBooksInLibrary.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        assertThat(actualBooksInLibrary.toArray(), is(expectedBooksInLibrary.toArray()));
    }

}