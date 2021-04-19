package CS2263.CourseProject;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** @author Madison May
 * Most Biggly Jelled
 * Task class for to do list manager */
public class Task {
    @Getter @Setter private String listName;
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
    /** Default constructor */
    public Task() {}

    /** Parameterized constructor
     * @param listName  Name of the containing list.
     * @param date  Due date of this task.
     * @param title  Name/title of the task.
     * @param description  Description of what the task entails.
     * @param status  Whether the task is complete, in-progress, incomplete, etc.
     * @param labels  Labels for categories this task should belong to.
     * @param priority  How important this task is.
     * @param parentList  Parent list this task belongs to. */
    public Task(String listName, String date, String title, String description, String status, List<String> labels, String priority, TaskList parentList)
    {
        this.listName = listName;
        this.date = date;
        this.title = title;
        this.description = description;
        this.status = status;
        this.labels = labels;
        this.priority = priority;
        this.parentList = parentList;
    }
}
