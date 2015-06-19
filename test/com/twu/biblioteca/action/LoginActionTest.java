package com.twu.biblioteca.action;

import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginActionTest {

    @Mock
    ConsoleView consoleView;

    @Test
    public void shouldPromptUserForUserName() {
        LoginAction loginAction = new LoginAction(consoleView);
        loginAction.performAction();

        verify(consoleView).print(Messages.USERNAME_PROMPT);
    }

    @Test
    public void shouldGetUserNameAndPassWord() throws IOException {
        LoginAction loginAction = new LoginAction(consoleView);
        loginAction.performAction();

        verify(consoleView,times(2)).getName();
    }

}