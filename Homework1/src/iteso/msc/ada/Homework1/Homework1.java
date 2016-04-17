package iteso.msc.ada.Homework1;

import java.util.Scanner;


public class Homework1 {
	public static long c = 0;

	public static void main(String args[]) {
		String op = null;
		Scanner in = new Scanner(System.in);
		
		do {
			System.out.println("ITESO - MSC - ADA");
			System.out.println("Mario Contreras (705080)");
			System.out.println("Homework 1");
			System.out.println();
			System.out.print("Select part (1) or part (2) or e(x)it: ");
			op = in.nextLine();
			
			// Homework 1, part 1.
			if (op.equals("1")) {
				String s1, s2;
				int elements, m, n;
				int[] array;
				Matrix A, B, C;
				
				// String Comparison
				System.out.println("String Comparison");
				System.out.print("First string: ");
				s1 = in.nextLine();
				System.out.print("Second string: ");
				s2 = in.nextLine();

				if (Utils.StringEquals(s1, s2))
					System.out.println("s1 and s2 are equals");
				else
					System.out.println("s1 and s2 are NOT equals");
				System.out.println();

				// Median
				System.out.println("Median");
				System.out.print("Number of elements: ");
				elements = in.nextInt();
				array = new int[elements];
				
				for (int i = 0; i < elements; i++) {
					System.out.print("Array[" + i + "]: ");
					array[i] = in.nextInt();
				}
				System.out.println("Median is: " + Utils.Median(array));
				System.out.println();

				// Matrix Multiplication
				System.out.println("Matrix Multiplication");
				System.out.print("First matrix, m size: ");
				m = in.nextInt();
				System.out.print("First matrix, n size: ");
				n = in.nextInt();
				A = Matrix.random(m, n);
				System.out.println();
				System.out.print("Second matrix, m size: ");
				m = in.nextInt();
				System.out.print("Second matrix, n size: ");
				n = in.nextInt();
				B = Matrix.random(m, n);

				C = A.times(B);

				System.out.println("First Matrix (A):");
				A.show();
				System.out.println("Second Matrix (B):");
				B.show();
				System.out.println("Result Matrix (C):");
				C.show();
				
				// Prime numbers [a..b]
				System.out.println("Prime numbers [a..b]");
				System.out.print("Lower bound: ");
				m = in.nextInt();
				System.out.print("Uper bound: ");
				n = in.nextInt();
				
				for (int i = m; i <= n; i++)
					if (Utils.Prime(i))
						System.out.println(i + " is a prime number");
					else
						System.out.println(i + " is NOT a prime number");
				System.out.println();
				
				// Number of divisions by 3 before reaching 1
				System.out.println("Divisions by 3");
				System.out.print("Number: ");
				m = in.nextInt();
				System.out.println(m + " was divided " + Utils.div3(m, 0) + " times by 3 in order to reach 1");
				System.out.println();

				// List all pairs (a, b) where cos(a) * sin(b) <= b / 2 * a
				System.out.println("Pairs for cos(a) * sin(b) <= b / 2 * a");
				System.out.print("Number: ");
				m = in.nextInt();
				Utils.Pairs(m);
				System.out.println();

				System.out.println();
			}
			
			// Homework 1, part 2.
			if (op.equals("2")) {
				long a, b;
				for (int n = 2; n <= 16; n++) {
					a = Fibonacci.fib(n);
					b = Fibonacci.fib(n - 1);
					c = 0;
					
					System.out.println("GCD(" + a + ", " + b + ") is: " + Euclid.gcd(a, b) + " (" + c + " divisions)");
				}
				System.out.println();
			}
			
			if (!(op.equals("1") || op.equals("2") || op.equalsIgnoreCase("x"))) {
				System.out.println("Invalid option. Try again.");
				System.out.println();
			}
		} while (!op.equalsIgnoreCase("x"));
		System.out.println("Closing program.");
		System.out.println();
		in.close();
	}
}
