import java.util.ArrayList;
import java.util.Arrays;

public class Uno {

	public static int[] ObtenerTorre(int[] pesos, int[] capacidades) {
		ArrayList<Integer> S = new ArrayList<>();
		boolean[] usados = new boolean[pesos.length];

		boolean solEncontrada = false;
		int bloqueMayCap = -1;
		int capacidadTorre = 0;
		// Buscamos un bloque más que apilar...
		do {
			solEncontrada = false;
			bloqueMayCap = -1;
			// Encontrar el bloque de mayor capacidad real (y que esté disponible) --> Selección
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
			// Evaluar si el candidato es válido
			// Si no tenemos ningún bloque agregado a la solución, el mayor se seleccionará
			if(S.size() == 0) {
				S.add(bloqueMayCap);
				solEncontrada = true;
				usados[bloqueMayCap] = true;
				capacidadTorre = capacidades[bloqueMayCap] - pesos[bloqueMayCap];
			}
			// Si ya teníamos algo, entonces hay que validar que podemos estribar el bloque
			else {
				int pesoAcum = pesos[S.get(S.size() - 1)];
				int minCR = Integer.MAX_VALUE;
				for (int i = S.size() - 2; i >= 0; i--) {
					pesoAcum += pesos[S.get(i)];
					minCR = Math.min(minCR, capacidades[S.get(i)] - pesoAcum);
				}

				if(bloqueMayCap >= 0 && capacidadTorre - pesos[bloqueMayCap] >= 0 && minCR - pesos[bloqueMayCap] >= 0) {
					S.add(bloqueMayCap);
					solEncontrada = true;
					usados[bloqueMayCap] = true;
					if(capacidadTorre == 0) capacidadTorre = capacidades[bloqueMayCap] - pesos[bloqueMayCap];
					else capacidadTorre -= pesos[bloqueMayCap];
				}
			}
		}while(solEncontrada);

		// Crear el arreglo resultante
		int[] arrayS = new int[S.size()];
		for (int i = 0; i < arrayS.length; i++) {
			arrayS[i] = S.get(i);
		}
		// Regresar el arreglo
		return arrayS;
	}

	public static void main(String[] args) {
		int[] pesos1       = {1, 5, 3};
		int[] capacidades1 = {7, 10, 4};
		int[] pesos2       = {83, 23, 9, 10, 10, 39, 30, 14, 48, 64, 74, 28, 67, 55, 88, 86, 7, 69, 41, 53, 15};
		int[] capacidades2 = {105, 25, 166, 58, 39, 47, 50, 111, 53, 130, 157, 49, 151, 98, 157, 173, 84, 115, 76, 67, 148};
		int[] pesos3       = {40, 73, 51, 36, 46, 59, 36, 10, 2, 100, 44, 27, 46, 4, 34, 21, 87, 72, 55, 40, 88, 13, 100, 12, 41, 5, 3, 61, 99, 74, 93};
		int[] capacidades3 = {148, 119, 127, 147, 176, 187, 38, 31, 25, 162, 48, 66, 54, 7, 88, 24, 134, 99, 64, 98, 138, 35, 123, 21, 171, 6, 58, 170, 166, 165, 107};
		int[] pesos4       = {6, 58, 78, 28, 26, 48, 92, 66, 34, 1, 10, 70, 63, 11, 31, 3, 51, 5, 7, 64, 77, 45, 13, 82, 17, 25, 5, 32, 99, 12};
		int[] capacidades4 = {62, 189, 98, 39, 130, 57, 112, 86, 71, 24, 10, 171, 92, 105, 35, 18, 71, 77, 85, 75, 166, 51, 22, 179, 66, 29, 156, 167, 159, 137};
		int[] pesos5       = {7, 8, 9};
		int[] capacidades5 = {10, 10, 10};
		int[] pesos6       = {63, 50, 38, 23, 48, 93, 68, 2, 27, 24, 100, 7, 3, 29, 54, 10, 26, 100, 51, 45, 27, 57, 98, 85, 80, 96, 21, 21, 5, 6};
		int[] capacidades6 = {93, 186, 72, 96, 172, 190, 135, 4, 69, 67, 133, 147, 155, 62, 108, 28, 40, 172, 183, 66, 75, 106, 131, 175, 106, 164, 118, 84, 37, 51};
		int[] pesos7       = {1, 50, 2};
		int[] capacidades7 = {100, 51, 3};
		int[] pesos8       = {2, 50, 3, 3, 1, 3};
		int[] capacidades8 = {100, 51, 5, 6, 3, 6};

		
		System.out.println(Arrays.toString(ObtenerTorre(pesos1, capacidades1)));
		System.out.println(Arrays.toString(ObtenerTorre(pesos2, capacidades2)));
		System.out.println(Arrays.toString(ObtenerTorre(pesos3, capacidades3)));
		System.out.println(Arrays.toString(ObtenerTorre(pesos4, capacidades4)));
		System.out.println(Arrays.toString(ObtenerTorre(pesos5, capacidades5)));
		System.out.println(Arrays.toString(ObtenerTorre(pesos6, capacidades6)));
		System.out.println(Arrays.toString(ObtenerTorre(pesos7, capacidades7)));
		System.out.println(Arrays.toString(ObtenerTorre(pesos8, capacidades8)));
	}
}
