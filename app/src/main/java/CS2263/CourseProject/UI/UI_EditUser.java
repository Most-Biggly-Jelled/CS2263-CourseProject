package CS2263.CourseProject.UI;

import CS2263.CourseProject.Task;
import CS2263.CourseProject.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

/** @author  Dustin Weber
 * UI to allow Admin to edit user credentials. */
public class UI_EditUser implements InterfaceUI
{
    // Variables
    private Stage stage;
    /** User being edited. */
    private final User user;
    /** Reference to controlling UI class. */
    private final UI ui;


    // Constructors
    /** Default constructor.
     * @param user  User to edit. */
    public UI_EditUser(UI ui, User user)
    {
        this.ui = ui;
        this.user = user;
    }

    // Methods
    public void show()
    {
        // Elements
        Button buttonCancel = new Button("Cancel");
        Label labelEmail = new Label("E-mail (Username)");
        TextField textEmail = new TextField();
        Label labelPw = new Label("Password");
        TextField textPw = new TextField();
        Button buttonEdit = new Button("Edit User");

        // Fill fields w. user info.
        textEmail.setText(user.getEmail());
        textPw.setText(user.getPassword());

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(buttonCancel, 2, 0);
        GridPane.setConstraints(labelEmail, 0, 0);
        GridPane.setConstraints(textEmail, 0, 1);
        GridPane.setConstraints(labelPw, 0, 2);
        GridPane.setConstraints(textPw, 0, 3);
        GridPane.setConstraints(buttonEdit, 0, 4);
        grid.getChildren().addAll(buttonCancel, labelEmail, textEmail, labelPw,
                textPw, buttonEdit);

        // Listeners
        buttonCancel.setOnAction(val -> buttonCancel());
        buttonEdit.setOnAction(val ->
                buttonEdit
                        (
                                textEmail.getText(),
                                textPw.getText()
                        ));

        // Scene
        Scene scene = new Scene(grid, 240, 180);
        stage = new Stage();
        stage.setTitle(UI.getWindowTitle() + " - List");
        stage.setScene(scene);
        stage.getIcons().add(UI.getIcon());

        // Final
        stage.show();
    }

    /** Cancel creation/modification button is pressed. */
    private void buttonCancel()
    {
        close();
    }

    /** Edit (user) button is pressed.
     * @param email  Username/email of the user.
     * @param pw  Password of the user. */
    private void buttonEdit(String email, String pw)
    {
        // Ensure all parameters are valid
        if (!email.isEmpty() && !pw.isEmpty())
        {
            User usr = new User(email, pw);
            ui.createUser(usr);

            // Notify user of file save before closing
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("User edited successfully.");
            a.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> close());
        }
        // Invalid parameters
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("All users need a username and password.");
            a.show();
        }
    }


    public void close()
    {
        stage.close();
    }
}
