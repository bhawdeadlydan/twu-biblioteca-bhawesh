package com.twu.biblioteca.action;

import com.twu.biblioteca.collection.Movies;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.listener.LoginHistoryListener;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class CheckOutMovieTest {
    private Movies movies;
    private CheckOutMovie checkOutMovie;
    ConsoleView consoleViewStub;

    @Mock
    LoginHistoryListener loginHistoryListener;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArrayList<Movie> availableMovieList = new ArrayList<Movie>();
        availableMovieList.add(new Movie("Movie 1", 2003, "JK Rowling", 1));
        availableMovieList.add(new Movie("Movie 2", 1886, "Arthur Conan Doyle", 2));

        ArrayList<Movie> checkedOutMovieList = new ArrayList<Movie>();
        movies = new Movies(availableMovieList, checkedOutMovieList);
        movies.addListener(loginHistoryListener);
        consoleViewStub = mock(ConsoleView.class);
        checkOutMovie = new CheckOutMovie(consoleViewStub, movies);
    }

    @Test
    public void shouldPromptUserToEnterMovieNameToCheckout() throws IOException {
        when(consoleViewStub.getName()).thenReturn("Movie 1");
        checkOutMovie.performAction();

        verify(consoleViewStub).print(Messages.MOVIE_CHECKOUT_PROMPT);
    }

    @Test
    public void shouldInputMovieNameFromUser() throws IOException {
        when(consoleViewStub.getName()).thenReturn("Movie 1");
        checkOutMovie.performAction();

        verify(consoleViewStub).getName();
    }

    @Test
    public void shouldCheckOutMovie() throws IOException {
        Movies Movies = mock(Movies.class);
        checkOutMovie = new CheckOutMovie(consoleViewStub, Movies);
        when(consoleViewStub.getName()).thenReturn("Movie 1");

        checkOutMovie.performAction();

        verify(Movies).checkout("Movie 1");
    }

    @Test
    public void shouldSuccessfullyCheckOutMovie() throws IOException {
        checkOutMovie = new CheckOutMovie(consoleViewStub, movies);
        when(consoleViewStub.getName()).thenReturn("Movie 1");

        checkOutMovie.performAction();

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleViewStub, times(2)).print(stringArgumentCaptor.capture());

        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.SUCCESSFULL_CHECKOUT_MOVIE;

        assertThat(expectedMessage, is(actualMessage));
    }

    @Test
    public void shouldNotBeAbleCheckOutMovieIfItIsInvalid() throws IOException {
        checkOutMovie = new CheckOutMovie(consoleViewStub, movies);
        when(consoleViewStub.getName()).thenReturn("Movie 4");

        checkOutMovie.performAction();

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleViewStub, times(2)).print(stringArgumentCaptor.capture());

        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.UNSUCCESSFULL_CHECKOUT_MOVIE;

        assertThat(expectedMessage, is(actualMessage));
    }

}