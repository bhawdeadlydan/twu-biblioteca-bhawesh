package com.twu.biblioteca;


import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.controller.MenuExecutor;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.view.ConsoleView;

import java.io.IOException;

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

    public void start() throws IOException {
        Boolean shouldContinue = true;
        while (shouldContinue) {
            consoleView.print(Messages.WELCOME_MESSAGE);
            consoleView.print(menu.toString());
            consoleView.print(Messages.ENTER_CHOICE_PROMPT);
            shouldContinue = menuExecutor.executeUserCommand();
        }
    }
}
