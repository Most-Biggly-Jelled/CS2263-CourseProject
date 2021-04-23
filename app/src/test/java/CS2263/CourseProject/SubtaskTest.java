package CS2263.CourseProject;

/**
 @author Madison May
 testing for subtask
 it's basically just a data class, so I don't imagine any of this will fail
 */


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SubtaskTest {
    @Test public void subtaskParentTask() {
        Task testParent = new Task();
        Subtask testSubtask = new Subtask(testParent);
        assertEquals(testParent, testSubtask.getParentTask());
    }
}
