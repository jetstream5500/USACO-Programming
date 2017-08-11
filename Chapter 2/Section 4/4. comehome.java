/*
TASK: comehome
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Octal
// LAST MODIFIED    :   2-8-14
// PROBLEM ID       :   comehome
// DESCRIPTION      :   Goes through and does the process
//                      shown in the text file in
//                      this section.  Loops through each cow
//                      to see which is closes to 'Z'
//                      Uses a lot of tables so look up of
//                      values it easier.
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
public class comehome
{
    static ArrayList<ArrayList<Integer>> memo;
    static int[][] memo2;
    static boolean[] cowPresent;
    static int[] distance;
    static boolean[] visited;
    static boolean[] used;
    public static void main(String args[]) throws IOException {
        // IO setup
        BufferedReader reader = new BufferedReader(new FileReader("comehome.in"));
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("comehome.out")));
        Scanner in = new Scanner(reader);

        // Inputs Number of Lines
        int numLines = in.nextInt();

        // Initializes Tables
        memo = new ArrayList<ArrayList<Integer>>();
        memo2 = new int[53][53];
        cowPresent = new boolean[53];
        distance = new int[53];
        visited = new boolean[53];
        used = new boolean[53];
        
        // Inputs Data
        for (int i = 0; i<numLines; i++) {
            String start = in.next();
            String end = in.next();
            char startChar = start.charAt(0);
            char endChar = end.charAt(0);
            int weight = in.nextInt();

            int startInt = (int) startChar;
            int endInt = (int) endChar;
            if (startInt != endInt) {

                if (startInt < 97) {
                    cowPresent[startInt-64+26] = true;
                }
                if (endInt < 97) {
                    cowPresent[endInt-64+26] = true;
                }

                if (startInt < 97) {
                    startInt -= (64-26);
                } else {
                    startInt -= 96;
                }

                if (endInt < 97) {
                    endInt -= (64-26);
                } else {
                    endInt -= 96;
                }

                used[startInt] = true;
                used[endInt] = true;
                if ((memo2[startInt][endInt] == 0) || (memo2[startInt][endInt] > weight)) {
                    memo2[startInt][endInt] = weight;
                    memo2[endInt][startInt] = weight;
                }
            }
        }

        // Sets up Tables
        for (int i = 0; i<=52; i++) {
            visited[i] = !used[i];
        }

        // Sets up Tables
        memo.add(new ArrayList<Integer>());
        for (int i = 1; i<=52; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(0);
            for (int j = 1; j<=52; j++) {
                int num = memo2[i][j];
                if (num!=0) {
                    list.add(j);
                }
            }
            memo.add(i, list);
        }

        // Sets up Tables
        for (int i = 1; i<=52; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // Goes through and calls the function for each cow
        int position = -1;
        int shortest = Integer.MAX_VALUE;
        for (int i = 1; i<=51; i++) {
            if (cowPresent[i]) {
                visited[i] = true;
                distance[i] = 0;
                int totalDistance = comehome.shortestPath(i, 52);
                for (int j = 1; j<=52; j++) {
                    visited[j] = !used[j];
                    distance[j] = Integer.MAX_VALUE;
                }
                if (totalDistance < shortest) {
                    shortest = totalDistance;
                    position = i;
                }
            }
        }

        char letter = (char) (position-26+64);
        writer.println(letter + " " + shortest);
        writer.close();
    }

    public static int shortestPath(int startNode, int endNode) {
        int previousNode = startNode;
        boolean notDone = true;
        while(notDone) {
            ArrayList<Integer> list = memo.get(previousNode);
            // Corrects distances (shortest distance based on node just traveled to
            for (int i = 1; i<list.size(); i++) {
                int num = list.get(i);
                if (!visited[num]) {
                    int newDistance = distance[previousNode]+memo2[previousNode][num];
                    if (distance[num] > newDistance) {
                        distance[num] = newDistance;
                    }
                }
            }
            // Chooses the node closest to source
            int shortestDistanceNode = -1;
            int shortestDistance = Integer.MAX_VALUE;
            ArrayList<Integer> problems = new ArrayList<Integer>();
            for (int i = 1; i<=52; i++) {
                if (!visited[i] && distance[i] < shortestDistance) {
                    shortestDistanceNode = i;
                    problems.clear();
                    problems.add(i);
                    shortestDistance = distance[i];
                } else if (!visited[i] && distance[i] == shortestDistance) {
                    //problems.add(i);
                }
            }

            /*
            if (problems.size() > 1) {
            int newShortestDistance = Integer.MAX_VALUE;
            int newShortestDistanceNode = -1;
            for (int i = 0; i<problems.size(); i++) {
            int problemNode = problems.get(i);
            ArrayList<Integer> list2 = memo.get(problemNode);
            for (int j = 1; j<list2.size(); j++) {
            int num = list2.get(j);
            if (!visited[num]) {
            int newDistance = memo2[problemNode][num];
            if (newShortestDistance > newDistance) {
            newShortestDistance = newDistance;
            newShortestDistanceNode = problemNode;
            }
            }
            }
            }
            if (newShortestDistanceNode == -1) {
            newShortestDistanceNode = problems.get(0);
            }
            shortestDistanceNode = newShortestDistanceNode;
            }*/

            visited[shortestDistanceNode] = true;
            previousNode = shortestDistanceNode;

            boolean done = true;
            if (!visited[52]) {
                done = false;
            }
            /*
            for (int i = 1; i<=52; i++) {
                if (!visited[i]) {
                    done = false;
                }
            }*/

            if (done) {
                notDone = false;
            }
        }

        // Returns shortest Distance
        return distance[52];
    }
}