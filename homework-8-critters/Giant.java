// Jonathan Chang
// 5/30/17
// CSE142
// TA: Robert Kolmos
// Assignment #8

// This class of critters hops forward until it hits a wall, then
// it turns right and continues to hop. Infects while it can. Appearance starts with "fee" then
// changes to  "fie", "foe", "fum" in that order, before returning to "fee". Changes appearance
// every six steps.

import java.awt.*;
import java.util.*;

public class Giant extends Critter {
   
   // Appearances giant should take in order
   private static final String[] APPEARANCE_ARRAY = {"fee", "fie", "foe", "fum"};
   private static final int NUM_STEPS = 6; // steps giant takes before changes appearance
   
   private String appearance; // current string representation of this critter
   private int numMoves; // number of times getMove method is called
   private int appearanceIndex; // index of the current appearance of giant in APPEARANCE_ARRAY
   
   // constructs a Giant
   public Giant() {
      appearance = "fee";
      appearanceIndex = 0;
      numMoves = 0;
   }
   
   // Returns next Action this Giant will make
   public Action getMove(CritterInfo info) {
      numMoves++;
      if (numMoves % NUM_STEPS == 0) {
         appearanceIndex++;
         if (appearanceIndex == APPEARANCE_ARRAY.length) {
            appearanceIndex = 0;
         }
         appearance = APPEARANCE_ARRAY[appearanceIndex];
      }
      
      if (info.getFront() == Neighbor.OTHER) {
         return Action.INFECT;
      } else if (info.getFront() == Neighbor.EMPTY) {
         return Action.HOP;
      } else {
         return Action.RIGHT;
      }
   }
   
   // This method returns the color of this critter
   public Color getColor() {
      return Color.GRAY;
   }
   
   // This method returns the string representation of this critter
   public String toString() {
      return appearance;
   }
}