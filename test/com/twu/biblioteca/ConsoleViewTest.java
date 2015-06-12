package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleViewTest {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {

    }

    @Test
    public void shouldShowTheWelcomeMessage() {
        ConsoleView consoleView = new ConsoleView();

        consoleView.print(Messages.WELCOME_MESSAGE);
        String actualOutput = outputStream.toString();

        assertThat(actualOutput, is(Messages.WELCOME_MESSAGE));
    }

}