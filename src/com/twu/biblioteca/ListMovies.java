package com.twu.biblioteca;

public class ListMovies implements MenuAction {
    private ConsoleView consoleView;
    private Movies movies;

    public ListMovies(ConsoleView consoleView, Movies movies) {
        this.consoleView = consoleView;
        this.movies = movies;
    }

    @Override
    public void performAction() {
        consoleView.print(movies.toString());
    }
}
