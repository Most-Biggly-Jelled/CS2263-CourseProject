package CS2263.CourseProject;

import CS2263.CourseProject.UI.UI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage stage)
    {
        /* Empty method needs to be here for JavaFX.
         * Actual UI is displayed via UI class. */
    }

    public static void main(String[] args)
    {
        // Initialize UI
        Platform.runLater(() ->
        {
            UI ui = new UI();
            ui.onStart();
        });
        launch(args);
    }
}
