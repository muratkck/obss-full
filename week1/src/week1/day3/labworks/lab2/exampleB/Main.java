package week1.day3.labworks.lab2.exampleB;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Student student1 = new Student("Murat");
        Course course1 = new Course("CENG322");

        student1.setCourse(course1);

        Student student2 = new Student("Yusuf", course1);


        writeToFile(student2, "/home/muratkucuk/Desktop/Workshop/OBSS/Lectures/Examples/src/day3/lab2/exampleB/object-stream-file");
        readFromFile("/home/muratkucuk/Desktop/Workshop/OBSS/Lectures/Examples/src/day3/lab2/exampleB/object-stream-file");
        // System.out.println(student1.toString());


    }

    private static void writeToFile(Student student, String fileName) throws IOException {

        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(student);
            objectOutputStream.flush();
        }
    }

    private static void readFromFile(String fileName) throws IOException, ClassNotFoundException {
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Student student = (Student) objectInputStream.readObject();
            System.out.println(student);
        }
    }
}
