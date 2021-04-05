package CS2263.CourseProject.UI;

import CS2263.CourseProject.*;
import javafx.application.Platform;
import javafx.scene.image.Image;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

/** @author  Dustin Weber
 * Primary UI class for access.
 * Other UI classes shouldn't be directly accessed except via this class. */
public class UI
{
    // Variables
    /** Currently selected task */
    @Getter @Setter private Task currentTask;
    /** Currently selected list */
    @Getter @Setter private TaskList currentList;
    /** User currently logged into the system. */
    @Getter @Setter private User currentUser;
    /** Default title to use for the application. */
    @Getter private final static String windowTitle = "To-Do List";
    /** Path to application icon. */
    @Getter private final static Image icon = new Image("file:icon.png");


    // Constructors
    /** Default constructor. Sets currentList variable as null. */
    public UI()
    {
        currentTask = null;
        currentList = null;
    }

    // Methods
    /** Shows splash screen. Should be called at program's start. */
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
     * @param user  User object to log in as. */
    public void login(UI_Login login, User user)
    {
        // TODO: This will need to integrated with login to check credentials
        if (user.getEmail().equals("admin"))
        {
            UI_Admin admin = new UI_Admin(this);
            admin.show();
            login.close();
        }
        else if (!user.getEmail().isEmpty() && !user.getPassword().isEmpty())
        {
            try
            {
                setCurrentUser(IO.LoadUser(user.getEmail()));

                UI_Main main = new UI_Main(this);
                main.show();
                login.close();
            }
            catch (IOException e)
            {
                // Display error to UI if user cannot be found.
                login.loginError();
            }
        }
        // Login error.
        else
            login.loginError();
    }

    /** Shows task creation UI.
     * @param task  Task to open with. Leave null to create a new task. */
    public void openTaskCreationUI(Task task)
    {
        UI_CreateNewTask ui = new UI_CreateNewTask(this, task);
        ui.show();
    }

    /** Shows list creation UI.
    * @param list  List to open with. Leave null to create a new list. */
    public void openListCreationUI(TaskList list)
    {
        UI_CreateNewList ui = new UI_CreateNewList(this, list);
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
        // TODO: Search class not created yet.
    }

    /** New user is created (registered).
     * @param user  User to create. */
    public void createUser(User user)
    {
        try
        {
            IO.SaveUser(user);
        }
        catch (IOException e)
        {
            System.out.println("New user couldn't be created.");
        }
    }

    /** User selects UI element to create a new task list.
    * @param list  The list to create. */
    public void createList(TaskList list)
    {
        try
        {
            currentUser.getTaskLists().add(list);
            IO.SaveUser(currentUser);
        }
        catch (IOException e)
        {
            System.out.println("User data couldn't be saved.");
        }
    }

    /** User modifies/edits currently selected task list.
     * @param list  Newly modified list to REPLACE currentList with. */
    public void editList(TaskList list)
    {
        try
        {
            currentList = list;
            IO.SaveUser(currentUser);
        }
        catch (IOException e)
        {
            System.out.println("User data couldn't be saved.");
        }
    }

    /** User deletes existing task list.
     * @param list  The list to delete. */
    public void deleteList(TaskList list)
    {
        try
        {
            currentUser.getTaskLists().remove(list);
            IO.SaveUser(currentUser);
        }
        catch (IOException e)
        {
            System.out.println("User data couldn't be saved.");
        }
    }

    /** User selects UI element to create a new task in the current list.
     * @param task  New task to create. */
    public void createTask(Task task)
    {
        try
        {
            currentList.getTasks().add(task);
            IO.SaveUser(currentUser);
        }
        catch (IOException e)
        {
            System.out.println("User data couldn't be saved.");
        }
    }

    /** User wishes to edit the current task.
     * @param task  Newly modified task to REPLACE currentTask with. */
    public void editTask(Task task)
    {
        try
        {
            currentTask = task;
            IO.SaveUser(currentUser);
        }
        catch (IOException e)
        {
            System.out.println("User data couldn't be saved.");
        }
    }

    /** User deletes existing task.
     * @param task  The task to delete. */
    public void deleteTask(Task task)
    {
        try
        {
            currentList.getTasks().remove(task);
            IO.SaveUser(currentUser);
        }
        catch (IOException e)
        {
            System.out.println("User data couldn't be saved.");
        }
    }

    /** User selects UI element to create a subtask of an existing task.
     * @param parent  Existing task which will have a subtask added to it.
     * @param child  New subtask/child task. */
    public void createSubtask(Task parent, Subtask child)
    {
        try
        {
            parent.getSubtasks().add(child);
            IO.SaveUser(currentUser);
        }
        catch (IOException e)
        {
            System.out.println("User data couldn't be saved.");
        }
    }

    /** User deletes subtask.
     * @param subtask  The subtask to delete. */
    public void deleteSubtask(Subtask subtask)
    {
        try
        {
            subtask.getParentTask().getSubtasks().remove(subtask);
            IO.SaveUser(currentUser);
        }
        catch (IOException e)
        {
            System.out.println("User data couldn't be saved.");
        }
    }

    /** User selects UI element to log out. */
    public void logout()
    {
        currentUser = null;
        UI_Login login = new UI_Login(this);
        login.show();
    }

    /** User selects UI element to exit the application. */
    public void quitApplication()
    {
        Platform.exit();
    }
}
