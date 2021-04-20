package CS2263.CourseProject.UI;

import CS2263.CourseProject.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/** @author  Dustin Weber
 * Register new user screen. Accessed from UI_Login */
public class UI_RegisterUser implements InterfaceUI
{
    // Variables
    private Stage stage;
    /** Username (e-mail) text field */
    private TextField textEmail;
    /** Password field */
    private PasswordField textPassword;
    /** User biography/description field */
    private TextField textBio;
    /** Reference to login UI. */
    private final UI_Login login;
    /** Reference to UI controller */
    private final UI ui;


    // Constructors
    public UI_RegisterUser(UI_Login login, UI ui)
    {
        this.login = login;
        this.ui = ui;
    }

    // Methods
    public void show()
    {
        // Elements
        Label labelEmail = new Label("E-mail");
        Label labelPassword = new Label("Password");
        Label labelBio = new Label("Biography");
        textEmail = new TextField();
        textPassword = new PasswordField();
        textBio = new TextField();
        Button buttonCreate = new Button("Create");
        Button buttonCancel = new Button("Cancel");

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(labelEmail, 0, 0);
        GridPane.setConstraints(labelPassword, 0, 2);
        GridPane.setConstraints(labelBio, 0, 4);
        GridPane.setConstraints(textEmail, 0, 1);
        GridPane.setConstraints(textPassword, 0, 3);
        GridPane.setConstraints(textBio, 0, 5);
        textBio.setPrefHeight(50f);
        GridPane.setConstraints(buttonCreate, 1, 6);
        GridPane.setConstraints(buttonCancel, 2, 6);
        grid.getChildren().addAll(labelEmail, labelPassword, labelBio, textEmail, textPassword,
                textBio, buttonCreate, buttonCancel);

        // Listeners
        buttonCreate.setOnAction(val -> buttonCreate());
        buttonCancel.setOnAction(val -> buttonCancel());

        // Scene
        Scene scene = new Scene(grid, 280, 190);
        stage = new Stage();
        stage.setTitle("Create New User");
        stage.setScene(scene);
        stage.getIcons().add(UI.getIcon());

        // Listen for input
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (key) ->
        {
            if (key.getCode() == KeyCode.ENTER)
                buttonCreate();
        });

        // Final
        stage.show();
    }

    public void close()
    {
        login.onRegisterClose();
        stage.close();
    }

    /** Checks user's information for errors
     * such as invalid credentials, empty fields, etc.
     * @return True if input is valid. */
    public boolean validateInputs()
    {
        // Invalid input
        if (textEmail.getText().isBlank() || textBio.getText().isBlank() || textPassword.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields.");
            alert.show();
            return false;
        }
        // Valid input
        return true;
    }

    /** Register button is pressed. */
    public void buttonCreate()
    {
        if (validateInputs())
        {
            User user = new User(textEmail.getText(), textPassword.getText());
            ui.createUser(user);
            // Input this information to the login UI screen
            login.getTextEmail().setText(textEmail.getText());
            login.getTextPassword().setText(textPassword.getText());

            close();
        }
    }

    /** Cancel button is pressed. */
    private void buttonCancel()
    {
        close();
    }
}
