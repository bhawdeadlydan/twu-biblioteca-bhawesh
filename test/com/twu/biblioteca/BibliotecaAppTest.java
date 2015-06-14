package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class BibliotecaAppTest  {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private BibliotecaApp bibliotecaApp;


    @Before
    public void setUp() {
        ArrayList<String[]> bookList = new ArrayList<String[]>();
        bookList.add(new String[]{"Book 1", "JK Rowling", "2003"});
        bookList.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        bookList.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        Books books =new Books(bookList);
        bibliotecaApp = new BibliotecaApp(books);
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldDispalyWelcomeMessageWhenBibliotecaAppStarts() {
        bibliotecaApp.welcomeMessage();

        String actualWelcomeMessage = outputStream.toString();

        assertThat(actualWelcomeMessage, is("Welcome"));
    }

    @Test
    public void shouldDisplayListOfAllLibraryBooksWithNameAuthorYearOfPublication(){
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
