package com.twu.biblioteca;


import java.util.ArrayList;

public class BibliotecaApp {

    private Books books;

    public BibliotecaApp(Books books) {
        this.books = books;
    }

    public void welcomeMessage() {
        System.out.print("Welcome");
    }

    public void printListOfBooks() {
        ArrayList<String[]> books = this.books.books();
        System.out.print("\nName\t" + "Author\t" + "Publication Year ");
        for (String bookDetails[] : books) {
            System.out.print("\n" + bookDetails[0]);
            System.out.print("\t" + bookDetails[1]);
            System.out.print("\t" + bookDetails[2]);
        }
    }
}
