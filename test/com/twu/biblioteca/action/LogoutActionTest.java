package com.twu.biblioteca.action;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.constants.Messages;
import com.twu.biblioteca.controller.LoginMenuExecutor;
import com.twu.biblioteca.listener.LoginListener;
import com.twu.biblioteca.menu.Menu;
import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogoutActionTest {
    @Mock
    BibliotecaApp bibliotecaApp;

    @Mock
    ConsoleView consoleView;

    @Mock
    Authenticator authenticator;

    @Mock
    Menu loginMenu;

    @Mock
    LoginMenuExecutor loginMenuExecutor;

    @Mock
    LoginListener loginListener;

    @Test
    public void shouldBeAbleToDisplayMessageForLogout() {
        LogoutAction logoutAction = new LogoutAction(consoleView, loginMenu, loginMenuExecutor);
        logoutAction.addListener(bibliotecaApp);
        logoutAction.performAction();

        verify(consoleView).print(Messages.LOGOUT_MESSAGE);
    }

    @Test
    public void shouldBeAbleToRedirectToLoginExecutorOnLogout() {
        LogoutAction logoutAction = new LogoutAction(consoleView, loginMenu, loginMenuExecutor);
        logoutAction.addListener(bibliotecaApp);
        logoutAction.performAction();

        verify(bibliotecaApp).update(loginMenu, loginMenuExecutor);
    }


}