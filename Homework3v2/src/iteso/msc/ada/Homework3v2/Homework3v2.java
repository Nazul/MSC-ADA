package iteso.msc.ada.Homework3v2;


public class Homework3v2 {
	public static void main(String[] args) {
		System.out.println("ITESO - MSC - ADA");
		System.out.println("Mario Contreras (705080)");
		System.out.println("Homework 3 (Version 2)");
		System.out.println();
		
		int MAX_N = 100, MAX_SAMPLE = 100;
		long start, end;

		float[] durQuick = new float[MAX_N];
		float[] durMerge = new float[MAX_N];

		for(int N = 1; N <= MAX_N; N++) {
			int[] arrayQuick, arrayMerge;
			
			System.out.println("\nRunning - Random (" + N + "/" + MAX_N + " - " + (MAX_SAMPLE * N) + ")");
			for (int i = 0; i < MAX_SAMPLE * N; i++) {
				arrayQuick = Utils.createArray(N * 100, N * -1_000, N * 1_000);
				arrayMerge = arrayQuick.clone();

				start = System.currentTimeMillis();
				Quick.Sort(arrayQuick);
				end = System.currentTimeMillis();		
				durQuick[N - 1] = end - start;
				
				start = System.currentTimeMillis();
				Merge.Sort(arrayMerge);
				end = System.currentTimeMillis();
				durMerge[N - 1] = end - start;

				if (i % 1000 == 0)
					System.out.print(".");
			}
			durQuick[N - 1] /= (MAX_SAMPLE * N);
			durMerge[N - 1] /= (MAX_SAMPLE * N);
		}
		System.out.println();
		
		Utils.printArray(durQuick);
		Utils.printArray(durMerge);
		System.out.println();

	
		durQuick = new float[MAX_N];
		durMerge = new float[MAX_N];

		for(int N = 1; N <= MAX_N; N++) {
			int[] arrayQuick, arrayMerge;
			
			System.out.println("\nRunning - Semi-Sorted (" + N + "/" + MAX_N + " - " + (MAX_SAMPLE * N) + ")");
			for (int i = 0; i < MAX_SAMPLE * N; i++) {
				arrayQuick = Utils.createSemiSortedArrayAsc(N * 100, N * -1_000, N * 1_000);
				arrayMerge = arrayQuick.clone();

				start = System.currentTimeMillis();
				Quick.Sort(arrayQuick);
				end = System.currentTimeMillis();		
				durQuick[N - 1] = end - start;
				
				start = System.currentTimeMillis();
				Merge.Sort(arrayMerge);
				end = System.currentTimeMillis();		
				durMerge[N - 1] = end - start;

				if (i % 1000 == 0)
					System.out.print(".");
			}
			durQuick[N - 1] /= (MAX_SAMPLE * N);
			durMerge[N - 1] /= (MAX_SAMPLE * N);
		}
		System.out.println();
		
		Utils.printArray(durQuick);
		Utils.printArray(durMerge);
		System.out.println();

	
		durQuick = new float[MAX_N];
		durMerge = new float[MAX_N];

		for(int N = 1; N <= MAX_N; N++) {
			int[] arrayQuick, arrayMerge;
			
			System.out.println("\nRunning - Semi-Inverted (" + N + "/" + MAX_N + " - " + (MAX_SAMPLE * N) + ")");
			for (int i = 0; i < MAX_SAMPLE * N; i++) {
				arrayQuick = Utils.createSemiSortedArrayDesc(N * 100, N * -1_000, N * 1_000);
				arrayMerge = arrayQuick.clone();

				start = System.currentTimeMillis();
				Quick.Sort(arrayQuick);
				end = System.currentTimeMillis();		
				durQuick[N - 1] = end - start;
				
				start = System.currentTimeMillis();
				Merge.Sort(arrayMerge);
				end = System.currentTimeMillis();		
				durMerge[N - 1] = end - start;

				if (i % 1000 == 0)
					System.out.print(".");
			}
			durQuick[N - 1] /= (MAX_SAMPLE * N);
			durMerge[N - 1] /= (MAX_SAMPLE * N);
		}
		System.out.println();
		
		Utils.printArray(durQuick);
		Utils.printArray(durMerge);
		System.out.println();
	}
}
