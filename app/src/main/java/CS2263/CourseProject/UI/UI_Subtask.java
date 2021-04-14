package CS2263.CourseProject.UI;

import CS2263.CourseProject.Subtask;
import CS2263.CourseProject.Task;
import CS2263.CourseProject.User;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/** @author  Dustin Weber
 * Used alongside UI_Main to show subtasks for the currently selected task. */
public class UI_Subtask implements InterfaceUI
{
    // Variables
    private Stage stage;
    /** Currently selected user */
    private User selectedUser;
    /** Subtask list view display */
    private ListView<Subtask> subtasks;
    /** Task to display subtasks of. */
    private Task task;
    /** Reference to main UI class. */
    private final UI_Main main;


    // Constructors
    /** Creates a new subtask UI
     * @param main  Main UI class this UI is working alongside.
     * @param task  Task to show subtasks for. */
    public UI_Subtask(UI_Main main, Task task)
    {
        this.main = main;
        this.task = task;
    }

    // Setters
    /** Set task to show subtasks for to a new task.
     * @param task  Task to show subtasks of. */
    public void setTask(Task task)
    {
        this.task = task;
        updateSubtaskView();
    }

    // Methods
    public void show()
    {
        // Elements
        subtasks = new ListView<>();

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(subtasks, 0, 0);
        grid.getChildren().addAll(subtasks);

        // Scene
        Scene scene = new Scene(grid, 480, 480);
        stage = new Stage();
        stage.setTitle(UI.getWindowTitle() + " Subtasks");
        stage.setScene(scene);
        stage.getIcons().add(UI.getIcon());

        // Listen for event of closing this UI
        stage.setOnHiding(event -> Platform.runLater(main::onSubtaskClose));

        // Final
        stage.show();
    }

    /** Updates subtask list view to reflect changes in the task to display subtasks of. */
    private void updateSubtaskView()
    {
        // Clear ListView of contents
        subtasks.getItems().clear();

        // Show all subtasks of the current task
        if (task.getSubtasks() != null && task.getSubtasks().size() > 0)
            for (Subtask sub : task.getSubtasks())
                subtasks.getItems().add(sub);
    }

    public void close()
    {
        stage.close();
    }
}
