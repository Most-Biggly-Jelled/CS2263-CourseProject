module CS2263.CourseProject
{
    requires javafx.controls;
    requires com.google.common;
    requires com.google.gson;
<<<<<<< HEAD
    requires lombok;
=======
    requires static lombok;
>>>>>>> 5c3edc999a2f430ff1692d039fc8f1be81907a58
    exports CS2263.CourseProject;
    opens CS2263.CourseProject to com.google.gson;
}