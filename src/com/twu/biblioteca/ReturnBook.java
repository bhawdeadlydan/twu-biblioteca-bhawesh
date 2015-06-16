package com.twu.biblioteca;

public class ReturnBook implements MenuAction{
    private ConsoleView consoleView;
    private Books books;

    public ReturnBook(ConsoleView consoleView, Books books) {
        this.consoleView = consoleView;
        this.books =books;
    }

    @Override
    public void performAction() {
        consoleView.print(Messages.BOOK_RETURN_PROMPT);
    }
}
