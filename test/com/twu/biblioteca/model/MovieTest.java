package com.twu.biblioteca.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class MovieTest {
    @Test
    public void shouldGiveMovieInVisualRepresentation() {
        Movie movie = new Movie("Movie123", 2001, "steven", 1);

        String actualOutput = movie.toString();
        String expectedOutput = "\n" + "Movie123\tsteven\t2001\trating:1";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldBeEqualToItself() {
        Movie firstMovie = new Movie("Matrix", 2000, "Director", 9);

        boolean actual = firstMovie.equals(firstMovie);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldFollowSymmetricProperty() {
        Movie firstMovie = new Movie("Matrix", 2000, "Director", 9);
        Movie secondMovie = new Movie("Matrix", 2000, "Director", 9);

        boolean actual = (firstMovie.equals(secondMovie) == secondMovie.equals(firstMovie));

        assertThat(actual, is(true));
    }

    @Test
    public void shouldFollowTransitiveProperty() {
        Movie firstMovie = new Movie("Matrix", 2000, "Director", 9);
        Movie secondMovie = new Movie("Matrix", 2000, "Director", 9);
        Movie thirdMovie = new Movie("Matrix", 2000, "Director", 9);

        boolean actual = firstMovie.equals(secondMovie) &&
                secondMovie.equals(thirdMovie) &&
                firstMovie.equals(thirdMovie);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldHaveSameHashCodeIfMoviesAreEqual() {
        Movie firstMovie = new Movie("Matrix", 2000, "Director", 9);
        Movie secondMovie = new Movie("Matrix", 2000, "Director", 9);

        boolean actual = firstMovie.equals(secondMovie) && (firstMovie.hashCode() == secondMovie.hashCode());

        assertThat(actual, is(true));
    }
}
