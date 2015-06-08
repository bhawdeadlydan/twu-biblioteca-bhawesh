package com.twu.biblioteca;


import java.util.ArrayList;

public class BibliotecaApp {

    private Library library;

    public BibliotecaApp() {
        library = new Library();
    }

    public void welcomeMessage() {
        System.out.print("Welcome");
    }

    public void printListOfBooks() {
        ArrayList<String> books = library.books();
        for(String book : books) {
            System.out.println(book);
        }
    }
}
