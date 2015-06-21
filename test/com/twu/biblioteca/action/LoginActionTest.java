package com.twu.biblioteca.action;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.controller.LibrarianMenuExecutor;
import com.twu.biblioteca.controller.UserMenuExecutor;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginActionTest {
    @Mock
    BibliotecaApp bibliotecaApp;

    @Mock
    ConsoleView consoleView;

    @Mock
    Authenticator authenticator;

    @Mock
    Menu librarianMenu, userMenu;

    @Mock
    LibrarianMenuExecutor librarianMenuExecutor;
    UserMenuExecutor userMenuExecutor;

    @Test
    public void shouldPromptUserForUserName() {
        LoginAction loginAction = new LoginAction(consoleView, authenticator, librarianMenu, userMenu, librarianMenuExecutor, userMenuExecutor);
        loginAction.performAction();
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleView, times(3)).print(stringArgumentCaptor.capture());
        List<String> capturedStrings = stringArgumentCaptor.getAllValues();
        String actualMessage = capturedStrings.get(0);
        String expectedMessage = Messages.USERNAME_PROMPT;

        assertThat(expectedMessage, is(actualMessage));
    }

    @Test
    public void shouldPromptUserForPassword() {
        LoginAction loginAction = new LoginAction(consoleView, authenticator, librarianMenu, userMenu, librarianMenuExecutor, userMenuExecutor);
        loginAction.addListener(bibliotecaApp);
        loginAction.performAction();

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleView, times(3)).print(stringArgumentCaptor.capture());
        List<String> capturedStrings = stringArgumentCaptor.getAllValues();
        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.PASSWORD_PROMPT;
        System.out.print(capturedStrings);

        assertThat(expectedMessage, is(actualMessage));
    }
    @Test
    public void shouldGetUserNameAndPassWord() throws IOException {
        LoginAction loginAction = new LoginAction(consoleView, authenticator, librarianMenu, userMenu, librarianMenuExecutor, userMenuExecutor);
        loginAction.addListener(bibliotecaApp);
        loginAction.performAction();

        verify(consoleView,times(2)).getName();
    }


    @Test
    public void shouldBeAbleToAuthenticateUser() {
        LoginAction loginAction = new LoginAction(consoleView, authenticator, librarianMenu, userMenu, librarianMenuExecutor, userMenuExecutor);
        loginAction.addListener(bibliotecaApp);
        loginAction.performAction();

        verify(authenticator).authenticate(anyString(), anyString());
    }

    @Test
    public void shouldBeAbleToAlertSuccessfulLogin() {
        when(authenticator.authenticate(anyString(), anyString())).thenReturn(1);
        LoginAction loginAction = new LoginAction(consoleView, authenticator, librarianMenu, userMenu, librarianMenuExecutor, userMenuExecutor);
        loginAction.addListener(bibliotecaApp);
        loginAction.performAction();
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleView, times(3)).print(stringArgumentCaptor.capture());
        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(2);
        String expectedMessage = Messages.SUCCESSFUL_LOGIN_LIBRARIAN;

        assertThat(expectedMessage, is(actualMessage));
    }

    @Test
    public void shouldBeAbleToAlertSuccessfulLoginForUser() {
        when(authenticator.authenticate(anyString(), anyString())).thenReturn(2);
        LoginAction loginAction = new LoginAction(consoleView, authenticator, librarianMenu, userMenu, librarianMenuExecutor, userMenuExecutor);
        loginAction.addListener(bibliotecaApp);
        loginAction.performAction();
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleView, times(3)).print(stringArgumentCaptor.capture());
        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(2);
        String expectedMessage = Messages.SUCCESSFUL_LOGIN_USER;

        assertThat(expectedMessage, is(actualMessage));
    }
}