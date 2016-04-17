//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 4, B. Formación por estaturas
//
// Mario Contreras (705080)
//
//*********************************************************
package iteso.msc.ada.Homework4;

import java.util.Scanner;


public class HeightAliment {
	public static void main(String[] args) {
//		System.out.println("ITESO - MSC - ADA");
//		System.out.println("Mario Contreras (705080)");
//		System.out.println("Homework 4");
//		System.out.println("B. Formación por estaturas");
//		System.out.println();

		Scanner sc = new Scanner(System.in);
		// Number of test cases
		int T = sc.nextInt();
		
		// For each test case...
		for(int t = 0; t < T; t++) {
			// How many students
			int N = sc.nextInt();
			String[] students = new String[N];
			String[] sorted = new String[N];
			
			// Get the students
			for(int n = 0; n < N; n++) {
				students[n] = sc.next();
			}
			// Get the model / expected result
			for(int n = 0; n < N; n++) {
				sorted[n] = sc.next();
			}

			// Sort the students (insertion sort?)
			// Boolean flag in order to know if we have moved at least one student
			boolean swapped = false;
			// Let's check all the elements in the sorted array searching for the "smallest" element
			for(int i = 0; i < sorted.length - 1; i++) {
				for (int j = i + 1; j <= sorted.length - 1; j++) {
					if (students[j].equals(sorted[i])) {
						// If a match was found, then move all previous elements to the right and add the current element
						for(int k = j - 1; k >= i; k--)
							students[k + 1] = students[k];
						// Print the element we just moved
						System.out.print(sorted[i] + " ");
						// And mark our flag as true
						swapped = true;
						//break;
					}
				}
			}
			// If no elements were moved, then notify list is sorted
			if(!swapped)
				System.out.println("Ordenado");
			else
				System.out.println();
		}
		System.out.println();
		sc.close();
	}
}

// EOF
