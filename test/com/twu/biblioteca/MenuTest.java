package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    @Test
    public void shouldReturnMenuOptions() {
        Menu menu = new Menu();

        String actualMenu = menu.displayMenu();

        assertThat(actualMenu, is("1. " +Messages.LIST_BOOKS));
    }

}