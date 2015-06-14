package com.twu.biblioteca;


public class BibliotecaApp {

    private Books books;
    private ConsoleView consoleView;
    private Menu menu;
    private MenuExecutor menuExecutor;

    public BibliotecaApp(ConsoleView consoleView, Menu menu, MenuExecutor menuExecutor) {
        this.consoleView = consoleView;
        this.menu = menu;
        this.menuExecutor = menuExecutor;
    }

    public void start() {
        consoleView.print(Messages.WELCOME_MESSAGE);
        displayMenu();
        consoleView.print(Messages.ENTER_CHOICE_PROMPT);
        menuExecutor.executeUserCommand();
    }

    public void printListOfBooks() {
        consoleView.print(books.toString());
    }

    public void displayMenu() {
        consoleView.print(menu.toString());
    }
}
