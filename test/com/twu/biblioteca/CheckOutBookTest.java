package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class CheckOutBookTest {
    private Books books;
    private ConsoleView consoleView;
    @Before
    public void setUp() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Book 1", "JK Rowling", 2003));
        bookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        bookList.add(new Book("Book 3", "Agatha Christie", 1800));

        books = new Books(bookList);
        consoleView = new ConsoleView(new Scanner(System.in));
        CheckOutBook checkOutBook = new CheckOutBook(consoleView, books);
    }


}