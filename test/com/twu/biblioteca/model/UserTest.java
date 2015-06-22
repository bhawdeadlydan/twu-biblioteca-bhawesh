package com.twu.biblioteca.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {
    @Test
    public void shouldBeAbleGiveAVisualRepresentationOfUserDetails() {
        User user = new User("Rajeev", "888-8888", "rogerpass", "raj@gmail.com", "9898000098");

        String actualOutput = user.toString();
        String expectedOutput = "userName='888-8888', name='Rajeev', password='rogerpass', phone='9898000098', email='raj@gmail.com'";
        assertThat(actualOutput, is(expectedOutput));
    }
}