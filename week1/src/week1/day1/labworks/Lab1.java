package week1.day1.labworks;

import java.util.Random;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {

        Random rand = new Random();
        // random integers in range 0 to 100
        int rand_int1 = rand.nextInt(101);
        int guess;
        System.out.println("Random number: " + rand_int1);

        for (int i = 0; i < 5; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter the prediction: ");

            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                if (guess == rand_int1) {
                    System.out.println("You got it!");
                    break;
                }
                else if (i == 4){
                    System.out.println("You could not find!");
                }
                else if (guess > rand_int1) {
                    System.out.println("Your guess is too high!");
                }
                else if (guess < rand_int1) {
                    System.out.println("Your guess is too low!");
                }
            }
            else{
                System.out.println("You should enter an integer!");
            }
        }
    }
}
