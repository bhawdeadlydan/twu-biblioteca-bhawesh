package com.twu.biblioteca.action;

import com.twu.biblioteca.view.ConsoleView;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.collection.Movies;

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

        if(movies.returnMovie(movieName)){
            consoleView.print(Messages.SUCCESSFULL_RETURN_MOVIE);
        }else{
            consoleView.print(Messages.UNSUCCESSFULL_RETURN_MOVIE);
        }
    }
}
