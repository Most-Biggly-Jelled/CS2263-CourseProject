package CS2263.CourseProject;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Subtask {
    @Getter @Setter private String priority;
    @Getter @Setter private String title;
    @Getter @Setter private int date;
    @Getter @Setter private String description;
    @Getter @Setter private String status;
    @Getter @Setter private List<String> labels;
   // @Getter @Setter private TaskList parentList;

}
