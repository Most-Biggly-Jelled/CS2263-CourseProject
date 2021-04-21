package CS2263.CourseProject;

/**
@author Madison May
 testing for user class
 it's basically just a data class, so I don't imagine any of this will fail
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;




public class UserTest {
    @Test
    public void canGenerateUser() {
        User testUser = new User("test_email", "test_password");
        assertEquals(testUser.getEmail(), "test_email");
        assertEquals(testUser.getPassword(), "test_password");
    }

    @Test
    public void testUserBiography() {
        User testUser = new User("test_email", "test_password");
        testUser.setBiography("biography");
        assertEquals(testUser.getBiography(), "biography");
    }

    @Test
    public void testUserTaskLists() {
        User testUser = new User("test_email", "test_password");
        ArrayList<TaskList> testArrayList = new ArrayList<>();
        testUser.setTaskLists(testArrayList);
        assertEquals(testUser.getTaskLists(), testArrayList);
    }
}