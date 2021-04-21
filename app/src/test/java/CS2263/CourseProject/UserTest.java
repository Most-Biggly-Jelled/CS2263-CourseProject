package CS2263.CourseProject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test void canGenerateUser() {
        User testUser = new User("test_email", "test_password");
        assertEquals(testUser.getEmail(), "test_email");
        assertEquals(testUser.getPassword(), "test_password");
    }
}
