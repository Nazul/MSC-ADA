package iteso.msc.ada.Exam1;

import java.util.Arrays;
import java.util.Random;


public class Exam1 {
	public static int[] createArray(int N, int min, int max) {
		Random rnd = new Random();
		int[] array = new int[N];
		int bound = max - min + 1;

		for(int i = 0; i < array.length; i++)
			array[i] = rnd.nextInt(bound) + min;
		
		return array;
	}
	
	public static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}

	public static void go(int array[]) {
		int ms = 0, mi = -1;
		int c = 0;
		for(int i = 1; i <= array.length; i *= 2) {
//			System.out.println("i: " + i);
			int s = 0;
			for(int b = i - 1; b < array.length; b += i) {
//				System.out.println("i: " + i + "; b: " + b);
				c++;
				s += array[b];
			}
			if(mi < 0 || s > ms) {
				ms = s;
				mi = i;
			}
		}
		//printf("%d, %d\n", mi, ms);
//		System.out.println(mi + ", " + ms);
		System.out.println(c);
	}

	public static int sum(int[] array, int start, int end) {
		int sum = 0;
		for(int i = start; i <= end; i ++) sum += array[i];
		return sum;
	}

	public static int maxSum(int[] array) {
		int maxSum = 0;
		for(int i = 0; i < array.length - 1; i ++) {
			for(int j = i + 1; j < array.length; j ++) {
//				System.out.println(i + ", " + j);
				int s = sum(array, i, j);
				if(s > maxSum) maxSum = s;
			}
		}
		return maxSum;
	}

	public static int maxSumDaC(int[] array) {
		int maxSum = 0;
		for(int i = 0; i < array.length - 1; i ++) {
//			for(int j = i + 1; j < array.length; j ++) {
//				int s = sum(array, i, j);
//				if(s > maxSum) maxSum = s;
//			}
			int sum = 0;
			for(int j = i + 1; j < array.length; j++) {
				sum += array[i];
				if(sum > maxSum) maxSum = sum;
			}
		}
		return maxSum;
	}
	
	
//	Diseñe un algoritmo del tipo divide y vencerás que solucione el problema.
//	a.	Puede comprobar su precisión comparando el resultado obtenido con el del algoritmo mostrado.
//	b.	El algoritmo dividirá siempre el espacio de búsqueda en dos mitades hasta llegar a la trivialidad.
//	c.	Calcula la suma obtenida en la primera mitad.
//	d.	Calcula la suma obtenida en la segunda mitad.
//	e.	Calcula la suma obtenida de un sub-arreglo que ocupe las dos mitades:
//	i.	Encuentra la suma máxima del sub-arreglo que termina en la primera mitad.
//	ii.	Encuentra la suma máxima del sub-arreglo que comienza en la segunda mitad.
//	iii.	Suma los dos resultados anteriores.
//	f.	Devuelve la mayor de las tres sumas.

//	public static int sumDaC(int[] array) {
//		if(array.length == 1)
//			return array[0];
//		else if(array.length == 2)
//			return array[0] + array[1];
//	
//		int half = 0;
//		int[] left, right;
//	
//		half = array.length / 2;
//		left = new int[half];
//		right = new int[array.length - half];
//		
//		for(int i = 0; i < left.length; i++)
//			left[i] = array[i];
//		for(int i = 0; i < right.length; i++)
//			right[i] = array[i + half];
//		
//		int s1 = sumDaC(left);
//		int s2 = sumDaC(right);
//		int s3 = s1 + s2;
//	
////		if(s1 > s2 && s1 > s3) return s1;
////		if(s2 > s1 && s2 > s3) return s2;
//		return s3;
//	}
//
//	public static int maxSumDaC(int[] array) {
//		int maxSum = 0;
//		for(int i = 0; i < array.length - 1; i ++) {
//			for(int j = i + 1; j < array.length; j ++) {
//				int s = sumDaC(array);
//				if(s > maxSum) maxSum = s;
//			}
//		}
//		return maxSum;
//	}
	
