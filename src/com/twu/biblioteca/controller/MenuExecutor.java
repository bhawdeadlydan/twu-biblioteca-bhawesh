package com.twu.biblioteca.controller;

import com.twu.biblioteca.view.ConsoleView;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.action.MenuAction;
import com.twu.biblioteca.action.Quit;

import java.io.IOException;
import java.util.HashMap;

public class MenuExecutor {
    private HashMap<Integer, MenuAction> menuActionMap;
    private ConsoleView consoleView;

    public MenuExecutor(HashMap<Integer, MenuAction> menuActionMap, ConsoleView consoleView) {
        this.menuActionMap = menuActionMap;
        this.consoleView = consoleView;
    }

    public boolean executeUserCommand() throws IOException {
        int userChoice = consoleView.read();
        if (menuActionMap.containsKey(userChoice)) {
            if (menuActionMap.get(userChoice) instanceof Quit) {
                return false;
            }
            menuActionMap.get(userChoice).performAction();
            return true;
        } else {
            consoleView.print(Messages.INVALID_OPTION_MESSAGE);
            return true;
        }
    }
}
