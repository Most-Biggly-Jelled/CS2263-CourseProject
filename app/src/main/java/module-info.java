module CS2263.CourseProject
{
    requires javafx.controls;
    requires com.google.common;
    requires com.google.gson;
    requires static lombok;
    exports CS2263.CourseProject;
    opens CS2263.CourseProject to com.google.gson;
}