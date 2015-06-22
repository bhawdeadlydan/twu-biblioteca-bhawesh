package com.twu.biblioteca.controller;

import com.twu.biblioteca.action.MenuAction;
import com.twu.biblioteca.view.ConsoleView;

import java.util.HashMap;

public class LoginMenuExecutor extends MenuBuilder {

    public LoginMenuExecutor(HashMap<Integer, MenuAction> menuActionMap, ConsoleView consoleView) {
        super(menuActionMap, consoleView);
    }

    @Override
    protected void removeUserHistoryCheckingOperation() {
        menuActionMap.remove(11);
    }

    @Override
    protected void removePrivilegedOperations() {
        menuActionMap.remove(3);
        menuActionMap.remove(4);
        menuActionMap.remove(6);
        menuActionMap.remove(7);
    }

    @Override
    protected void removeListingOperationOptions() {

    }

    @Override
    protected void removeLogoutOperation() {

    }

    @Override
    protected void removeLoginOperation() {
        menuActionMap.remove(8);
    }

    @Override
    protected void removeUserDetailOption() {
        menuActionMap.remove(10);
    }
}
