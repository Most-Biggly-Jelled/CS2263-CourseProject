package CS2263.CourseProject;
/*
@author Madison May
 */

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IO {
    //Only the SaveUser and LoadUser methods should be called outside this class.
    public static void SaveUser(User user) throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(user);

            FileWriter writer = new FileWriter("app/src/main/resources" + user.getName() + ".json");
            writer.write(json);

            if (user.getTaskList() != null) {
                SaveTaskList(user, user.getTaskList());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("IOException on file write.");
        }

    }

    public static User LoadUser(String userName) throws IOException {
        try {
            Gson gson = new Gson();

            FileReader reader = new FileReader("app/src/main/resources/" + userName + ".json");

            User loadedUser = gson.fromJson(reader, User.class);
            loadedUser.setTaskList(LoadTaskList(userName));

            return loadedUser;

        } catch (IOException e) {
            System.out.println("IOException on file read.");
        }
        return null;
    }

    //private methods below

    private static void SaveTaskList(User user, TaskList taskList) throws IOException {
        try {
            Gson gson = new Gson();


            FileWriter writer = new FileWriter("app/src/main/resources" + user.getName() + "_tasks.json");
            for (Task task: taskList.getTasks()) {
                String json = gson.toJson(task);
                writer.write(json);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("IOException on file write.");
        }

    }

    private static TaskList LoadTaskList(String userName) throws IOException {
        try {
            Gson gson = new Gson();

            FileReader reader = new FileReader("app/src/main/resources/" + userName + "_tasks.json");

            return gson.fromJson(reader, TaskList.class);
        } catch (IOException e) {
            System.out.println("IOException on file read.");
        }
        return null;
    }

    private static void SaveTask(User user, TaskList taskList, Task task) throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(task);

            FileWriter writer = new FileWriter("app/src/main/resources" + user.getName() + "_" + taskList.getName() + "_" + task.getTitle() + ".json");
            writer.write(json);

            writer.close();
        } catch (IOException e) {
            System.out.println("IOException on file write.");
        }

    }


    private static Task LoadTask(String userName, String taskListName, String taskName) throws IOException {
        try {
            Gson gson = new Gson();

            FileReader reader = new FileReader("app/src/main/resources/" + userName + "_" + taskListName + "_" + taskName +".json");

            Task loadedTask = gson.fromJson(reader, Task.class);


        } catch (IOException e) {
            System.out.println("IOException on file read.");
        }
        return null;
    }

    private static void SaveSubtask(User user, TaskList taskList, Task task, Subtask subtask) throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(subtask);

            FileWriter writer = new FileWriter("app/src/main/resources" + user.getName() + "_" + taskList.getName() + "_" + task.getTitle() + "_" + subtask.getTitle() +".json");
            writer.write(json);

            writer.close();
        } catch (IOException e) {
            System.out.println("IOException on file write.");
        }

    }


    private static Subtask LoadSubtask(String userName, String taskListName, String taskName, String subtaskName) throws IOException {
        try {
            Gson gson = new Gson();

            FileReader reader = new FileReader("app/src/main/resources/" + userName + "_" + taskListName + "_" + taskName + "_" + subtaskName +".json");

            return gson.fromJson(reader, Subtask.class);
        } catch (IOException e) {
            System.out.println("IOException on file read.");
        }
        return null;
    }


}
