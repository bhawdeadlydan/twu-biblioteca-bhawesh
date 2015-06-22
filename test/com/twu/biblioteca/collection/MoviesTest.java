package com.twu.biblioteca.collection;

import com.twu.biblioteca.listener.LoginHistoryListener;
import com.twu.biblioteca.model.Movie;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MoviesTest {
    private Movies movies;

    @Mock
    LoginHistoryListener loginHistoryListener;


    @Before
    public void setUp() {
        ArrayList<Movie> availableMovieList = new ArrayList<Movie>();
        availableMovieList.add(new Movie("Movie 1", 2003, "JK Rowling", 1));
        availableMovieList.add(new Movie("Movie 2", 1886, "Arthur Conan Doyle", 2));

        ArrayList<Movie> checkedOutMovieList = new ArrayList<Movie>();
        movies = new Movies(availableMovieList, checkedOutMovieList);
        movies.addListener(loginHistoryListener);
    }

    @Test
    public void shouldGiveAllMoviesFromLibrary() {

        String actualMoviesInLibrary = movies.toString();
        String expectedMoviesInLibrary = "\nName\tDirector\tYear\tRating\n\nMovie 1\tJK Rowling\t2003\trating:1\n\nMovie 2\tArthur Conan Doyle\t1886\trating:2";

        assertThat(actualMoviesInLibrary, is(expectedMoviesInLibrary));
    }

    @Test
    public void shouldMatchTheMovieBasedOnNameInMovieList() {
        boolean actual = movies.isMovieInMovieList("Movie 1");

        MatcherAssert.assertThat(actual, is(true));
    }

    @Test
    public void shouldCheckOutMovie() {
        String movieName = "Movie 1";
        boolean actual = movies.checkout(movieName);

        assertTrue(actual);
    }

    @Test
    public void shouldReturnMovie() {
        movies.checkout("Movie 1");
        String movieName = "Movie 1";
        boolean actual = movies.returnMovie(movieName);

        assertTrue(actual);
    }

    @Test
    public void shouldUpdateHistoryOnCheckoutMovie() {
        String movieName = "Movie 1";
        Boolean actual = movies.checkout(movieName);

        verify(loginHistoryListener).updateMovie(new Movie("Movie 1", 2003, "JK Rowling", 1), -1);
    }
}