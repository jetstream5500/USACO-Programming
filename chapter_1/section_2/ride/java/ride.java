/*
ID: mcokzoo1
LANG: JAVA
TASK: ride
*/

import java.io.*;
import java.util.*;

public class ride {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("ride.in"));
		PrintWriter out = new PrintWriter("ride.out");
		int group = 1;
		int comet = 1;
		for (char c : in.next().toCharArray()) {
			group *= (int) c - 64;
		}
		for (char c : in.next().toCharArray()) {
			comet *= (int) c - 64;
		}
		int a = true ? 0 : 1;
		out.println((group-comet) % 47 == 0 ? "GO" : "STAY");
		out.close();
	}
}
