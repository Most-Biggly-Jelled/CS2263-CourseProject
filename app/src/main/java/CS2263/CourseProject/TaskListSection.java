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
     * @param name  Name of the section.
     * @param tasks  Array of tasks in this section.
     * @param description  Description off what this section contains.*/
    public TaskListSection(String name, ArrayList<Task> tasks, String description)
    {
        this.name = name;
        this.tasks = tasks;
        this.description = description;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
