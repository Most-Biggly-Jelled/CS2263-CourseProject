package CS2263.CourseProject;

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
}
