package com.twu.biblioteca;

import java.io.IOException;

public class ReturnMovie implements MenuAction {
    private ConsoleView consoleView;
    private Movies movies;

    public ReturnMovie(ConsoleView consoleView, Movies movies) {
        this.consoleView = consoleView;
        this.movies = movies;
    }

    @Override
    public void performAction() {
        consoleView.print(Messages.MOVIE_RETURN_PROMPT);
        String movieName = "";
        try {
            movieName = consoleView.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        movies.returnMovie(movieName);
    }
}
