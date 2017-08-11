/*
TASK: ttwo
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Octal
// LAST MODIFIED    :   2-9-14
// PROBLEM ID       :   ttwo
// DESCRIPTION      :   Moves the cow and farmer until they end up in
//                      the same square.  If the number is greater than
//                      79200 there is no solution because that is the
//                      worst case in theory but not even in practice.
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
public class ttwo
{
    static boolean[][] forest;
    static final int SIZE = 10;
    static ArrayList<Point> points;
    static ArrayList<Integer> directions;
    static int counter = 0;
    public static void main(String args[]) throws IOException {
        // IO setup
        BufferedReader reader = new BufferedReader(new FileReader("ttwo.in"));
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("ttwo.out")));
        Scanner in = new Scanner(reader);

        forest = new boolean[SIZE][SIZE];
        Point cow = new Point(0,0);
        Point farmer = new Point(0,0);
        points = new ArrayList<Point>();
        directions = new ArrayList<Integer>();
        for (int i = 0; i<SIZE; i++) {
            String line = in.nextLine();
            for (int j = 0; j<SIZE; j++) {
                String character = line.substring(j, j+1);
                if (character.equals("*")) {
                    forest[i][j] = true;
                } else if (character.equals("C")) {
                    cow = new Point(i, j);
                } else if (character.equals("F")) {
                    farmer = new Point(i, j);
                }
            }
        }

        points.add(cow);
        directions.add(1);

        points.add(farmer);
        directions.add(1);

        boolean breaker = true;
        while (breaker) {
            ttwo.move();
            Point cowCheck = points.get(0);
            Point farmerCheck = points.get(1);
            int cowXCheck = (int) cowCheck.getX();
            int cowYCheck = (int) cowCheck.getY();
            int farmerXCheck = (int) farmerCheck.getX();
            int farmerYCheck = (int) farmerCheck.getY();
            
            if (cowXCheck == farmerXCheck && cowYCheck == farmerYCheck) {
                writer.println(counter);
                breaker = false;
            }
            
            int cowX = (int) cow.getX();
            int cowY = (int) cow.getY();
            int farmerX = (int) farmer.getX();
            int farmerY = (int) farmer.getY();
            int cowDirection = directions.get(0);
            int farmerDirection = directions.get(1);
            
            if (counter > 79200) {
                writer.println(0);
                breaker = false;
            }
            /*
            if (cowX == cowXCheck && cowY == cowYCheck &&
                farmerX == farmerXCheck && farmerY == farmerYCheck &&
                cowDirection == 1 && farmerDirection == 1) {
                    writer.println(0);
                    breaker = false;
                }
                */
        }
        writer.close();
    }

    public static void move() {
        counter++;
        for (int i = 0; i<points.size(); i++) {
            Point point = points.get(i);
            int direction = directions.get(i);
            int x = (int) point.getX();
            int y = (int) point.getY();

            if (direction == 1 && x-1 >= 0 && !(forest[x-1][y])) {
                points.set(i, new Point(x-1, y));
            } else if (direction == 1) {
                directions.set(i, 2);
            }

            if (direction == 2 && y+1 < SIZE && !(forest[x][y+1])) {
                points.set(i, new Point(x, y+1));
            } else if (direction == 2) {
                directions.set(i, 3);
            }

            if (direction == 3 && x+1 < SIZE && !(forest[x+1][y])) {
                points.set(i, new Point(x+1, y));
            } else if (direction == 3) {
                directions.set(i, 4);
            }

            if (direction == 4 && y-1 >= 0 && !(forest[x][y-1])) {
                points.set(i, new Point(x, y-1));
            } else if (direction == 4) {
                directions.set(i, 1);
            }
        }
    }
}