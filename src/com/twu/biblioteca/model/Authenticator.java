package com.twu.biblioteca.model;

import com.twu.biblioteca.controller.LibrarianMenuExecutor;
import com.twu.biblioteca.controller.UserMenuExecutor;
import com.twu.biblioteca.menu.Menu;

import java.util.Arrays;
import java.util.HashMap;

public class Authenticator {
    private HashMap<Integer, String[]> loginDetailsMap;
    private Menu librarianMenu;
    private Menu userMenu;
    private LibrarianMenuExecutor librarianMenuExecutor;
    private UserMenuExecutor userMenuExecutor;


    public Authenticator(HashMap<Integer, String[]> loginDetailsMap) {
        this.loginDetailsMap = loginDetailsMap;
    }

    public int authenticate(String username, String password) {
        String[] loginDetail = new String[]{username, password};
        if (Arrays.equals(loginDetailsMap.get(1), (loginDetail))) {
            return 1;
        } else if (loginDetailsMap.containsValue(loginDetail)) {
            return 2;
        }
        return 3;
    }
}
