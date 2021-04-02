package CS2263.CourseProject.UI;

import CS2263.CourseProject.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/** @author  Dustin Weber
 * Login screen. Should appear as the first screen the user sees after splash. */
public class UI_Login implements InterfaceUI
{
    // Variables
    private Stage stage;
    // Username (e-mail) text field
    private TextField textEmail;
    private PasswordField textPassword;
    // Reference to controlling UI class.
    private final UI ui;


    // Constructors
    public UI_Login(UI ui) { this.ui = ui; }

    // Methods
    public void show()
    {
        // Elements
        Label labelEmail = new Label("E-mail");
        Label labelPassword = new Label("Password");
        textEmail = new TextField();
        textPassword = new PasswordField();
        Button buttonLogin = new Button("Login");
        Button buttonCreate = new Button("Register");
        Button buttonCancel = new Button("Cancel");

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(labelEmail, 0, 0);
        GridPane.setConstraints(labelPassword, 0, 2);
        GridPane.setConstraints(textEmail, 0, 1);
        GridPane.setConstraints(textPassword, 0, 3);
        GridPane.setConstraints(buttonLogin, 0, 4);
        GridPane.setConstraints(buttonCreate, 1, 4);
        GridPane.setConstraints(buttonCancel, 2, 4);
        grid.getChildren().addAll(labelEmail, labelPassword, textEmail, textPassword, buttonLogin, buttonCreate, buttonCancel);

        // Listeners
        buttonLogin.setOnAction(val -> buttonSubmit());
        buttonCreate.setOnAction(val -> buttonCreate());
        buttonCancel.setOnAction(val -> buttonCancel());

        // Scene
        Scene scene = new Scene(grid, 280, 120);
        stage = new Stage();
        stage.setTitle(UI.windowTitle + " Login");
        stage.setScene(scene);
        stage.getIcons().add(UI.icon);

        // Listen for input
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (key) ->
        {
            if (key.getCode() == KeyCode.ENTER)
                buttonSubmit();
        });

        // Final
        stage.show();
    }

    public void close()
    {
        stage.close();
    }

    /** Called whenever there is a login error
     * such as invalid credentials, empty fields, etc. */
    public void loginError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Could not log in.");
        alert.show();
    }

    /** Submit (login) button is pressed. */
    private void buttonSubmit()
    {
        ui.login(this, textEmail.getText(), textPassword.getText());
    }

    /** Create New User (register) button is pressed. */
    public void buttonCreate()
    {
        // TODO: Add parameters to new user constructor when User is created.
        User user = new User();
        ui.createUser(user);
        // Login as the new user.
        // TODO: Change this to use the user's info rather than directly from fields.
        ui.login(this, textEmail.getText(), textPassword.getText());
    }

    /** Cancel button is pressed. */
    private void buttonCancel()
    {
        ui.quitApplication();
    }
}