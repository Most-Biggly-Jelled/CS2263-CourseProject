package CS2263.CourseProject.UI;

import CS2263.CourseProject.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Getter;

/** @author  Dustin Weber
 * Login screen. Should appear as the first screen the user sees after splash. */
public class UI_Login implements InterfaceUI
{
    /** Variables */
    private Stage stage;
    /** Username (e-mail) text field */
    @Getter private TextField textEmail;
    /** Password field */
    @Getter private PasswordField textPassword;
    /** Reference to currently open register UI window.
     * Only 0-1 of these windows should be open at any time. */
    private UI_RegisterUser register;
    /** Reference to controlling UI class. */
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
        stage.setTitle(UI.getWindowTitle() + " Login");
        stage.setScene(scene);
        stage.getIcons().add(UI.getIcon());

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
        User user = new User(textEmail.getText(), textPassword.getText());
        ui.login(this, user);
    }

    /** Create New User (register) button is pressed. */
    public void buttonCreate()
    {
        // Open UI_RegisterUser if not already open
        if (register == null)
        {
            register = new UI_RegisterUser(this, ui);
            register.show();
        }
    }

    /** Cancel button is pressed. */
    private void buttonCancel()
    {
        ui.quitApplication();
    }

    /** Call when register UI is being closed to set reference to null. */
    public void onRegisterClose()
    {
        register = null;
    }
}