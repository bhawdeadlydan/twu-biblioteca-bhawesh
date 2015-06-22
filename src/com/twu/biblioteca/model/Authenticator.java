package com.twu.biblioteca.model;

import com.twu.biblioteca.listener.HistoryListenable;
import com.twu.biblioteca.listener.LoginHistoryListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Authenticator implements HistoryListenable {
    private HashMap<Integer, String[]> loginDetailsMap;
    private String currentLoggedInUser = "";
    private LoginHistoryListener loginHistoryListener;

    public Authenticator(HashMap<Integer, String[]> loginDetailsMap) {
        this.loginDetailsMap = loginDetailsMap;
    }

    public int authenticate(String username, String password) {
        String[] loginDetail;
        loginDetail = new String[]{username, password};
        if (Arrays.equals(loginDetailsMap.get(1), (loginDetail))) {
            loginHistoryListener.updateUser(username);
            return 1;
        } else {
            Set<Integer> keys = (Set<Integer>) loginDetailsMap.keySet();
            for (Integer key : keys) {
                if (Arrays.equals(loginDetailsMap.get(key), loginDetail)) {
                    loginHistoryListener.updateUser(username);
                    return 2;
                }
            }
        }
        return 3;
    }

    @Override
    public void addListener(LoginHistoryListener listener) {
        this.loginHistoryListener = listener;
    }
}
