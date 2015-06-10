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
        ArrayList<String[]> books = library.books();
        System.out.print("\nName\t" + "Author\t" + "Publication Year ");
        for (String bookDetails[] : books) {
            System.out.print("\n" + bookDetails[0]);
            System.out.print("\t" + bookDetails[1]);
            System.out.print("\t" + bookDetails[2]);
        }
    }
}
