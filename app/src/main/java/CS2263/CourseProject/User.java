package CS2263.CourseProject;

import java.util.ArrayList;

/** @author  Dustin Weber
 * User object for the application. */
public class User
{
    // Variables
    /** Email (username). */
    private final String email;
    /** Password for this user. */
    private final String password;
    /** All task lists belonging to this user. */
    private ArrayList<TaskList> taskLists;


    // Constructors
    /** Parameterized constructor.
     * @param email  Email/username to use for this user.
     * @param password  Password to use for this user. */
    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    // Getters
    /** @return email (username) of the user. */
    public String getEmail() { return email; }
    /** @return password of the user. */
    public String getPassword() { return password; }
    /** @return Task lists array */
    public ArrayList<TaskList> getTaskLists() { return taskLists; }

    // Setters
    /** Sets user task lists.
     * @param taskLists  ArrayList of task lists to set to. */
    public void setTaskLists(ArrayList<TaskList> taskLists) { this.taskLists = taskLists; }
}
