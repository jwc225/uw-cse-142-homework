// Jonathan Chang
// 5/23/17
// CSE142
// TA: Robert Kolmos
// Assignment #7

// This program processes Keirsey personality test answers from the input file provided
// by the user and calculates the corresponding Keirsey personality type. Prompts the
// user for an output file name. Outputs personality results to that file.

import java.io.*;
import java.util.*;

public class Personality {

   public static final int NUM_DIMENSIONS = 4; // number of dimensions in Keirsey test
   public static final int NUM_QUESTIONS = 7; // number of questions in a group
   
   public static void main(String[] args) throws FileNotFoundException {
      giveIntro();
      Scanner console = new Scanner(System.in);
      System.out.print("input file name? ");
      String inputFileName = console.nextLine();
      System.out.print("output file name? ");
      String outputFileName = console.nextLine();
      Scanner input = new Scanner(new File(inputFileName));
      PrintStream output = new PrintStream(new File(outputFileName));


      while (input.hasNextLine()) {
         String name = input.nextLine();
         String answers = input.nextLine();
         int[] bPercentages = getBPercentages(answers);
         String personalityType = getPersonalityType(bPercentages);
         output.println(name + ": " + Arrays.toString(bPercentages) + " = " + personalityType);
      }
   }
   
   // introduces the program to the user
   public static void giveIntro() {
      System.out.println("This program processes a file of answers to the");
      System.out.println("Keirsey Temperament Sorter.  It converts the");
      System.out.println("various A and B answers for each person into");
      System.out.println("a sequence of B-percentages and then into a");
      System.out.println("four-letter personality type.");
      System.out.println();
   }
   
   // Returns an array of the percentage of b anwers for each personality dimension.
   // params needed:
   //    answers = string of answers to personality test
   public static int[] getBPercentages(String answers) {
      answers = answers.toUpperCase();
      int[] aCounts = new int[NUM_DIMENSIONS];
      int[] bCounts = new int[NUM_DIMENSIONS];
      for (int i = 0; i < answers.length(); i++) {
         char answer = answers.charAt(i);
         if (i % NUM_QUESTIONS == 0) {
            addCount(aCounts, bCounts, answer, 0);
         } else if (i % NUM_QUESTIONS == 1 || i % NUM_QUESTIONS == 2) {
            addCount(aCounts, bCounts, answer, 1);
         } else if (i % NUM_QUESTIONS == 3 || i % NUM_QUESTIONS == 4) {
            addCount(aCounts, bCounts, answer, 2);
         } else {
            addCount(aCounts, bCounts, answer, 3);
         }
      }
      
      int[] bPercentages = new int[NUM_DIMENSIONS];
      for (int i = 0; i < NUM_DIMENSIONS; i++) {
         bPercentages[i] = calculatePercent(aCounts[i], bCounts[i]);
      }
      return bPercentages;
   }
   
   // this method increments the appropriate count in either aCounts or bCounts given answer
   // and count index.
   // params needed:
   //    aCounts     = array of a counts
   //    bCounts     = array of b counts
   //    answer      = a character answer to personality test
   //    countIndex  = index of the count to increment
   public static void addCount(int[] aCounts, int[] bCounts, char answer, int countIndex) {
      if (answer == 'A') {
         aCounts[countIndex]++;
      } else if (answer == 'B') {
         bCounts[countIndex]++;
      }
   }
   
   // returns the percent of b answers given number of b answers bCount, and the number of a
   // answers aCount. Rounds to the nearest integer.
   // params needed:
   //    aCount   = number of a answers
   //    bCount   = number of b answers
   public static int calculatePercent(int aCount, int bCount) {
      double percentage = bCount * 100.0 / (aCount + bCount);
      return (int) Math.round(percentage);
   }
   
   // returns a string of Keirsey test personality type given bPercentages.
   // params needed:
   //     bPercentages  = array of percentages of questions answered as b for each
   //                     personality dimension
   public static String getPersonalityType(int[] bPercentages) {
      String personalityString = "";
      for (int i = 0; i < bPercentages.length; i++) {
         int percent = bPercentages[i];
         String personalityA = "";
         String personalityB = "";
         if (i % NUM_DIMENSIONS == 0) {
            personalityA = "E";
            personalityB = "I";
         } else if (i % NUM_DIMENSIONS == 1) {
            personalityA = "S";
            personalityB = "N";
         } else if (i % NUM_DIMENSIONS == 2) {
            personalityA = "T";
            personalityB = "F";
         } else {
            personalityA = "J";
            personalityB = "P";
         }
         personalityString += calculateDimension(percent, personalityA, personalityB);
      }
      return personalityString;
   }
   
   // returns personalityA or personalityB given the percentage of b answers, percent.
   // params needed:
   //    percent        = percentage of b answers
   //    personalityA   = A Keirsey personality type
   //    personalityB   = B Keirsey personality type
   public static String calculateDimension(int percent, String personalityA, String personalityB) {
      if (percent == 50) {
         return "X";
      } else if (percent > 50) {
         return personalityB;
      } else {
         return personalityA;
      }
   }
}