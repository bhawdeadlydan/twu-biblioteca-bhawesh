package com.twu.biblioteca.action;

import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;

public class UserDetailAction implements MenuAction {
    private  Authenticator authenticator;
    private  ArrayList<User> userList;

    public UserDetailAction(ArrayList<User> userList, Authenticator authenticator) {
        this.userList = userList;
        this.authenticator = authenticator;
    }

    @Override
    public void performAction() {
        authenticator.authenticatedUser(userList);
    }
}
