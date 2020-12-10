// Jonathan Chang
// 3/30/17
// CSE142
// TA: Robert Kolmos
// Assignment #3
 
// This program draws Cafe Wall illusions.
 
import java.awt.*;
 
public class CafeWall {
   public static final int MORTAR_WIDTH = 2; // change this value to modify mortar width
   
   public static void main(String[] args) {
      DrawingPanel panel = new DrawingPanel(650, 400);
      panel.setBackground(Color.GRAY);
      Graphics g = panel.getGraphics();
      drawSquarePairs(g, 0, 0, 4, 20);
      drawSquarePairs(g, 50, 70, 5, 30);
      drawGrid(g, 10, 150, 0, 8, 25);
      drawGrid(g, 250, 200, 10, 6, 25);
      drawGrid(g, 400, 20, 35, 4, 35);
      drawGrid(g, 425, 180, 10, 10, 20);
   }
   
   // Draws one row of square pairs using g at (xCoord, yCoord) with numSquarePairs pairs, and
   // squares of size width/length.
   // parameters needed:
   //    g                 = graphics object used to draw figures
   //    xCoord            = x-coordinate at the beginning of row
   //    yCoord            = y-coordinate at the beginning of row
   //    numSquarePairs    = number of square pairs in the row
   //    size              = width/length of each square
   public static void drawSquarePairs(Graphics g, int xCoord, int yCoord, int numSquarePairs,
         int size) {
      for (int i = 0; i < numSquarePairs; i++) {
         // draws black square
         g.setColor(Color.BLACK);
         g.fillRect(xCoord, yCoord, size, size);
         
         // draws blue x inscribed in black square
         g.setColor(Color.BLUE);
         g.drawLine(xCoord, yCoord, xCoord + size, yCoord + size);
         g.drawLine(xCoord, yCoord + size, xCoord + size, yCoord);
         
         // draws white square
         g.setColor(Color.WHITE);
         g.fillRect(xCoord + size, yCoord, size, size);
         xCoord = xCoord + (size * 2);
      }
   }
   
   // Draws a grid of squares using g at (xCoord, yCoord) with numRows rows, and  row width of
   // square size.
   // Draws every other row of squares offsetted by offset pixels.
   // parameters needed:
   //    g              = graphics object used to draw figures
   //    xCoord         = x-coordinate at the beginning of grid (first row)
   //    yCoord         = y-coordinate at the beginning of grid (first row)
   //    offset         = offset of every other (even) row in the grid
   //    numRows        = number of rows in the grid
   //    size           = width of each row of squares
   public static void drawGrid(Graphics g, int xCoord, int yCoord, int offset, int numRows,
         int size) {
      
      drawEveryOtherRow(g, xCoord, yCoord, numRows, size);
      yCoord += size + MORTAR_WIDTH;
      drawEveryOtherRow(g, xCoord + offset, yCoord, numRows, size);
   }
   
   // Draws every other row of a grid using g at (xCoord, yCoord) with numRows rows, and  row width 
   // square size.
   // parameters needed:
   //    g              = graphics object used to draw figures
   //    xCoord         = x-coordinate at the beginning of  first squares row
   //    yCoord         = y-coordinate at the beginning of first squares row
   //    numRows        = number of rows in the grid
   //    size           = width of each row of squares
   public static void drawEveryOtherRow(Graphics g, int xCoord, int yCoord, int numRows, int size) {
      for (int i = 0; i < numRows; i += 2) {
         drawSquarePairs(g, xCoord, yCoord, numRows / 2, size);
         yCoord += 2 * (size + MORTAR_WIDTH);
      }
   }
}