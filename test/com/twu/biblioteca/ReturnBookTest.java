package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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

    @Test
    public void shouldSuccessfullyReturnBook() throws IOException {
        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        checkedOutBookList.add(new Book("Book 3", "Agatha Christie", 1800));
        books = new Books(availableBookList, checkedOutBookList);
        consoleViewStub = mock(ConsoleView.class);
        returnBook = new ReturnBook(consoleViewStub, books);
        when(consoleViewStub.getBookName()).thenReturn("Book 3");

        returnBook.performAction();

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleViewStub, times(2)).print(stringArgumentCaptor.capture());

        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.SUCCESSFULL_RETURN;

        assertThat(expectedMessage, is(actualMessage));
    }


}