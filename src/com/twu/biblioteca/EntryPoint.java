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

        ConsoleView consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
        HashMap<Integer, String> menuMap = new HashMap<Integer, String>();

        menuMap.put(1, Messages.LIST_BOOKS);
        menuMap.put(2, Messages.QUIT);
        menuMap.put(3, Messages.CHECKOUT_BOOK);

        Menu menu = new Menu(menuMap);
        HashMap<Integer, MenuAction> menuActionMap = new HashMap<Integer, MenuAction>();
        menuActionMap.put(1, new ListBooks(books, consoleView));
        menuActionMap.put(2, new Quit());
        menuActionMap.put(3, new CheckOutBook(consoleView, books));
        MenuExecutor menuExecutor = new MenuExecutor(menuActionMap, consoleView);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleView, menu, menuExecutor);
        bibliotecaApp.start();

    }
}
