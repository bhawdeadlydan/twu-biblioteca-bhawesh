package com.twu.biblioteca.model;

import com.twu.biblioteca.listener.Listenable;
import com.twu.biblioteca.listener.LoginListener;

public class Authenticator implements Listenable {
    private LoginListener loginListener;

    public boolean authenticate(String username, String password) {
        return false;
    }

    @Override
    public void addListener(LoginListener loginListener) {
        this.loginListener =loginListener;
    }
}
