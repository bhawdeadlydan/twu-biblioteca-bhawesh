package com.twu.biblioteca.action;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.UserHistory;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserHistoryActionTest {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private  UserHistoryAction userHistoryAction;
    private UserHistory userHistory;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        ConsoleView consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
        Book book = new Book("Raj", "Comics", 2000);
        HashMap<String, Book> bookUserHistory = new HashMap<String, Book>();
        bookUserHistory.put("user333", book);
        Movie movie = new Movie("Art Of living", 1945, "Director", 4);
        HashMap<String, Movie> movieUserHistory = new HashMap<String, Movie>();
        movieUserHistory.put("user222", movie);
        userHistory = new UserHistory(bookUserHistory, movieUserHistory);
        userHistoryAction = new UserHistoryAction(userHistory, consoleView);
    }
    @Test
    public void shouldBeAbleToDisplayUserHistory() {
        userHistoryAction.performAction();

        String actualOutput = outputStream.toString();
        String expectedOutput = "\nuser222: \nArt Of living\tDirector\t1945\trating:4\nuser333: Raj\tComics\t2000\n";

        assertThat(actualOutput, is(expectedOutput));
    }
}