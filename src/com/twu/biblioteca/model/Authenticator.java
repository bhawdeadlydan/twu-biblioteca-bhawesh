package com.twu.biblioteca.model;

import com.twu.biblioteca.controller.LibrarianMenuExecutor;
import com.twu.biblioteca.controller.UserMenuExecutor;
import com.twu.biblioteca.listener.Listenable;
import com.twu.biblioteca.listener.LoginListener;
import com.twu.biblioteca.menu.Menu;

import java.util.Arrays;
import java.util.HashMap;

public class Authenticator implements Listenable {
    private  HashMap<Integer, String[]> loginDetailsMap;
    private  Menu librarianMenu;
    private  Menu userMenu;
    private  LibrarianMenuExecutor librarianMenuExecutor;
    private  UserMenuExecutor userMenuExecutor;
    private LoginListener loginListener;

    public Authenticator(HashMap<Integer, String[]> loginDetailsMap, Menu librarianMenuMap, Menu userMenuMap,
                         LibrarianMenuExecutor librarianMenuExecutor, UserMenuExecutor userMenuExecutor)
    {
        this.loginDetailsMap = loginDetailsMap;
        this.librarianMenu = librarianMenuMap;
        this.userMenu = userMenuMap;
        this.librarianMenuExecutor = librarianMenuExecutor;
        this.userMenuExecutor = userMenuExecutor;

    }

    public boolean authenticate(String username, String password) {
        String[] loginDetail = new String[]{username, password};
        if (Arrays.equals(loginDetailsMap.get(1), (loginDetail))) {
            loginListener.update(librarianMenu, librarianMenuExecutor);
            return true;
        } else if (loginDetailsMap.containsValue(loginDetail)) {
            loginListener.update(userMenu, librarianMenuExecutor);
            return true;
        }
        return false;
    }

    @Override
    public void addListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
}
