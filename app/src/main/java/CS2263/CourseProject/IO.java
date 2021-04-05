package CS2263.CourseProject;
/*
@author Madison May
 */

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO {
    /*
    This method is able to save user's and their respective task lists all in this one method.

    Each user will be saved under their own JSON file with their name. We need to make sure that users of the same name
    do not exist to avoid writing over another user's data
     */
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

    /*
    Only parameter here is the user's name. Password checking should all be in other methods.
     */

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
