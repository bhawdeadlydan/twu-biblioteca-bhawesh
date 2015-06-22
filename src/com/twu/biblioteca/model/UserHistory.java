package com.twu.biblioteca.model;

import com.twu.biblioteca.listener.LoginHistoryListener;

import java.util.HashMap;
import java.util.Set;

public class UserHistory implements LoginHistoryListener {

    private final HashMap<String, Movie> movieUserHistory;
    private final HashMap<String, Book> bookUserHistory;
    private String loggedInUser;
    private Book bookChanged;
    private Movie movieChanged;

    public UserHistory(HashMap<String, Book> bookUserHistory, HashMap<String, Movie> movieUserHistory) {
        this.bookUserHistory = bookUserHistory;
        this.movieUserHistory = movieUserHistory;
    }

    @Override
    public void updateUser(String currentLoggedInUser) {
        this.loggedInUser = currentLoggedInUser;

    }

    @Override
    public void updateBook(Book book, int status) {
        this.bookChanged = book;
        if (status == -1)
            bookUserHistory.put(loggedInUser, book);
        else if(status == 1)
            if(bookUserHistory.containsValue(book)){
                Set<String> keys = bookUserHistory.keySet();
                for(String key : keys){
                    if(bookUserHistory.get(key).equals(book))
                        bookUserHistory.remove(key);
                }
            }
    }

    @Override
    public void updateMovie(Movie movie, int status) {
        this.movieChanged = movie;
        if (status == -1)
            movieUserHistory.put(loggedInUser, movie);
    }
}
