import java.io.*;
import java.util.*;
import java.awt.*;
 
// Jonathan Chang
// 5/16/17
// CSE142
// TA: Robert Kolmos
// Assignment #6

// This program looks up a baby name given by user and prints out
// relevant super cool data about the given name and also draws
// a graph of the data.

public class BabyNames {
   
   public static final int STARTING_YEAR = 1890; // change value to shift starting decade of graph
   public static final int WIDTH = 60; // change value to modify width of decade bars of graph
   public static final int HEIGHT = 30; // change value to modify height of legend bars of graph
   public static final int PANEL_HEIGHT = 500 + HEIGHT * 2; // change value to modify panel height
   
   public static void main(String[] args) throws FileNotFoundException {
      giveIntro();
      Scanner console = new Scanner(System.in);
      System.out.print("Name: ");
      String name = console.next();
      System.out.print("Gender (M or F): ");
      String gender = console.next();
      
      
      Scanner frequenciesInput = new Scanner(new File("names.txt"));
      Scanner meaningInput = new Scanner(new File("meanings.txt"));
      String frequencyLine = findName(frequenciesInput, name, gender);
      String meaningLine = findName(meaningInput, name, gender);
      if (frequencyLine.length() > 0 && meaningLine.length() > 0) {
         System.out.println(frequencyLine);
         System.out.println(meaningLine);
         DrawingPanel panel = new DrawingPanel(780, PANEL_HEIGHT);
         panel.setBackground(Color.WHITE);
         Graphics g = panel.getGraphics();
         drawFixedGraph(meaningLine, g);
         drawGraphBars(frequencyLine, g);
      } else {
         System.out.println("\"" + name + "\" not found.");
      }
   }
   
   // introduces the program to the user
   public static void giveIntro() {
      System.out.println("This program allows you to search through the");
      System.out.println("data from the Social Security Administration");
      System.out.println("to see how popular a particular name has been");
      System.out.println("since " + STARTING_YEAR + ".");
      System.out.println();
   }
   
   // Searches for and returns the line in the given input that corresponds to the given 
   // name and gender. Returns an empty string if not found.
   // parameters needed:
   //    input    = scanner to search in
   //    name     = name to search for
   //    gender   = gender to search for
   public static String findName(Scanner input, String name, String gender) {
      name = name.toLowerCase();
      gender = gender.toLowerCase();
      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner tokenScanner = new Scanner(line);
         String firstToken = tokenScanner.next();
         String secondToken = tokenScanner.next();
         if (firstToken.toLowerCase().equals(name) && secondToken.toLowerCase().equals(gender)) {
            return line;
         }
      }
      return "";
   }
   
   // draws the fixed parts of the graph on the given graphics g using the given meaningLine.
   // parameters needed:
   //    meaningLine   = meaning of the desired name
   //    g             = graphics to draw with
   public static void drawFixedGraph(String meaningLine, Graphics g) {
      g.setColor(Color.LIGHT_GRAY);
      g.fillRect(0, 0, 780, HEIGHT);
      g.fillRect(0, PANEL_HEIGHT - HEIGHT, 780, HEIGHT);
      g.setColor(Color.BLACK);
      g.drawLine(0, HEIGHT, 780, HEIGHT);
      g.drawLine(0, PANEL_HEIGHT - HEIGHT, 780, PANEL_HEIGHT - HEIGHT);
      g.drawString(meaningLine, 0, 16);
   }   
         
   // draws the bars, ranks, and dates on the given graphics g based on the given
   // frequencyLine data.
   // parameters needed:
   //    frequencyLine = line containing frequency data of given name
   //    g             = graphics to draw with
   public static void drawGraphBars(String frequencyLine, Graphics g) {
      Scanner frequencyData = new Scanner(frequencyLine);
      frequencyData.next();
      frequencyData.next();
      int xCoord = 0;
      int year = STARTING_YEAR;
      while (frequencyData.hasNextInt()) {
         int printedRank = frequencyData.nextInt();
         int calculatedRank = printedRank;
         if (printedRank == 0) {
            calculatedRank = 1000;
         }
         int yCoord = HEIGHT + calculatedRank / 2;
         
         g.setColor(Color.GREEN);
         g.fillRect(xCoord, yCoord, WIDTH / 2, PANEL_HEIGHT - HEIGHT - yCoord);
         g.setColor(Color.BLACK);
         g.drawString("" + printedRank, xCoord, yCoord);
         g.drawString("" + year, xCoord, PANEL_HEIGHT - 8);
         
         xCoord += WIDTH;
         year += 10;
      }
   }
}