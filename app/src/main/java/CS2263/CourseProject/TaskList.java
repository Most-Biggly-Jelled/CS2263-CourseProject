package CS2263.CourseProject;

import java.time.LocalDate;
import java.util.ArrayList;
import lombok.*;

/** @author  Madison May
 * Task or to-do list. */
@NoArgsConstructor
public class TaskList
{
    // Variables
    @Getter @Setter private String name;
    @Getter @Setter private ArrayList<TaskListSection> sections;
    /** Due date of the list */
    @Getter @Setter private String date;
    /** Description of what this list/project entails. */
    @Getter @Setter private String description;


    /** Parameterized constructor
     * @param sections  Array of list sections. */
    public TaskList(ArrayList<TaskListSection> sections)
    {
        this.sections = sections;
    }

    /** @author Dustin Weber
     * @param s  String to convert to a LocalDate
     * @return  due date as a LocalDate object */
    public LocalDate getDateAsLocalDate(String s)
    {
        try
        {
            return LocalDate.parse(s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
