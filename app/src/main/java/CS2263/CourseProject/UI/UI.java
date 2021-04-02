package CS2263.CourseProject.UI;

import CS2263.CourseProject.Subtask;
import CS2263.CourseProject.Task;
import CS2263.CourseProject.TaskList;
import CS2263.CourseProject.User;
import javafx.application.Platform;
import javafx.scene.image.Image;

/** @author  Dustin Weber
 * Primary UI class for access.
 * Other UI classes shouldn't be directly accessed except via this class. */
public class UI
{
    // Variables
    // Currently selected task
    private Task currentTask;
    // Currently selected list
    private TaskList currentList;
    // Default title to use for the application.
    public final static String windowTitle = "To-Do List";
    // Path to application icon.
    public final static Image icon = new Image("file:icon.png");


    // Constructors
    /** Default constructor. Sets currentList variable as null. */
    public UI()
    {
        currentTask = null;
        currentList = null;
    }

    /** Parameterized constructor.
     * Should only be used if the parameters being set exists prior to the UI being called.
     * i.e. the list is loaded from file.
     * @param currentList  TaskList to be set as the currently viewed list. */
    public UI(Task currentTask, TaskList currentList)
    {
        this.currentTask = currentTask;
        this.currentList = currentList;
    }

    // Getters
    /** @return  Currently selected list on the UI. */
    public TaskList getCurrentList() { return currentList; }
    /** @return  Currently selected task on the UI. */
    public Task getCurrentTask() { return currentTask; }

    // Setters
    /** Sets currentList (currently selected list on UI).
     * @param currentList  List to set as current selection. */
    public void setCurrentList(TaskList currentList) { this.currentList = currentList; }
    /** Sets currentTask (currently selected currentTask on UI).
     * @param currentTask  Task to set as current selection. */
    public void setCurrentTask(Task currentTask) { this.currentTask = currentTask; }

    // Methods
    /** Shows splash screen. */
    public void onStart()
    {
        UI_Splash ui = new UI_Splash(this);
        ui.show();
    }

    /** Shows login UI. Should be called after splash screen was shown. */
    public void onSplashFinish()
    {
        UI_Login login = new UI_Login(this);
        login.show();
    }

    /** User selects UI element to login.
     * @param login  Login class calling this function.
     * @param user  Username (e-mail) entered by the user.
     * @param password  Password entered by the user. */
    public void login(UI_Login login, String user, String password)
    {
        /* TODO: This will need to integrated with actual login to check credentials
         * For now I have it "login" if the username & password aren't empty
         * Also admin is logged in by typing "admin" into username. This also must be changed. */
        if (user.equals("admin"))
        {
            // TODO
            UI_Admin admin = new UI_Admin(this);
            admin.show();
            login.close();
        }
        else if (!user.isEmpty() && !password.isEmpty())
        {
            UI_Main main = new UI_Main(this);
            main.show();
            login.close();
        }
        // Login error.
        else
            login.loginError();
    }

    /** Shows task creation UI. */
    public void openTaskCreationUI()
    {
        UI_CreateNewTask ui = new UI_CreateNewTask(this);
        ui.show();
    }

    /** Shows list creation UI. */
    public void openListCreationUI()
    {
        UI_CreateNewList ui = new UI_CreateNewList(this);
        ui.show();
    }

    /** Shows options UI. */
    public void openOptionsUI()
    {
        UI_Options ui = new UI_Options(this);
        ui.show();
    }

    /** Searches all tasks, subtasks, and task lists for matching text.
     * @param search  String to search for. */
    public void search(String search)
    {
        // TODO
    }

    /** New user is created (registered).
     * @param user  User to create. */
    public void createUser(User user)
    {
        // TODO
    }

    /** User selects UI element to create a new task list.
    * @param list  The list to create. */
    public void createList(TaskList list)
    {
        // TODO
    }

    /** User selects UI element to modify/edit the currently selected task list. */
    public void editList()
    {
        // TODO
    }

    /** User deletes existing task list.
     * @param list  The list to delete. */
    public void deleteList(TaskList list)
    {
        // TODO
    }

    /** User selects UI element to create a new task in the current list.
     * @param task  New task to create. */
    public void createTask(Task task)
    {
        // TODO
    }

    /** User wishes to edit the current task. */
    public void editTask()
    {
        // TODO
    }

    /** User deletes existing task.
     * @param task  The task to delete. */
    public void deleteTask(Task task)
    {
        // TODO
    }

    /** User selects UI element to create a subtask of an existing task.
     * @param parent  Existing task which will have a subtask added to it.
     * @param child  New subtask/child task. */
    public void createSubtask(Task parent, Subtask child)
    {
        // TODO
    }

    /** User deletes subtask.
     * @param subtask  The subtask to delete. */
    public void deleteSubtask(Subtask subtask)
    {
        // TODO
    }

    /** User selects UI element to log out. */
    public void logout()
    {
        UI_Login login = new UI_Login(this);
        login.show();
    }

    /** User selects UI element to exit the application. */
    public void quitApplication()
    {
        Platform.exit();
    }
}
