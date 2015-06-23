package com.twu.biblioteca.action;

import com.twu.biblioteca.model.Authenticator;
import com.twu.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailActionTest {
    private UserDetailAction userDetailAction;
    private ArrayList<User> userList;
    @Mock
    Authenticator authenticator;

    @Before
    public void setUp() {
        User firstUser = new User("Rajesh", "222-2222", "user222", "rajesh@twu.edu", "9090898909");
        User secondUser = new User("Abhishek", "333-3333", "user333", "abhi@twu.edu", "9090898909");
        User thirdUser = new User("Sukreet", "444-4444", "user444", "awk@twu.edu", "9090898909");
        userList = new ArrayList<User>();
        userList.add(firstUser);
        userList.add(secondUser);
        userList.add(thirdUser);
        userDetailAction = new UserDetailAction(userList, authenticator);
    }

    @Test
    public void shouldBeAbleToIdentifyCurrentLoggedInUser() {
        userDetailAction.performAction();

        verify(authenticator).authenticatedUser(userList);
    }
}