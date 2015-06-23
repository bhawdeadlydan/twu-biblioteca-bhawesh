package com.twu.biblioteca.model;

import com.twu.biblioteca.listener.LoginHistoryListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorTest {
    private Authenticator authenticator;
    private ArrayList<User> userList;
    private HashMap<Integer, String[]> userNameAndPasswordMap;

    @Mock
    LoginHistoryListener listener;

    @Before
    public void setUp() {

        userNameAndPasswordMap = new HashMap<Integer, String[]>();
        userNameAndPasswordMap.put(1, new String[]{"111-1111", "librarian123"});
        userNameAndPasswordMap.put(2, new String[]{"222-2222", "user222"});
        userNameAndPasswordMap.put(3, new String[]{"333-3333", "user333"});
        userNameAndPasswordMap.put(4, new String[]{"444-4444", "user444"});
        userNameAndPasswordMap.put(5, new String[]{"555-5555", "user555"});
        userNameAndPasswordMap.put(6, new String[]{"666-6666", "user666"});
        userNameAndPasswordMap.put(7, new String[]{"777-7777", "user777"});
        userNameAndPasswordMap.put(8, new String[]{"888-8888", "user888"});
        authenticator = new Authenticator(userNameAndPasswordMap);
        authenticator.addListener(listener);
        User firstUser = new User("Rajesh", "222-2222", "user222", "rajesh@twu.edu", "9090898909");
        User secondUser = new User("Abhishek", "333-3333", "user333", "abhi@twu.edu", "9090898909");
        User thirdUser = new User("Sukreet", "444-4444", "user444", "awk@twu.edu", "9090898909");
        userList = new ArrayList<User>();
        userList.add(firstUser);
        userList.add(secondUser);
        userList.add(thirdUser);

    }

    @Test
    public void shouldBeAbleToAuthenticateLibrarian() {
        int userParsed = authenticator.authenticate("111-1111", "librarian123");

        assertThat(userParsed, is(1));
    }

    @Test
    public void shouldBeAbleToAuthenticateUser() {
        int userParsed = authenticator.authenticate("222-2222", "user222");

        assertThat(userParsed, is(2));
    }

    @Test
    public void shouldNotBeAbleToAuthenticateInvalidUser() {
        int userParsed = authenticator.authenticate("222-2222", "randompass");

        assertThat(userParsed, is(3));
    }

    @Test
    public void shouldBeAbleToUpdateUserHistoryWithTheCurrentLoggedInUser() {
        int userParsed = authenticator.authenticate("222-2222", "user222");

        verify(listener).updateUser("222-2222");
    }

    @Test
    public void shouldBeAbleToProvideTheCurrentUser() {
        Authenticator authenticator = new Authenticator(userNameAndPasswordMap);
        authenticator.addListener(listener);
        User firstUser = new User("Rajesh", "222-2222", "user222", "rajesh@twu.edu", "9090898909");
        User secondUser = new User("Abhishek", "333-3333", "user333", "abhi@twu.edu", "9090898909");
        User thirdUser = new User("Sukreet", "444-4444", "user444", "awk@twu.edu", "9090898909");
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(firstUser);
        userList.add(secondUser);
        userList.add(thirdUser);
        authenticator.authenticate("222-2222", "user222");
        ArrayList<User> expectedListOfUsers = new ArrayList<User>();
        expectedListOfUsers.add(firstUser);
        ArrayList<User> actualListOfUsers = authenticator.authenticatedUser(userList);

        assertThat(actualListOfUsers, is(expectedListOfUsers));
    }
}