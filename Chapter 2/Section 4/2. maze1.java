/*
TASK: maze1
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Octal
// LAST MODIFIED    :   2-9-14
// PROBLEM ID       :   maze1
// DESCRIPTION      :   Uses a floodfill method to find the farthest
//                      position from the opening.  Fill the opening
//                      and increase numbers as you fill.  Last number
//                      is the farthest from the opening.
// SOURCES/HELPERS  :   N/A

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;
import java.math.*;

/**
 * @author Michael Orwin
 * @version 2/6/14
 */
public class maze1
{
    static String[][] maze;
    static int col;
    static int row;
    public static void main(String args[]) throws IOException {
        // IO setup
        BufferedReader reader = new BufferedReader(new FileReader("maze1.in"));
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("maze1.out")));
        Scanner in = new Scanner(reader);

        col = in.nextInt();
        row = in.nextInt();
        maze = new String[row+row+1][col+col+1];
        ArrayList<Point> locations = new ArrayList<Point>();
        in.nextLine();
        for (int i = 0; i<row+1+row; i++) {
            String line = in.nextLine();
            for (int j = 0; j<col+col+1; j++) {
                maze[i][j] = line.substring(j, j+1);
            }
        }

        for (int j = 1; j<col+1+col; j+=2) {
            if (!(maze[0][j]).equals("-")) {
                maze[1][j] = "1";
                locations.add(new Point(1, j));
            }
            if (!(maze[row+row][j]).equals("-")) {
                maze[row+row-1][j] = "1";
                locations.add(new Point(row+row-1, j));
            }
        }

        for (int i = 1; i<row+1+row; i+=2) {
            if (!(maze[i][0]).equals("|")) {
                maze[i][1] = "1";
                locations.add(new Point(i, 1));
            }
            if (!(maze[i][col+col]).equals("|")) {
                maze[i][col+col-1] = "1";
                locations.add(new Point(i, col+col-1));
            }
        }

        boolean breaker = true;
        int counter = 2;
        while (breaker) {
            ArrayList<Point> points = maze1.flood(locations, counter);
            if (points.size() == 0) {
                breaker = false;
            } else {
                counter++;
                breaker = true;
                locations = points;
            }
        }
        writer.println(counter-1);

        writer.close();
    }

    public static ArrayList<Point> flood(ArrayList<Point> locations, int round) {
        ArrayList<Point> newLocations = new ArrayList<Point>();
        for (int i = 0; i<locations.size(); i++) {
            Point spot = locations.get(i);
            int x = (int) spot.getX();
            int y = (int) spot.getY();
            if (x-2 >= 0) {
                if (!(maze[x-1][y]).equals("-") && (maze[x-2][y]).equals(" ")) {
                    newLocations.add(new Point(x-2, y));
                    maze[x-2][y] = round + "";
                }
            }
            if (x+2 <= row+row) {
                if (!(maze[x+1][y]).equals("-") && (maze[x+2][y]).equals(" ")) {
                    newLocations.add(new Point(x+2, y));
                    maze[x+2][y] = round + "";
                }
            }
            if (y-2 >= 0) {
                if (!(maze[x][y-1]).equals("|") && (maze[x][y-2]).equals(" ")) {
                    newLocations.add(new Point(x, y-2));
                    maze[x][y-2] = round + "";
                }
            }
            if (y+2 <= col+col) {
                if (!(maze[x][y+1]).equals("|") && (maze[x][y+2]).equals(" ")) {
                    newLocations.add(new Point(x, y+2));
                    maze[x][y+2] = round + "";
                }
            }
        }
        return newLocations;
    }
}