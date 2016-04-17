package iteso.msc.ada.Homework3v2;

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

	public static int[] createSemiSortedArrayAsc(int N, int min, int max) {
		int[] array = new int[N];

		for(int i = 0; i < array.length; i++)
			array[i] = (int)(min + ((max - min) / (float)N) * i);

		for(int i = 1; i < array.length; i++)
			if(i % 10 == 0)
				swap(array, i, i - 1);
		
		return array;
	}

	public static int[] createSemiSortedArrayDesc(int N, int min, int max) {
		int[] array = new int[N];

		for(int i = 0; i < array.length; i++)
			array[i] = (int)(max - ((max - min) / (float)N) * i);

		for(int i = 1; i < array.length; i++)
			if(i % 10 == 0)
				swap(array, i, i - 1);
		
		return array;
	}

	public static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}
	
	public static void printArray(long[] array) {
		System.out.println(Arrays.toString(array));
	}
	
	public static void printArray(float[] array) {
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
}
