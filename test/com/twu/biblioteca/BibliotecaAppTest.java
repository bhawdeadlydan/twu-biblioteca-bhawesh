package com.twu.biblioteca;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class BibliotecaAppTest  {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldDispalyWelcomeMessageWhenBibliotecaAppStarts() {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.welcomeMessage();

        String actualWelcomeMessage = outputStream.toString();

        assertThat(actualWelcomeMessage, is("Welcome"));
    }

    @Test
    public void shouldDisplayListOfAllLibraryBooksWithNameAuthorYearOfPublication(){
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.printListOfBooks();
        String actualListOfBooks = outputStream.toString();
        String expectedListOfBooks = "\nName\tAuthor\tPublication Year \nBook 1\tJK Rowling\t2003\nBook 2\tArthur Conan Doyle\t1886\nBook 3\tAgatha Christie\t1800";

        assertThat(actualListOfBooks,is(expectedListOfBooks));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}
