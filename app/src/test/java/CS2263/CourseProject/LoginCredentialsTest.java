package CS2263.CourseProject;

/**
 @author Madison May
 testing for user login credentials class
 it's basically just a data class, so I don't imagine any of this will fail
 */


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class LoginCredentialsTest {
    @Test public void canVerifyPassword() {
        LoginCredentials testLoginCredentials = new LoginCredentials("username", "super_secret_email", "super_secret_password");
        assertFalse(testLoginCredentials.verifyUserPassword("wrong_password"));
        assertTrue(testLoginCredentials.verifyUserPassword("super_secret_password"));
    }

    @Test public void canVerifyName() {
        LoginCredentials testLoginCredentials = new LoginCredentials("username", "super_secret_email", "super_secret_password");
        assertFalse(testLoginCredentials.verifyUserName("wrong username"));
        assertTrue(testLoginCredentials.verifyUserName("username"));
    }

}
