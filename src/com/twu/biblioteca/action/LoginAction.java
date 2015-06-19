package com.twu.biblioteca.action;

import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.view.ConsoleView;

import java.io.IOException;

public class LoginAction implements MenuAction {
    Authenticator authenticator;

    private ConsoleView consoleView;

    public LoginAction(ConsoleView consoleView, Authenticator authenticator) {
        this.consoleView = consoleView;
        this.authenticator = authenticator;
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

        try {
            password = consoleView.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (authenticator.authenticate(username, password)) {
            consoleView.print(Messages.SUCCESSFUL_LOGIN);
        }
    }
}
