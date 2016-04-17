package iteso.msc.ada.Homework3v2;

public class Median {
	public static int getMedian(int[] array) {
		return getMedian(array, array.length / 2);
	}

	private static int getMedian(int[] array, int k) {
		int p = 0;
		for(int i = 0; i < array.length; i++)
			if(array[i] < array[array.length / 2]) p++;
		if(p == k)
			return array[array.length / 2];
		if(p > k) {
			int[] l1 = new int[p];
			for(int i = 0, j = 0; i < array.length; i++)
				if(array[i] < array[array.length / 2]) l1[j++] = array[i];
			return getMedian(l1, k);
		}
		int[] l2 = new int[array.length - p - 1];
		for(int i = 0, j = 0; i < array.length; i++)
			if(array[i] > array[array.length / 2]) l2[j++] = array[i];
		return getMedian(l2, k - p - 1);
	}
}
