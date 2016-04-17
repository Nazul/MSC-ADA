//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 4, D. Eclipse planetario
//
// Mario Contreras (705080)
//
//*********************************************************
package iteso.msc.ada.Homework4;

//import java.math.BigInteger;
import java.util.Scanner;


public class Eclipse {
	public static void main(String[] args) {
//		System.out.println("ITESO - MSC - ADA");
//		System.out.println("Mario Contreras (705080)");
//		System.out.println("Homework 4");
//		System.out.println("D. Eclipse planetario");
//		System.out.println();

		Scanner sc = new Scanner(System.in);
		// Number of test cases
		int N = sc.nextInt();

		// For each test case...
		for(int n = 0; n < N; n++) {
			// Time for first planet (a)
			long a = sc.nextLong();
			// Time for second planet (b)
			long b = sc.nextLong();

			// Get the result
//			long gcd = gcd(a, b);
//			BigInteger result = BigInteger.valueOf(a);
//			result = result.multiply(BigInteger.valueOf(b));
//			result = result.divide(BigInteger.valueOf(gcd));
			long result = gcd(a, b);
			result = b / result;
			result *= a;
			// Output
			System.out.println(result);
		}
		System.out.println();
		sc.close();
	}
	
	// Auxiliary method. Greatest Common Divisor (non-recursive).
    public static long gcd(long p, long q) {
        while (q != 0) {
            long temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }
}

// EOF
