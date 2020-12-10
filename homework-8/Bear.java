// Jonathan Chang
// 5/30/17
// CSE142
// TA: Robert Kolmos
// Assignment #8

// This is a class of critters that hops forward infecting when it can.
// When it hits a wall, it turns left so it hops along the walls. Can either
// be a polar bear or a normal bear.

import java.awt.*;

public class Bear extends Critter {
   private Color color; // the color of this critter
   private String appearance; // the string representation of this critter
   
   // constructs a polar bear if and only if polar is true
   public Bear(boolean polar) {
      if (polar == true) {
         color = Color.WHITE;
      } else {
         color = Color.BLACK;
      }
      appearance = "/";
   }
   
   // Returns next Action this Bear will make
   public Action getMove(CritterInfo info) {
      if (appearance.equals("\\")) {
         appearance = "/";
      } else {
         appearance = "\\";
      }
      
      if (info.getFront() == Neighbor.OTHER) {
         return Action.INFECT;
      } else if (info.getFront() == Neighbor.EMPTY) {
         return Action.HOP;
      } else {
         return Action.LEFT;
      }
   }
   
   // This method returns the color of this critter
   public Color getColor() {
      return color;
   }

   // This method returns the string representation of this critter
   public String toString() {
      return appearance;
   }
}