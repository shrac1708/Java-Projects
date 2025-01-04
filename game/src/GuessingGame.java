import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int randomNumber = random.nextInt(100) + 1;
        int guess;
        int attempts = 0;

        System.out.println("Welcome to the Guessing Game!");
        System.out.println("Try to guess the number I picked (between 1 and 100).");

        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt(); // Read user input
            attempts++;

            if (guess < randomNumber) {
                System.out.println("Your guess is too low.");
            } else if (guess > randomNumber) {
                System.out.println("Your guess is too high.");
            } else {
                System.out.println("Congratulations! You've guessed the number.");
                System.out.println("It took you " + attempts + " attempts to win.");
            }
        } while (guess != randomNumber);

        scanner.close(); // Close the scanner
    }
}
