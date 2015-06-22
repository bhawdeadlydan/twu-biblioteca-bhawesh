package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

}