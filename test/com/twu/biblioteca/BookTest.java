package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BookTest {
    @Test
    public void toStringShouldReturnNameAuthorAndPublicationYear() {
        Book book = new Book("GreatBook", "Conan", 1994);

        String actualBookToString = book.toString();

        assertThat(actualBookToString, is("\nGreatBook\tConan\t1994"));
    }



}