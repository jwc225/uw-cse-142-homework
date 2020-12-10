// Jonathan Chang
// 5/30/17
// CSE142
// TA: Robert Kolmos
// Assignment #8

// This is a class of critters that hops toward a wall, then toward a corner. Huddles
// in the corner with other Huskies. They face outward like the Spartans in the movie 300.
// Changes appearance every turn, cycling between three colors and five appearances.

// Experiments:
// First tried to get the Huskies to move into the walls and turn their backs to the wall.
// Then tried to get them to go to a corner, but some Huskies got stuck so 
// I added an extra if condition that made them move toward corner if there was room. 
// I ran a test with 30 Huskies and 30 Bears and
// was destroyed quickly -- Huskies faced toward the center 
// but wall-hugging Bears came from the sides. Finally included an if condition that turned 
// Huskies at the end toward the side and incoming Bears. I retried the same test 
// multiple times with Huskies winning slowly but surely, 100% of the time. 
// I also tested Lions and won quickly every time or stalemated because lions are scared. 
// The only Critter that beats Huskies in a 1v1 scenario are the Giants.
// I did not test against FlyTraps because it would result in a stalemate 
// as both critters stop moving once appropriate conditions are met.

import java.awt.*;
import java.util.*;

public class Husky extends Critter {
   
   // Appearances husky should take in order
   private static final String[] APPEARANCE_ARRAY = {"d0g", "pUp", "po0cH", "sc00by", "c4nin3"};
   private static final int NUM_STEPS = 1; // steps Husky takes before changing appearance
   
   private Color color; // color of this critter
   private int numMoves; // number of times getMove method is called
   private String appearance; // string representation of this critter
   private int appearanceIndex; // index of the current appearance of Husky in APPEARANCE_ARRAY
   
   // constructs a Husky
   public Husky() {
      numMoves = 0;
      appearance = "d0g";
      appearanceIndex = 0;
      color = Color.MAGENTA;
   }
   
   // Returns next Action this Husky will make
   public Action getMove(CritterInfo info) {
      // update color
      numMoves++;
      if (numMoves % 3 == 0) {
         color = Color.MAGENTA;
      } else if (numMoves % 3 == 1) {
         color = Color.GREEN;
      } else {
         color = Color.WHITE;
      }
      
      // update appearance
      if (numMoves % NUM_STEPS == 0) {
         appearanceIndex++;
         if (appearanceIndex == APPEARANCE_ARRAY.length) {
            appearanceIndex = 0;
         }
         appearance = APPEARANCE_ARRAY[appearanceIndex];
      }
      
      // determine Action to take
      if (info.getFront() == Neighbor.OTHER) {
         return Action.INFECT;
      } else if (info.getFront() == Neighbor.EMPTY && info.getBack() != Neighbor.WALL &&
                 info.getLeft() != Neighbor.WALL) {
         return Action.HOP; // hop only if not in corner with other Huskies
      } else if (info.getFront() != Neighbor.EMPTY) {
         return Action.LEFT;
      } else if (info.getRight() == Neighbor.EMPTY && info.getBack() != Neighbor.SAME) {
         return Action.RIGHT; // turn toward right corner if there is room for this Husky
      } else if (info.getRight() == Neighbor.SAME && (info.getLeft() == Neighbor.OTHER || 
                 info.getLeft() == Neighbor.EMPTY)) {
         return Action.LEFT; // turn to defend the end of the line of Huskies
      } else {
         return Action.INFECT; // defensive formation, wait for enemy critters to come
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