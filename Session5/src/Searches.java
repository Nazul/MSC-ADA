import iteso.msc.ada.Session5.Utils;

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
	
	public static void main(String[] args) {
		int array[] = Utils.createIntArray(1000, 0, 1000); // { 3, 2, 6, 1, 5, 8, 0, 4, 7, 9 };
		Nodo raiz = crearArbol(array);
		print(raiz, 0);
	}
}
