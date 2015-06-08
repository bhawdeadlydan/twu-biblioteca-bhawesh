package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {
    private ArrayList<String> books;

    public Library() {
        books = new ArrayList<String>();
        books.add("Book 1");
        books.add("Book 2");
        books.add("Book 3");
    }

    public ArrayList<String> books() {
        return books;
    }
}
