package com.twu.biblioteca;


import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.controller.MenuBuilder;
import com.twu.biblioteca.listener.LoginListener;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.view.ConsoleView;

import java.io.IOException;

public class BibliotecaApp implements LoginListener {

    private ConsoleView consoleView;
    private Menu menu;
    private MenuBuilder menuExecutor;

    public BibliotecaApp(ConsoleView consoleView, Menu menu, MenuBuilder menuExecutor) {
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


    @Override
    public void update(Menu menu, MenuBuilder menuExecutor) {
        this.menu = menu;
        this.menuExecutor = menuExecutor;
    }
}
