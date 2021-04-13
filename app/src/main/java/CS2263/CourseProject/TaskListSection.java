package CS2263.CourseProject;

import lombok.*;
import java.util.ArrayList;

/** @author  Dustin Weber
 * Task list section. */
@NoArgsConstructor
public class TaskListSection
{
    // Variables
    @Getter @Setter
    private String name;
    @Getter @Setter private ArrayList<Task> tasks;
    /** Description of what this section entails. */
    @Getter @Setter private String description;


    // Constructors
    /** Parameterized constructor
     * @param tasks  Array of tasks in this section. */
    public TaskListSection(ArrayList<Task> tasks)
    {
        this.tasks = tasks;
    }
}
