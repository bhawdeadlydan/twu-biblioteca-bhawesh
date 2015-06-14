package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Menu {
    private HashMap<Integer, String> menuMap;

    public Menu(HashMap menuMap) {
        this.menuMap = menuMap;
    }

    @Override
    public String toString() {
        String menuList = "";
        Iterator iterator = menuMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            menuList = menuList + "\n" + pair.getKey() + " " + pair.getValue();
        }
        return menuList;
    }
}