package com.twu.biblioteca;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class LibraryTest {
    @Test
    public void shouldReturnAllBooksFromLibrary(){
        Library library = new Library();

        ArrayList<String> actualBooksInLibrary = library.books();
        ArrayList <String> expectedBooksInLibrary = new ArrayList<String>();
        expectedBooksInLibrary.add("Book 1");
        expectedBooksInLibrary.add("Book 2");
        expectedBooksInLibrary.add("Book 3");

        assertThat(actualBooksInLibrary,is(expectedBooksInLibrary));
    }

}