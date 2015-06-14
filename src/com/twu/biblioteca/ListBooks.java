package com.twu.biblioteca;

public class ListBooks implements MenuAction {
    private Books books;

    public ListBooks(Books books) {
        this.books = books;
    }

    @Override
    public void performAction() {
        ConsoleView consoleView = new ConsoleView();
        consoleView.print(books.toString());
    }
}
