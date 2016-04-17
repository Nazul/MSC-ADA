import iteso.msc.ada.Session4.Utils;


public class DivideConquer {

	public static int median(int[] array, int k) 
	{
/*		
		P <- el número de elementos más pequeños que LN/2
		Si P = K, devolver LK
		Si P > K 		//en la mitad hay un número grande
		     a)Crear una lista L1 con los elementos más pequeños que LN/2
		     b)Devolver Mediana(L1, K)
		Si P < K 		 //en la mitad hay un número pequeño
		     a)Crear una lista L2 con los elementos más grandes que LN/2
		     b)Devolver  Mediana(L2, K – P – 1)
*/
		int p = 0;
		for (int i = 0; i < array.length; i++) 
		{
			if(array[i] < array[array.length / 2]) p++;
		}
		
		if(p == k){
			return array[array.length / 2];
		}
		else if(p > k){
			int[] arraymin = new int[p];
			for (int i = 0, j=0; i < array.length; i++) 
			{
				if(array[i] < array[array.length / 2]) arraymin[j++] = array[i];
				
			}
			return median(arraymin,k);
		}
		else{

			/*
			Si P < K 		 //en la mitad hay un número pequeño
		     a)Crear una lista L2 con los elementos más grandes que LN/2
		     b)Devolver  Mediana(L2, K – P – 1)
		     */

			
			int[] arraymax = new int[array.length - p - 1];
			for (int i = 0, j=0; i < array.length; i++) 
			{
				if(array[i] > array[array.length / 2]) arraymax[j++] = array[i];
				
			}
			return median(arraymax,k-p-1);
			
		}
	}
	
	public static int slowMedian(int[] array) {
		for(int i = 0; i < array.length; i ++) {
			int p = 0;
			for(int j = 0; j < array.length; j ++) {
				if(array[j] < array[i]) p ++;
			}
			if(p == array.length / 2) return array[i];
		}
		return 0;
	}
	
	public static void main(String[] args) {
//		int[] a = {19, 10, 47, 5, 13, 26, 8, 35, 13, 18};
		final int N = 1_000_000;
		int[] a = Utils.createIntArray(N, -16 * N, 16 * N);
		
		long start = System.currentTimeMillis();
		System.out.println(median(a, a.length / 2));
		long end = System.currentTimeMillis();
		System.out.println("Milisegundos: " + (end - start));
		
		start = System.currentTimeMillis();
		System.out.println(slowMedian(a));
		end = System.currentTimeMillis();
		System.out.println("Milisegundos: " + (end - start));
	}

}
