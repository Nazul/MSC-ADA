package iteso.msc.ada.Homework2;

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
	
	public static void printArray(float[] array) {
		System.out.println(Arrays.toString(array));
	}
	
	public static void swap(int[] array, int i1, int i2) {
		int tmp = array[i1];

		array[i1] = array[i2];
		array[i2] = tmp;
	}
	
	public static void swap(Student[] array, int i1, int i2) {
		Student tmp = array[i1];

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
