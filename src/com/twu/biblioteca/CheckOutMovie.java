package com.twu.biblioteca;

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
    }
}
