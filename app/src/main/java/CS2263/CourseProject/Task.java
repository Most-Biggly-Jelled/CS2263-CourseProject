package CS2263.CourseProject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/** @author Madison May
 * Most Biggly Jelled
 * Task class for to do list manager */

@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Getter @Setter private String name;
    @Getter @Setter private String date;
    @Getter @Setter private String title;
    @Getter @Setter private String description;
    @Getter @Setter private String status;
    @Getter @Setter private List<String> labels;
    @Getter @Setter private String priority;
    @Getter @Setter private TaskList parentList;
    /** All subtasks of this task. */
    @Getter @Setter protected ArrayList<Subtask> subtasks;


    // Constructors

    /** Parameterized constructor
     * @param name  Name of the containing list.
     * @param date  Due date of this task.
     * @param title  Name/title of the task.
     * @param description  Description of what the task entails.
     * @param status  Whether the task is complete, in-progress, incomplete, etc.
     * @param labels  Labels for categories this task should belong to.
     * @param priority  How important this task is.
     * @param parentList  Parent list this task belongs to. */
    public Task(String name, String date, String title, String description, String status, List<String> labels, String priority, TaskList parentList)
    {
        this.name = name;
        this.date = date;
        this.title = title;
        this.description = description;
        this.status = status;
        this.labels = labels;
        this.priority = priority;
        this.parentList = parentList;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
