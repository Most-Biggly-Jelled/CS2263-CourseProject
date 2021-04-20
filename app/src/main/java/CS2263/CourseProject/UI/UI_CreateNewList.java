package CS2263.CourseProject.UI;

import CS2263.CourseProject.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDate;

/** @author  Dustin Weber
 * List creation/modification UI. */
public class UI_CreateNewList implements InterfaceUI
{
    // Variables
    private Stage stage;
    /** List to edit. Null if a new list is being created from this UI. */
    private final TaskList list;
    /** Reference to main UI. */
    private final UI_Main main;
    /** Reference to controlling UI class. */
    private final UI ui;


    // Constructors
    /** Parameterized constructor. Use this when modifying an existing task.
     * @param ui  Reference to controlling UI class.
     * @param main  Reference to main UI.
     * @param list  Existing list to modify. */
    public UI_CreateNewList(UI ui, UI_Main main, TaskList list)
    {
        this.ui = ui;
        this.main = main;
        this.list = list;
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
        Label labelDescription = new Label("Description");
        TextField textDescription = new TextField();
        Button buttonCreate;
        // Set buttonCreate text based on modification/creation state
        if (list != null)
            buttonCreate = new Button("Update List");
        else
            buttonCreate = new Button("Create List");

        // Fill fields if a list has been provided for edit
        if (list != null)
        {
            textTitle.setText(list.getName());
            date.setValue(list.getDateAsLocalDate(list.getDate()));
            textDescription.setText(list.getDescription());
        }

        // Layout
        GridPane grid = new GridPane();
        GridPane.setConstraints(buttonCancel, 2, 0);
        GridPane.setConstraints(labelTitle, 0, 0);
        GridPane.setConstraints(textTitle, 0, 1);
        GridPane.setConstraints(labelDate, 0, 2);
        GridPane.setConstraints(date, 0, 3);
        GridPane.setConstraints(labelDescription, 0, 4);
        GridPane.setConstraints(textDescription, 0, 5);
        GridPane.setConstraints(buttonCreate, 0, 6);
        grid.getChildren().addAll(buttonCancel, labelTitle, textTitle, labelDate,
                date, labelDescription, textDescription, buttonCreate);

        // Listeners
        buttonCancel.setOnAction(val -> buttonCancel());
        buttonCreate.setOnAction(val ->
                buttonCreate
                        (
                            textTitle.getText(),
                            date.getValue(),
                            textDescription.getText()
                        ));

        // Scene
        Scene scene = new Scene(grid, 240, 180);
        stage = new Stage();
        stage.setTitle(UI.getWindowTitle() + " - List");
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
     * @param title  Title the new list should take.
     * @param date  Due date of the list.
     * @param description  Description of the list. */
    private void buttonCreate(String title, LocalDate date, String description)
    {
        // Ensure all parameters are valid
        if (!title.isEmpty() && date != null && !description.isEmpty())
        {
            TaskList newList = new TaskList(title, null, date.toString(), description);
            // Call create or edit function based on context
            if (list == null)
                ui.createList(newList);
            else
                ui.editList(newList);

            // Notify user of file save before closing
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("List saved successfully.");
            a.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> close());
        }
        // Invalid parameters
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Lists require a title, date, and description.");
            a.show();
        }
    }

    public void close()
    {
        main.populateListViews();
        stage.close();
    }
}
