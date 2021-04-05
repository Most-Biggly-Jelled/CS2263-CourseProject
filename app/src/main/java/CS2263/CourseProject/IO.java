package CS2263.CourseProject;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** @author Madison May */
public class IO {
    //Only the SaveUser and LoadUser methods should be called outside this class.
    public static void SaveUser(User user) throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(user);

            // TODO: Although it should be "resources/", this causes an error for some reason
            // So I'm leaving it as is for now.
            FileWriter writer = new FileWriter("app/src/main/resources" + user.getEmail() + ".json");
            writer.write(json);


            writer.close();
        } catch (IOException e) {
            System.out.println("IOException on file write.");
        }

    }

    public static User LoadUser(String userName) throws IOException {
        try {
            Gson gson = new Gson();

            // TODO: For some reason this throws IOException when "resources" is "resources/"
            FileReader reader = new FileReader("app/src/main/resources" + userName + ".json");

            return gson.fromJson(reader, User.class);

        } catch (IOException e) {
            System.out.println("IOException on file read.");
        }
        return null;
    }





}
