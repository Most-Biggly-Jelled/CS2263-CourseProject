package CS2263.CourseProject.UI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/** @author  Dustin Weber
 * Options UI class. This shows user program preferences and allows user to modify them. */
public class UI_Options implements InterfaceUI
{
    // Variables
    private Stage stage;
    // Reference to controlling UI class.
    private final UI ui;


    // Constructors
    public UI_Options(UI ui) { this.ui = ui; }


    // Methods
    public void show()
    {
        // Elements
        Label labelOptions = new Label("Options");

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(labelOptions, 0, 0);
        grid.getChildren().addAll(labelOptions);

        // Scene
        Scene scene = new Scene(grid, 720, 480);
        stage = new Stage();
        stage.setTitle(UI.getWindowTitle() + " Options");
        stage.setScene(scene);
        stage.getIcons().add(UI.getIcon());

        // Final
        stage.show();
    }

    public void close()
    {
        stage.close();
    }
}
