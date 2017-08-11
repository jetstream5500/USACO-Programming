/*
TASK: zerosum
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Octal
// LAST MODIFIED    :   2/6/14
// PROBLEM ID       :   zerosum
// DESCRIPTION      :   Substituted "+", "-",  and " " to see which totals added up
//                      to zero. I did this problem a long time ago.  Looks like I
//                      used recursion for sums.  Basically it looks like a brute
//                      force where each symbol is supplied and tested for.
// SOURCES/HELPERS  :   N/A

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

/**
 * @author Michael Orwin
 * @version 2/6/14
 */
public class zerosum
{
    static int num;
    //static long[][] cache;
    //static ArrayList<Integer> listOfChange;
    static PrintWriter writer;
    public static void main(String args[]) throws IOException {
        //IO setup
        FileReader reader = new FileReader("zerosum.in");
        writer = new PrintWriter( new BufferedWriter( new FileWriter("zerosum.out")));
        Scanner in = new Scanner(reader);

        num = in.nextInt();
        zerosum.DP(num, 0, "", "+", "");
        writer.close();
    }

    public static void DP(int numsLeft, int sum, String previousSigns, String sign, String str) {
        if (numsLeft != 0) {
            int currentNumber = ((num-numsLeft) + 1);
            String additionToString = str + sign + currentNumber;
            if (sign.equals("+")) {
                if ((numsLeft == 1) && (sum+currentNumber == 0)) {
                    writer.println(additionToString.substring(1, additionToString.length()));
                } else {
                    zerosum.DP(numsLeft-1, sum+currentNumber, previousSigns+"+", " ", additionToString);
                    zerosum.DP(numsLeft-1, sum+currentNumber, previousSigns+"+", "+", additionToString);
                    zerosum.DP(numsLeft-1, sum+currentNumber, previousSigns+"+", "-", additionToString);
                }
            } else if (sign.equals("-")) {
                if ((numsLeft == 1) && (sum-currentNumber == 0)) {
                    writer.println(additionToString.substring(1, additionToString.length()));
                } else {
                    zerosum.DP(numsLeft-1, sum-currentNumber, previousSigns+"-", " ", additionToString);
                    zerosum.DP(numsLeft-1, sum-currentNumber, previousSigns+"-", "+", additionToString);
                    zerosum.DP(numsLeft-1, sum-currentNumber, previousSigns+"-", "-", additionToString);
                }
            } else {
                int newSum = sum;
                int counter = 1;
                String symbol = "";
                for (int i = previousSigns.length()-1; i>=0; i--) {
                    if (previousSigns.substring(i, i+1).equals(" ")) {
                        counter++;
                    } else {
                        symbol = previousSigns.substring(i, i+1);
                        break;
                    }
                }
                int difference = 0;
                for (int i = currentNumber-counter; i<currentNumber; i++) {
                    difference = difference*10 + i;
                }
                if (symbol.equals("+")) {
                    newSum -= difference;
                    difference = difference*10 + currentNumber;
                    newSum += difference;
                } else {
                    newSum += difference;
                    difference = difference*10 + currentNumber;
                    newSum -= difference;
                }
                if ((numsLeft == 1) && (newSum == 0)) {
                    writer.println(additionToString.substring(1, additionToString.length()));
                } else {
                    zerosum.DP(numsLeft-1, newSum, previousSigns+" ", " ", additionToString);
                    zerosum.DP(numsLeft-1, newSum, previousSigns+" ", "+", additionToString);
                    zerosum.DP(numsLeft-1, newSum, previousSigns+" ", "-", additionToString);
                }
            }
        }
    }
}
