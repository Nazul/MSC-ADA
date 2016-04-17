import java.util.Arrays;

public class Sorts {

	static int[] createIntArray(int N, int min, int max) {
		int[] intArray = new int[N];
		for (int i = 0; i < N; i++) {
			intArray[i] = min + ((int) (Math.random() * (max - min + 1)));
		}
		return intArray;
	}

	static void printArray(int[] toPrint) {
		System.out.println(Arrays.toString(toPrint));
	}

	static void swap(int[] array, int index1, int index2) {
		if (index1 >= 0 && index1 < array.length && index2 >= 0
				&& index2 < array.length) {
			int temp = array[index1];
			array[index1] = array[index2];
			array[index2] = temp;
		}
	}

	static boolean isSorted(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				return false;
			}
		}
		return true;
	}

	static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[minIndex] > array[j])
					minIndex = j;
			}
			if (minIndex != i)
				swap(array, i, minIndex);
		}
	}

	static void insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int current = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > current) {
				array[j + 1] = array[j--];
			}
			array[j + 1] = current;
		}

	}

	static void bubbleSort(int[] array) {
		boolean swapped = true;
		for(int i = 0; i < array.length - 1 && swapped; i ++) {
			swapped = false;
			for(int j = 0; j < array.length - 1 - i; j ++) {
				if(array[j] > array[j + 1]) {
					swap(array, j, j + 1);
					swapped = true;
				}				
			}
		}
	}
	
	public static void main(String[] args) {
		final int N = 100_000;
		int[] arrayI = Sorts.createIntArray(N, -N / 2, N / 2);
		int[] arrayB = arrayI.clone();
		int[] arrayS = arrayI.clone();
		
		long start = System.currentTimeMillis();
		insertionSort(arrayI);
		long end = System.currentTimeMillis();
		System.out.println("Insertion: " + (end - start) / 1000.0);
		
		start = System.currentTimeMillis();
		bubbleSort(arrayB);
		end = System.currentTimeMillis();
		System.out.println("Burbuja: " + (end - start) / 1000.0);

		start = System.currentTimeMillis();
		selectionSort(arrayS);
		end = System.currentTimeMillis();
		System.out.println("Selection: " + (end - start) / 1000.0);

	}

}
