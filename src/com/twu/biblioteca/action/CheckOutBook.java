package com.twu.biblioteca.action;

import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.view.ConsoleView;

import java.io.IOException;

public class CheckOutBook implements MenuAction {
    private Books books;
    private ConsoleView consoleView;

    public CheckOutBook(ConsoleView consoleView, Books books) {
        this.consoleView = consoleView;
        this.books = books;
    }

    @Override
    public void performAction() {

        consoleView.print(Messages.BOOK_CHECKOUT_PROMPT);
        String bookName = "";
        try {
            bookName = consoleView.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (books.checkout(bookName)) {
            consoleView.print(Messages.SUCCESSFULL_CHECKOUT);
        } else {
            consoleView.print(Messages.UNSUCCESSFULL_CHECKOUT);
        }
    }
}

