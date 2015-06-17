package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MovieTest {
    @Test
    public void shouldGiveMovieInVisualRepresentation() {
        Movie movie = new Movie("Movie123", 2001, "steven", 1);

        String actualOutput = movie.toString();
        String expectedOutput = "Movie123\tsteven\t2001\trating:1";

        assertEquals(expectedOutput, actualOutput);
    }
}
