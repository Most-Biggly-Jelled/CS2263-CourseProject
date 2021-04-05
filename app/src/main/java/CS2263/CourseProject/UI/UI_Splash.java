package CS2263.CourseProject.UI;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/** @author  Dustin Weber
 * Splash screen to be shown at program start. */
public class UI_Splash implements InterfaceUI
{
    // Variables
    private Stage stage;
    // Reference to controlling UI class.
    private final UI ui;


    // Constructors
    /** Default constructor.
     * @param ui  Reference to controlling UI class. */
    public UI_Splash(UI ui) { this.ui = ui; }

    // Methods
    public void show()
    {
        // Elements
        ImageView img = new ImageView(new Image("file:splash.png"));

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(img, 0, 0);
        grid.getChildren().addAll(img);

        // Scene
        Scene scene = new Scene(grid, 720, 480);
        scene.setFill(Color.TRANSPARENT);

        stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(UI.getIcon());
        stage.initStyle(StageStyle.TRANSPARENT);

        // Time before splash screen switches to next screen.
        FadeTransition fade = new FadeTransition(Duration.seconds(3), grid);
        fade.setOnFinished((event -> transition()));
        fade.play();

        // Final
        stage.show();
    }

    /** Call when splash screen is done displaying to transition to login UI. */
    private void transition()
    {
        ui.onSplashFinish();
        close();
    }

    public void close()
    {
        stage.close();
    }
}
