import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int MIN_RANGE = 1;
        final int MAX_RANGE = 100;
        final int MAX_ATTEMPTS = 10;
        final int TOTAL_ROUNDS = 3;
        boolean playAgain;

        System.out.println("🎮 Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between " + MIN_RANGE + " and " + MAX_RANGE);

        do {
            int score = 0;
            for (int round = 1; round <= TOTAL_ROUNDS; round++) {
                int targetNumber = getRandomNumber(MIN_RANGE, MAX_RANGE);
                int attempts = 0;

                System.out.println("\n🔁 Round " + round + " of " + TOTAL_ROUNDS);
                System.out.println("Current Total Score: " + score);

                while (attempts < MAX_ATTEMPTS) {
                    int guess = getUserGuess(scanner, MIN_RANGE, MAX_RANGE);
                    attempts++;

                    if (guess == targetNumber) {
                        System.out.println("🎉 Correct! You guessed it in " + attempts + " attempts.");
                        int roundScore = calculateScore(attempts);
                        score += roundScore;
                        System.out.println("✅ Round Score: " + roundScore);
                        break;
                    } else if (guess < targetNumber) {
                        System.out.println("📉 Too low. Try again.");
                    } else {
                        System.out.println("📈 Too high. Try again.");
                    }

                    if (attempts == MAX_ATTEMPTS) {
                        System.out.println("❌ Out of attempts! The correct number was: " + targetNumber);
                    }
                }
            }

            System.out.println("\n🏁 Game Over! Your Total Score: " + score);

            // Ask if user wants to play again
            System.out.print("🔁 Do you want to play again? (yes/no): ");
            scanner.nextLine(); // consume newline
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes");

        } while (playAgain);

        System.out.println("👋 Thanks for playing!");
        scanner.close();
    }

    // Method to generate a random number
    private static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    // Method to calculate score based on attempts
    private static int calculateScore(int attempts) {
        if (attempts <= 3) return 10;
        else if (attempts <= 6) return 5;
        else return 1;
    }

    // Method to get valid user input
    private static int getUserGuess(Scanner scanner, int min, int max) {
        int guess;
        while (true) {
            System.out.print("Enter your guess (" + min + "-" + max + "): ");
            try {
                guess = scanner.nextInt();
                if (guess >= min && guess <= max) {
                    return guess;
                } else {
                    System.out.println("⚠ Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠ Invalid input. Please enter a valid number.");
                scanner.next(); // clear invalid input
            }
        }
    }
}

