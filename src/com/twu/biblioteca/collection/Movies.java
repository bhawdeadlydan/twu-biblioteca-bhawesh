package com.twu.biblioteca.collection;

import com.twu.biblioteca.listener.HistoryListenable;
import com.twu.biblioteca.listener.LoginHistoryListener;
import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;

public class Movies implements HistoryListenable {
    private ArrayList<Movie> availableMovieList;
    private ArrayList<Movie> checkedOutMovieList;
    private LoginHistoryListener listener;

    public Movies(ArrayList<Movie> availableMovieList, ArrayList<Movie> checkedOutMovieList) {
        this.availableMovieList = availableMovieList;
        this.checkedOutMovieList = checkedOutMovieList;
    }

    @Override
    public String toString() {
        String moviePrint = "\nName\tDirector\tYear\tRating";
        for (Movie movie : availableMovieList) {
            moviePrint += "\n" + movie.toString();
        }
        return moviePrint;
    }


    public boolean checkout(String movieName) {
        for (Movie movie : availableMovieList) {
            if (movie.isMovieSame(movieName)) {
                checkedOutMovieList.add(movie);
                availableMovieList.remove(movie);
                listener.updateMovie(movie, -1);
                return true;
            }
        }
        return false;
    }

    public boolean isMovieInMovieList(String movieName) {
        for (Movie movie : availableMovieList)
            if (movie.isMovieSame(movieName))
                return true;
        for (Movie movie : checkedOutMovieList)
            if (movie.isMovieSame(movieName))
                return true;
        return false;
    }

    public boolean returnMovie(String movieName) {
        for (Movie movie : checkedOutMovieList) {
            if (movie.isMovieSame(movieName)) {
                availableMovieList.add(movie);
                checkedOutMovieList.remove(movie);
                listener.updateMovie(movie, 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addListener(LoginHistoryListener listener) {
        this.listener = listener;
    }
}
