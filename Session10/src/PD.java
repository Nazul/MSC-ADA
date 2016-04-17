
public class PD {

	static long calls = 0;
	static long coeficienteBinomial(int N, int K) {
//		System.out.println(N + ", " + K);
		calls ++;
		if(K == 0 || K == N) return 1;
		if(K > N) return 0;
		return coeficienteBinomial(N - 1, K - 1) +
			   coeficienteBinomial(N - 1, K);
	}
	
	static long coeficienteBinomialPD(int N, int K) {
		long[][] mat = new long[K + 1][N];
		for(int k = 0; k <= K; k ++) {
			for(int n = 1; n <= N; n ++) {
				if(k == 0 || k == n) mat[k][n - 1] = 1;
				else if(k > n) mat[k][n - 1] = 0;
				else mat[k][n - 1] = mat[k][n - 2] + mat[k - 1][n - 2];
			}
		}
		return mat[K][N - 1];
	}
	
	static int cambio(int C, int[] M) {
		int[][] mat = new int[M.length][C];
		for(int m = 0; m < M.length; m ++) {
			for(int c = 1; c <= C; c ++) {
				if(M[m] == 1) mat[m][c - 1] = c;
				else if(c == M[m]) mat[m][c - 1] = 1;
				else if(c < M[m]) mat[m][c - 1] = mat[m - 1][c - 1];
				else {
					int nuevo = mat[m][c - 1 - M[m]] + 1; 
					if(nuevo < mat[m - 1][c - 1]) mat[m][c - 1] = nuevo;
					else mat[m][c - 1] = mat[m - 1][c - 1];
				}
			}
		}		
		
		System.out.println("Monedas utilizadas:");
		int m = M.length - 1;
		int c = C;
		while(m > 0) {
			int count = 0;
			System.out.print("De $" + M[m] + ": ");
			while(c > 0 && mat[m][c - 1] < mat[m - 1][c - 1]) {
				count ++;
				c -= M[m];
			}
			System.out.println(count);
			m --;
		}
//		Si la moneda de $1.00 todavía tiene que algo que aportar
		if(c > 0) System.out.println("De $" + M[m] + ": " + mat[m][c - 1]);
		else System.out.println("De $" + M[m] + ": 0");
		
		return mat[M.length - 1][C - 1];
	}
	
	public static void main(String[] args) {
//		long cb = coeficienteBinomial(12, 6);
//		System.out.println(cb);
//		cb = coeficienteBinomialPD(60, 30);
//		System.out.println(cb);
//		
//		int[] monedas = {1, 2, 3, 4, 6, 9};
//		System.out.println(cambio(10, monedas));

//		int[] prod1 = {1, 2, 3, 4, 6, 9};
//		int[] prod2 = {1, 150, 200, 250, 400};
//		System.out.println(cambio(10, prod1));
//		System.out.println(cambio(500, prod2));

		System.out.println(coeficienteBinomialPD(5, 0));
	}
}
