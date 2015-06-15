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

    @Test
    public void shouldBeEqualToItself() {
        Book firstBook = new Book("GreatBook", "Conan", 1994);

        boolean actual = firstBook.equals(firstBook);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldFollowSymmetricProperty() {
        Book firstBook = new Book("GreatBook", "Conan", 1994);
        Book secondBook = new Book("GreatBook", "Conan", 1994);

        boolean actual = (firstBook.equals(secondBook) == secondBook.equals(firstBook));

        assertThat(actual, is(true));
    }

    @Test
    public void shouldFollowTransitiveProperty() {
        Book firstBook = new Book("GreatBook", "Conan", 1994);
        Book secondBook = new Book("GreatBook", "Conan", 1994);
        Book thirdBook = new Book("GreatBook", "Conan", 1994);

        boolean actual = firstBook.equals(secondBook) &&
                secondBook.equals(thirdBook) &&
                firstBook.equals(thirdBook);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldHaveSameHashCodeIfBooksAreEqual() {
        Book firstBook = new Book("GreatBook", "Conan", 1994);
        Book secondBook = new Book("GreatBook", "Conan", 1994);

        boolean actual = firstBook.equals(secondBook) && (firstBook.hashCode() == secondBook.hashCode());

        assertThat(actual, is(true));
    }




}