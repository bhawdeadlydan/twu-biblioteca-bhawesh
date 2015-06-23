package com.twu.biblioteca.action;

import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailActionTest {
    private ByteArrayOutputStream outputStream;
    private UserDetailAction userDetailAction;
    private ArrayList<User> userList;
    private User firstUser, secondUser, thirdUser;
    @Mock
    Authenticator authenticator;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        firstUser = new User("Rajesh", "222-2222", "user222", "rajesh@twu.edu", "9090898909");
        secondUser = new User("Abhishek", "333-3333", "user333", "abhi@twu.edu", "9090898909");
        thirdUser = new User("Sukreet", "444-4444", "user444", "awk@twu.edu", "9090898909");
        userList = new ArrayList<User>();
        userList.add(firstUser);
        userList.add(secondUser);
        userList.add(thirdUser);
        ConsoleView consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
        userDetailAction = new UserDetailAction(userList, authenticator, consoleView);
    }

    @Test
    public void shouldBeAbleToIdentifyCurrentLoggedInUser() {
        userDetailAction.performAction();

        verify(authenticator).authenticatedUser(userList);
    }

    @Test
    public void shouldBeAbleToDisplayUserDetailOfCurrentLoggedInUser() {
        ArrayList<User> loggedInUserInUserList = new ArrayList<User>();
        loggedInUserInUserList.add(firstUser);
        when(authenticator.authenticatedUser(userList)).
                thenReturn(loggedInUserInUserList);
        userDetailAction.performAction();
        String actualOutput = outputStream.toString();
        String expectedOutput = "userName='222-2222', name='Rajesh', password='user222', phone='9090898909', email='rajesh@twu.edu'\n";

        assertThat(actualOutput, is(expectedOutput));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
}