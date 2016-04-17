package iteso.msc.ada.Homework2;


public class Homework2 {
	public static void main(String[] args) {
		System.out.println("ITESO - MSC - ADA");
		System.out.println("Mario Contreras (705080)");
		System.out.println("Homework 2");
		System.out.println();
		
		int MAX_N = 200, MAX_SAMPLE = 500;

		float[] compSel = new float[MAX_N];
		float[] swapSel = new float[MAX_N];
		float[] compBub = new float[MAX_N];
		float[] swapBub = new float[MAX_N];
		float[] compIns = new float[MAX_N];
		float[] swapIns = new float[MAX_N];
		float[] compShl = new float[MAX_N];
		float[] swapShl = new float[MAX_N];

		for(int N = 1; N <= MAX_N; N++) {
			int[] a;
			
			System.out.println("\nRunning (" + N + "/" + MAX_N + " - " + (MAX_SAMPLE * N) + ")");
			for (int i = 0; i < MAX_SAMPLE * N; i++) {
				a = Utils.createArray(N, -N * 1000, N * 1000);
				Sort.selection(a);
				compSel[N - 1] += Sort.numComp;
				swapSel[N - 1] += Sort.numSwap;
				
				a = Utils.createArray(N, -N * 1000, N * 1000);
				Sort.bubble(a);
				compBub[N - 1] += Sort.numComp;
				swapBub[N - 1] += Sort.numSwap;

				a = Utils.createArray(N, -N * 1000, N * 1000);
				Sort.insertion(a);
				compIns[N - 1] += Sort.numComp;
				swapIns[N - 1] += Sort.numSwap;

				
				a = Utils.createArray(N, -N * 1000, N * 1000);
				Sort.shell(a);
				compShl[N - 1] += Sort.numComp;
				swapShl[N - 1] += Sort.numSwap;
				if (i % 1000 == 0)
					System.out.print(".");
			}
			compSel[N - 1] /= (MAX_SAMPLE * N);
			swapSel[N - 1] /= (MAX_SAMPLE * N);
			compBub[N - 1] /= (MAX_SAMPLE * N);
			swapBub[N - 1] /= (MAX_SAMPLE * N);
			compIns[N - 1] /= (MAX_SAMPLE * N);
			swapIns[N - 1] /= (MAX_SAMPLE * N);
			compShl[N - 1] /= (MAX_SAMPLE * N);
			swapShl[N - 1] /= (MAX_SAMPLE * N);
		}
		System.out.println();
		
		Utils.printArray(compSel);
		Utils.printArray(swapSel);
		Utils.printArray(compBub);
		Utils.printArray(swapBub);
		Utils.printArray(compIns);
		Utils.printArray(swapIns);
		Utils.printArray(compShl);
		Utils.printArray(swapShl);
		System.out.println();
	}
}
