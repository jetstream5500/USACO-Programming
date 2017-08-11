/*
TASK: concom
ID: mcokzoo1
LANG: JAVA
 */

// NAME             :   Michael Orwin
// GROUP            :   Octal
// LAST MODIFIED    :   2/6/14
// PROBLEM ID       :   concom
// DESCRIPTION      :   Basically does what instructions says.  Adds all percents
//                      To corresponding companies that own them.  After this print
//                      All countries that are greater than 50% (Changed>50 = 100)
// SOURCES/HELPERS  :   N/A

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

/**
 * @author Michael Orwin
 * @version 2/6/14
 */
public class concom
{
    public static void main(String args[]) throws IOException {     
        // File Setup + Scanner for File Reader
        FileReader reader = new FileReader("concom.in");
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("concom.out")));
        Scanner in = new Scanner(reader);

        // Inputs numLines
        int numOfTriples = in.nextInt();

        int[][] companies = new int[101][101];
        int maxCompanies = 0;
        for (int i = 0; i<numOfTriples; i++) {
            int owner = in.nextInt();
            int otherPerson = in.nextInt();
            int percent = in.nextInt();
            companies[owner][otherPerson] = percent;
            if ((owner > otherPerson) && (owner > maxCompanies)) {
                maxCompanies = owner;
            } else if ((otherPerson > owner) && (otherPerson > maxCompanies)) {
                maxCompanies = otherPerson;
            }
        }

        boolean[] completed = new boolean[101];
        for (int i = 1; i<=maxCompanies; i++) {
            boolean done = true;
            for (int j = 1; j<=maxCompanies; j++) {
                if (i != j) {
                    int percentage = companies[i][j];
                    if (!completed[j]) {
                        if (percentage > 50) {
                            completed[j] = true;
                            done = false;
                            for (int k = 1; k<=maxCompanies; k++) {
                                if (companies[i][k] + companies[j][k] > 100) {
                                    companies[i][k] = 100;
                                } else {
                                    companies[i][k] = companies[i][k] + companies[j][k];
                                }
                            }
                        }
                    }
                }
            }
            if (!done) {
                i--;
            } else {
                completed = new boolean[101];
            }
        }

        for (int i = 1; i<=maxCompanies; i++) {
            for (int j = 1; j<=maxCompanies; j++) {
                if (i!=j) {
                    if (companies[i][j] > 50) {
                        writer.println(i + " " + j);
                    }
                }
            }
        }
        // Ends the PrintWriter
        writer.close();
    }
}
