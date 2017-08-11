/*
TASK: lamps
ID: mcokzoo1
LANG: JAVA
 */

// NAME             : Michael Orwin
// GROUP            : Octal
// LAST MODIFIED    : April 26, 2014
// PROBLEM ID       : lamps
// DESCRIPTION        The way my program works is it does brute force
//                    but uses the insight that it repeats every 6, 
//                    order of switches doesn't matter and 2 clicks = nothing
//                    this mean it is pascal triangle number of possibilites.
//

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

/**
 * lamps
 * 
 * @author Michael Orwin
 * @version 8/21/13
 */
public class lamps
{
    public static void main(String args[]) throws IOException {
        //IO setup
        FileReader reader = new FileReader("lamps.in");
        PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter("lamps.out")));
        Scanner in = new Scanner(reader);

        int N = in.nextInt();
        int C = in.nextInt();

        ArrayList<String> possibles = new ArrayList<String>();
        String lights = "";
        for (int i = 0; i<N; i++) {
            lights = lights + "1";
        }
        possibles.add(lights);

        possibles.add(lamps.button1(lights));
        possibles.add(lamps.button2(lights));
        possibles.add(lamps.button3(lights));
        possibles.add(lamps.button4(lights));

        possibles.add(lamps.button1(lamps.button2(lights)));
        possibles.add(lamps.button1(lamps.button3(lights)));
        possibles.add(lamps.button1(lamps.button4(lights)));
        possibles.add(lamps.button2(lamps.button3(lights)));
        possibles.add(lamps.button2(lamps.button4(lights)));
        possibles.add(lamps.button3(lamps.button4(lights)));

        possibles.add(lamps.button1(lamps.button2(lamps.button3(lights))));
        possibles.add(lamps.button1(lamps.button2(lamps.button4(lights))));
        possibles.add(lamps.button1(lamps.button3(lamps.button4(lights))));
        possibles.add(lamps.button2(lamps.button3(lamps.button4(lights))));

        possibles.add(lamps.button1(lamps.button2(lamps.button3(lamps.button4(lights)))));
        /*
        for (int i = 0; i<possibles.size(); i++) {
        String lamp = possibles.get(i);
        System.out.println(lamp);
        if ((i==0) || (i==4) || (i==10) || (i==14)) {
        System.out.println();
        }
        }
         */

        while (1==1) {
            int position = in.nextInt();
            if (position == -1) {
                break;
            } else {
                for (int i = 0; i<possibles.size(); i++) {
                    String lamps = possibles.get(i);
                    if (lamps.equals("x")) {
                        continue;
                    }
                    if (lamps.substring(position-1, position).equals("0")) {
                        possibles.set(i, "x");
                    }
                }
            }
        }

        while (1==1) {
            int position = in.nextInt();
            if (position == -1) {
                break;
            } else {
                for (int i = 0; i<possibles.size(); i++) {
                    String lamps = possibles.get(i);
                    if (lamps.equals("x")) {
                        continue;
                    }
                    if (lamps.substring(position-1, position).equals("1")) {
                        possibles.set(i, "x");
                    }
                }
            }
        }

        ArrayList<String> finals = new ArrayList<String>();

        for (int i = 0; i<possibles.size(); i++) {
            String lamps = possibles.get(i);
            if (lamps.equals("x")) {
                continue;
            }
            if ((i<1) && ((C==0) || (C==2) || (C>4))) {
                finals.add(lamps);
            } else if ((i<5) && ((C==1) || (C==3) || (C>5))) { 
                finals.add(lamps);
            } else if ((i<11) && ((C==2) || (C==4) || (C>6))) {
                finals.add(lamps);
            } else if ((i<15) && ((C==3) || (C==5) || (C>7))){
                finals.add(lamps);
            } else if ((i<16) && ((C==4) || (C==6) || (C>8))) {
                finals.add(lamps);
            }
        }

        Collections.sort(finals);

        String previous = "";
        if (finals.size() == 0) {
            writer.println("IMPOSSIBLE");
        } else {
            for (int i = 0; i<finals.size(); i++) {
                String lamps = finals.get(i);
                if (!lamps.equals(previous)) {
                    writer.println(lamps);
                    previous = lamps;
                }
            }
        }
        writer.close();
    }

    public static String button1(String lamps) {
        String newLamps = "";
        for (int i = 0; i<lamps.length(); i++) {
            String digit = lamps.substring(i, i+1);
            if (digit.equals("0")) {
                newLamps = newLamps + "1";
            } else {
                newLamps = newLamps + "0";
            }
        }
        return newLamps;
    }

    public static String button2(String lamps) {
        String newLamps = "";
        for (int i = 0; i<lamps.length(); i++) {
            String digit = lamps.substring(i, i+1);
            if (i%2 == 1) {
                newLamps = newLamps + digit;
            } else {
                if (digit.equals("0")) {
                    newLamps = newLamps + "1";
                } else {
                    newLamps = newLamps + "0";
                }
            }
        }
        return newLamps;
    }

    public static String button3(String lamps) {
        String newLamps = "";
        for (int i = 0; i<lamps.length(); i++) {
            String digit = lamps.substring(i, i+1);
            if (i%2 == 0) {
                newLamps = newLamps + digit;
            } else {
                if (digit.equals("0")) {
                    newLamps = newLamps + "1";
                } else {
                    newLamps = newLamps + "0";
                }
            }
        }
        return newLamps;
    }

    public static String button4(String lamps) {
        String newLamps = "";
        for (int i = 0; i<lamps.length(); i++) {
            String digit = lamps.substring(i, i+1);
            if (i%3 != 0) {
                newLamps = newLamps + digit;
            } else {
                if (digit.equals("0")) {
                    newLamps = newLamps + "1";
                } else {
                    newLamps = newLamps + "0";
                }
            }
        }
        return newLamps;
    }
}
