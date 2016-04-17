package iteso.msc.ada.Homework3;

import jds.Queue;
import jds.collection.LinkedList;


public class Sort {
	public static long numComp, numSwap;
	
	public static void selection(int[] array) {
		numComp = numSwap = 0;
		
		for(int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			
			for(int j = i + 1; j < array.length; j++) {
				numComp++;
				if(array[minIndex] > array[j])
					minIndex = j;
			}

			if(minIndex != i) {
				numSwap++;
				Utils.swap(array, i, minIndex);
			}
		}
	}
	
	public static void bubble(int[] array) {
	    boolean swapped = true;
		numComp = numSwap = 0;

	    while (swapped) {
	    	swapped = false;
			for(int i = 0; i < array.length - 1; i++) {
				numComp++;
				if (array[i] > array[i + 1]) {
					numSwap++;
					Utils.swap(array, i, i + 1);
					swapped = true;
				}
			}
	    }
	}
	
	static void insertion(int[] array) {
		numComp = numSwap = 0;

		for(int i = 1; i < array.length; i++) {
			int current = array[i];
			int j = i - 1;

			numComp++;
			while (j >= 0 && array[j] > current) {
				numSwap++;
				array[j + 1] = array[j--];
			}
			numSwap++;
			array[j + 1] = current;
		}
	}
	
	// Reference: http://algs4.cs.princeton.edu/21elementary/Shell.java.html
	public static void shell(int[] array) {
		int N = array.length;
		int h = 1;
		numComp = numSwap = 0;
		
		// 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
		while (h < N / 3)
			h = 3 * h + 1;
		
		while (h >= 1) {
			// h-sort the array
			for (int i = h; i < N; i++) {
				numComp++;
				for (int j = i; j >= h && array[j] < array[j - h]; j -= h) {
					numSwap++;
					Utils.swap(array, j, j - h);
				}
			}
			h /= 3;
		}
	}

	// Reference: http://web.engr.oregonstate.edu/~budd/Books/jds/info/src/jds/sort/RadixSort.java
	public static void radix(int[] array) {
		boolean flag = true;
		int divisor = 1;
		Queue [ ] buckets = new Queue[10];
		for (int i = 0; i < 10; i++)
			buckets[i] = new LinkedList();

		while (flag) {
			flag = false;
				// first copy the values into buckets
			for (int i = 0; i < array.length; i++) {
				int hashIndex = (array[i] / divisor) % 10;
				if (hashIndex > 0) flag = true;
				buckets[hashIndex].addLast(new Integer(array[i]));
			}
				// then copy the values back into vector
			divisor *= 10;
			int i = 0;
			for (int j = 0; j < 10; j++) {
				while (! buckets[j].isEmpty()) {
					Integer ival = (Integer) buckets[j].getFirst();
					buckets[j].removeFirst();
					array[i++] = ival.intValue();
				}
			}
		}
	}
}
