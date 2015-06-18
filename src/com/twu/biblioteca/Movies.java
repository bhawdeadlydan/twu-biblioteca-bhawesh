package com.twu.biblioteca;

import java.util.ArrayList;

public class Movies {
    private ArrayList<Movie> availableMovieList;
    private ArrayList<Movie> checkedOutMovieList;

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
}
