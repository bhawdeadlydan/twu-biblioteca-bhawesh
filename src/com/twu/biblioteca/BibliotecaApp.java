package com.twu.biblioteca;


public class BibliotecaApp {

    private Books books;
    ConsoleView consoleView;
    Menu menu;

    public BibliotecaApp(Books books, ConsoleView consoleView, Menu menu) {
        this.books = books;
        this.consoleView = consoleView;
        this.menu = menu;
    }

    public void start() {
        consoleView.print(Messages.WELCOME_MESSAGE);
        displayMenu();

    }

    public void printListOfBooks() {
        consoleView.print(books.toString());
    }

    public void displayMenu() {
        consoleView.print(menu.toString());
    }
}
