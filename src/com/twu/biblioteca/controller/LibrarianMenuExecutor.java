package com.twu.biblioteca.controller;

import com.twu.biblioteca.action.MenuAction;
import com.twu.biblioteca.view.ConsoleView;

import java.util.HashMap;

public class LibrarianMenuExecutor extends MenuBuilder {

    public LibrarianMenuExecutor(HashMap<Integer, MenuAction> menuActionMap, ConsoleView consoleView) {
        super(menuActionMap, consoleView);
    }


    @Override
    public void removeUserHistoryCheckingOperation() {

    }

    @Override
    public void removePrivilegedOperations() {

    }

    @Override
    public void removeListingOperationOptions() {

    }

    @Override
    public void removeLogoutOperation() {

    }

    @Override
    public void removeLoginOperation() {
        menuActionMap.remove(8);
    }

    @Override
    protected void removeUserDetailOption() {

    }
}
