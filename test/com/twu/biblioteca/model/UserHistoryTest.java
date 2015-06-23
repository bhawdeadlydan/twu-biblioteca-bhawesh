package com.twu.biblioteca.model;

import com.twu.biblioteca.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserHistoryTest {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private UserHistory userHistory;
    private ConsoleView consoleView;
    @Mock
    HashMap<String, Movie> movieUserHistory;

    @Mock
    Book book;

    @Mock
    Movie movie;

    @Mock
    HashMap<String, Book> bookUserHistory;

    @Before
    public void setUp() {
        userHistory = new UserHistory(bookUserHistory, movieUserHistory);
        consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void shouldUpdateUserHistoryOnBookCheckout() {
        userHistory.updateUser("user222");
        userHistory.updateBook(book, -1);

        verify(bookUserHistory).put("user222", book);
    }

    @Test
    public void shouldUpdateUserHistoryOnBookReturn() {
        HashMap<String, Book> bookUserHistory = new HashMap<String, Book>();
        bookUserHistory.put("user222", book);
        HashMap<String, Movie> movieUserHistory = new HashMap<String, Movie>();
        userHistory = new UserHistory(bookUserHistory, movieUserHistory);

        userHistory.updateBook(book, 1);
        HashMap<String, Book> expectedBookUserHistory = new HashMap<String, Book>();
        assertThat(bookUserHistory, is(expectedBookUserHistory));
    }

    @Test
    public void shouldUpdateUserHistoryOnMovieCheckout() {
        userHistory.updateUser("user222");
        userHistory.updateMovie(movie, -1);

        verify(movieUserHistory).put("user222", movie);
    }

    @Test
    public void shouldUpdateUserHistoryOnMovieReturn() {
        HashMap<String, Book> bookUserHistory = new HashMap<String, Book>();
        HashMap<String, Movie> movieUserHistory = new HashMap<String, Movie>();
        movieUserHistory.put("user222", movie);
        userHistory = new UserHistory(bookUserHistory, movieUserHistory);

        userHistory.updateMovie(movie, 1);
        HashMap<String, Movie> expectedMovieUserHistory = new HashMap<String, Movie>();
        assertThat(movieUserHistory, is(expectedMovieUserHistory));
    }

    @Test
    public void shouldBeAbleToDisplayUserHistoryForDefaulters() {
        Book book = new Book("Raj", "Comics", 2000);
        HashMap<String, Book> bookUserHistory = new HashMap<String, Book>();
        bookUserHistory.put("user333", book);
        Movie movie = new Movie("Art Of living", 1945, "Director", 4);
        HashMap<String, Movie> movieUserHistory = new HashMap<String, Movie>();
        movieUserHistory.put("user222", movie);
        userHistory = new UserHistory(bookUserHistory, movieUserHistory);


        String actualOutput = userHistory.toString();
        System.out.println(actualOutput);
        String expectedOutput = "\nuser222: \nArt Of living\tDirector\t1945\trating:4\nuser333: Raj\tComics\t2000";

        assertThat(actualOutput, is(expectedOutput));

    }

}