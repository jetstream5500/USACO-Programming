/*
TASK: runround
ID: mcokzoo1
LANG: JAVA
 */

// NAME             : Michael Orwin
// GROUP            : Octal
// LAST MODIFIED    : April 26, 2014
// PROBLEM ID       : runround
// DESCRIPTION      : The way my program works is it mods all numbers by
//                    the length of the number.  It brute forces all
//                    possibilites except when its not unique and the
//                    and the table already has the value of a given
//                    test for a runaround.

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

/**
 * Write a description of class lamps here.
 * 
 * @author Michael Orwin
 * @version 8/15/13
 */
public class runround
{
    static ArrayList<String> modNumbers;
    public static void main(String args[]) throws IOException {
        //IO setup
        FileReader reader = new FileReader("runround.in");
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("runround.out")));
        Scanner in = new Scanner(reader);
        modNumbers = new ArrayList<String>();
        String num = in.next();
        int number = Integer.parseInt(num);
        number++;
        number = runround.nextUnique(number);
        num = number + "";
        while (1==1) {
            String modNum = "";
            for (int i = 0; i<num.length(); i++) {
                int digit = Integer.parseInt(num.substring(i, i+1));
                int newDigit = digit % num.length();
                modNum = modNum + newDigit;
            }
            if (runround.isARunaround(modNum)) {
                writer.println(num);
                break;
            } else {
                for (int i = 0; i<num.length(); i++) {
                    modNumbers.add(modNum.substring(num.length()-1) + modNum.substring(0, num.length()-1));
                }
                number = Integer.parseInt(num);
                number++;
                number = runround.nextUnique(number);
                num = number + "";
            }
        }
        writer.close();
    }

    public static boolean isARunaround(String num) {
        if (modNumbers.indexOf(num) != -1) {
            return false;
        } else {
            ArrayList<Integer> positionsUsed = new ArrayList<Integer>();
            int position = 0;
            for (int i = 0; i<num.length(); i++) {
                int movement = Integer.parseInt(num.substring(position, position+1));
                int newPosition = (position + movement) % num.length();
                if (positionsUsed.indexOf(newPosition) != -1){
                    return false;
                } else {
                    positionsUsed.add(newPosition);
                    position = newPosition;
                }
            }
        }
        return true;
    }

    public static int nextUnique(int number) {
        while (1==1) {
            String num = number + "";
            if ((!(num.contains("0"))) && (!(num.contains(""+num.length())))) {
                int[] nums = new int[10];
                boolean toContinue = false;
                for (int i = 0; i<num.length(); i++) {
                    int digit = Integer.parseInt(num.substring(i, i+1));
                    if (nums[digit] == 0) {
                        nums[digit] = 1;
                    } else {
                        number++;
                        toContinue = true;
                        break;
                    }
                }
                if (toContinue) {
                    continue;
                }
                break;
            } else {
                number++;
            }
        }
        return number;
    }
}
