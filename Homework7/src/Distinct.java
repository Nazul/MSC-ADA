//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 7, D. Distinct Subsequences
//
// Mario Contreras (705080)
//
//*********************************************************
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
Sample Input
2
babgbag
bag
rabbbit
rabbit

Sample Output
5
3
*/

class Distinct {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// How many test cases
		int N = sc.nextInt();
		
		// For each test case
		for(int n = 0; n < N; n++) {
			// Get 2 strings
			String X = sc.next();
			String Z = sc.next();
			// Get all possible solutions and print how many we had found
			System.out.println(getSolutions(X, Z).size());
		}
		System.out.println();
		sc.close();
	}

	static ArrayList<int[]> getSolutions(String X, String Z) {
		// All solutions
		ArrayList<int[]> S = new ArrayList<>();
		
		// Let's try to get a solution, but we will be "removing" characters from X using a visited boolean array
		// This will test 2^N cases where N is X's length
		// Basically this method should work like a greedy algorithm
		for (int i = 0; i < Math.pow(2, X.length()); i++) {
			// Let's see if we can find a solution based on the current set
			int[] s = getSolution(X, Z, int2bits(i, X.length()));
			// s will store an array of indexes where Z was found, however, if one or more characters coudn't be found...
			// then s will have Integer.MAX_VALUE in one or more elements
			// So let's see if this is a real solution or not
			if(Arrays.binarySearch(s, Integer.MAX_VALUE) < 0) {
				// If it is a real solution, let's make sure it's a new solution
				if(!isInList(S, s)) {
					// If is is a new solution, then add it to the solutions list (S)
					S.add(s);
				}
			}
		}
		// Return all solutions
		return S;
	}

	private static int[] getSolution(String X, String Z, boolean[] visited) {
		// An array that represents all the indexes where we can find Z in X
		// But visited could remove some of the characters
		int[] s = new int[Z.length()];
		for (int i = 0; i < s.length; i++) {
			// Default value -> represents not found
			s[i] = Integer.MAX_VALUE;
		}
		for (int i = 0, k = 0; i < X.length(); i++) {
			// If we found a match and it was not marked as visited, then add it to the solution array
			if(X.charAt(i) == Z.charAt(k) && !visited[i]) {
				s[k++] = i;
				if(k == Z.length()) {
					break;
				}
			}
		}
		// Return the "solution"
		return s;
	}

	// Checks if an array is already stored in a list of arrays (value comparison, not reference comparison)
	static boolean isInList(ArrayList<int[]> list, int[] array) {
		for(int[] item : list) {
			if(Arrays.equals(item, array)) {
				return true;
			}
		}
		return false;
	}

	// Converts an integer value to an array of booleans
	static boolean[] int2bits(int value, int size) {
		boolean[] bits = new boolean[size];
		String binary = Integer.toBinaryString(value);
		for (int i = binary.length() - 1, j = bits.length - 1; i >= 0; i--, j--) {
			bits[j] = binary.charAt(i) == '1' ? true : false;
		}
		return bits;
	}
}

//EOF
