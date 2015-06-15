package com.twu.biblioteca;

import java.util.ArrayList;

public class Books {
    private ArrayList<Book> books;

    public Books(ArrayList<Book> bookList) {
        this.books = bookList;
    }

    @Override
    public String toString() {
        String bookPrint = "\nName\tAuthor\tPublication Year";
        for (Book book : books) {
            bookPrint += "\n" + book.toString();
        }
        return bookPrint;
    }

    public boolean isBookInBookList(String greatBook) {
        for (Book book : books)
            if (book.isBookSame(greatBook))
                return true;
        return false;
    }
}
