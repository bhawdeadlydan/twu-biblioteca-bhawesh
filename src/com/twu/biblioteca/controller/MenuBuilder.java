package com.twu.biblioteca.controller;

import com.twu.biblioteca.action.MenuAction;
import com.twu.biblioteca.view.ConsoleView;

import java.util.HashMap;

public abstract class MenuBuilder {
    private ConsoleView consoleView;
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

    protected abstract void removeUserHistoryCheckingOperation();

    protected abstract void removePrivilegedOperations();

    protected abstract void removeListingOperationOptions();

    protected abstract void removeLogoutOperation();

    protected abstract void removeLoginOperation();
}
