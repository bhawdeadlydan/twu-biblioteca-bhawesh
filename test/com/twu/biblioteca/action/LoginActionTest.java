package com.twu.biblioteca.action;

import com.twu.biblioteca.constants.Messages;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginActionTest {

    @Mock
    ConsoleView consoleView;

    @Mock
    Authenticator authenticator;

    @Test
    public void shouldPromptUserForUserName() {
        LoginAction loginAction = new LoginAction(consoleView, authenticator);
        loginAction.performAction();

        verify(consoleView).print(Messages.USERNAME_PROMPT);
    }

    @Test
    public void shouldGetUserNameAndPassWord() throws IOException {
        LoginAction loginAction = new LoginAction(consoleView, authenticator);
        loginAction.performAction();

        verify(consoleView,times(2)).getName();
    }

    @Test
    public void shouldBeAbleToAuthenticateUser() {
        LoginAction loginAction = new LoginAction(consoleView, authenticator);
        loginAction.performAction();

        verify(authenticator).authenticate(anyString(), anyString());
    }

    @Test
    public void shouldBeAbleToAlertSuccessfulLogin() {
        when(authenticator.authenticate(anyString(), anyString())).thenReturn(true);
        LoginAction loginAction = new LoginAction(consoleView, authenticator);
        loginAction.performAction();
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleView, times(2)).print(stringArgumentCaptor.capture());
        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.SUCCESSFUL_LOGIN;

        assertThat(expectedMessage, is(actualMessage));
    }

    @Test
    public void shouldBeAbleToAlertUnSuccessfulLogin() {
        when(authenticator.authenticate(anyString(), anyString())).thenReturn(false);
        LoginAction loginAction = new LoginAction(consoleView, authenticator);
        loginAction.performAction();
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleView, times(2)).print(stringArgumentCaptor.capture());
        List<String> capturedStrings = stringArgumentCaptor.getAllValues();

        String actualMessage = capturedStrings.get(1);
        String expectedMessage = Messages.UNSUCCESSFUL_LOGIN;

        assertThat(expectedMessage, is(actualMessage));
    }
}