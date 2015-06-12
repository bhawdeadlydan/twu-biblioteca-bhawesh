package com.twu.biblioteca;

import java.util.ArrayList;

public class Books {
    private ArrayList<String[]> books;

    public Books() {
        books = new ArrayList<String[]>();
        books.add(new String[]{"Book 1", "JK Rowling", "2003"});
        books.add(new String[]{"Book 2", "Arthur Conan Doyle", "1886"});
        books.add(new String[]{"Book 3", "Agatha Christie", "1800"});
    }

    public ArrayList<String[]> books() {
        return books;
    }

    @Override
    public String toString() {
        String bookPrint = "";
        for (String[] book : books) {
            bookPrint +="\n"+ book[0]+"...."
                      + book[1]+"...."
                      + book[2];
        }
        return bookPrint;

    }
}
