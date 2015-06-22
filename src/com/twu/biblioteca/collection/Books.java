package com.twu.biblioteca.collection;

import com.twu.biblioteca.listener.HistoryListenable;
import com.twu.biblioteca.listener.LoginHistoryListener;
import com.twu.biblioteca.model.Book;

import java.util.ArrayList;

public class Books implements HistoryListenable {
    private ArrayList<Book> availableBooks;
    private ArrayList<Book> checkedOutBooks;
    private LoginHistoryListener loginHistoryListener;

    public Books(ArrayList<Book> availableBookList, ArrayList<Book> checkedOutBooks) {
        this.availableBooks = availableBookList;
        this.checkedOutBooks = checkedOutBooks;
    }

    @Override
    public String toString() {
        String bookPrint = "\nName\tAuthor\tPublication Year";
        for (Book book : availableBooks) {
            bookPrint += "\n" + book.toString();
        }
        return bookPrint;
    }

    public boolean isBookInBookList(String greatBook) {
        for (Book book : availableBooks)
            if (book.isBookSame(greatBook))
                return true;
        for (Book book : checkedOutBooks)
            if (book.isBookSame(greatBook))
                return true;
        return false;
    }

    public boolean isAvailable(String bookName) {
        for (Book book : availableBooks)
            if (book.isBookSame(bookName))
                return true;
        return false;
    }

    public boolean checkout(String bookName) {
        if (isAvailable(bookName)) {
            for (Book book : availableBooks) {
                if (book.isBookSame(bookName)) {
                    checkedOutBooks.add(book);
                    availableBooks.remove(book);
                    loginHistoryListener.updateBook(book, -1);
                    return true;
                }

            }
        }
        return false;
    }

    public boolean returnBook(String bookName) {
        if (isCheckedOut(bookName)) {
            for (Book book : checkedOutBooks) {
                if (book.isBookSame(bookName)) {
                    availableBooks.add(book);
                    checkedOutBooks.remove(book);
                    loginHistoryListener.updateBook(book, 1);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCheckedOut(String bookName) {
        for (Book book : checkedOutBooks)
            if (book.isBookSame(bookName))
                return true;
        return false;
    }

    @Override
    public void addListener(LoginHistoryListener listener) {
        this.loginHistoryListener = listener;
    }
}
