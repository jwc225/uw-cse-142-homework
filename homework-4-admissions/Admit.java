// Jonathan Chang
// 4/25/17
// CSE142
// TA: Robert Kolmos
// Assignment #4

// This program prompts for exam scores and GPA to compare two
// applicants. Reports which applicant has better scores.

import java.util.*;

public class Admit {
   public static void main(String[] args) {
      giveIntro();
      Scanner console = new Scanner(System.in);
      System.out.println("Information for applicant #1:");
      double examScore1 = getExamScore(console);
      double gpaScore1 = getGPAScore(console);
      System.out.println("Information for applicant #2:");
      double examScore2 = getExamScore(console);
      double gpaScore2 = getGPAScore(console);
      reportResults(examScore1, gpaScore1, examScore2, gpaScore2);
   }
   
   // Introduces the program to the user
   public static void giveIntro() {
      System.out.println("This program compares two applicants to");
      System.out.println("determine which one seems like the stronger");
      System.out.println("applicant.  For each candidate I will need");
      System.out.println("either SAT or ACT scores plus a weighted GPA.");
      System.out.println();
   }
   
   // Prompts the user for exam type and prompts for appropriate subscores
   // using the given Scanner.
   // parameters needed:
   //    console     = scanner used to get user input
   public static double getExamScore(Scanner console) {
      System.out.print("    do you have 1) SAT scores or 2) ACT scores? ");
      int number = console.nextInt();
      double examScore = 0;
      if (number == 1) {
         examScore = getSatExamScore(console);
      } else {
         examScore = getActExamScore(console);
      }
      System.out.println("    exam score = " + round(examScore));
      return examScore;
   }
   
   // Prompts the user for SAT Exam subscores using the given Scanner.
   // parameters needed:
   //    console     = scanner used to get user input
   public static double getSatExamScore(Scanner console) {
      System.out.print("    SAT math? ");
      int satMath = console.nextInt();
      System.out.print("    SAT critical reading? ");
      int satRead = console.nextInt();
      System.out.print("    SAT writing? ");
      int satWrit = console.nextInt();
      return satScoreFor(satMath, satRead, satWrit);
   }
   
   // Prompts the user for ACT Exam subscores using the given Scanner.
   // parameters needed:
   //    console     = scanner used to get user input
   public static double getActExamScore(Scanner console) {
      System.out.print("    ACT English? ");
      int actEngl = console.nextInt();
      System.out.print("    ACT math? ");
      int actMath = console.nextInt();
      System.out.print("    ACT reading? ");
      int actRead = console.nextInt();
      System.out.print("    ACT science? ");
      int actSci = console.nextInt();
      return actScoreFor(actEngl, actMath, actRead, actSci);
   }
   
   // Calculates SAT Exam score using the given subscores in each subject.
   // parameters needed:
   //    satMath     = math subscore
   //    satRead     = reading subscore
   //    satWrit     = writing subscore
   public static double satScoreFor(int satMath, int satRead, int satWrit) {
      return (2.0 * satMath + satRead + satWrit) / 32;
   }
   
   // Calculates SAT Exam score using the given subscores in each subject.
   // parameters needed:
   //    actEngl     = english subscore
   //    actMath     = math subscore
   //    actRead     = reading subscore
   //    actSci      = science subscore
   public static double actScoreFor(int actEngl, int actMath, int actRead, int actSci) {
      return (actEngl + 2 * actMath + actRead + actSci) / 1.8;
   }
   
   // Prompts the user for information required to calculate
   // GPA score using the given Scanner.
   // parameters needed:
   //    console     = scanner used to get user input
   // returns GPA score
   public static double getGPAScore(Scanner console) {
      System.out.print("    overall GPA? ");
      double realGrade = console.nextDouble();
      System.out.print("    max GPA? ");
      double maxGrade = console.nextDouble();
      System.out.print("    Transcript Multiplier? ");
      double multi = console.nextDouble();
      double gpaScore = gpaScoreFor(realGrade, maxGrade, multi);
      System.out.println("    GPA score = " + round(gpaScore));
      System.out.println();
      return gpaScore;
   }
   
   // Calculates GPA score using the given GPA and multiplier values.
   // parameters needed:
   //    realGrade      = actual GPA
   //    maxGrade       = max GPA
   //    multi          = transcript multiplier
   // returns calculated GPA score
   public static double gpaScoreFor(double realGrade, double maxGrade, double multi) {
      return realGrade / maxGrade * 100 * multi;
   }
   
   // Prints the overall scores of each applicant calculated from given exam and GPA scores.
   // Prints which applicant has the better score.
   // parameters needed:
   //    examScore1     = exam score of first applicant
   //    gpaScore1      = GPA score of first applicant
   //    examScore2     = exam score of second applicant
   //    gpaScore2      = GPA score of second applicant
   public static void reportResults(double examScore1, double gpaScore1, double examScore2,
                                    double gpaScore2) {
      double overallScore1 = examScore1 + gpaScore1;
      System.out.println("First applicant overall score  = " + round(overallScore1));
      double overallScore2 = examScore2 + gpaScore2;
      System.out.println("Second applicant overall score = " + round(overallScore2));
      reportStatus(overallScore1, overallScore2);
   }
   
   // Prints which applicant has the better score given the overall scores of each applicant.
   // Or prints that the applicants are equal.
   // parameters needed:
   //    overallScore1     = overall score of first applicant
   //    overallScore2     = overall score of second applicant
   public static void reportStatus(double overallScore1, double overallScore2) {
      if (overallScore1 == overallScore2) {
         System.out.println("The two applicants seem to be equal");
      } else if (overallScore1 > overallScore2) {
         System.out.println("The first applicant seems to be better");
      } else {
         System.out.println("The second applicant seems to be better");
      }
   }
   
   // returns the result of rounding n to 1 digit after the decimal point
   public static double round (double n) {
      return Math.round(n * 10.0) / 10.0;
   }
}