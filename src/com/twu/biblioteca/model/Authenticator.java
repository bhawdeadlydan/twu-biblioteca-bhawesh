package com.twu.biblioteca.model;

import com.twu.biblioteca.listener.ListenableForHistoryOfUsers;
import com.twu.biblioteca.listener.LoginHistoryListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Authenticator implements ListenableForHistoryOfUsers {
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
            this.currentLoggedInUser = username;
            loginHistoryListener.updateUser(this.currentLoggedInUser);
            return 1;
        } else {
            Set<Integer> keys = (Set<Integer>) loginDetailsMap.keySet();
            for (Integer key : keys) {
                if (Arrays.equals(loginDetailsMap.get(key), loginDetail)) {
                    this.currentLoggedInUser = username;
                    loginHistoryListener.updateUser(this.currentLoggedInUser);
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

    public ArrayList<User> authenticatedUser(ArrayList<User> userList) {
        ArrayList<User> userListWithCurrentLoggedInUser = new ArrayList<User>();
        for (User user : userList) {
            if (user.isSameAs(this.currentLoggedInUser)) {
                userListWithCurrentLoggedInUser.add(user);
            }
        }
        return userListWithCurrentLoggedInUser;
    }
}