//	public static int sumDaC(int[] array, int start, int end) {
//		if (end - start == 0)
//			return array[start];
//		if (end - start == 1)
//			return array[start] + array[end];
////		if (end - start < 2)
////			return array[start];
//		int s1 = sumDaC(array, start, end - ((end - start) / 2));
//		int s2 = sumDaC(array, start + 1 + ((end - start) / 2), end);
//		int s3 = s1 + s2;
//		return s3;
//		//if(s1 > s2 && s1 > s3) return s1;
//		//if(s2 > s1 && s2 > s3) return s2;
////		if(s3 > s1 && s3 > s2)
//		//return s3;
//
////		int sum = 0;
////		for(int i = start; i <= end; i ++) sum += array[i];
////		return sum;
//	}
//
//	public static int maxSumDaC(int[] array) {
//		int maxSum = 0;
//		for(int i = 0; i < array.length - 1; i ++) {
//			for(int j = i + 1; j < array.length; j ++) {
//				int s = sumDaC(array, i, j);
//				if(s > maxSum) maxSum = s;
//			}
//		}
//		return maxSum;
//	}


	public static boolean isHeap(int[] array) {
		boolean result = true;
		
		if(array.length < 2)
			return false;
		
		if(array[0] > array[1] || array[0] > array[2])
			result = false;
		
		for (int i = 1; i <= array.length; i++) {
			if(2 * i <= array.length - 1)
				if(array[i] > array[2 * i])
					result = false;
				else
					result = true;
			
				if(2 * i + 1 <= array.length - 1)
					if(array[i] > array[2 * i + 1])
						result = false;
					else
						result = true;
		}
		return result;
	}
	
	public static class Node {
		public Node child1 = null;
		public Node child2 = null;
		public Node child3 = null;
		public int value1;
		public int value2;
		
		public Node(int value1, int value2) {
			this.value1 = value1;
			this.value2 = value2;
		}
	}
	
	public static Node createTree() {
		Node root = new Node(6, 15);
		
		root.child1 = new Node(2, 4);
		root.child2 = new Node(9, 12);
		root.child3 = new Node(18, 24);

		root.child3.child1 = new Node(16, 17);
		root.child3.child2 = new Node(20, 22);
		root.child3.child3 = new Node(26, 28);

		return root;
	}
	
//	// No relation between value1 and value2 nor n values and children values
//	public static void search(Node n, int value) {
//		if(n == null) return;
//		if(value == n.value1 || value == n.value2) {
//			System.out.println("Encontrado");
//			return;
//		}
//		search(n.child1, value);
//		search(n.child2, value);
//		search(n.child3, value);
//	}

	// If child1.value1 < child1.value2 < value1 < child2.value1 < child2.value2 < value2 < child3.value1 < child3.value2   
	public static void search(Node n, int value) {
		if(n == null) return;
		if(value == n.value1 || value == n.value2) {
			System.out.println("Encontrado");
			return;
		}
		if(value < n.value1) search(n.child1, value);
		if(value > n.value1 && value < n.value2) search(n.child2, value);
		if(value > n.value2) search(n.child3, value);
	}
	
	public static void main(String args[]) {
//		int[] sortedArray = {5, 4, 3, 2, 1};
		//int[] a1 = createArray(2_000, -1_000, 1_000);
//		int[] a1 = {-13, 64, -100, -95, -12, 50};
		
//		insertion(sortedArray);
//		go(sortedArray);
//		printArray(a1);
//		go(a1);
//		go(a2);

//		for(int n = 1; n <= 1024; n++) {
//			int[] a = createArray(n, -100, 100);
//			System.out.print("" + n + "\t");
//			go(a);
//		}
//		long start = System.currentTimeMillis();
//		System.out.println(maxSum(a1));
//		long end = System.currentTimeMillis();
//		System.out.println(end - start);
//
//		start = System.currentTimeMillis();
//		System.out.println(maxSumDaC(a1));
//		end = System.currentTimeMillis();
//		System.out.println(end - start);

//		int[] a1 = {5, 8, 7, 9, 1, 3};
//		int[] a2 = {5, 3, 1, 7, 8, 9};
//		int[] a3 = {5, 2, 3, 4, 5, 6, 7, 8};
//		System.out.println(isHeap(a1));
//		System.out.println(isHeap(a2));
//		System.out.println(isHeap(a3));

		Node root = createTree();
		for(int i = 0; i < 30; i++) {
			System.out.println("i: " +i);
			search(root, i);
		}
		
		System.out.println();
	}
}
