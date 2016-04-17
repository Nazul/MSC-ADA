//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 4, C. Minúsculas
//
// Mario Contreras (705080)
//
//*********************************************************
package iteso.msc.ada.Homework4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class Lowercase {
	public static void main(String[] args) {
//		System.out.println("ITESO - MSC - ADA");
//		System.out.println("Mario Contreras (705080)");
//		System.out.println("Homework 4");
//		System.out.println("C. Minúsculas");
//		System.out.println();

		Scanner sc = new Scanner(System.in);
		// Number of test cases
		int T = sc.nextInt();

		// For each test case...
		for(int t = 0; t < T; t++) {
			// How many words
			int N = sc.nextInt();
			// List (queue) for storing words
			LinkedList<String> words = new LinkedList<String>();
			
			// Get all words and add them to the queue
			for(int n = 0; n < N; n++) {
				words.add(sc.next());
			}

			// While we have more than 2 words, we still have to merge them
			while(words.size() > 1) {
				// Merge 2 words from the queue
				String word = words.remove();
				if (!words.isEmpty())
					word += words.remove();
				// Convert the word to a char array...
				char[] chars = word.toCharArray();
				// ... and sort the chars
				Arrays.sort(chars);
				// Revert char array to string...
				word = new String(chars);
				// ... and add it again to the queue
				words.add(word);
				// Output so far
				System.out.println(word);
			}
			// Break between test cases
			System.out.println();
		}
		System.out.println();
		sc.close();
	}
}

// EOF
