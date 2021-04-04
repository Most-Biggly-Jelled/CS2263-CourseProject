package CS2263.CourseProject;

<<<<<<< HEAD
import java.util.ArrayList;

/** @author  Dustin Weber
 * Task or to-do list. */
public class TaskList
{
    // Variables
    private ArrayList<Task> tasks;


    // Constructors
    /** Default constructor */
    public TaskList() {}

    /** Parameterized constructor
     * @param tasks  Array of tasks. */
    public TaskList(ArrayList<Task> tasks)
    {
        this.tasks = tasks;
    }

    // Getters
    /** Returns all tasks belonging to this list. */
    public ArrayList<Task> getTasks() { return tasks; }

    // Setters
    /** Sets tasks array. */
    public void setTasks(ArrayList<Task> tasks) { this.tasks = tasks; }
=======
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class TaskList {
    //placeholder class as this branch does not have a task list
    @Getter @Setter private String name;
    @Getter @Setter private ArrayList<Task> tasks;
>>>>>>> feature/io
}
