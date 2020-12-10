// Jonathan Chang
// 5/30/17
// CSE142
// TA: Robert Kolmos
// Assignment #8

// This is a class of critters that hops forward until it reaches a wall.
// Then it turns around. When it crosses another Lion, they turn opposite directions
// and hop away from one another. Infects when it can. Randomly changes colors every
// three turns.

import java.awt.*;
import java.util.*;

public class Lion extends Critter {
   
   private Color color; // the color if this critter
   private Random random; // random object used for setting color
   private int numMoves; // number of times getMoves method is called
   
   // constructs a lion
   public Lion() {
      numMoves = 0;
      random = new Random();
      setRandomColor();
   }
   
   // this method sets the color of this critter randomly
   public void setRandomColor() {
      int numColors = 3;
      int randomColor = random.nextInt(numColors);
      if (randomColor == 0) {
         color = Color.RED;
      } else if (randomColor == 1) {
         color = Color.GREEN;
      } else {
         color = Color.BLUE;
      }
   }
   
   // Returns next Action this Lion will make
   public Action getMove(CritterInfo info) {
      numMoves++;
      if (numMoves % 3 == 0) {
         setRandomColor();
      }
      if (info.getFront() == Neighbor.OTHER) {
         return Action.INFECT;
      } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
         return Action.LEFT;
      } else if (info.getFront() == Neighbor.SAME) {
         return Action.RIGHT;
      } else {
         return Action.HOP;
      }
   }
   
   // This method returns the color of this critter
   public Color getColor() {
      return color;
   }
     
   // This method returns the string representation of this critter
   public String toString() {
      return "L";
   }
}