package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class BooksTest {
    @Test
    public void shouldReturnAllBooksFromLibrary() {
        Books books = new Books();

        ArrayList<String[]> actualBooksInLibrary = books.books();
        ArrayList<String[]> expectedBooksInLibrary = new ArrayList<String[]>();
        expectedBooksInLibrary.add(new String[]{"Book 1", "JK Rowling", "2003"});
        expectedBooksInLibrary.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        expectedBooksInLibrary.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        assertThat(actualBooksInLibrary.toArray(), is(expectedBooksInLibrary.toArray()));
    }

}