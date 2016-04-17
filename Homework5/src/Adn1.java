//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 5, B. Secuencias ADN (Adn1.java)
//
// Erick González (705070)
// Mario Contreras (705080)
//
//*********************************************************
import java.util.ArrayList;
import java.util.Scanner;
/*
Sample Input
2
4 7
CG
ACG
AACG
AACT
4 17
CG
ACG
AACG
AACT

Sample Output
CG ACG AACG AACT
AACT ACG AACG CG
*/

class Adn1 {
	// Auxiliary class - represents one DNA sequence. Implements Comparable so we can sort it easily (and fast, in theory).
	static class DnaSequence implements Comparable<DnaSequence> {
		String sequence; // The DNA sequence
		long hashcode;   // The hashcode used for sorting
		// Constructor
		DnaSequence(String s, int p) {
			sequence = s;
			hashcode = num(s, p); // hashcode is calculated by num() method using a prime number
		}
		@Override
		public int compareTo(DnaSequence dna) {
			// If the hashcode is the same, we won't use it... we use sequence instead.
			int retval = Long.compare(this.hashcode, dna.hashcode);
			if(retval == 0)
				retval = this.sequence.compareTo(dna.sequence);
			return retval;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// How many test cases
		int T = sc.nextInt();
		
		// For each test case
		for(int t = 0; t < T; t++) {
			// How many sequences
			int N = sc.nextInt();
			// Prime number
			int p = sc.nextInt();
			// List of sequences
			ArrayList<DnaSequence> sequences = new ArrayList<>();
			// Add all typed sequences to the list
			for(int n = 0; n < N; n++) {
				sequences.add(new DnaSequence(sc.next(), p));
			}
			// Let's sort it. This will call DnaSequence.compareTo() method.
			sequences.sort(null);
//			for(DnaSequence dna : sequences) System.out.print(dna.sequence + " ");
//			System.out.println();
//			for(DnaSequence dna : sequences) System.out.print(dna.hashcode + " ");
			// Show the first 5 elements of the list
			for(int i = 0; i < sequences.size() && i < 5; i++) System.out.print(sequences.get(i).sequence + " ");
			// Line break for each test case
			System.out.println();
		}
		System.out.println();
		sc.close();
	}

	// Used to get the numeric value of each nucleotide
	static int getValue(char c) {
		switch(c) {
		case 'A': return 1;
		case 'C': return 2;
		case 'G': return 3;
		case 'T': return 4;
		default: return 0;
		}
	}

	// Get the DNA sequence hashcode
	// num(adn) = (adn[0] + adn[1]*4 + adn[2]*4^2 + ...) % prime
	static long num(String dna, int prime) {
		long h = getValue(dna.charAt(0));
		for(int i = 1; i < dna.length(); i++) {
			h = (h + (long)Math.pow(4, i) * getValue(dna.charAt(i))) % prime;
		}
		return h;
	}
}
