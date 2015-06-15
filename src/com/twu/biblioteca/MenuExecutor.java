package com.twu.biblioteca;

import java.util.HashMap;

public class MenuExecutor {
    private HashMap<Integer, MenuAction> menuActionMap;
    private ConsoleView consoleView;

    public MenuExecutor(HashMap<Integer, MenuAction> menuActionMap, ConsoleView consoleView) {
        this.menuActionMap = menuActionMap;
        this.consoleView = consoleView;
    }

    public boolean executeUserCommand() {
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
