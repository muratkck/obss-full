package week1.day3.labworks.lab2.exampleA;

public class Course {
    // private static final long serialVersionUID = 10001;
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
        return courseName;
    }
}
