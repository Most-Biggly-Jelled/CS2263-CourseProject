package CS2263.CourseProject.UI;

import CS2263.CourseProject.Subtask;
import CS2263.CourseProject.Task;
import CS2263.CourseProject.TaskListSection;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

/** @author  Dustin Weber
 * Section creation/modification UI. */
public class UI_CreateNewSection implements InterfaceUI
{
    // Variables
    private Stage stage;
    /** Task to edit. Null if a new task is being created from this UI. */
    private final TaskListSection section;
    /** Reference to main UI. */
    private final UI_Main main;
    /** Reference to controlling UI class. */
    private final UI ui;


    // Constructors
    /** Parameterized constructor.
     * @param ui  Reference to controlling UI class.
     * @param main  Reference to main UI.
     * @param section  Existing section to modify. */
    public UI_CreateNewSection(UI ui, UI_Main main, TaskListSection section)
    {
        this.ui = ui;
        this.main = main;
        this.section = section;
    }

    // Methods
    public void show()
    {
        // Elements
        Button buttonCancel = new Button("Cancel");
        Label labelTitle = new Label("Title");
        TextField textTitle = new TextField();
        Label labelDescription = new Label("Description");
        TextField textDescription = new TextField();
        Button buttonCreate;
        // Set buttonCreate text based on modification/creation state
        if (section != null)
            buttonCreate = new Button("Update Section");
        else
            buttonCreate = new Button("Create Section");

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(buttonCancel, 2, 0);
        GridPane.setConstraints(labelTitle, 0, 0);
        GridPane.setConstraints(textTitle, 0, 1);
        GridPane.setConstraints(labelDescription, 0, 2);
        GridPane.setConstraints(textDescription, 0, 3);
        GridPane.setConstraints(buttonCreate, 0, 4);
        grid.getChildren().addAll(buttonCancel, labelTitle, textTitle,
                labelDescription, textDescription, buttonCreate);

        // Listeners
        buttonCancel.setOnAction(val -> buttonCancel());
        buttonCreate.setOnAction(val ->
                buttonCreate
                        (
                                textTitle.getText(),
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

    /** Create button is pressed.
     * @param title  Title/name the new section should take.
     * @param description  Description of the section. */
    private void buttonCreate(String title, String description)
    {
        // Ensure all parameters are valid
        if (!title.isEmpty() && !description.isEmpty())
        {
            TaskListSection sec = new TaskListSection
                    (
                            title,
                            null,
                            description
                    );
            // Call create or edit function based on context
            if (section == null)
                ui.createSection(sec);
            else
                ui.editSection(sec);

            // Notify user of file save before closing
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Section saved successfully.");
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