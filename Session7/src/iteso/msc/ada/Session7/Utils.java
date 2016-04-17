package iteso.msc.ada.Session7;


public class Utils {
	public static int[] createIntArray(int N, int min, int max) {
		int[] intArray = new int[N];
		for (int i = 0; i < N; i++) {
			intArray[i] = min + ((int) (Math.random() * (max - min + 1)));
		}
		return intArray;
	}

	public static boolean isSorted(int[] array) {
		for(int i = 0; i < array.length -1; i++)
			if(array[i] > array[i + 1])
				return false;
		return true;
	}
}
