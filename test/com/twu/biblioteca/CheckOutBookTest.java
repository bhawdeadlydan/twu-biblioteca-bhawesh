package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class CheckOutBookTest {
    private Books books;
    private CheckOutBook checkOutBook;
    ConsoleView consoleViewStub;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Book 1", "JK Rowling", 2003));
        bookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        bookList.add(new Book("Book 3", "Agatha Christie", 1800));
        consoleViewStub = mock(ConsoleView.class);
        books = new Books(bookList);
        checkOutBook = new CheckOutBook(consoleViewStub, books);
    }

    @Test
    public void shouldPromptUserToEnterBookNameToCheckout() {
        checkOutBook.performAction();

        Mockito.verify(consoleViewStub).print(Messages.BOOK_CHECKOUT_PROMPT);
    }

    @Test
    public void shouldInputBookNameFromUser() {
        checkOutBook.performAction();

        Mockito.verify(consoleViewStub).getBookName();
    }
}