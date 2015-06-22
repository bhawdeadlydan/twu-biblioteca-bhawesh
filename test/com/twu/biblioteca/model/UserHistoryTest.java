package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserHistoryTest {
    private UserHistory userHistory;
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



}