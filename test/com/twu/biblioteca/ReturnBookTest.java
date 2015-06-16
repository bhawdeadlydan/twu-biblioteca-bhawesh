package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ReturnBookTest {
    private Books books;
    private ReturnBook returnBook;
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
        returnBook = new ReturnBook(consoleViewStub, books);
    }
    @Test
    public void shouldPromptUserToEnterBookNameToReturn() throws IOException {
        when(consoleViewStub.getBookName()).thenReturn("Book 1");
        returnBook.performAction();

        verify(consoleViewStub).print(Messages.BOOK_RETURN_PROMPT);
    }

    @Test
    public void shouldInputBookNameFromUser() throws IOException {
        when(consoleViewStub.getBookName()).thenReturn("Book 1");
        returnBook.performAction();

        verify(consoleViewStub).getBookName();
    }

    @Test
    public void shouldReturnBook() throws IOException {
        Books books = mock(Books.class);
        returnBook = new ReturnBook(consoleViewStub, books);
        when(consoleViewStub.getBookName()).thenReturn("Book 1");

        returnBook.performAction();

        verify(books).returnBook("Book 1");
    }


}