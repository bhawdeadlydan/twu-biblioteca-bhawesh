package com.twu.biblioteca.controller;

import com.twu.biblioteca.action.MenuAction;
import com.twu.biblioteca.view.ConsoleView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserMenuExecutorTest {

    @Mock
    HashMap<Integer, MenuAction> menuActionMap;

    ConsoleView consoleView;


    @Test
    public void setUp() {
        consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
    }

    @Test
    public void shouldRemoveLoginOperation() {
        UserMenuExecutor userMenuExecutor = new UserMenuExecutor(menuActionMap, consoleView);
        userMenuExecutor.buildMenu();
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(menuActionMap, times(2)).remove(integerArgumentCaptor.capture());

        List<Integer> capturedIntegers = integerArgumentCaptor.getAllValues();
        Integer actualInteger = capturedIntegers.get(0);
        Integer expectedInteger = 8;

        assertThat(actualInteger, is(expectedInteger));
    }


    @Test
    public void shouldRemoveUserHistoryOperation() {
        UserMenuExecutor userMenuExecutor = new UserMenuExecutor(menuActionMap, consoleView);
        userMenuExecutor.buildMenu();
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(menuActionMap, times(2)).remove(integerArgumentCaptor.capture());

        List<Integer> capturedIntegers = integerArgumentCaptor.getAllValues();
        Integer actualInteger = capturedIntegers.get(1);
        Integer expectedInteger = 11;

        assertThat(actualInteger, is(expectedInteger));
    }


}