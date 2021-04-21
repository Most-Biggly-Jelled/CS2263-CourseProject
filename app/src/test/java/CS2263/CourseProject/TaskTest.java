package CS2263.CourseProject;

/**
@author Madison May
        testing for task
it's basically just a data class, so I don't imagine any of this will fail
        */


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void canGenerateTask() {
        Task testTask = new Task();
        testTask.setDescription("description");
        testTask.setName("name");
        assertEquals("description", testTask.getDescription());
        assertEquals("name", testTask.getName());
        assertNull(testTask.getStatus());
    }
}
