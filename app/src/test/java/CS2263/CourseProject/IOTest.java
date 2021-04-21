package CS2263.CourseProject;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static CS2263.CourseProject.IO.LoadUser;
import static CS2263.CourseProject.IO.SaveUser;
import static org.junit.jupiter.api.Assertions.*;

/**
 @author Madison May
 testing for IO class

 */


public class IOTest {

    @SneakyThrows
    @Test public void canSaveAndLoadUser() {
        User testUser = new User("email", "pass");
        SaveUser(testUser);
        User loadedUser = LoadUser("email");
        assert loadedUser != null;
        assertEquals(testUser.getEmail(), loadedUser.getEmail());
        assertEquals(testUser.getPassword(), loadedUser.getPassword());
    }
    @SneakyThrows
    @Test public void loadedUsersHaveTasks() {
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

        SaveUser(testUser);
        User newUser = LoadUser("test_email");

        assertNotNull(newUser);
        assertEquals("quest", newUser.getTaskLists().get(0).getSections().get(0).getTasks().get(0).getName());

    }

}
