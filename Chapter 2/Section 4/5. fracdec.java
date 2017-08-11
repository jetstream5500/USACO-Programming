/*
TASK: fracdec
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Octal
// LAST MODIFIED    :   2-6-14
// PROBLEM ID       :   fracdec
// DESCRIPTION      :   Divides and mods the number to produce next number.
//                      When there is a repeat outcome I find the spot where
//                      it last appeared and insert the parenthesis. If it
//                      ever converges on 0 there is no parenthesis.  Wasn't
//                      working orginally because of indexOf function.  I
//                      converted it to an array holding the location of the
//                      number.  Also pre-did all calculations.
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
public class fracdec
{
    // Allows writer to be used in function
    static PrintWriter writer;
    public static void main(String args[]) throws IOException {
        // IO setup
        BufferedReader reader = new BufferedReader(new FileReader("fracdec.in"));
        writer = new PrintWriter( new BufferedWriter( new FileWriter("fracdec.out")));
        Scanner in = new Scanner(reader);
        
        // Inputs information
        int N = in.nextInt();
        int D = in.nextInt();

        // Sets up arrays for division, modding, and for positions of numbers
        int[] divide = new int[D*10];
        int[] mod = new int[D*10];
        int[] used2 = new int[D*10];
        
        //Increments through doing division, modding, and setting locations to -1.
        int spot = 0;
        for (int i = 0; i<10; i++) {
            for (int j = 0; j<D; j++) {
                divide[spot] = i;
                mod[spot] = j;
                used2[spot] = -1;
                spot++;
            }
        }
        
        // Sets up arraylists holding decimals and one for length
        // (First could be optimized out)
        ArrayList<Integer> used = new ArrayList<Integer>();
        ArrayList<Integer> decimals = new ArrayList<Integer>();
        boolean endNotFound = true;
        
        int wholeNumber = N/D;
        int currentMod = N%D;
        used.add(currentMod);
        used2[currentMod] = used.size()-1;
        
        // Finds all the decimals of the number and when it comes across
        // the same number or 0 it stops.
        int position = 0;
        while (endNotFound && (currentMod != 0)) {
            currentMod *= 10;
            decimals.add(divide[currentMod]);
            currentMod = mod[currentMod];
            int location = used2[currentMod];
            if (location == -1) {
                used.add(currentMod);
                used2[currentMod] = used.size()-1;
            } else {
                endNotFound = false;
                position = location;
            }
        }

        // Counting digits
        int counter = 0;
        int newWholeNumber = wholeNumber;
        if (newWholeNumber == 0) {
            counter++;
        }
        while (newWholeNumber > 0) {
            newWholeNumber/=10;
            counter++;
        }
        
        
        // Printing.  Also counts digits and checks for newline
        if (decimals.size() == 0) {
            writer.println(wholeNumber + ".0");
            counter+=2;
        } else if (currentMod == 0) {
            writer.print(wholeNumber + ".");
            counter++;
            counter = fracdec.checkCounter(counter);
            for (int i = 0; i<decimals.size()-1; i++) {
                writer.print(decimals.get(i));
                counter++;
                counter = fracdec.checkCounter(counter);
            }
            writer.println(decimals.get(decimals.size()-1));
        } else {
            writer.print(wholeNumber + ".");
            counter++;
            counter = fracdec.checkCounter(counter);
            boolean startParenth = false;
            for (int i = 0; i<decimals.size()-1; i++) {
                if (i == position) {
                    writer.print("(");
                    counter++;
                    counter = fracdec.checkCounter(counter);
                    startParenth = true;
                }
                writer.print(decimals.get(i));
                counter++;
                counter = fracdec.checkCounter(counter);
            }
            if (!startParenth) {
                writer.print("(");
                counter++;
                counter = fracdec.checkCounter(counter);
            }
            writer.println(decimals.get(decimals.size()-1) + ")");
        }
        
        // Closes PrintWriter
        writer.close();
    }
    
    public static int checkCounter(int counter) {
        // Checks if newline is needed
        if (counter == 76) {
            writer.println();
            return 0;
        } else {
            return counter;
        }
    }
}