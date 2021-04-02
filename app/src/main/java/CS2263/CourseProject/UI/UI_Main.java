package CS2263.CourseProject.UI;

import CS2263.CourseProject.Task;
import CS2263.CourseProject.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/** @author  Dustin Weber
 * Main UI class. This is the main program window which users see after logging in. */
public class UI_Main implements InterfaceUI
{
    // Variables
    private Stage stage;
    // Reference to controlling UI class.
    private final UI ui;


    // Constructors
    public UI_Main(UI ui) { this.ui = ui; }


    // Methods
    public void show()
    {
        // Elements
        TextField textSearch = new TextField("Search");
        Button buttonFilter = new Button("Filter");
        Button buttonLogout = new Button("Log out");
        Button buttonOptions = new Button("Options");
        ListView<TaskList> lists = new ListView<>();
        ListView<Task> tasks = new ListView<>();
        Button buttonCreateList = new Button("Create List");
        Button buttonEditList = new Button("Edit List");
        Button buttonDeleteList = new Button("Delete");
        Button buttonCreateTask = new Button("Create Task");
        Button buttonEditTask = new Button("Edit Task");
        Button buttonDeleteTask = new Button("Delete");

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(textSearch, 0, 0);
        GridPane.setConstraints(buttonFilter, 1, 0);
        GridPane.setConstraints(buttonLogout, 4, 0);
        GridPane.setConstraints(buttonOptions, 5, 0);
        GridPane.setConstraints(lists, 0, 1);
        GridPane.setConstraints(tasks, 5, 1);
        GridPane.setConstraints(buttonCreateList, 0, 2);
        GridPane.setConstraints(buttonEditList, 1, 2);
        GridPane.setConstraints(buttonDeleteList, 2, 2);
        GridPane.setConstraints(buttonCreateTask, 3, 2);
        GridPane.setConstraints(buttonEditTask, 4, 2);
        GridPane.setConstraints(buttonDeleteTask, 5, 2);
        grid.getChildren().addAll(textSearch, buttonFilter, buttonLogout, buttonOptions, lists,
                tasks, buttonCreateList, buttonEditList, buttonDeleteList, buttonCreateTask, buttonEditTask,
                buttonDeleteTask);

        // Listeners
        buttonFilter.setOnAction(val -> buttonFilter(textSearch.getText()));
        buttonLogout.setOnAction(val -> buttonLogout());
        buttonOptions.setOnAction(val -> buttonOptions());
        buttonCreateList.setOnAction(val -> buttonCreateList());
        buttonEditList.setOnAction(val -> buttonEditList());
        buttonDeleteList.setOnAction(val -> buttonDeleteList());
        buttonCreateTask.setOnAction(val -> buttonCreateTask());
        buttonEditTask.setOnAction(val -> buttonEditTask());
        buttonDeleteTask.setOnAction(val -> buttonDeleteTask());
        lists.setOnMouseClicked(val -> listSelect(lists));
        tasks.setOnMouseClicked(val -> taskSelect(tasks));

        // Scene
        Scene scene = new Scene(grid, 750, 480);
        stage = new Stage();
        stage.setTitle(UI.windowTitle);
        stage.setScene(scene);
        stage.getIcons().add(UI.icon);

        // Final
        stage.show();
    }

    public void close()
    {
        stage.close();
    }

    /** Filter results button is pressed.
     * @param search  String to search for. */
    private void buttonFilter(String search)
    {
        ui.search(search);
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

    /** Create new list button is pressed. */
    private void buttonCreateList()
    {
        ui.openListCreationUI();
    }

    /** Edit current list button is pressed. */
    private void buttonEditList()
    {
        // A list is selected
        if (ui.getCurrentList() != null)
            ui.editList();
        // No list selected
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "No list selected.");
            a.show();
        }
    }

    /** Delete current list button is pressed. */
    private void buttonDeleteList()
    {
        // A list is selected
        if (ui.getCurrentList() != null)
        {
            // Show confirm box to user before deleting
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> ui.deleteList(ui.getCurrentList()));
        }
        // No list selected
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "No list selected.");
            a.show();
        }
    }

    /** Create new task button is pressed. */
    private void buttonCreateTask()
    {
        // Ensure a list is open for the task to become a child of.
        if (ui.getCurrentList() != null)
            ui.openTaskCreationUI();
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select or create a list for the task to be created in first.");
            a.show();
        }
    }

    /** Edit current task button is pressed. */
    private void buttonEditTask()
    {
        // A task is selected
        if (ui.getCurrentTask() != null)
            ui.editTask();
        // No task selected
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "No task selected.");
            a.show();
        }
    }

    /** Delete current task button is pressed. */
    private void buttonDeleteTask()
    {
        // A task is selected
        if (ui.getCurrentTask() != null)
        {
            // Show confirm box to user before deleting
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> ui.deleteTask(ui.getCurrentTask()));
        }
        // No task selected
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "No task selected.");
            a.show();
        }
    }

    /** Task list is selected (clicked).
     * @param list  The ListView to get selection from. */
    private void listSelect(ListView<TaskList> list)
    {
        TaskList currentList = list.getSelectionModel().getSelectedItem();
        ui.setCurrentList(currentList);
    }

    /** Task is selected (clicked).
     * @param task  The ListView to get selection from. */
    private void taskSelect(ListView<Task> task)
    {
        Task currentTask = task.getSelectionModel().getSelectedItem();
        ui.setCurrentTask(currentTask);
        // TODO: Need to do a check if currentTask has subtasks here
        // Open a new UI showing the subtasks if so.
    }
}
