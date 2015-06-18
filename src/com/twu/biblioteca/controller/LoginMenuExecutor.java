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

    }

    @Override
    protected void removePrivilegedOperations() {

    }

    @Override
    protected void removeListingOperationOptions() {

    }

    @Override
    protected void removeLogoutOperation() {

    }

    @Override
    protected void removeLoginOperation() {

    }
}
