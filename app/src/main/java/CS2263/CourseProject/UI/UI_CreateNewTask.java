package CS2263.CourseProject.UI;

import CS2263.CourseProject.Subtask;
import CS2263.CourseProject.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDate;

/** @author  Dustin Weber
 * Task creation/modification UI. */
public class UI_CreateNewTask implements InterfaceUI
{
    // Variables
    private Stage stage;
    /** Task to edit. Null if a new task is being created from this UI. */
    private final Task task;
    /** Reference to main UI. */
    private final UI_Main main;
    /** Reference to controlling UI class. */
    private final UI ui;


    // Constructors
    /** Parameterized constructor. Use this when modifying an existing task.
     * @param ui  Reference to controlling UI class.
     * @param main  Reference to main UI.
     * @param task  Existing task to modify. */
    public UI_CreateNewTask(UI ui, UI_Main main, Task task)
    {
        this.ui = ui;
        this.main = main;
        this.task = task;
    }

    // Methods
    public void show()
    {
        // Elements
        Button buttonCancel = new Button("Cancel");
        Label labelTitle = new Label("Title");
        TextField textTitle = new TextField();
        Label labelDate = new Label("Date");
        DatePicker date = new DatePicker();
        Label labelPriority = new Label("Priority");
        ComboBox<String> priority = new ComboBox<>();
        priority.getItems().addAll("Highest", "High", "Medium", "Low");
        priority.setValue("Highest");
        Label labelDescription = new Label("Description");
        TextField textDescription = new TextField();
        Button buttonSubtask = new Button("Create as Subtask");
        Button buttonCreate;
        // Set buttonCreate text based on modification/creation state
        if (task != null)
            buttonCreate = new Button("Update Task");
        else
            buttonCreate = new Button("Create Task");

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(buttonCancel, 2, 0);
        GridPane.setConstraints(labelTitle, 0, 0);
        GridPane.setConstraints(textTitle, 0, 1);
        GridPane.setConstraints(labelDate, 0, 2);
        GridPane.setConstraints(date, 0, 3);
        GridPane.setConstraints(labelPriority, 0, 4);
        GridPane.setConstraints(priority, 0, 5);
        GridPane.setConstraints(labelDescription, 0, 6);
        GridPane.setConstraints(textDescription, 0, 7);
        GridPane.setConstraints(buttonSubtask, 0, 8);
        GridPane.setConstraints(buttonCreate, 0, 9);
        grid.getChildren().addAll(buttonCancel, labelTitle, textTitle, labelDate, date,
                labelPriority, priority, labelDescription, textDescription, buttonSubtask, buttonCreate);

        // Listeners
        buttonCancel.setOnAction(val -> buttonCancel());
        buttonCreate.setOnAction(val ->
                buttonCreate
                        (
                                textTitle.getText(),
                                date.getValue(),
                                textDescription.getText(),
                                priority.getValue()
                        ));
        buttonSubtask.setOnAction(val ->
                buttonSubtask
                        (
                                textTitle.getText(),
                                date.getValue(),
                                textDescription.getText()
                        ));

        // Scene
        Scene scene = new Scene(grid, 240, 230);
        stage = new Stage();
        stage.setTitle(UI.getWindowTitle() + " - Task");
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

    /** Create as Subtask button is pressed.
     * @param title  Title the new subtask should take.
     * @param date  Due date of the subtask.
     * @param description  Description of the subtask. */
    private void buttonSubtask(String title, LocalDate date, String description)
    {
        // Ensure all parameters are valid
        if (!title.isEmpty() && date != null && !description.isEmpty() && task != null)
        {
            Subtask newTask = new Subtask(ui.getCurrentTask());
            ui.createSubtask(task, newTask);

            // Notify user of file save before closing
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Subtask added successfully.");
            a.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> close());
        }
        // Invalid parameters
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Subtasks require a title, date, priority, description, and parent task selected.");
            a.show();
        }
    }

    /** Create button is pressed.
     * @param title  Title the new task should take.
     * @param date  Due date of the task.
     * @param description  Description of the task.
     * @param priority  Priority of the task. (High, low, etc.) */
    private void buttonCreate(String title, LocalDate date, String description, String priority)
    {
        // Ensure all parameters are valid
        if (!title.isEmpty() && date != null && !description.isEmpty())
        {
            Task newTask = new Task
                    (
                            ui.getCurrentList().toString(),
                            date.toString(),
                            title,
                            description,
                            "Incomplete",
                            null,
                            priority,
                            ui.getCurrentList()
                    );
            // Call create or edit function based on context
            if (task == null)
                ui.createTask(newTask);
            else
                ui.editTask(newTask);

            // Notify user of file save before closing
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Task saved successfully.");
            a.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> close());
        }
        // Invalid parameters
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Tasks require a title, due date, priority and description.");
            a.show();
        }
    }

    public void close()
    {
        main.populateListViews();
        stage.close();
    }
}
