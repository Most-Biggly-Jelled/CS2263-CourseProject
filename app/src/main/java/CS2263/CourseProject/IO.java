package CS2263.CourseProject;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IO {
    public void SaveUser(User user) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(user);

        FileWriter writer = new FileWriter("app/src/main/resources/users");
        writer.write(json);

        writer.close();

    }
    public void SaveList(TaskList list) {

    }
    public void SaveTask(Task task) {

    }

    public User LoadUser(String name) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("app/src/main/resources/users");
        reader.read();
        User users = gson.fromJson(reader, User);





        return users;

    }

    public List<User> LoadAllUsers() {

    }

    /*
    There should be a method here called LoadList, but I do not know what whoever wrote up the class diagrams was
    wanting from it.
     */

    public Task LoadTask() {

    }
}
