package com.twu.biblioteca.action;

import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.view.ConsoleView;

public class ListBooks implements MenuAction {
    private ConsoleView consoleView;
    private Books books;

    public ListBooks(Books books, ConsoleView consoleView) {
        this.books = books;
        this.consoleView = consoleView;
    }

    @Override
    public void performAction() {
        consoleView.print(books.toString());
    }
}
