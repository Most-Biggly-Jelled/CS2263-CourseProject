package CS2263.CourseProject;

import java.time.LocalDate;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/** @author  Madison May
 * Task or to-do list. */
public class TaskList
{
    // Variables
    @Getter @Setter private String name;
    @Getter @Setter private ArrayList<Task> tasks;
    /** Due date of the list */
    @Getter @Setter private LocalDate date;
    /** Description of what this list/project entails. */
    @Getter @Setter private String description;


    // Constructors
    /** Default constructor */
    public TaskList() {}

    /** Parameterized constructor
     * @param tasks  Array of tasks. */
    public TaskList(ArrayList<Task> tasks)
    {
        this.tasks = tasks;
    }
}
