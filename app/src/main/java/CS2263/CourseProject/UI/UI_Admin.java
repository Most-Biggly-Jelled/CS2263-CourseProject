package CS2263.CourseProject.UI;

import CS2263.CourseProject.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/** @author  Dustin Weber
 * Main Admin UI class. This is the main program window which the admin sees after logging in. */
public class UI_Admin implements InterfaceUI
{
    // Variables
    private Stage stage;
    /** Currently selected user */
    private User selectedUser;
    /** Reference to controlling UI class. */
    private final UI ui;


    // Constructors
    public UI_Admin(UI ui) { this.ui = ui; }

    // Methods
    public void show()
    {
        // Elements
        TextField textSearch = new TextField("Search");
        Button buttonFilter = new Button("Filter");
        Button buttonLogout = new Button("Log out");
        Button buttonOptions = new Button("Options");
        ListView<User> users = new ListView<>();
        Button buttonEditUser = new Button("Modify User");
        Button buttonDeleteUser = new Button("Delete");

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(textSearch, 0, 0);
        GridPane.setConstraints(buttonFilter, 1, 0);
        GridPane.setConstraints(buttonLogout, 2, 0);
        GridPane.setConstraints(buttonOptions, 3, 0);
        GridPane.setConstraints(users, 0, 1);
        GridPane.setConstraints(buttonEditUser, 0, 2);
        GridPane.setConstraints(buttonDeleteUser, 1, 2);
        grid.getChildren().addAll(textSearch, buttonFilter, buttonLogout, buttonOptions, users,
                buttonEditUser, buttonDeleteUser);

        // Listeners
        buttonFilter.setOnAction(val -> buttonFilter(textSearch.getText()));
        buttonLogout.setOnAction(val -> buttonLogout());
        buttonOptions.setOnAction(val -> buttonOptions());
        buttonEditUser.setOnAction(val -> buttonEditUser());
        buttonDeleteUser.setOnAction(val -> buttonDeleteUser());
        users.setOnMouseClicked(val -> userSelect(users));

        // Scene
        Scene scene = new Scene(grid, 720, 480);
        stage = new Stage();
        stage.setTitle(UI.getWindowTitle() + " ADMIN");
        stage.setScene(scene);
        stage.getIcons().add(UI.getIcon());

        // Final
        stage.show();
    }

    /** Filter results button is pressed.
     * @param search  User to search for. */
    private void buttonFilter(String search)
    {
        // TODO: Search users method calls here if implemented
        // Otherwise remove this function and the associated elements for searching.
    }


    /** Logout button is pressed. */
    private void buttonLogout()
    {
        ui.logout();
        stage.close();
    }

    /** Options button is pressed. */
    private void buttonOptions()
    {
        ui.openOptionsUI();
    }

    /** Edit currently selected user button is pressed. */
    private void buttonEditUser()
    {
        // Selection exists
        if (selectedUser != null)
        {
            UI_EditUser editUi = new UI_EditUser(ui, selectedUser);
            editUi.show();
        }
        // No selection
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "No user selected.");
            a.show();
        }
    }

    /** Delete currently selected user button is pressed. */
    private void buttonDeleteUser()
    {
        // Selection exists
        if (selectedUser != null)
        {
            // Show confirm box to user before deleting
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            // TODO: Un-comment the showAndWait and remove show() when Admin is implemented
            // Also the response -> ... must be filled in with the delete function.
            a.show();
            //a.showAndWait()
            //        .filter(response -> response == ButtonType.OK)
            //        .ifPresent(response -> DELETE FUNCTION HERE));
        }
        // No selection
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "No user selected.");
            a.show();
        }
    }

    /** User is selected (clicked).
     * @param users  The ListView to get selection from. */
    private void userSelect(ListView<User> users)
    {
        selectedUser = users.getSelectionModel().getSelectedItem();
        // TODO: Must be connected to Admin class.
    }

    public void close()
    {
        stage.close();
    }
}