package com.twu.biblioteca;

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
        HashMap<Integer, String> menuMap = new HashMap<Integer, String>();

        menuMap.put(1, Messages.LIST_BOOKS);
        menuMap.put(2, Messages.QUIT);
        menuMap.put(3, Messages.CHECKOUT_BOOK);
        menuMap.put(4, Messages.RETURN_BOOK);
        menuMap.put(5, Messages.LIST_MOVIES);
        menuMap.put(6, Messages.CHECKOUT_MOVIE);


        Menu menu = new Menu(menuMap);
        HashMap<Integer, MenuAction> menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleView));
        menuActionMap.put(2, new Quit());
        menuActionMap.put(3, new CheckOutBook(consoleView, books));
        menuActionMap.put(4, new ReturnBook(consoleView, books));
        menuActionMap.put(5, new ListMovies(consoleView, movies));
        menuActionMap.put(6, new CheckOutMovie(consoleView, movies));
        MenuExecutor menuExecutor = new MenuExecutor(menuActionMap, consoleView);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleView, menu, menuExecutor);
        bibliotecaApp.start();

    }
}
