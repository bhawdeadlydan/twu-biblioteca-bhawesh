package com.twu.biblioteca.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Authenticator {
    private HashMap<Integer, String[]> loginDetailsMap;

    public Authenticator(HashMap<Integer, String[]> loginDetailsMap) {
        this.loginDetailsMap = loginDetailsMap;
    }

    public int authenticate(String username, String password) {
        String[] loginDetail;
        loginDetail = new String[]{username, password};
        if (Arrays.equals(loginDetailsMap.get(1), (loginDetail))) {
            return 1;
        } else {
            Set<Integer> keys = (Set<Integer>) loginDetailsMap.keySet();
            for( Integer key : keys){
                if(Arrays.equals(loginDetailsMap.get(key), loginDetail))
                    return 2;
            }
        }
        return 3;
    }
}
