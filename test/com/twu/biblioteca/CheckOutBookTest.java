package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class CheckOutBookTest {
    private Books books;
    private CheckOutBook checkOutBook;
    ConsoleView consoleViewStub;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        availableBookList.add(new Book("Book 3", "Agatha Christie", 1800));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        books = new Books(availableBookList, checkedOutBookList);
        consoleViewStub = mock(ConsoleView.class);
        checkOutBook = new CheckOutBook(consoleViewStub, books);
    }

    @Test
    public void shouldPromptUserToEnterBookNameToCheckout() throws IOException{
        when(consoleViewStub.getBookName()).thenReturn("Book 1");
        checkOutBook.performAction();

        verify(consoleViewStub).print(Messages.BOOK_CHECKOUT_PROMPT);
    }

    @Test
    public void shouldInputBookNameFromUser() throws IOException {
        when(consoleViewStub.getBookName()).thenReturn("Book 1");
        checkOutBook.performAction();

        verify(consoleViewStub).getBookName();
    }
    @Test
    public void shouldCheckOutBook() throws IOException {
        Books books = mock(Books.class);
        checkOutBook = new CheckOutBook(consoleViewStub, books);
        when(consoleViewStub.getBookName()).thenReturn("Book 1");

        checkOutBook.performAction();

        verify(books).checkout("Book 1");
    }
}