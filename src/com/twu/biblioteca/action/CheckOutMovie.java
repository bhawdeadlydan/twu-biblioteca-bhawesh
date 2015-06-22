package com.twu.biblioteca.action;

import com.twu.biblioteca.collection.Movies;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.view.ConsoleView;

import java.io.IOException;

public class CheckOutMovie implements MenuAction {
    private ConsoleView consoleView;
    private Movies movies;

    public CheckOutMovie(ConsoleView consoleView, Movies movies) {
        this.consoleView = consoleView;
        this.movies = movies;
    }

    @Override
    public void performAction() {
        consoleView.print(Messages.MOVIE_CHECKOUT_PROMPT);
        String movieName = "";
        try {
            movieName = consoleView.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (movies.checkout(movieName)) {
            consoleView.print(Messages.SUCCESSFULL_CHECKOUT_MOVIE);
        } else {
            consoleView.print(Messages.UNSUCCESSFULL_CHECKOUT_MOVIE);
        }
    }
}
