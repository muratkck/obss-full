package week1.day1.examples.example3;

import java.util.Random;
import java.util.Scanner;

public class Example3 {
    public static void main(String[] args) {

        String[] choices = {"Rock", "Scissors", "Paper"};

        String computerChoice = "";
        String playerChoice = "";

        int playerWins = 0;
        int computerWins = 0;

        System.out.println("*** Welcome to the Rock-Scissors-Paper game ***");
        System.out.println("R: Rock\nS: Scissors\nP: Paper");

        boolean continuePlaying = true;

        while (continuePlaying) {
            Random rand = new Random();
            computerChoice = choices[rand.nextInt(3)];
            // computerChoice = choices[0];
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your move: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("R")) {
                playerChoice = choices[0];
            }
            else if (input.equalsIgnoreCase("S")) {
                playerChoice = choices[1];
            }
            else if (input.equalsIgnoreCase("P")) {
                playerChoice = choices[2];
            }
            else {
                System.out.println("You have entered an invalid character!\nPlease try again!");
                continue;
            }

            if(playerChoice.equals(computerChoice)) {
                System.out.println("Computer's move was: " + computerChoice + ". Its a tie!.");
            }

            //checking for all other moves possible
            else if(playerChoice.equals(choices[0])) {

                if(computerChoice.equals(choices[1])) {
                    System.out.println("Computer's move was: " + computerChoice + ". You win this round.");
                    playerWins++;
                }
                else if(computerChoice.equals(choices[2])) {
                    System.out.println("Computer's move was: " + computerChoice + ". You lost this round.");
                    computerWins++;
                }
            }

            else if(playerChoice.equals(choices[2])) {

                if(computerChoice.equals(choices[0])) {
                    System.out.println("Computer's move was: " + computerChoice + ". You win this round.");
                    playerWins++;
                }
                else if(computerChoice.equals(choices[1])) {
                    System.out.println("Computer's move was: " + computerChoice + ". You lost this round.");
                    computerWins++;
                }
            }

            else if(playerChoice.equals(choices[1])) {

                if(computerChoice.equals(choices[2])) {
                    System.out.println("Computer's move was: " + computerChoice + ". You win this round.");
                    playerWins++;
                }
                else if(computerChoice.equals(choices[0])) {
                    System.out.println("Computer's move was: " + computerChoice + ". You lost this round.");
                    computerWins++;
                }
            }

            System.out.println("*****************");
            System.out.println("Player: " + playerWins + " - " + computerWins + " Computer");
            System.out.println("*****************");

            // If the difference is 1, the game will not be ended!
            if (Math.abs(playerWins - computerWins) > 1 ) {
                if(playerWins >= 3 || computerWins >= 3) {
                    continuePlaying = false;
                }
            }
        }
    }
}
