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

    @Test
    public void shouldBeEqualToItself() {
        User firstUser = new User("Rajeev","888-8888","rogerpass", "raj@gmail.com", "9898000098");

        boolean actual = firstUser.equals(firstUser);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldFollowSymmetricProperty() {
        User firstUser = new User("Rajeev","888-8888","rogerpass", "raj@gmail.com", "9898000098");
        User secondUser = new User("Rajeev","888-8888","rogerpass", "raj@gmail.com", "9898000098");

        boolean actual = (firstUser.equals(secondUser) == secondUser.equals(firstUser));

        assertThat(actual, is(true));
    }

    @Test
    public void shouldFollowTransitiveProperty() {
        User firstUser = new User("Rajeev","888-8888","rogerpass", "raj@gmail.com", "9898000098");
        User secondUser = new User("Rajeev","888-8888","rogerpass", "raj@gmail.com", "9898000098");
        User thirdUser = new User("Rajeev","888-8888","rogerpass", "raj@gmail.com", "9898000098");

        boolean actual = firstUser.equals(secondUser) &&
                secondUser.equals(thirdUser) &&
                firstUser.equals(thirdUser);

        assertThat(actual, is(true));
    }

    @Test
    public void shouldHaveSameHashCodeIfUsersAreEqual() {
        User firstUser = new User("Rajeev","888-8888","rogerpass", "raj@gmail.com", "9898000098");
        User secondUser = new User("Rajeev","888-8888","rogerpass", "raj@gmail.com", "9898000098");

        boolean actual = firstUser.equals(secondUser) && (firstUser.hashCode() == secondUser.hashCode());

        assertThat(actual, is(true));
    }

    @Test
    public void shouldBeAbleToIdentifyUserEqualityIfUsersHaveSameUserName() {
        User firstUser = new User("Rajeev","888-8888","rogerpass", "raj@gmail.com", "9898000098");

        boolean actual = firstUser.isSameAs("888-8888");

        assertThat(actual, is(true));
    }
}