package com.twu.biblioteca;

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
        }
    }
}
