package com.twu.biblioteca;

import com.twu.biblioteca.action.*;
import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.collection.Movies;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.controller.LibrarianMenuExecutor;
import com.twu.biblioteca.controller.LoginMenuExecutor;
import com.twu.biblioteca.controller.UserMenuExecutor;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.UserHistory;
import com.twu.biblioteca.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class EntryPoint {
    public static void main(String args[]) throws IOException {
        UserHistory userHistory = null;
        HashMap<String, Book> bookUserHistory = new HashMap<String, Book>();
        HashMap<String, Movie> movieUserHistory = new HashMap<String, Movie>();
        userHistory = new UserHistory(bookUserHistory, movieUserHistory);
        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        availableBookList.add(new Book("Book 3", "Agatha Christie", 1800));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        Books books = new Books(availableBookList, checkedOutBookList);
        books.addListener(userHistory);
        HashMap<Integer, MenuAction> menuActionMap;
        Menu librarianMenu = null;
        Menu userMenu = null;
        Menu loginMenu = null;
        BibliotecaApp bibliotecaApp;

        ArrayList<Movie> availableMovieList = new ArrayList<Movie>();
        availableMovieList.add(new Movie("Movie 1", 2003, "director 1", 8));
        availableMovieList.add(new Movie("Movie 2", 2005, "nayan", 2));
        availableMovieList.add(new Movie("Movie 3", 2006, "ashwin", 4));
        availableMovieList.add(new Movie("Movie 4", 2002, "abhishek", 3));

        ArrayList<Movie> checkedOutMovieList = new ArrayList<Movie>();
        Movies movies = new Movies(availableMovieList, checkedOutMovieList);
        movies.addListener(userHistory);
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

        loginMenu = new Menu(loginMenuMap);
        userMenu = new Menu(userMenuMap);
        librarianMenu = new Menu(librarianMenuMap);
        menuActionMap = new HashMap<Integer, MenuAction>();
        librarianMenuExecutor = new LibrarianMenuExecutor(menuActionMap, consoleView);
        userMenuExecutor = new UserMenuExecutor(menuActionMap, consoleView);
        Authenticator authenticator = new Authenticator(userNameAndPasswordMap);
        menuActionMap.put(1, new ListBooks(books, consoleView));
        menuActionMap.put(2, new Quit());
        menuActionMap.put(3, new CheckOutBook(consoleView, books));
        menuActionMap.put(4, new ReturnBook(consoleView, books));
        menuActionMap.put(5, new ListMovies(consoleView, movies));
        menuActionMap.put(6, new CheckOutMovie(consoleView, movies));
        menuActionMap.put(7, new ReturnMovie(consoleView, movies));
        LoginMenuExecutor loginMenuExecutor = new LoginMenuExecutor(menuActionMap, consoleView);
        Menu menu = new Menu(loginMenuMap);
        LoginAction loginAction = new LoginAction(consoleView, authenticator, librarianMenu, userMenu, librarianMenuExecutor, userMenuExecutor);
        menuActionMap.put(8, loginAction);
        LogoutAction logoutAction = new LogoutAction(consoleView, loginMenu, loginMenuExecutor);
        menuActionMap.put(9, logoutAction);
        authenticator.addListener(userHistory);

        bibliotecaApp = new BibliotecaApp(consoleView, menu, loginMenuExecutor);
        loginAction.addListener(bibliotecaApp);
        logoutAction.addListener(bibliotecaApp);

        bibliotecaApp.start();
    }
}
