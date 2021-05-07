package CS2263.CourseProject;


import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 @author Madison May
 testing for user class
 it's basically just a data class, so I don't imagine any of this will fail
 */



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

    @Test public void canAccessTasks() {
        User testUser = new User("test_email", "test_password");
        TaskList testTaskList = new TaskList();
        TaskListSection testTaskListSection = new TaskListSection();
        Task testTask = new Task();

        testTask.setName("quest");

        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(testTask);
        testTaskListSection.setTasks(taskArrayList);

        ArrayList<TaskListSection> taskListSectionArrayList = new ArrayList<>();
        taskListSectionArrayList.add(testTaskListSection);
        testTaskList.setSections(taskListSectionArrayList);

        ArrayList<TaskList> taskListArrayList = new ArrayList<>();
        taskListArrayList.add(testTaskList);
        testUser.setTaskLists(taskListArrayList);

        assertEquals("quest", testUser.getTaskLists().get(0).getSections().get(0).getTasks().get(0).getName());

        //Why is this so freaking complicated???
    }
}