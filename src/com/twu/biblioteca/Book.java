package com.twu.biblioteca;

public class Book {
    private int publicationYear;
    private String bookName;
    private String author;


    public Book(String bookName, String author, int publicationYear) {
        this.bookName = bookName;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        String bookDetail = "\n";
        bookDetail += bookName + "\t" + author + "\t" + publicationYear;
        return bookDetail;
    }
}
