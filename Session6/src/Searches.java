import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class Searches {

	static class Nodo {
		int value;
		Nodo left;
		Nodo right;
		int index;

		public Nodo(int value, int index) {
			this.value = value;
			this.index = index;
			this.left = null;
			this.right = null;

		}
	}

	public static Nodo crearArbol(int[] array) {

		Nodo raiz = new Nodo(array[0], 0);

		for (int i = 1; i < array.length; i++) {
			Nodo nodo = new Nodo(array[i], i);
			Nodo iterador = raiz;
			while (true) {
				if (iterador.value > nodo.value) {
					if (iterador.left == null) {
						iterador.left = nodo;
						break;
					} else {
						iterador = iterador.left;
					}
				} else {
					if (iterador.right == null) {
						iterador.right = nodo;
						break;
					} else {
						iterador = iterador.right;
					}

				}
			}
		}

		return raiz;
	}

	public static void print(Nodo n, int spaces) {
		if(n == null) return;
		for(int i = 0; i < spaces; i ++) System.out.print(' ');
		System.out.println(n.value + ", " + n.index);
		print(n.left, spaces + 1);
		print(n.right, spaces + 1);
	}
	
	static int calls = 0;
	
	public static int indexOf(Nodo nodo, int value) {
		calls ++;
		if(nodo == null) return -1;
		if(value == nodo.value) return nodo.index;
		if(value > nodo.value) return indexOf(nodo.right, value);
		else return indexOf(nodo.left, value);		
	}
	
	public static int indexOf(int array[], int value) {
		for(int i = 0; i < array.length; i ++)
			if(array[i] == value) return i;
		return -1;
	}
	
	public static void main(String[] args) {
		int N = 1048576;
//		int array[] = new int[N];
//		for(int i = 0; i < N; i ++) array[i] = i;
		int array[] = Utils.createIntArray(N, 0, 2 * N); // { 3, 2, 6, 1, 5, 8, 0, 4, 7, 9 };
		Nodo raiz = crearArbol(array);
		
		int d1 = 0, d2 = 0;
		for(int s = 0; s < 10000; s ++) {
			int value = (int) ((2 * N) * Math.random());
			long start = System.currentTimeMillis();
			indexOf(raiz, value);
			long end   = System.currentTimeMillis();
			d1 += end - start;

			start = System.currentTimeMillis();
			indexOf(array, value);
			end   = System.currentTimeMillis();
			d2 += end - start;
		}
		
		System.out.println(d1 + ", " + d2);

//		System.out.println(calls);
//		print(raiz, 0);
	}
}
