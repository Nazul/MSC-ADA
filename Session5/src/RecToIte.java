import java.util.Stack;

import iteso.msc.ada.Session5.Utils;

public class RecToIte {

	static class Parameters {
		int left, right;
		public Parameters(int l, int r) {
			left = l;
			right = r;
		}
	}
	
	static long partitions = 0;
	
	static int partition(int[] array, int left, int right) {
		partitions += right - left + 1;
//		return right;
		return (left + right) / 2;
	}
	
	public static void quicksort(int[] array, int left, int right) {
		if(left >= right) return;
		int p = partition(array, left, right);
		quicksort(array, left, p - 1);
		quicksort(array, p + 1, right);
	}
	
	public static void quicksort(int[] array) {
		int left = 0, right = array.length - 1;
//		Crear una pila de parámetros y depositar los parámetros iniciales
		Stack<Parameters> stack = new Stack<Parameters>();
		stack.push(new Parameters(left, right));
//		Mientras la pila no esté vacía
		while(!stack.isEmpty()) {
//			Sacar de la pila los últimos parámetros depositados (en orden inverso)
			Parameters params = stack.pop();
//			¿Puedo ir a la siguiente iteración sin meter parámetros?  (como el fin de la recursión)
			if(params.left >= params.right) continue;
//			Operaciones con el espacio de búsqueda
			int p = partition(array, params.left, params.right);
//			Meter a la pila parámetros2, parámetros1
			stack.push(new Parameters(p + 1, params.right));
			stack.push(new Parameters(params.left,  p - 1));
		}
	}
	
	
	
	public static void main(String[] args) {
		final int N = 500_000; //1_073_741_824;
		int[] array = Utils.createIntArray(N, 0, N);
//		quicksort(array, 0, N);
		quicksort(array);
//		System.out.println(Utils.isSorted(array));
		System.out.println(partitions);
	}
	
}
