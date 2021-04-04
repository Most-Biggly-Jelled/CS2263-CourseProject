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


            writer.close();
        } catch (IOException e) {
            System.out.println("IOException on file write.");
        }

    }

    public static User LoadUser(String userName) throws IOException {
        try {
            Gson gson = new Gson();

            FileReader reader = new FileReader("app/src/main/resources/" + userName + ".json");

            return gson.fromJson(reader, User.class);

        } catch (IOException e) {
            System.out.println("IOException on file read.");
        }
        return null;
    }





}
