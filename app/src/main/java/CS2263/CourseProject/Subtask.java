package CS2263.CourseProject;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** @author Madison May
 * Most Biggly Jelled
 * Task class for to do list manager */

public class Subtask {
    @Getter @Setter private Task parentTask;
    @Getter @Setter private String listName;
    @Getter @Setter private String date;
    @Getter @Setter private String title;
    @Getter @Setter private String description;
    @Getter @Setter private String status;
    @Getter @Setter private List<String> labels;
    @Getter @Setter private String priority;
    @Getter @Setter private TaskList parentList;


    // Constructors
    /** Parameterized constructor.
     * @param parentTask  Task this subtask belongs to. */
    public Subtask(Task parentTask)
    {
        this.parentTask = parentTask;
    }
}
