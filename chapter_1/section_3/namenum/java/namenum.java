/*
ID: mcokzoo1
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.*;

public class namenum {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("namenum.in"));
		PrintWriter fout = new PrintWriter("namenum.out");
		Scanner din = new Scanner(new File("dict.txt"));

		String brandNumber = fin.next();
		boolean printed = false;
		while (din.hasNext()) {
			String name = din.next();
			if (validName(name, brandNumber)) {
				fout.println(name);
				printed = true;
			}
		}
		if (!printed) {
			fout.println("NONE");
		}
		fout.close();
	}

	public static boolean validName(String name, String brandNumber) {
		String total = "";
		for (int i = 0; i<name.length(); i++) {
			int val = name.charAt(i) - 'A';
			if (val > 15) {
				val--;
			}
			total += val / 3 + 2;
		}
		return (total.equals(brandNumber));
	}
}
