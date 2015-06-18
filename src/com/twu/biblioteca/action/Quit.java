package com.twu.biblioteca.action;

public class Quit implements MenuAction {

    @Override
    public void performAction() {
        System.exit(23);
    }
}
