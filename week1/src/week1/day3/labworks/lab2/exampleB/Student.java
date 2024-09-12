package week1.day3.labworks.lab2.exampleB;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 10000;

    private String name;
    private Course course;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student's name: " + name + " " + course;
    }
}
