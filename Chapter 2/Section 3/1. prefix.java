/*
TASK: prefix
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Octal
// LAST MODIFIED    :   2-6-14
// PROBLEM ID       :   prefix
// DESCRIPTION      :   This idea was given to me by Rob Kolstad.
//                      Got stuck on problem with time and helped
//                      me through it (Prob: Strings are not mutable)
//                      Showed me an explanation with DP and cleared up
//                      my understanding on DP.  Program gets primitives
//                      and string.  Goes through and says a prefix can make
//                      a substring if it ends in a prefix and the previous
//                      part was a prefix.  This was stored in an array with
//                      booleans.  After passing through the whole string
//                      the last 1 in the array was the length of the longest
//                      prefix.
// SOURCES/HELPERS  :   Rob Kolstad

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

/** 
 * @author Michael Orwin
 * @version 2/6/14
 */
public class prefix
{
    public static void main(String args[]) throws IOException {
        //IO setup
        BufferedReader reader = new BufferedReader(new FileReader("prefix.in"));
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("prefix.out")));
        // Inputs Primitives
        ArrayList<String> subStrs = new ArrayList<String>();
        ArrayList<Integer> subStrLens = new ArrayList<Integer>();
        
        String subStrings = "";
        while (1==1) {
            String primitives = reader.readLine();
            if (primitives.contains(".")) {
                break;
            } else {
                subStrings += primitives + " ";
            }
        }
        Scanner in = new Scanner(subStrings);
        while (in.hasNext()) {
            String subStr = in.next();
            subStrs.add(subStr);
            subStrLens.add(subStr.length());
        }

        // Inputs String
        StringBuilder builder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String str = builder.toString();
        int totalstrlength = str.length();
        
        
        boolean[] dp = new boolean[str.length()+1];
        dp[0] = true;

        String endPart = "";
        for (int i = 1; i<=totalstrlength; i++) {
            endPart = str.substring( Math.max (0, i-11), i);
            for (int j = 0; j<subStrs.size(); j++) {
                String subStr = subStrs.get(j);
                int len = subStrLens.get(j);
                if (i-len >= 0) {
                    if ((dp[i-len]) && endPart.endsWith(subStr)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        for (int i = dp.length-1; i>=0; i--) {
            if (dp[i]) {
                writer.println(i);
                break;
            }
        }
        writer.close();
    }
}