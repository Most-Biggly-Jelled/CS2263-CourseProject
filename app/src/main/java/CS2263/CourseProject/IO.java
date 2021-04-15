package CS2263.CourseProject;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** @author Madison May */
public class IO
{
    // Variables
    /** Directory to get user data from */
    private static final String userDir = "./config/";


    /*
    This method is able to save user's and their respective task lists all in this one method.

    Each user will be saved under their own JSON file with their name. We need to make sure that users of the same name
    do not exist to avoid writing over another user's data
     */
    public static void SaveUser(User user) throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(user);

            // TODO: Although it should be "resources/", this causes an error for some reason
            // So I'm leaving it as is for now.
            FileWriter writer = new FileWriter(userDir + user.getEmail() + ".json");
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

            // TODO: For some reason this throws IOException when "resources" is "resources/"
            FileReader reader = new FileReader(userDir + userName + ".json");

            return gson.fromJson(reader, User.class);

        } catch (IOException e) {
            System.out.println("IOException on file read.");
        }
        return null;
    }

    private static void checkDir()
    {
        //
    }
}
