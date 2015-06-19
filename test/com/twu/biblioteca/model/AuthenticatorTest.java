package com.twu.biblioteca.model;

import com.twu.biblioteca.action.*;
import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.collection.Movies;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.controller.LibrarianMenuExecutor;
import com.twu.biblioteca.controller.UserMenuExecutor;
import com.twu.biblioteca.listener.LoginListener;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorTest {
    private Authenticator authenticator;

    @Mock
    LoginListener listener;

    @Before
    public void setUp() {
        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        availableBookList.add(new Book("Book 3", "Agatha Christie", 1800));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        Books books = new Books(availableBookList, checkedOutBookList);

        Menu librarianMenu = null;
        Menu userMenu = null;
        Menu loginMenu = null;

        ArrayList<Movie> availableMovieList = new ArrayList<Movie>();
        availableMovieList.add(new Movie("Movie 1", 2003, "director 1", 8));
        availableMovieList.add(new Movie("Movie 2", 2005, "nayan", 2));
        availableMovieList.add(new Movie("Movie 3", 2006, "ashwin", 4));
        availableMovieList.add(new Movie("Movie 4", 2002, "abhishek", 3));

        ArrayList<Movie> checkedOutMovieList = new ArrayList<Movie>();
        Movies movies = new Movies(availableMovieList, checkedOutMovieList);
        LibrarianMenuExecutor librarianMenuExecutor = null;
        UserMenuExecutor userMenuExecutor = null;
        ConsoleView consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
        HashMap<Integer, String> librarianMenuMap = new HashMap<Integer, String>();


        librarianMenuMap.put(1, Messages.LIST_BOOKS);
        librarianMenuMap.put(2, Messages.QUIT);
        librarianMenuMap.put(3, Messages.CHECKOUT_BOOK);
        librarianMenuMap.put(4, Messages.RETURN_BOOK);
        librarianMenuMap.put(5, Messages.LIST_MOVIES);
        librarianMenuMap.put(6, Messages.CHECKOUT_MOVIE);
        librarianMenuMap.put(7, Messages.RETURN_MOVIE);
        librarianMenuMap.put(9, Messages.LOGOUT);
        librarianMenuMap.put(11, Messages.DEFAULTERS_LIST);

        HashMap<Integer, String> userMenuMap = new HashMap<Integer, String>();

        userMenuMap.put(1, Messages.LIST_BOOKS);
        userMenuMap.put(2, Messages.QUIT);
        userMenuMap.put(3, Messages.CHECKOUT_BOOK);
        userMenuMap.put(4, Messages.RETURN_BOOK);
        userMenuMap.put(5, Messages.LIST_MOVIES);
        userMenuMap.put(6, Messages.CHECKOUT_MOVIE);
        userMenuMap.put(7, Messages.RETURN_MOVIE);
        userMenuMap.put(9, Messages.LOGOUT);


        HashMap<Integer, String> loginMenuMap = new HashMap<Integer, String>();

        loginMenuMap.put(1, Messages.LIST_BOOKS);
        loginMenuMap.put(2, Messages.QUIT);
        loginMenuMap.put(5, Messages.LIST_MOVIES);
        loginMenuMap.put(8, Messages.LOGIN);

        HashMap<Integer, String[]> userNameAndPasswordMap = new HashMap<Integer, String[]>();
        userNameAndPasswordMap.put(1, new String[]{"111-1111", "librarian123"});
        userNameAndPasswordMap.put(2, new String[]{"222-2222", "user222"});
        userNameAndPasswordMap.put(3, new String[]{"333-3333", "user333"});
        userNameAndPasswordMap.put(4, new String[]{"444-4444", "user444"});
        userNameAndPasswordMap.put(5, new String[]{"555-5555", "user555"});
        userNameAndPasswordMap.put(6, new String[]{"666-6666", "user666"});
        userNameAndPasswordMap.put(7, new String[]{"777-7777", "user777"});
        userNameAndPasswordMap.put(8, new String[]{"888-8888", "user888"});

        userMenu = new Menu(userMenuMap);
        librarianMenu = new Menu(librarianMenuMap);
        authenticator = new Authenticator(userNameAndPasswordMap, librarianMenu, userMenu, librarianMenuExecutor, userMenuExecutor);

        HashMap<Integer, MenuAction> menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleView));
        menuActionMap.put(2, new Quit());
        menuActionMap.put(3, new CheckOutBook(consoleView, books));
        menuActionMap.put(4, new ReturnBook(consoleView, books));
        menuActionMap.put(5, new ListMovies(consoleView, movies));
        menuActionMap.put(6, new CheckOutMovie(consoleView, movies));
        menuActionMap.put(7, new ReturnMovie(consoleView, movies));
        menuActionMap.put(8, new LoginAction(consoleView, authenticator));


        userMenuExecutor = new UserMenuExecutor(menuActionMap, consoleView);
        librarianMenuExecutor = new LibrarianMenuExecutor(menuActionMap, consoleView);
        authenticator.addListener(listener);
    }

    @Test
    public void shouldBeAbleToAuthenticateValidUser() {
        assertTrue(authenticator.authenticate("111-1111", "librarian123"));
    }

}