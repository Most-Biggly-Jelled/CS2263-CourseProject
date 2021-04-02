package CS2263.CourseProject;

import lombok.Getter;
import lombok.Setter;


public class Subtask extends Task {
    @Getter @Setter private Task parentTask;


    // Constructors
    /** Parameterized constructor.
     * @param parentTask  Task this subtask belongs to. */
    public Subtask(Task parentTask)
    {
        this.parentTask = parentTask;
    }
}
