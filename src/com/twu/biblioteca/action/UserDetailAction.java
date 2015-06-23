package com.twu.biblioteca.action;

import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.view.ConsoleView;

import java.util.ArrayList;

public class UserDetailAction implements MenuAction {
    private ConsoleView consoleView;
    private Authenticator authenticator;
    private ArrayList<User> userList;

    public UserDetailAction(ArrayList<User> userList, Authenticator authenticator, ConsoleView consoleView) {
        this.userList = userList;
        this.authenticator = authenticator;
        this.consoleView = consoleView;
    }

    @Override
    public void performAction() {
        ArrayList<User> userListWithLoggedInUser = authenticator.authenticatedUser(userList);
        for(User user : userListWithLoggedInUser) {
            consoleView.print(user.toString());
        }
    }
}
