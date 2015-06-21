package com.twu.biblioteca.action;

import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.controller.LibrarianMenuExecutor;
import com.twu.biblioteca.controller.UserMenuExecutor;
import com.twu.biblioteca.listener.Listenable;
import com.twu.biblioteca.listener.LoginListener;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.view.ConsoleView;

import java.io.IOException;

public class LoginAction implements MenuAction, Listenable {
    private final UserMenuExecutor userMenuExecutor;
    private final LibrarianMenuExecutor librarianMenuExecutor;
    private final Menu librarianMenu;
    private final Menu userMenu;
    Authenticator authenticator;
    private LoginListener loginListener;

    private ConsoleView consoleView;

    public LoginAction(ConsoleView consoleView, Authenticator authenticator, Menu librarianMenu, Menu userMenu,
                       LibrarianMenuExecutor librarianMenuExecutor, UserMenuExecutor userMenuExecutor)
    {
        this.consoleView = consoleView;
        this.authenticator = authenticator;
        this.librarianMenuExecutor = librarianMenuExecutor;
        this.userMenuExecutor = userMenuExecutor;
        this.librarianMenu = librarianMenu;
        this.userMenu = userMenu;
    }

    @Override
    public void performAction() {
        consoleView.print(Messages.USERNAME_PROMPT);

        String username = "";
        String password = "";

        try {
            username = consoleView.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }

        consoleView.print(Messages.PASSWORD_PROMPT);

        try {
            password = consoleView.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int authentication = authenticator.authenticate(username, password);
        if (authentication == 1) {
            loginListener.update(librarianMenu, librarianMenuExecutor);
            consoleView.print(Messages.SUCCESSFUL_LOGIN_LIBRARIAN);
        } else if (authentication == 2) {
            loginListener.update(userMenu, userMenuExecutor);
            consoleView.print(Messages.SUCCESSFUL_LOGIN_USER);
        } else {
            consoleView.print(Messages.UNSUCCESSFUL_LOGIN);
        }
    }

    @Override
    public void addListener(LoginListener listener) {
        this.loginListener = listener;
    }
}
