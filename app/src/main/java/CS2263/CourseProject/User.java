package CS2263.CourseProject;

/** @author  Dustin Weber
 * User object for the application. */
public class User
{
    // Variables
    /** Email (username). */
    private final String email;
    /** Password for this user. */
    private final String password;


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
    /** Returns email (username) of the user. */
    public String getEmail() { return email; }
    /** Returns password of the user. */
    public String getPassword() { return password; }
}
