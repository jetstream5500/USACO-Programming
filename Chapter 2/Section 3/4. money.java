/*
TASK: money
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Octal
// LAST MODIFIED    :   2/6/14
// PROBLEM ID       :   money
// DESCRIPTION      :   Uses DP to solve this problem.  Array row
//                      and col is which coins to use and amount
//                      of money left.
// SOURCES/HELPERS  :   N/A

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

/**
 * Write a description of class lamps here.
 * 
 * @author Michael Orwin
 * @version 8/21/13
 */
public class money
{
    static long[][] cache;
    static boolean[][] cache2;
    static ArrayList<Integer> listOfChange;
    public static void main(String args[]) throws IOException {
        //IO setup
        FileReader reader = new FileReader("money.in");
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("money.out")));
        Scanner in = new Scanner(reader);

        int V = in.nextInt();
        int N = in.nextInt();
        cache = new long[N+1][V+1];
        cache2 = new boolean[N+1][V+1];
        listOfChange = new ArrayList<Integer>();

        for (int i = 0; i<V; i++) {
            int changeValue = in.nextInt();
            if (listOfChange.indexOf(changeValue) == -1) {
                listOfChange.add(changeValue);
            }
            cache[0][i] = 1;
            cache2[0][i] = true;
        }
        cache[0][V] = 1;
        cache2[0][V] = true;
        V = listOfChange.size();
        writer.println(money.search(N, V));
        writer.close();
    }

    public static long search(int N, int V) {
        if (N<0) {
            return 0;
        } else if (cache2[N][V] == true) {
            return cache[N][V];
        } else {
            long sum = 0;
            int size = listOfChange.size();
            for (int i = size-V; i<size; i++) {
                int changeValue = listOfChange.get(i);
                sum += money.search(N-changeValue, size-i);
            }
            cache[N][V] = sum;
            cache2[N][V] = true;

            return sum;
        }
    }
}
