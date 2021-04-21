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
//    @Test public void loadedUsersHaveTasks() {
//        User testUser = new User("email", "pass");
//        ArrayList<TaskList> testArrayList = new ArrayList<>();
//        ArrayList<Task> taskArrayList = new ArrayList<>();
//
//        Task testTask = new Task();
//        testTask.setName("task_name");
//
//        taskArrayList.add(testTask);
//        TaskList taskList = new TaskList();
//        taskList.setSections(taskArrayList);
//
//        testArrayList.add(taskList);
//        testUser.setTaskLists(testArrayList);
//
//        assertEquals("task_name", testUser.getTaskLists().get(0).getSections().get(0).getName());
//
//    }

}
