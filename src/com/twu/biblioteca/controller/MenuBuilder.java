package com.twu.biblioteca.controller;

import com.twu.biblioteca.action.MenuAction;
import com.twu.biblioteca.action.Quit;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.view.ConsoleView;

import java.io.IOException;
import java.util.HashMap;

public abstract class MenuBuilder {
    protected ConsoleView consoleView;
    protected HashMap<Integer, MenuAction> menuActionMap;

    public MenuBuilder(HashMap<Integer, MenuAction> menuActionMap, ConsoleView consoleView) {
        this.menuActionMap = menuActionMap;
        this.consoleView = consoleView;
    }

    public final void buildMenu() {
        removeLoginOperation();
        removeLogoutOperation();
        removeListingOperationOptions();
        removePrivilegedOperations();
        removeUserHistoryCheckingOperation();
    }

    public boolean executeUserCommand() throws IOException {
        int userChoice = consoleView.read();
        if (menuActionMap.containsKey(userChoice)) {
            if(menuActionMap.get(userChoice) instanceof Quit)
                return false;
            menuActionMap.get(userChoice).performAction();
            return true;
        } else {
            consoleView.print(Messages.INVALID_OPTION_MESSAGE);
            return true;
        }
    }

    protected abstract void removeUserHistoryCheckingOperation();

    protected abstract void removePrivilegedOperations();

    protected abstract void removeListingOperationOptions();

    protected abstract void removeLogoutOperation();

    protected abstract void removeLoginOperation();
}
