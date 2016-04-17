package iteso.msc.ada.Homework3v2;

public class Quick {
	public static int[] Sort(int[] array) {
		int[] sorted = array.clone();

		sort(sorted, 0, array.length - 1);
		return sorted;
	}

	private static void sort(int[] array, int left, int right) {
		if(right - left < 0) {
			return;
		}
		int p = partition(array, left, right);
		sort(array, left, p - 1);
		sort(array, p + 1, right);
	}
	
	private static int partition(int[] array, int left, int right) {
		int pivot = array[right];
		int p1 = left, p2 = right;

		do {
			for (p1 = left; p1 < right; p1++) {
			if(array[p1] > pivot)
				break;
		}
		for (p2 = right; p2 > left; p2--) {
			if(array[p2] < pivot)
				break;
		}
		if(p1 >= p2) {
			Utils.swap(array, p1, right);
			return p1;
		}
		Utils.swap(array, p1, p2);
		} while(p1 <= p2);
		return p1;
	}
}
