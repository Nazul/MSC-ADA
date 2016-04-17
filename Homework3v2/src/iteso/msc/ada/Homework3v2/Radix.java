package iteso.msc.ada.Homework3v2;

import java.util.LinkedList;


public class Radix {

	private static int digit(int val, int digit) {
        String s = String.valueOf(val);
        if(s.length() - digit < 0) return -1;
        return Character.getNumericValue(s.charAt(s.length() - digit));
    }
	
	private static LinkedList[] createQueueArray(int size) {
		LinkedList<Integer>[] q = new LinkedList[size];
		for (int i = 0; i < q.length; i++)
			q[i] = new LinkedList<Integer>();
		return q;
	}

	public static int[] Sort(int[] array) {
		LinkedList<Integer>[] qs = createQueueArray(10);
		LinkedList<Integer>[] qps = createQueueArray(10);
		int[] sorted = new int[array.length];
		int max = 0;
		
		for (int i = 0; i < array.length; i++) {
			qs[array[i] % 10].add(array[i]);
			if(array[i] > max) max = array[i];
		}
		int D = String.valueOf(Math.abs(max)).length();


		for(int d = 2; d <= D; d++) {
			for(int c = 0; c < 10; c++) {
				int T = qs[c].size();
				for(int t = 1; t <= T; t++) {
					int digit = digit(qs[c].getFirst(), d);
					if(digit != -1) {
						int v = qs[c].removeFirst();
						qps[digit].addLast(v);
					}
					else {
						int v = qs[c].removeFirst();
						qps[0].addLast(v);
					}
				}
			}
			qs = qps;
			qps = createQueueArray(10);
		}
		
		for(int c = 0, i = 0; c < 10; c++) {
			int T = qs[c].size();
			for(int t = 0; t < T; t++) {
				sorted[i++] = qs[c].removeFirst();
			}
		}
		
		return sorted;
	}
}
