// Jonathan Chang
// 5/1/17
// CSE142
// TA: Robert Kolmos
// Assignment #5

// This program plays a guessing game with the user.
// Program asks the user to guess the number the computer is thinking of.
// It reports interesting stats about the games played at the end.

import java.util.*;

public class Guess {
   public static final int MAX_NUMBER = 20; // change this value to adjust max number used in game
   
   public static void main(String[] args) {
      giveIntro();
      Scanner console = new Scanner(System.in);
      String playAgain = "y";
      int totalGames = 0;
      int totalGuesses = 0;
      int bestGame = 77777777;
      while (playAgain.startsWith("y")) {
         int numGuesses = playGame(console);
         totalGuesses += numGuesses;
         bestGame = Math.min(bestGame, numGuesses);
         totalGames++;
         System.out.print("Do you want to play again? ");
         playAgain = console.next();
         playAgain = playAgain.toLowerCase();
         System.out.println();
      }
      double averageGuesses = round(totalGuesses * 1.0 / totalGames);
      reportResults(totalGames, totalGuesses, averageGuesses, bestGame);
   }
   
   // Introduces the program to the user.
   public static void giveIntro() {
      System.out.println("This program allows you to play a guessing game.");
      System.out.println("I will think of a number between 1 and");
      System.out.println("100 and will allow you to guess until");
      System.out.println("you get it.  For each guess, I will tell you");
      System.out.println("whether the right answer is higher or lower");
      System.out.println("than your guess.");
      System.out.println();
   }
   
   // Plays a single guessing game with the user.
   // Given a console Scanner, continues to prompt the user for numbers
   // until the user types the correct number.
   // Returns the number of guesses made.
   // Parameters needed:
   //       Scanner console   = console to get user input
   public static int playGame(Scanner console) {
      Random r = new Random();
      int answer = r.nextInt(MAX_NUMBER) + 1;
      int guess = 0;
      int numGuesses = 0;
      System.out.println("I'm thinking of a number between 1 and " + MAX_NUMBER + "...");
      while (guess != answer) {
         System.out.print("Your guess? ");
         guess = console.nextInt();
         numGuesses++;
         if (guess > answer) {
            System.out.println("It's lower.");
         } else if (guess < answer) {
            System.out.println("It's higher.");
         }
      }
      System.out.print("You got it right in " + numGuesses);
      if (numGuesses == 1) {
         System.out.println(" guess");
      } else {
         System.out.println(" guesses");
      }
      return numGuesses;
   }
   
   // Prints the given game statistics.
   // Parameters needed:
   //       totalGames        = total number of games played
   //       totalGuesses      = total number of guesses in all games played
   //       averageGuesses    = average number of guesses per game
   //       bestGame          = fewest number of guesses in any game played
   public static void reportResults(int totalGames, int totalGuesses, double averageGuesses,
                                    int bestGame) {
      System.out.println("Overall results:");
      System.out.println("    total games   = " + totalGames);
      System.out.println("    total guesses = " + totalGuesses);
      System.out.println("    guesses/game  = " + averageGuesses);
      System.out.println("    best game     = " + bestGame);
   }
   
   // returns the result of rounding number to 1 digit after the decimal point
   public static double round (double number) {
      return Math.round(number * 10.0) / 10.0;
   }
}