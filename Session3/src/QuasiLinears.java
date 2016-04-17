import iteso.msc.ada.Session3.Utils;


public class QuasiLinears {

	public static void heapsort(int[] array) {
//		Step 1: heapify array
		for(int i = 1; i < array.length; i ++) {  // por cada hijo
//			Llevarlo tan arriba como su valor lo permita
			int ch = i;
			int p = (ch + 1) / 2 - 1;
			while(p >= 0 && array[ch] > array[p]) {
				Utils.swap(array, ch, p);
				ch = p;
				p = (ch + 1) / 2 - 1;
			}
		}
//		Step 2: sort heap
		for(int heapLast = array.length - 1; heapLast > 0; heapLast --) { 
			Utils.swap(array, 0, heapLast);
			int p = 0;
			int ch  = 1;
			if(array[2] > array[1] && heapLast > 2) ch = 2;
			
			while(ch < heapLast && array[p] < array[ch]) {
				Utils.swap(array, ch, p);
				p = ch;
				int chL = (p + 1) * 2 - 1;
				if(chL >= heapLast) break;
				int chR = chL + 1;
				if(chR >= heapLast) ch = chL;
				else ch  = array[chL] > array[chR]? chL : chR;
			}
		}		
	}
	
	public static void main(String[] args) {
		int[] array = {5, 8, 7, 9, 1, 3, 4, 10, 6, 2, -5, -3};
		heapsort(array);
		Utils.printArray(array);
		final int N = 16_000_000;
		array = Utils.createIntArray(N, -N, N);
		long start = System.currentTimeMillis();
		heapsort(array);
		long end = System.currentTimeMillis();
		System.out.println(Utils.isSorted(array));
		System.out.println(end - start);
	}
}
