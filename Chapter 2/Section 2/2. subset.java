/*
TASK: subset
ID: mcokzoo1
LANG: JAVA
 */

// NAME             : Michael Orwin
// GROUP            : Octal
// LAST MODIFIED    : April 26, 2014
// PROBLEM ID       : subset
// DESCRIPTION        The way my program works is brute forces but
//                    searches a memoized list to see if it had been tested
//                    before.  It searches by the postion you have checked
//                    and sum.

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

/**
 * Subset
 * 
 * @author Michael Orwin
 * @version 8/21/13
 */
public class subset
{
    static int[][] cache;
    static boolean[][] cache2;
    public static void main(String args[]) throws IOException {
        //IO setup
        FileReader reader = new FileReader("subset.in");
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("subset.out")));
        Scanner in = new Scanner(reader);

        int n = in.nextInt();

        int sum = (n*(n+1))/2;

        if (sum % 2 != 0) {
            writer.println(0);
        } else {
            sum = sum/2 - 1;
            cache = new int[n][sum+1];
            cache2 = new boolean[n][sum+1];
            for (int i = 0; i<sum+1; i++) {
                cache[0][i] = 0;
                cache2[0][i] = true;
            }
            for (int i = 0; i<n; i++) {
                cache[i][0] = 1;
                cache2[i][0] = true;
            }
            int[] set = new int[n-1];
            for (int i = 2; i<=n; i++) {
                set[i-2] = i;
            }

            writer.println(subset.findSubsets(set, n-1, sum));
        }
        writer.close();
    }

    public static int findSubsets(int[] set, int position, int currentSum) {
        if (cache2[position][currentSum] == true) {
            return cache[position][currentSum];
        } else {
            int a = subset.findSubsets(set, position-1, currentSum);
            int b = 0;
            if (currentSum >= set[position-1]) {
                b = subset.findSubsets(set, position-1, currentSum-set[position-1]);
            }
            
            cache2[position][currentSum] = true;
            cache[position][currentSum] = a+b;
            return a+b;
        }
    }
}
