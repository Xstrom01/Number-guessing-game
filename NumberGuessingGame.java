import java.io.*;
import java.util.*;

public class NumberGuessingGame {
    static Scanner sc = new Scanner(System.in);
    static int round = 1;

    public static void main(String[] args) {
        System.out.println("🎯 Welcome to the Number Guessing Game!");
        System.out.println("Instructions: Guess a number between 1 and 100.");
        System.out.println("Type 'exit' anytime to quit the game.\n");

        while (true) {
            playRound();
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String choice = sc.next().toLowerCase();
            if (!choice.equals("yes")) {
                System.out.println("Thanks for playing! Goodbye!");
                break;
            }
            round++;
        }
    }

    public static void playRound() {
        int number = 1 + (int)(Math.random() * 100);
        int attempts = 0;
        int guess;

        System.out.println("\n🔁 Round " + round + " started!");

        while (true) {
            System.out.print("Enter your guess: ");
            String input = sc.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("❌ Game exited.");
                System.exit(0);
            }

            try {
                guess = Integer.parseInt(input);
                attempts++;

                if (guess == number) {
                    System.out.println("✅ Congratulations! You guessed the number in " + attempts + " attempts.");
                    saveResult(round, attempts);
                    break;
                } else if (guess < number) {
                    System.out.println("📉 Too low! Try again.");
                } else {
                    System.out.println("📈 Too high! Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("⚠ Invalid input. Please enter a number between 1 and 100.");
            }
        }
    }

    public static void saveResult(int round, int attempts) {
        String filename = "guessing_game_scores.txt";
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println("Round " + round + ": Guessed in " + attempts + " attempts");
            System.out.println("💾 Result saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("❌ Error saving the result to file.");
        }
    }
}