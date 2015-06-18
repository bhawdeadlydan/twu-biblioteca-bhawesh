package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MoviesTest {
    private Movies movies;
    @Before
    public void setUp() {
        ArrayList<Movie> availableMovieList = new ArrayList<Movie>();
        availableMovieList.add(new Movie("Movie 1", 2003, "JK Rowling", 1));
        availableMovieList.add(new Movie("Movie 2", 1886, "Arthur Conan Doyle", 2));

        ArrayList<Movie> checkedOutMovieList = new ArrayList<Movie>();
        movies = new Movies(availableMovieList, checkedOutMovieList);
    }

    @Test
    public void shouldGiveAllMoviesFromLibrary() {

        String actualMoviesInLibrary = movies.toString();
        String expectedMoviesInLibrary = "\nName\tDirector\tYear\tRating\n\nMovie 1\tJK Rowling\t2003\trating:1\n\nMovie 2\tArthur Conan Doyle\t1886\trating:2";

        assertThat(actualMoviesInLibrary, is(expectedMoviesInLibrary));
    }

}