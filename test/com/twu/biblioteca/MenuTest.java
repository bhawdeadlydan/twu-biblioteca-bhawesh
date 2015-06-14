package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    private Menu menu;

    @Before
    public void setUp() {
        HashMap<Integer, String> menuCodeMenuText = new HashMap<Integer, String>();
        menuCodeMenuText.put(1, Messages.LIST_BOOKS);
        menu = new Menu(menuCodeMenuText);
    }

    @Test
    public void shouldReturnMenuOptions() {
        String actualMenu = menu.toString();

        assertThat(actualMenu, is("\n" + "1 " + Messages.LIST_BOOKS));
    }
}