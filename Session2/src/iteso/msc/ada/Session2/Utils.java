package iteso.msc.ada.Session2;

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

	
	public static void main(String[] args) {
		int[] a1 = createArray(10, 5, 100);
		
		printArray(a1);
		swap(a1, 3, 4);
		printArray(a1);
		System.out.println(isSorted(a1));
		selectionSort(a1);
		printArray(a1);
		System.out.println(isSorted(a1));

		int[] a2 = {-3, -2, 4, 7};
		printArray(a2);
		System.out.println(isSorted(a2));
		
		int[] a3 = createArray(80000, -80000, 80000);
		
		long start = System.currentTimeMillis();
		selectionSort(a3);
		long end = System.currentTimeMillis();
		System.out.println("Duración: " + (end - start));
	}
}
