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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Book book = (Book) o;

        if (publicationYear != book.publicationYear)
            return false;
        if (!bookName.equals(book.bookName))
            return false;
        return author.equals(book.author);

    }

    @Override
    public int hashCode() {
        int result = publicationYear;
        result = 31 * result + bookName.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }

    public boolean isBookSame(String book) {
        return book == this.bookName;
    }
}
