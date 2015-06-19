package com.twu.biblioteca.action;

import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

}