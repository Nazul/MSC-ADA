package nazul.Temp;

import java.util.Arrays;
import java.util.Random;

public class Temp {

//	static void t1(String s) {
//		s = s.substring(3, 5);
//		System.out.println(s);
//	}
//	
//	public static void main(String args[]) {
//		String s = "Hola, mundo";
//		
//		System.out.println(s);
//		t1(s);
//		System.out.println(s);
//	}

	public static void main(String[] args) {

	    int input = 15;

	    boolean[] bits = new boolean[7];
	    for (int i = 6; i >= 0; i--) {
	        bits[i] = (input & (1 << i)) != 0;
	    }

	    System.out.println(input + " = " + Arrays.toString(bits));
	}
}
