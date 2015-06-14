package com.twu.biblioteca;

import java.util.ArrayList;

public class Books {
    private ArrayList<String[]> books;

    public Books(ArrayList<String[]> bookList) {
        this.books = bookList;
    }

    public ArrayList<String[]> books() {
        return books;
    }

    @Override
    public String toString() {
        String bookPrint = "\nName\tAuthor\tPublication Year";
        for (String[] book : books) {
            bookPrint += "\n" + book[0] + "\t"
                    + book[1] + "\t"
                    + book[2];
        }
        return bookPrint;

    }
}
