/*
TASK: cowtour
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Discrete Math - Junior
// LAST MODIFIED    :   12-7-14
// PROBLEM ID       :   cowtour
// DESCRIPTION      :   We find the minimum distance - shortest path
//                      to each field and then the diameter is the
//                      biggest value of all the shortest paths to
//                      the last point.
// SOURCES/HELPERS  :   N/A

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;
import java.math.*;

public class cowtour
{
    static double[][] weights;
    static boolean[] visited;
    static double[] distances;
    static ArrayList<ArrayList<Integer>> lists;
    static ArrayList<Integer> visited2;
    public static void main(String args[]) throws IOException {
        // IO setup
        BufferedReader reader = new BufferedReader(new FileReader("cowtour.in"));
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("cowtour.out")));
        Scanner in = new Scanner(reader);

        // Set up points
        int numPoints = in.nextInt();
        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i<numPoints; i++) {
            points.add(new Point(in.nextInt(), in.nextInt()));
        }
        // initialize statics
        weights = new double[numPoints+1][numPoints+1];
        visited = new boolean[numPoints+1];
        distances = new double[numPoints+1];
        visited2 = new ArrayList<Integer>();
        for (int i = 0; i<=numPoints; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        lists = new ArrayList<ArrayList<Integer>>();

        //Sets up weights and lists
        lists.add(new ArrayList<Integer>());
        for (int i = 1; i<=numPoints; i++) {
            String line = in.next();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 1; j<=line.length(); j++) {
                if ((line.substring(j-1, j)).equals("1")) {
                    double distance = findDistance(points.get(i-1), points.get(j-1));
                    weights[i][j] = distance;
                    list.add(j);
                }
            }
            lists.add(list);
        }
        ArrayList<ArrayList<Integer>> pastures = new ArrayList<ArrayList<Integer>>();
        double smallest = Integer.MAX_VALUE;

        for (int i = 1; i<=numPoints; i++) {
            if (!visited[i]) {
                visited2 = new ArrayList<Integer>();
                visited[i] = true;
                distances[i] = 0;
                cowtour.findShortest(i);
                ArrayList<Integer> pasture = new ArrayList<Integer>();
                for (int j = 0; j<visited2.size(); j++) {
                    pasture.add(visited2.get(j));
                }
                pastures.add(pasture);
            }
        }
        for (int reset = 0; reset<distances.length; reset++) {
            distances[reset] = Integer.MAX_VALUE;
            visited[reset] = false;
        }
        double maxDist1 = 0;
        double maxNode1 = -1;
        double maxDist2 = 0;
        double maxNode2 = -1;
        for (int c = 0; c<pastures.size(); c++) {
            ArrayList<Integer> pasture1 = pastures.get(c);
            for (int d = 0; d<pastures.size(); d++) {
                if ((c!=d && c<d)) {
                    ArrayList<Integer> pasture2 = pastures.get(d);
                    for (int i = 0; i<pasture1.size(); i++) {
                        int node1 = pasture1.get(i);
                        visited[node1] = true;
                        distances[node1] = 0;
                        double dist1 = cowtour.findShortest(node1);
                        if (dist1>maxDist1) {
                            maxDist1 = dist1;
                            maxNode1 = node1;
                        }

                        for (int j = 0; j<pasture2.size(); j++) {
                            int node2 = pasture2.get(j);
                            visited[node2] = true;
                            distances[node2] = 0;

                            double dist2 = cowtour.findShortest(node2);
                            if (dist2>maxDist2) {
                                maxDist2 = dist2;
                                maxNode2 = node2;
                            }
                            Point point1 = points.get(node1-1);
                            Point point2 = points.get(node2-1);
                            double mid = cowtour.findDistance(point1, point2);
                            double total = dist1+dist2+mid;
                            if (total < smallest) {
                                smallest = total;
                                //System.out.println(smallest);
                            }
                            for (int reset = 0; reset<distances.length; reset++) {
                                distances[reset] = Integer.MAX_VALUE;
                                visited[reset] = false;
                            }
                        }
                    }
                }
            }
        }
        ArrayList<Double> possibles = new ArrayList<Double>();
        possibles.add(smallest);
        possibles.add(maxDist1);
        possibles.add(maxDist2);
        Collections.sort(possibles);
        long small = Math.round(possibles.get(possibles.size()-1)*1000000);
        String smallStr = (small)+"";
        smallStr = smallStr.substring(0, smallStr.length()-6) + "." + smallStr.substring(smallStr.length()-6);
        writer.println(smallStr);
        writer.close();
    }

    public static double findDistance(Point start, Point end) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
    }

    public static double findShortest(int startNode) {
        visited2.add(startNode);
        int size = lists.size();
        int previousNode = startNode;
        boolean notDone = true;
        double biggestDistance = 0;
        while (notDone) {
            ArrayList<Integer> list = lists.get(previousNode);

            for (int i = 0; i<list.size(); i++) {
                int num = list.get(i);
                if (!visited[num]) {
                    double newDistance = distances[previousNode]+weights[previousNode][num];
                    if (distances[num] > newDistance) {
                        distances[num] = newDistance;
                    }
                }
            }

            int shortestDistanceNode = -1;
            double shortestDistance = Integer.MAX_VALUE;
            for (int i = 1; i<=size-1; i++) {
                if (!visited[i] && distances[i] < shortestDistance) {
                    shortestDistanceNode = i;
                    shortestDistance = distances[i];
                }
            }
            if (shortestDistanceNode == -1) {
                break;
            } else {
                visited2.add(shortestDistanceNode);
                if (biggestDistance < shortestDistance) {
                    biggestDistance = shortestDistance;
                }
            }
            visited[shortestDistanceNode] = true;
            previousNode = shortestDistanceNode;
        }
        return biggestDistance;
    }
}