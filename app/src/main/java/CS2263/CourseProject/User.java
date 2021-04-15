package CS2263.CourseProject;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/** @author  Dustin Weber
 * User object for the application. */
public class User
{
    // Variables
    /** Email (username). */
    @Getter private final String email;
    /** Password for this user. */
    @Getter private final String password;
    /** Biography of the user. */
    @Getter @Setter private String biography;
    /** All task lists belonging to this user. */
    @Getter @Setter private ArrayList<TaskList> taskLists;


    // Constructors
    /** Parameterized constructor.
     * @param email  Email/username to use for this user.
     * @param password  Password to use for this user. */
    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }
}
