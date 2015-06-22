package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
@RunWith(MockitoJUnitRunner.class)
public class UserHistoryTest {
    private UserHistory userHistory;
    @Mock
    HashMap<String, Movie> movieUserHistory;

    @Mock
    Book book;

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

        Mockito.verify(bookUserHistory).put("user222", book);
    }

}