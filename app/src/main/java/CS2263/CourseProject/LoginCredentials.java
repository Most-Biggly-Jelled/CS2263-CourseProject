package CS2263.CourseProject;

import lombok.Getter;

/** @author  Madison May
 * Verifies user login information. */
public class LoginCredentials {
    @Getter private final String user_name;
    @Getter private final String user_email;
    @Getter private final String user_password;


    public LoginCredentials(String user_name, String user_email, String user_password) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    /** Checks a user name (email) is valid.
     * @param input  User name to validate. */
    public boolean verifyUserName(String input) {
        /* I know the conceptual design for this method said it did not take in any parameters,
        but I think it would be better if we got the input when we actually implement the method.
         */
        return input.equals(user_name);
    }

    /** Checks a user password is valid.
     * @param input  Password to validate. */
    public boolean verifyUserPassword(String input) {
        return input.equals(user_password);
    }

}
