package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReturnMovieTest {
    private Movies movies;
    private ReturnMovie returnMovie;
    ConsoleView consoleViewStub;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArrayList<Movie> availableMovieList = new ArrayList<Movie>();
        availableMovieList.add(new Movie("Movie 1", 2003, "JK Rowling", 1));
        availableMovieList.add(new Movie("Movie 2", 1886, "Arthur Conan Doyle", 2));

        ArrayList<Movie> checkedOutMovieList = new ArrayList<Movie>();
        movies = new Movies(availableMovieList, checkedOutMovieList);
        consoleViewStub = mock(ConsoleView.class);
        returnMovie = new ReturnMovie(consoleViewStub, movies);
    }

    @Test
    public void shouldPromptUserToEnterMovieNameToReturn() throws IOException {
        when(consoleViewStub.getName()).thenReturn("Movie 1");
        returnMovie.performAction();

        verify(consoleViewStub).print(Messages.MOVIE_RETURN_PROMPT);
    }

}