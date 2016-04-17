import java.util.Arrays;
import java.util.Random;

public class Utils {

	public static int[] createArray(int N, int min, int max) {
		Random rnd = new Random();
		int[] array = new int[N];
		
		int bound = max - min + 1;
		for(int i = 0; i < array.length; i ++) {
			array[i] = rnd.nextInt(bound) + min;
		}		
		
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
		for(int i = 0; i < array.length - 1; i ++) {
			if(array[i] > array[i + 1]) return false; 
		}
		return true;
	}
	
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min_index = i;
			for (int j = i + 1; j < array.length; j++)
				if (array[j] < array[min_index]) min_index = j;
			if (i != min_index) swap(array, i, min_index);
		}
	}
	
	public static void main(String[] args) {
		int N = 100000;
		int[] a1 = createArray(N, 0, N);
//		printArray(a1);
		System.out.println(isSorted(a1));
		
		long start = System.currentTimeMillis();
		selectionSort(a1);
		long end = System.currentTimeMillis();
		System.out.println("Duración: " + (end - start));
		
		System.out.println(isSorted(a1));
		
		int[] a2 = {-5, -3, 0, 7, 10};
		System.out.println(isSorted(a2));
	}

}
