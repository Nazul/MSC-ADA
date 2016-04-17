package iteso.msc.ada.Session3;

import java.util.Arrays;
import java.util.Random;


public class Utils {
	public static int[] createArray(int N, int min, int max) {
		Random rnd = new Random();
		int[] array = new int[N];
		int bound = max - min + 1;

		for(int i = 0; i < array.length; i++)
			array[i] = rnd.nextInt(bound) + min;
		
		return array;
	}

	public static int[] createIntArray(int N, int min, int max) {
		int[] intArray = new int[N];
		for (int i = 0; i < N; i++) {
			intArray[i] = min + ((int) (Math.random() * (max - min + 1)));
		}
		return intArray;
	}

	public static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}
	
	public static void swap(int[] array, int i1, int i2) {
		int tmp = array[i1];

		array[i1] = array[i2];
		array[i2] = tmp;
	}
	
	public static boolean isSorted(int[] array) {
		for(int i = 0; i < array.length -1; i++)
			if(array[i] > array[i + 1])
				return false;
		return true;
	}
	
	public static void selectionSort(int[] array) {
		
		for(int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			
			for(int j = i + 1; j < array.length; j++)
				if(array[minIndex] > array[j])
					minIndex = j;

			if(minIndex != i)
				swap(array, i, minIndex);
		}
	}

	public static void heapSort(int[] array) {
		// Step 1: "Heapify" array
		for(int i = 1; i < array.length; i++) { // for each child
			// Move it up as far as its value allow it
			int ch = i;
			int p = (ch + 1) / 2 - 1;
			while(p >= 0 && array[ch] > array[p]) {
				Utils.swap(array, ch, p);
				ch = p;
				p = (ch + 1) / 2 - 1;
			}
		}
		
		// Step 2: Sort heap
		for (int heapLast = array.length - 1; heapLast > 0; heapLast--) {
			Utils.swap(array, 0, heapLast);
			int p = 0;
			int ch = 1;
			if(array[2] > array[1] && heapLast > 2)
				ch = 2;

			while(ch < heapLast && array[p] < array[ch]){
				Utils.swap(array, ch, p);
				p = ch;
				int chL = (p + 1) * 2 - 1;
				if(chL >= heapLast)
					break;
				int chR = chL + 1;
				if(chR >= heapLast)
					ch = chL;
				else
					ch = array[chL] > array[chR] ? chL : chR;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a1 = {5, 8, 7, 9, 1, 3};
		
		Utils.heapSort(a1);
		Utils.printArray(a1);
		System.out.println(Utils.isSorted(a1));

		a1 = Utils.createArray(500_000, -32000, 32000);
		long start = System.currentTimeMillis();
		Utils.heapSort(a1);
		long end = System.currentTimeMillis();		
		System.out.println(Utils.isSorted(a1));
		System.out.println("Duración: " + (end - start));
	}
}
