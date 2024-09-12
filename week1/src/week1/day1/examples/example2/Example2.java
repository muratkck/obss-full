package week1.day1.examples.example2;

import java.util.Scanner;

public class Example2 {
    public static void main(String[] args) {

        int size = 0;

        boolean checkInput = false;
        while (!checkInput) {

            Scanner input = new Scanner(System.in);
            System.out.print("Enter a number: ");

            if (input.hasNextInt()) {
                size = input.nextInt();
                checkInput = true;
            }
            else {
                System.out.println("Please enter an integer");
            }
        }
        // Middle line of the diamond
        int n = (size + 1) / 2;

        for (int i = 1; i <= size; i++) {

            // I don't want the longest asterisk line twice in the output due to even number
            if (size % 2 == 0 && i == size / 2) {
                continue;
            }
            int numStars;
            // For the middle line and upside
            if (i <= n) {
                numStars = 2 * i - 1;
            }
            // For the downside
            else{
                numStars = 2 * (size - i) + 1;
            }
            int numSpaces = (size - numStars) / 2;

            for (int j = 0; j < numSpaces; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < numStars; j++) {
                System.out.print("*");
            }
            // Go to new line
            System.out.println();

        }
    }
}
