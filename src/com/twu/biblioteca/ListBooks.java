package com.twu.biblioteca;

import java.util.ArrayList;

public class ListBooks implements MenuAction {


    @Override
    public void performAction() {
        ArrayList<String[]> bookList = new ArrayList<String[]>();
        bookList.add(new String[]{"Book 1", "JK Rowling", "2003"});
        bookList.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        bookList.add(new String[]{"Book 3", "Agatha Christie", "1800"});

        Books books =new Books(bookList);
        ConsoleView consoleView = new ConsoleView();
        consoleView.print(books.toString());
    }
}
