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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginMenuExecutorTest {
    @Mock
    HashMap<Integer, MenuAction> menuActionMap;

    ConsoleView consoleView;

    @Test
    public void setUp() {
        consoleView = new ConsoleView(new BufferedReader(new InputStreamReader(System.in)));
    }

    @Test
    public void shouldNotHaveOptionsNotNeededInLoginMenu() {
        LoginMenuExecutor loginMenuExecutor = new LoginMenuExecutor(menuActionMap, consoleView);
        loginMenuExecutor.buildMenu();
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(menuActionMap, times(6)).remove(integerArgumentCaptor.capture());

        List<Integer> capturedIntegers = integerArgumentCaptor.getAllValues();
        List<Integer> expectedIntegers = new ArrayList<Integer>();
        expectedIntegers.add(8);
        expectedIntegers.add(3);
        expectedIntegers.add(4);
        expectedIntegers.add(6);
        expectedIntegers.add(7);
        expectedIntegers.add(11);

        assertThat(capturedIntegers, is(expectedIntegers));
    }

}