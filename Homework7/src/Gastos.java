//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 7, B. Gastos perplejos
//
// Mario Contreras (705080)
//
//*********************************************************
import java.util.Arrays;
import java.util.Scanner;
/*
Sample Input
10 5 3 9 6 2 4
500 4 250 200 150 400
0

Sample Output
Secretario de Finanzas
Ni modo
*/

class Gastos {
	public static void main(String[] args) {
		// First scanner. Used for console input.
		Scanner sc1 = new Scanner(System.in);

		// Get lines until there are no more lines.
		while(sc1.hasNextLine()){
			String line = sc1.nextLine();
			// For each line we get from the console, let's parse it using a new scanner
			Scanner sc2 = new Scanner(line);
			
			// First value is the value to expend (budget?)
		    int money = sc2.nextInt();
		    // However, if we found zero, that means we have reach the end of the program
		    if(money == 0) {
		    	sc2.close();
		    	break;
		    }
		    // Second value is how many products we need to scan
		    int P = sc2.nextInt();
		    // Array of products
		    int[] products = new int[P];
		    // Populate the array from scanner (string)
		    for (int i = 0; i < products.length; i++) {
				products[i] = sc2.nextInt();
			}
		    // Sort the products
		    Arrays.sort(products);
		    // Let's try all options (2^N where N = number of products)
		    // Greedy algorithm
		    int total = 0;
		    // If we found an option where selected products values sum the total budget to expend, we have can finish successfully 
		    for (int i = 0; i < Math.pow(2, P) && total != money; i++) {
		    	total = sum(products, int2bits(i, products.length));
			}
		    // Let's print if we found a solution or not
		    if(total == money)
		    	System.out.println("Secretario de Finanzas");
		    else
		    	System.out.println("Ni modo");
			sc2.close();
		}
		System.out.println();
		sc1.close();
	}

	// Sum all selected products
	static int sum(int[] products, boolean[] selected) {
		int total = 0;
    	for (int i = 0; i < selected.length; i++) {
			if(selected[i]) total += products[i];
		}
    	return total;
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
