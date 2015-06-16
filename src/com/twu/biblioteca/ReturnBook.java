package com.twu.biblioteca;

import java.io.IOException;

public class ReturnBook implements MenuAction {
    private ConsoleView consoleView;
    private Books books;

    public ReturnBook(ConsoleView consoleView, Books books) {
        this.consoleView = consoleView;
        this.books = books;
    }

    @Override
    public void performAction() {
        consoleView.print(Messages.BOOK_RETURN_PROMPT);
        String bookName = "";
        try {
            bookName = consoleView.getBookName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(books.returnBook(bookName)){
         consoleView.print(Messages.SUCCESSFULL_RETURN);
        }
    }
}
