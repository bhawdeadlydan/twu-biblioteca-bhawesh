package com.twu.biblioteca.action;

import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.listener.LoginHistoryListener;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReturnBookTest {
    private Books books;
    private ReturnBook returnBook;
    ConsoleView consoleViewStub;
    @Mock
    LoginHistoryListener loginHistoryListener;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArrayList<Book> availableBookList = new ArrayList<Book>();
        availableBookList.add(new Book("Book 1", "JK Rowling", 2003));
        availableBookList.add(new Book("Book 2", "Arthur Conan Doyle", 1886));
        availableBookList.add(new Book("Book 3", "Agatha Christie", 1800));

        ArrayList<Book> checkedOutBookList = new ArrayList<Book>();
        books = new Books(availableBookList, checkedOutBookList);
        books.addListener(loginHistoryListener);
        consoleViewStub = mock(ConsoleView.class);
        returnBook = new ReturnBook(consoleViewStub, books);
    }

    @Test
    public void shouldPromptUserToEnterBookNameToReturn() throws IOException {
        when(consoleViewStub.getName()).thenReturn("Book 1");
        returnBook.performAction();

        verify(consoleViewStub).print(Messages.BOOK_RETURN_PROMPT);
    }

    @Test
    public void shouldInputBookNameFromUser() throws IOException {
        when(consoleViewStub.getName()).thenReturn("Book 1");
        returnBook.performAction();

        verify(consoleViewStub).getName();
    }

    @Test
    public void shouldReturnBook() throws IOException {
        Books books = mock(Books.class);
        returnBook = new ReturnBook(consoleViewStub, books);
        when(consoleViewStub.getName()).thenReturn("Book 1");

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
        books.addListener(loginHistoryListener);
        consoleViewStub = mock(ConsoleView.class);
        returnBook = new ReturnBook(consoleViewStub, books);
        when(consoleViewStub.getName()).thenReturn("Book 3");
        returnBook.performAction();
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleViewStub, times(2)).print(stringArgumentCaptor.capture());
        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.SUCCESSFULL_RETURN;

        assertThat(expectedMessage, is(actualMessage));
    }

    @Test
    public void shouldNotBeAbleToReturnInvalidBook() throws IOException {
        returnBook = new ReturnBook(consoleViewStub, books);
        when(consoleViewStub.getName()).thenReturn("Book 1");

        returnBook.performAction();

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleViewStub, times(2)).print(stringArgumentCaptor.capture());

        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.UNSUCCESSFULL_RETURN;

        assertThat(expectedMessage, is(actualMessage));
    }
}