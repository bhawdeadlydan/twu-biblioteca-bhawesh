package com.twu.biblioteca.listener;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;

public interface LoginHistoryListener {
    public void updateUser(String currentLoggedInUser);
    public void updateBook(Book book, int status);
    public void updateMovie(Movie movie, int status);
}
