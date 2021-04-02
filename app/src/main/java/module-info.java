module gradle.skilldrill
{
    requires javafx.controls;
    requires com.google.common;
    requires com.google.gson;
    exports CS2263.CourseProject;
    opens CS2263.CourseProject to com.google.gson;
}