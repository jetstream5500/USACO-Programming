/*
TASK: nocows
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Octal
// LAST MODIFIED    :   2-6-14
// PROBLEM ID       :   nocows
// DESCRIPTION      :   Uses recursion to find the number of possibilities.
//                      total = left + right.  To make sure the depth is
//                      I subtract the results of one lower depth to make
//                      sure all ones with lower depths are ommitted
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
public class nocows
{
    // Sets up arrays for DP
    static BigInteger[][] memo;
    static boolean[][] memoBool;
    public static void main(String args[]) throws IOException {
        // IO setup
        BufferedReader reader = new BufferedReader(new FileReader("nocows.in"));
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("nocows.out")));
        Scanner in = new Scanner(reader);
        
        // Inputs Nodes and Depth
        int nodes = in.nextInt();
        int depth = in.nextInt();

        // Sets up arrays
        memo = new BigInteger[nodes+1][depth+1];
        memoBool = new boolean[nodes+1][depth+1];
        
        // Possibly not all need but base cases
        // (code was changed around so values are different)
        memo[3][2] = BigInteger.valueOf(1);
        memoBool[3][2] = true;
        for (int i = 0; i<nodes+1; i++) {
            memo[i][0] = BigInteger.valueOf(0);
            memoBool[i][0] = true;
        }
        
        // Calculates possibilities with specified depth
        BigInteger first = nocows.search(nodes, depth);
        
        // Calculates how many have a lower depth
        BigInteger second = nocows.search(nodes, depth-1);
        
        // Subtracts, Mods by 9901, and Prints
        writer.println((first.subtract(second)).mod(BigInteger.valueOf(9901)));
        
        // Closes Print Writer
        writer.close();
    }

    public static BigInteger search(int nodes, int depth) {
        if (memoBool[nodes][depth]) {
            // DP / Memo Look-up
            return memo[nodes][depth];
        } else if (nodes == 1) {
            return BigInteger.valueOf(1);
        } else {
            BigInteger total = BigInteger.valueOf(0);
            for (int i = 1; i<nodes; i+=2) {
                // Left side
                BigInteger left = nocows.search(nodes-1-i, depth-1);
                memo[nodes-1-i][depth-1] = left;
                memoBool[nodes-1-i][depth-1] = true;

                // Right Side
                BigInteger right = nocows.search(i, depth-1);
                memo[i][depth-1] = right;
                memoBool[i][depth-1] = true;

                // Total
                BigInteger subTotal = left.multiply(right);
                total = total.add(subTotal);
            }
            return total;
        }
    }
}