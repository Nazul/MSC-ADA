package iteso.msc.ada.Homework3v2;

public class Merge {
	public static int[] Sort(int[] array) {
		int[] left, right;
		int size = array.length;
		
		if(Utils.isSorted(array))
			return array;
		
		left = new int[size / 2];
		right = new int[size - left.length];

		for (int i = 0; i < left.length; i++) {
			left[i] = array[i];
		}
		for (int i = 0; i < right.length; i++) {
			right[i] = array[size / 2 + i];
		}
		
		left = Sort(left);
		right = Sort(right);
		return merge(left, right);
	}
	
	private static int[] merge(int[] left, int[] right) {
		int size = left.length + right.length;
		int[] sorted = new int[size];
	
		for(int l = 0, r = 0, c = 0; c < size; c++) {
			if(l < left.length && r < right.length) {
				if(left[l] < right[r]) {
					sorted[c] = left[l++];
				}
				else {
					sorted[c] = right[r++];
				}
			}
			else {
				if(l == left.length) {
					sorted[c] = right[r++];
				}
				else {
					sorted[c] = left[l++];
				}
			}
		}
		return sorted;
	}
}
