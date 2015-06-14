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
        welcomeMessage();
        displayMenu();

    }

    public void welcomeMessage() {
        consoleView.print("Welcome");
    }

    public void printListOfBooks() {
        consoleView.print(books.toString());
    }

    public void displayMenu() {
        consoleView.print(menu.toString());
    }
}
