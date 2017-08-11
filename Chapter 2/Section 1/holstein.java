/*
TASK: holstein
ID: mcokzoo1
LANG: JAVA
 */

// NAME             : Michael Orwin
// GROUP            : Freshman
// LAST MODIFIED    : June 7, 2013 - Summer 2013
// PROBLEM ID       : holstein
// DESCRIPTION      : The way my program works is creates a list of lists
//                    In the list of lists is lists containing the sums
//                    of different feeds.  If the sum meets the
//                    requirements for all the vitamins the program
//                    prints and quits.  Else it creates new lists that
//                    are sums of the current one and all the ones
//                    afterward (individually).

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

public class holstein
{
    public static void main(String[] args) throws IOException {
        //IO setup
        FileReader reader = new FileReader("holstein.in");
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
        Scanner in = new Scanner(reader);
        
        //Reads in number of vitamins
        int numOfVitamins = in.nextInt();
        
        //Reads in vitamin requirements
        int[] vitaminRequirements = new int[numOfVitamins];
        for (int i = 0; i<numOfVitamins; i++) {
            vitaminRequirements[i] = in.nextInt();
        }
        
        //Reads in number of feeds
        int numOfFeeds = in.nextInt();
        
        //Reads in vitamin amounts for each feed.
        int[][] vitamins = new int[numOfFeeds][numOfVitamins];
        for (int i = 0; i<numOfFeeds; i++) {
            for (int j = 0; j<numOfVitamins; j++) {
               vitamins[i][j] = in.nextInt();
            }
        }
        
        //Creates a list of of lists.  Each list contains the vitamins and the index of feed.
        ArrayList<ArrayList<Integer>> totals = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i<numOfFeeds; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j<numOfVitamins; j++) {
                list.add(vitamins[i][j]);
            }
            list.add(i+1);
            totals.add(list);
        }

        //Goes through totals looking for mixture of feeds
        for (int i = 0; i<totals.size(); i++) {
            //Checks to see if there is enough vitamins
            ArrayList<Integer> total = totals.get(i);
            int notEnough = 0;
            for (int j = 0; j<numOfVitamins; j++) {
                int totalForVitamin = total.get(j);
                if (totalForVitamin >= vitaminRequirements[j]) {
                    notEnough++;
                } else {
                    break;
                }
            }
            if (notEnough == numOfVitamins) {
                //If enough -> quits and prints
                writer.print(total.size()-numOfVitamins);
                for (int j = numOfVitamins; j<total.size(); j++) {
                    writer.print(" " + total.get(j));
                }
                writer.println();
                break;
            } else {
                //If not enough -> adds new lists that are sums of the other lists.
                int lastAdded = total.get(total.size()-1);
                for (int j = (lastAdded+1); j<numOfFeeds+1; j++) {
                    ArrayList<Integer> newTotal = new ArrayList<Integer>();
                    for (int k = 0; k<numOfVitamins; k++) {
                        newTotal.add(total.get(k) + vitamins[j-1][k]);
                    }
                    for (int k = numOfVitamins; k<total.size(); k++) {
                        newTotal.add(total.get(k));
                    }
                    newTotal.add(j);
                    totals.add(newTotal);
                }
            }
        }
        
        //Quits Writer
        writer.close();
    }
}