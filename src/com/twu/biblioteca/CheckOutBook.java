package com.twu.biblioteca;

import java.io.IOException;

public class
        CheckOutBook implements MenuAction {
    private Books books;
    private ConsoleView consoleView;

    public CheckOutBook(ConsoleView consoleView, Books books) {
        this.consoleView = consoleView;
        this.books = books;
    }

    @Override
    public void performAction() {

        consoleView.print(Messages.BOOK_CHECKOUT_PROMPT);
        String bookName = null;
        try {
            bookName = consoleView.getBookName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        books.checkout(bookName);
    }
}

