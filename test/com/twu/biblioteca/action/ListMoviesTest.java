package com.twu.biblioteca.action;

import com.twu.biblioteca.collection.Movies;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListMoviesTest {
    private ListMovies listMovies;
    private ByteArrayOutputStream outputStream;
    private Movies movies;
    private ArrayList<String[]> MovieList;
    private ConsoleView consoleView;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ArrayList<Movie> availableMovieList = new ArrayList<Movie>();
        availableMovieList.add(new Movie("Movie 1", 2003, "JK Rowling", 1));
        availableMovieList.add(new Movie("Movie 2", 1886, "Arthur Conan Doyle", 2));

        ArrayList<Movie> checkedOutMovieList = new ArrayList<Movie>();
        movies = new Movies(availableMovieList, checkedOutMovieList);
        consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
        listMovies = new ListMovies(consoleView, movies);

    }

    @Test
    public void shouldDisplayListOfAllLibraryMoviesWithNameAuthorYearOfPublication() {
        listMovies.performAction();

        String actualListOfMovies = outputStream.toString();
        String expectedListOfMovies = "\nName\tDirector\tYear\tRating\n\nMovie 1\tJK Rowling\t2003\trating:1\n\nMovie 2\tArthur Conan Doyle\t1886\trating:2\n";

        assertThat(expectedListOfMovies, is(actualListOfMovies));
    }
}