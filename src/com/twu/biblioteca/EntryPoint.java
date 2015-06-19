package com.twu.biblioteca;

import com.twu.biblioteca.action.*;
import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.collection.Movies;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.controller.LoginMenuExecutor;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class EntryPoint {
    public static void main(String args[]) throws IOException {
        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        availableBookList.add(new Book("Book 3", "Agatha Christie", 1800));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        Books books = new Books(availableBookList, checkedOutBookList);


        ArrayList<Movie> availableMovieList = new ArrayList<Movie>();
        availableMovieList.add(new Movie("Movie 1", 2003, "director 1", 8));
        availableMovieList.add(new Movie("Movie 2", 2005, "nayan", 2));
        availableMovieList.add(new Movie("Movie 3", 2006, "ashwin", 4));
        availableMovieList.add(new Movie("Movie 4", 2002, "abhishek", 3));

        ArrayList<Movie> checkedOutMovieList = new ArrayList<Movie>();
        Movies movies = new Movies(availableMovieList, checkedOutMovieList);

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


        Menu menu = new Menu(loginMenuMap);
        HashMap<Integer, MenuAction> menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleView));
        menuActionMap.put(2, new Quit());
        menuActionMap.put(3, new CheckOutBook(consoleView, books));
        menuActionMap.put(4, new ReturnBook(consoleView, books));
        menuActionMap.put(5, new ListMovies(consoleView, movies));
        menuActionMap.put(6, new CheckOutMovie(consoleView, movies));
        menuActionMap.put(7, new ReturnMovie(consoleView, movies));
        menuActionMap.put(8, new LoginAction(consoleView, movies));

        LoginMenuExecutor loginMenuExecutor = new LoginMenuExecutor(menuActionMap, consoleView);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleView, menu, loginMenuExecutor);
        bibliotecaApp.start();

    }
}
