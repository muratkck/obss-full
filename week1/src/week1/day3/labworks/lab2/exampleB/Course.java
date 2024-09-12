package week1.day3.labworks.lab2.exampleB;

import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 10000;
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    @Override
    public String toString() {
        return "Course name: " + courseName;
    }
}
