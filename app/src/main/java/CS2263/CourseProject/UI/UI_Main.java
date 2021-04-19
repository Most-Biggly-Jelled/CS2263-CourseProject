package CS2263.CourseProject.UI;

import CS2263.CourseProject.Task;
import CS2263.CourseProject.TaskList;
import CS2263.CourseProject.TaskListSection;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/** @author  Dustin Weber
 * Main UI class. This is the main program window which users see after logging in. */
public class UI_Main implements InterfaceUI
{
    // Variables
    private Stage stage;
    /** Reference to controlling UI class. */
    private final UI ui;
    private UI_Subtask subtaskUI;
    // List views
    private ListView<TaskList> lists;
    private ListView<TaskListSection> sections;
    private ListView<Task> tasks;


    // Constructors
    public UI_Main(UI ui) { this.ui = ui; }


    // Methods
    public void show()
    {
        // Elements
        TextField textSearch = new TextField();
        textSearch.setPromptText("Search");
        Button buttonFilter = new Button("Filter");
        Button buttonLogout = new Button("Log out");
        Button buttonOptions = new Button("Options");
        lists = new ListView<>();
        sections = new ListView<>();
        tasks = new ListView<>();
        Button buttonCreateList = new Button("Create List");
        Button buttonEditList = new Button("Edit List");
        Button buttonDeleteList = new Button("Delete List");

        Button buttonCreateSec = new Button("Create Section");
        Button buttonEditSec = new Button("Edit Section");
        Button buttonDeleteSec = new Button("Delete Section");

        Button buttonCreateTask = new Button("Create Task");
        Button buttonEditTask = new Button("Edit Task");
        Button buttonDeleteTask = new Button("Delete Task");

        populateListViews();

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(textSearch, 0, 0);
        GridPane.setConstraints(buttonFilter, 1, 0);
        GridPane.setConstraints(buttonLogout, 7, 0);
        GridPane.setConstraints(buttonOptions, 8, 0);
        GridPane.setConstraints(lists, 0, 1);
        GridPane.setConstraints(sections, 3, 1);
        GridPane.setConstraints(tasks, 6, 1);
        GridPane.setConstraints(buttonCreateList, 0, 2);
        GridPane.setConstraints(buttonEditList, 1, 2);
        GridPane.setConstraints(buttonDeleteList, 2, 2);
        GridPane.setConstraints(buttonCreateSec, 3, 2);
        GridPane.setConstraints(buttonEditSec, 4, 2);
        GridPane.setConstraints(buttonDeleteSec, 5, 2);
        GridPane.setConstraints(buttonCreateTask, 6, 2);
        GridPane.setConstraints(buttonEditTask, 7, 2);
        GridPane.setConstraints(buttonDeleteTask, 8, 2);
        grid.getChildren().addAll(textSearch, buttonFilter, buttonLogout, buttonOptions, lists,
                sections, tasks,
                buttonCreateList, buttonEditList, buttonDeleteList,
                buttonCreateSec, buttonEditSec, buttonDeleteSec,
                buttonCreateTask, buttonEditTask, buttonDeleteTask);

        // Scene
        Scene scene = new Scene(grid, 1200, 500);
        stage = new Stage();
        stage.setTitle(UI.getWindowTitle());
        stage.setScene(scene);
        stage.getIcons().add(UI.getIcon());

        // Listeners
        buttonFilter.setOnAction(val -> buttonFilter(textSearch.getText()));
        buttonLogout.setOnAction(val -> buttonLogout());
        buttonOptions.setOnAction(val -> buttonOptions());

        buttonCreateList.setOnAction(val -> buttonCreateList());
        buttonEditList.setOnAction(val -> buttonEditList());
        buttonDeleteList.setOnAction(val -> buttonDeleteList());

        buttonCreateSec.setOnAction(val -> buttonCreateSection());
        buttonEditSec.setOnAction(val -> buttonEditSection());
        buttonDeleteSec.setOnAction(val -> buttonDeleteSection());

        buttonCreateTask.setOnAction(val -> buttonCreateTask());
        buttonEditTask.setOnAction(val -> buttonEditTask());
        buttonDeleteTask.setOnAction(val -> buttonDeleteTask());

        lists.setOnMouseClicked(val -> listSelect(lists, sections, tasks));
        sections.setOnMouseClicked(val -> sectionSelect(sections, tasks));
        tasks.setOnMouseClicked(val -> taskSelect(tasks));

        // Final
        stage.show();
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
        ui.openListCreationUI(null);
    }
    /** Edit current list button is pressed. */
    private void buttonEditList()
    {
        // A list is selected
        if (ui.getCurrentList() != null)
            ui.openListCreationUI(ui.getCurrentList());
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

    /** Create Section button is pressed. */
    private void buttonCreateSection()
    {
        // Ensure a list is open for the section to become a child of.
        if (ui.getCurrentList() != null)
            ui.openSectionCreationUI(null);
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select or create a list for the section to be created in first.");
            a.show();
        }
    }
    /** Edit Section button is pressed. */
    private void buttonEditSection()
    {
        // A section is selected
        if (ui.getCurrentSection() != null)
            ui.openSectionCreationUI(ui.getCurrentSection());
        // No section selected
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "No section selected.");
            a.show();
        }
    }
    /** Delete Section button is pressed. */
    private void buttonDeleteSection()
    {
        // Selection
        if (ui.getCurrentSection() != null)
        {
            // Show confirm box to user before deleting
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> ui.deleteSection(ui.getCurrentSection()));
        }
        // No selection
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "No section selected.");
            a.show();
        }
    }


    /** Create new task button is pressed. */
    private void buttonCreateTask()
    {
        // Ensure a section is open for the task to become a child of.
        if (ui.getCurrentSection() != null)
            ui.openTaskCreationUI(null);
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select or create a section for the task to be created in first.");
            a.show();
        }
    }
    /** Edit current task button is pressed. */
    private void buttonEditTask()
    {
        // A task is selected
        if (ui.getCurrentTask() != null)
            ui.openTaskCreationUI(ui.getCurrentTask());
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
     * @param list  The ListView to get selection from.
     * @param tasks  ListView to display all tasks belonging to this Task List. */
    private void listSelect(ListView<TaskList> list, ListView<TaskListSection> sections, ListView<Task> tasks)
    {
        TaskList currentList = list.getSelectionModel().getSelectedItem();

        // Ensure a list was selected and not empty space
        if (currentList != null)
        {
            ui.setCurrentList(currentList);

            // Clear tasks and sections ListView of contents
            tasks.getItems().clear();
            sections.getItems().clear();

            // Show all sections of the current list
            if (currentList.getSections() != null && currentList.getSections().size() > 0)
                for (TaskListSection section : currentList.getSections())
                    sections.getItems().add(section);
        }
    }

    /** Task list section is clicked.
     * @param section  ListView to get selection from. */
    private void sectionSelect(ListView<TaskListSection> section, ListView<Task> tasks)
    {
        TaskListSection currentSection = section.getSelectionModel().getSelectedItem();

        if (currentSection != null)
        {
            ui.setCurrentSection(currentSection);

            if (currentSection.getTasks() != null)
            {
                // Clear tasks ListView of contents
                tasks.getItems().clear();

                // Show tasks of current section
                if (currentSection.getTasks() != null && currentSection.getTasks().size() > 0)
                    for (Task task : currentSection.getTasks())
                        tasks.getItems().add(task);
            }
        }
    }

    /** Task is selected (clicked).
     * @param task  The ListView to get selection from. */
    private void taskSelect(ListView<Task> task)
    {
        Task currentTask = task.getSelectionModel().getSelectedItem();

        if (currentTask != null)
        {
            ui.setCurrentTask(currentTask);

            // Open subtask UI if selected task has subtasks & subtask UI isn't already open
            if (currentTask.getSubtasks() != null
                    && currentTask.getSubtasks().size() > 0
                    && subtaskUI == null)
                subtaskUI = ui.openSubtaskUI(this, currentTask);
                // If subtask UI is already open the update it to show the newly selected task
            else if (subtaskUI != null)
                subtaskUI.setTask(currentTask);
        }
    }

    /** Call when the subtask UI is closed to set reference to null. */
    public void onSubtaskClose()
    {
        subtaskUI = null;
    }

    /** Populates list views with their respective objects */
    public void populateListViews()
    {
        // Clear all of contents
        lists.getItems().clear();
        sections.getItems().clear();
        tasks.getItems().clear();

        ArrayList<TaskList> userLists = ui.getCurrentUser().getTaskLists();

        // Populate lists
        if (userLists != null)
            for (TaskList tl : userLists)
                lists.getItems().add(tl);

        // Populate sections
        if (ui.getCurrentList() != null)
            for (TaskListSection sec : ui.getCurrentList().getSections())
                sections.getItems().add(sec);

        // Populate tasks
        if (ui.getCurrentSection() != null)
            for (Task t : ui.getCurrentSection().getTasks())
                tasks.getItems().add(t);
    }

    public void close()
    {
        ui.setMain(null);
        stage.close();
    }
}
