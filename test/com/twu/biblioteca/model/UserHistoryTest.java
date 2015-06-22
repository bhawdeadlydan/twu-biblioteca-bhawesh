package com.twu.biblioteca.model;

import org.junit.Before;

import java.util.HashMap;

public class UserHistoryTest {
    private UserHistory userHistory;
    HashMap<String, Movie> movieUserHistory;
    HashMap<String, Book> bookUserHistory;
    @Before
    public void setUp() {
        bookUserHistory = new HashMap<String, Book>();
        movieUserHistory = new HashMap<String, Movie>();
        userHistory = new UserHistory(bookUserHistory, movieUserHistory);
    }

    public void shouldUpdateUserHistoryOnBookCheckout() {

    }

}