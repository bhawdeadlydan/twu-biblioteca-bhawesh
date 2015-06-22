package com.twu.biblioteca.action;

import com.twu.biblioteca.collection.Books;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.listener.LoginHistoryListener;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class CheckOutBookTest {
    private Books books;
    private CheckOutBook checkOutBook;
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
        checkOutBook = new CheckOutBook(consoleViewStub, books);
    }

    @Test
    public void shouldPromptUserToEnterBookNameToCheckout() throws IOException {
        when(consoleViewStub.getName()).thenReturn("Book 1");
        checkOutBook.performAction();

        verify(consoleViewStub).print(Messages.BOOK_CHECKOUT_PROMPT);
    }

    @Test
    public void shouldInputBookNameFromUser() throws IOException {
        when(consoleViewStub.getName()).thenReturn("Book 1");
        checkOutBook.performAction();

        verify(consoleViewStub).getName();
    }

    @Test
    public void shouldCheckOutBook() throws IOException {
        Books books = mock(Books.class);
        checkOutBook = new CheckOutBook(consoleViewStub, books);
        when(consoleViewStub.getName()).thenReturn("Book 1");

        checkOutBook.performAction();

        verify(books).checkout("Book 1");
    }

    @Test
    public void shouldSuccessfullyCheckOutBook() throws IOException {
        checkOutBook = new CheckOutBook(consoleViewStub, books);
        when(consoleViewStub.getName()).thenReturn("Book 1");

        checkOutBook.performAction();

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleViewStub, times(2)).print(stringArgumentCaptor.capture());

        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.SUCCESSFULL_CHECKOUT;

        assertThat(expectedMessage, is(actualMessage));
    }

    @Test
    public void shouldNotCheckOutBook() throws IOException {
        checkOutBook = new CheckOutBook(consoleViewStub, books);
        when(consoleViewStub.getName()).thenReturn("Book 4");

        checkOutBook.performAction();

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleViewStub, times(2)).print(stringArgumentCaptor.capture());

        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.UNSUCCESSFULL_CHECKOUT;

        assertThat(expectedMessage, is(actualMessage));
    }


}