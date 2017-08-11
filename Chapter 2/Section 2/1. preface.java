/*
TASK: preface
ID: mcokzoo1
LANG: JAVA
 */

// NAME             : Michael Orwin
// GROUP            : Freshman
// LAST MODIFIED    : June 8, 2013 - Summer 2013
// PROBLEM ID       : preface
// DESCRIPTION      : The way my program works is it uses formulas to
//                    calucate how many ruman numberals to use.  If a
//                    number is divisble by 5 than that means that it
//                    uses 7 I's.  If divisible by 10 than it uses 5 V's.
//                    If divisible by 10 it uses 10 X's.  Than it loops
//                    back through and works with the same numbers
//                    multiplied by 10.  It also takes into account
//                    leftover pieces.  Also the fact that 10's, 50's,
//                    100's, etc.  don't go 10, 20, 30 they go 10, 11,
//                    12, 13, ... 30.

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

public class preface
{
    public static void main(String[] args) throws IOException {
        //IO setup
        FileReader reader = new FileReader("preface.in");
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("preface.out")));
        Scanner in = new Scanner(reader);

        //Intializes Variables
        int num = in.nextInt();
        int num2 = num;
        int[] numOfLetters = new int[9];
        String[] letters = new String[7];
        
        //Adds letters for printing
        letters[0] = "I";
        letters[1] = "V";
        letters[2] = "X";
        letters[3] = "L";
        letters[4] = "C";
        letters[5] = "D";
        letters[6] = "M";
        
        //Loops through 4 times to take care of 1*(10^i) and 5*(10^i)
        for (int i = 0; i<4; i++) {
            //Multiplies by 7 if divisble by 5 (Takes into account different i's)
            numOfLetters[i*2] += ((num/5)*7)*Math.pow(10, i);
            
            int mod5 = (num%5);
            
            //If its less than 4 than it is sequential.  Other wise you just add one for four (Ex. IV)
            //The added number takes into account the leftovers.  (Ex. the 87 -> 8%5 = 3 -> 1*10  + 2*10 + 3*7)
            if (mod5 < 4) {
                numOfLetters[i*2] += ((((mod5-1)*(mod5))/(2)) * Math.pow(10, i)) + (mod5*((num2 % Math.pow(10, i))+1));
            } else {
                numOfLetters[i*2] += ((((mod5-1)*(mod5))/(2)) * Math.pow(10, i)) + (1*((num2 % Math.pow(10, i))+1));
            }
            
            //If divisible by 10, add 5, 50, or 500 depending on i (Ex. 20 -> (IV, V, VI, VII, VIII)*Quotient)
            numOfLetters[(i*2)+1] += (num/10)*5*Math.pow(10, i);
            
            //If divisible by 10, add 1, 10, or 100 depending on i (Ex. (IX)*Quotient)
            numOfLetters[(i*2)+2] += (num/10)*Math.pow(10, i);
            
            int mod10 = (num%10);
            
            //If its greater than 8 all are accounted for and another 5, 50, or 500 can be added.
            //Otherwise the number that have been completed is calculated and the leftover is added
            if (mod10>8) {
                numOfLetters[(i*2)+1] += 5*Math.pow(10, i);
            } else if (mod10>3) {
                numOfLetters[(i*2)+1] += ((mod10-4) * Math.pow(10, i)) + (1*((num2 % Math.pow(10, i))+1));
            }
            
            //This takes into account if the number is not divisble by 10 but still has a 9 on the end.
            if (mod10 == 9) {
                numOfLetters[(i*2)+2] += (1*((num2 % Math.pow(10, i))+1));
            }
            
            //num is divided to work with the next two numbers
            num = num/10;
        }
        
        //Printing
        for (int i = 0; i<numOfLetters.length-1; i++) {
            if (numOfLetters[i] > 0) {
                writer.println(letters[i] + " " + numOfLetters[i]);
            }
        }
        
        //Quit writer
        writer.close();
    }
}