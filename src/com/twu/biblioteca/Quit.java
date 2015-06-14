package com.twu.biblioteca;

public class Quit implements MenuAction{

    @Override
    public void performAction() {
        System.exit(23);
    }
}
