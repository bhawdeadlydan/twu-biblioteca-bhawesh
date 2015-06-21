package com.twu.biblioteca.action;

import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.controller.LoginMenuExecutor;
import com.twu.biblioteca.listener.Listenable;
import com.twu.biblioteca.listener.LoginListener;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.view.ConsoleView;

public class LogoutAction implements MenuAction, Listenable {
    private ConsoleView consoleView;
    private Menu loginMenu;
    private LoginMenuExecutor loginMenuExecutor;
    private LoginListener loginListener;

    public LogoutAction(ConsoleView consoleView, Menu loginMenu, LoginMenuExecutor loginMenuExecutor) {
        this.consoleView = consoleView;
        this.loginMenu = loginMenu;
        this.loginMenuExecutor = loginMenuExecutor;
    }

    @Override
    public void addListener(LoginListener listener) {
        this.loginListener = listener;
    }

    @Override
    public void performAction() {
        consoleView.print(Messages.LOGOUT_MESSAGE);
    }
}
