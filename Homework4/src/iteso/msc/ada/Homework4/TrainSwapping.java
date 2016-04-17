//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 4, A. Train swapping
//
// Mario Contreras (705080)
//
//*********************************************************
package iteso.msc.ada.Homework4;

import java.util.Scanner;


public class TrainSwapping {
	public static void main(String[] args) {
//		System.out.println("ITESO - MSC - ADA");
//		System.out.println("Mario Contreras (705080)");
//		System.out.println("Homework 4");
//		System.out.println("A. Train swapping");
//		System.out.println();

		Scanner sc = new Scanner(System.in);
		// Number of test cases
		int N = sc.nextInt();

		// For each test case...
		for(int n = 0; n < N; n++) {
			// Train length
			int L = sc.nextInt();
			int[] array = new int[L];
			
			// Get all carriages
			for(int l = 0; l < L; l++) {
				array[l] = sc.nextInt();
			}
			
		    boolean swapped = true;
			int swap = 0;

			// Bubble sort can be used for this problem
		    while (swapped) {
		    	swapped = false;
				for(int i = 0; i < array.length - 1; i++) {
					if (array[i] > array[i + 1]) {
						swap(array, i, i + 1);
						swap++;
						swapped = true;
					}
				}
		    }
		    // Output
			System.out.println("Optimal train swapping takes " + swap + " swaps.");
		}
		System.out.println();
		sc.close();
	}

	// Auxiliary method
	public static void swap(int[] array, int i1, int i2) {
		int tmp = array[i1];

		array[i1] = array[i2];
		array[i2] = tmp;
	}
}

// EOF
