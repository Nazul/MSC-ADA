import java.util.ArrayList;
import java.util.Arrays;


public class Exam {
	
	public static int[] ObtenerTorre(int[] pesos, int[] capacidades) {
		ArrayList<Integer> s = new ArrayList<>();
		boolean[] usados = new boolean[pesos.length];

		boolean solEncontrada = false;
		int bloqueMayCap = -1;
		int capacidadTorre = 0;
		int pesoAcumulado = 0;
		// Buscamos un bloque más que apilar...
		do {
			solEncontrada = false;
			bloqueMayCap = -1;
			// Encontrar el bloque de mayor capacidad real (y que esté disponible)
			for (int i = 0; i < pesos.length; i++) {
				if(usados[i]) continue;
				int capacidadReal = capacidades[i] - pesos[i];
				if(bloqueMayCap < 0) {
					bloqueMayCap = i;
				}
				else {
					if(capacidadReal > capacidades[bloqueMayCap] - pesos[bloqueMayCap]) {
						bloqueMayCap = i;
					}
				}
			}
			// Si no tenemos ningún bloque agregado a la solución, el mayor se seleccionará
			if(s.size() == 0) {
				s.add(bloqueMayCap);
				solEncontrada = true;
				usados[bloqueMayCap] = true;
				capacidadTorre = capacidades[bloqueMayCap] - pesos[bloqueMayCap];
				pesoAcumulado += pesos[bloqueMayCap];
			}
			// Si ya teníamos algo, entonces hay que validar que podemos estribar el bloque
			else {
				int lastIndex = s.get(s.size() - 1);
				if(capacidadTorre - pesos[bloqueMayCap] >= 0 && capacidades[lastIndex] - pesos[lastIndex] - pesos[bloqueMayCap] > 0 && pesoAcumulado - pesos[bloqueMayCap] > 0) {
					s.add(bloqueMayCap);
					solEncontrada = true;
					usados[bloqueMayCap] = true;
					if(capacidadTorre == 0) capacidadTorre = capacidades[bloqueMayCap] - pesos[bloqueMayCap];
					else capacidadTorre -= pesos[bloqueMayCap];
					pesoAcumulado += pesos[bloqueMayCap];
					System.out.println("");
				}
			}
		}while(solEncontrada);

		// Build return value
		int[] retval = new int[s.size()];
		for (int i = 0; i < retval.length; i++) {
			retval[i] = s.get(i);
		}
		// Return solution
		return retval;
	}

	public static void main(String[] args) {
//		int[] pesos = {1, 5, 3};
//		int[] capacidades = {7, 10, 4};
//		int[] pesos = {83, 23, 9, 10, 10, 39, 30, 14, 48, 64, 74, 28, 67, 55, 88, 86, 7, 69, 41, 53, 15};
//		int[] capacidades = {105, 25, 166, 58, 39, 47, 50, 111, 53, 130, 157, 49, 151, 98, 157, 173, 84, 115, 76, 67, 148};
		int[] pesos = {40, 73, 51, 36, 46, 59, 36, 10, 2, 100, 44, 27, 46, 4, 34, 21, 87, 72, 55, 40, 88, 13, 100, 12, 41, 5, 3, 61, 99, 74, 93};
		int[] capacidades = {148, 119, 127, 147, 176, 187, 38, 31, 25, 162, 48, 66, 54, 7, 88, 24, 134, 99, 64, 98, 138, 35, 123, 21, 171, 6, 58, 170, 166, 165, 107};
		
		System.out.println(Arrays.toString(ObtenerTorre(pesos, capacidades)));
	}
}
