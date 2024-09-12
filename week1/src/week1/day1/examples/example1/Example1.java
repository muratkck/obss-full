package week1.day1.examples.example1;

import java.util.Scanner;

public class Example1 {
    public static void main(String[] args) {

        int number = 0;

        boolean checkInput = false;
        while (!checkInput) {

            Scanner input = new Scanner(System.in);
            System.out.print("Enter a number: ");

            if (input.hasNextInt()) {
                number = input.nextInt();
                checkInput = true;
            }
            else {
                System.out.println("Please enter an integer");
            }
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " * " + i + " = " + (number * i));
        }
    }
}
