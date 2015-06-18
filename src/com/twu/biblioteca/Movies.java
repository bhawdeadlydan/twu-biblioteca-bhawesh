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


    public boolean checkout(String movieName) {
        for (Movie Movie : availableMovieList) {
            if (Movie.isMovieSame(movieName)) {
                checkedOutMovieList.add(Movie);
                availableMovieList.remove(Movie);
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
        for (Movie Movie : availableMovieList) {
            if (Movie.isMovieSame(movieName)) {
                checkedOutMovieList.add(Movie);
                availableMovieList.remove(Movie);
                return true;
            }
        }
        return false;
    }
}
