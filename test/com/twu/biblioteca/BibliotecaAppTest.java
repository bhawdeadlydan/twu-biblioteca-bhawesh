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
    public void shouldDisplayListOfAllLibraryBooks(){
        BibliotecaApp bibliotecaApp = new BibliotecaApp();

        bibliotecaApp.printListOfBooks();
        String actualListOfBooks = outputStream.toString();
        String expectedListOfBooks = "Book 1"+System.lineSeparator()+
                                     "Book 2"+System.lineSeparator()+
                                     "Book 3"+System.lineSeparator();

        assertThat(actualListOfBooks,is(expectedListOfBooks));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}
