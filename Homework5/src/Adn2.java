//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 5, C. Más de secuencias ADN (Adn2.java)
//
// Erick González (705070)
// Mario Contreras (705080)
//
//*********************************************************
import java.util.Scanner;
/*
Sample Input
1
4 7
ACGT
AACG
GTCC
TTAC
GAGT
GCAT
AAAC
4
TTAC
AAAC
GAGT
GCAA

Sample Output
1 3 2 2
*/

class Adn2 {
	// Gets the position of a sequence in the childs[] array
	static int getValue(char c) {
		switch(c) {
		case 'A': return 0;
		case 'C': return 1;
		case 'G': return 2;
		case 'T': return 3;
		default: return -1;
		}
	}

	// Auxiliary class - represents one DNA sequence. childs[] array stores references to other sequences creating a tree.
	static class DnaSequence {
		String sequence; // The DNA sequence - tree root is not a real sequence, just the word "root"
		// Childs. 1..4 (position is calculated by getValue())
		DnaSequence[] childs = {null, null, null, null};
		// Constructor
		DnaSequence(String s) {
			sequence = s;
		}
	}
	
	// Current tree root
	static DnaSequence root;

	// Recursive method. Adds a new sequence to the tree.
	static void addSequence(DnaSequence dna, String s, int level) {
		int childIndex = getValue(s.charAt(level));
		if(dna.childs[childIndex] == null) dna.childs[childIndex] = new DnaSequence(s);
		else addSequence(dna.childs[childIndex], s, level + 1);
	}

	// Recursive method. Looks for a sequence in the tree.
	static int lookupSequence(DnaSequence dna, String s, int level) {
		if(dna == null) return level - 1;
		int childIndex = getValue(s.charAt(level));
		if(dna.childs[childIndex] != null && dna.childs[childIndex].sequence.equals(s)) return level + 1;
		return lookupSequence(dna.childs[childIndex], s, level + 1);
	}

	// Wrapper of recursive method
	static int lookupSequence(String s) {
		return lookupSequence(root, s, 0);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// How many test cases
		int C = sc.nextInt();
		
		// For each test case
		for(int c = 0; c < C; c++) {
			// Creates new root for a new tree
			root = new DnaSequence("root");
			// Sequence length (L) - not used
			sc.nextInt();
			// How many sequences
			int N = sc.nextInt();
			// Add sequences to the tree
			for (int n = 0; n < N; n++) {
				addSequence(root, sc.next(), 0);
			}
			// How many sequences we are going to search
			int B = sc.nextInt();
			// Print how many levels (comparisons) we need to reach the sequence we are searching for
			for (int b = 0; b < B; b++) {
				System.out.print(lookupSequence(sc.next()) + " ");
			}
			// Line break for each test case
			System.out.println();
		}
		System.out.println();
		sc.close();
	}
}
