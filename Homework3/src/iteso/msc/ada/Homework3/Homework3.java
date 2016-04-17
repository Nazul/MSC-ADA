package iteso.msc.ada.Homework3;


public class Homework3 {
	public static void main(String[] args) {
		System.out.println("ITESO - MSC - ADA");
		System.out.println("Mario Contreras (705080)");
		System.out.println("Homework 3");
		System.out.println();
		
		int MAX_N = 200, MAX_SAMPLE = 50;
		long start, end;

		float[] durRadix = new float[MAX_N];
		float[] durQuick = new float[MAX_N];
		float[] durMerge = new float[MAX_N];

		for(int N = 1; N <= MAX_N; N++) {
			int[] arrayRadix, arrayQuick, arrayMerge;
			
			System.out.println("\nRunning (" + N + "/" + MAX_N + " - " + (MAX_SAMPLE * N) + ")");
			for (int i = 0; i < MAX_SAMPLE * N; i++) {
				//arrayRadix = Utils.createArray(N * 100, 1, N * 1_000);
				//arrayRadix = Utils.createSemiSortedArrayAsc(N * 100, 1, N * 1_000);
				arrayRadix = Utils.createSemiSortedArrayDesc(N * 100, 1, N * 1_000);
				arrayQuick = arrayRadix.clone();
				arrayMerge = arrayRadix.clone();

				start = System.currentTimeMillis();
				Sort.radix(arrayRadix);
				end = System.currentTimeMillis();		
				durRadix[N - 1] = end - start;
				
				start = System.currentTimeMillis();
				Quick.sort(arrayQuick);
				end = System.currentTimeMillis();		
				durQuick[N - 1] = end - start;
				
				start = System.currentTimeMillis();
				Merge.sort(arrayMerge);
				end = System.currentTimeMillis();		
				durMerge[N - 1] = end - start;

				if (i % 1000 == 0)
					System.out.print(".");
			}
			durRadix[N - 1] /= (MAX_SAMPLE * N);
			durQuick[N - 1] /= (MAX_SAMPLE * N);
			durMerge[N - 1] /= (MAX_SAMPLE * N);
		}
		System.out.println();
		
		Utils.printArray(durRadix);
		Utils.printArray(durQuick);
		Utils.printArray(durMerge);
		
//		int[] a1 = Utils.createSemiSortedArrayAsc(100, 100, 1_000);
//		int[] a2 = Utils.createSemiSortedArrayDesc(100, 100, 1_000);
//		Utils.printArray(a1);
//		Utils.printArray(a2);
		
		System.out.println();
	}
}
