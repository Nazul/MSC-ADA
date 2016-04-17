
public class Rosenbrock {

	static double MIN_X1  = -5, MIN_X2  = -5;
	static double MAX_X1  =  5, MAX_X2  =  5;
	static double BEST_X1 =  1, BEST_X2 =  1;
	
	static double R(double x1, double x2) {
		return 100 * Math.pow(x2 - x1 * x1, 2) +
				     Math.pow(1 - x1, 2);
	}
	
	static double getFitness(double r) {
		return 1.0 / (1 + r);
//		0.1 <-- Bueno (Cercano a 1)
//		10.8 <-- Malo (Cercano a 0)
	}
	
	static double rand(double a, double b) {
		return a + (b - a) * Math.random();
	}
	
	static double norm(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}
	
	static void runHC(int G, double minDX) {
		double x1 = rand(MIN_X1, MAX_X1);
		double x2 = rand(MIN_X2, MAX_X2);
		double  r = R(x1, x2);
		double  f = getFitness(r);
		int g = 0;
		double Dx1 = (MAX_X1 - MIN_X1) * (1 - f);
		double Dx2 = (MAX_X2 - MIN_X2) * (1 - f); 
		while(g < G - 1 && norm(Dx1, Dx2) > minDX) {
			double dx1 = rand(-Dx1 / 2, Dx1 / 2);
			double dx2 = rand(-Dx2 / 2, Dx2 / 2);
			double r1 = R(x1 + dx1, x2 + dx2);
			if(r1 < r) {
				r = r1;
				f = getFitness(r);
				x1 += dx1;
				x2 += dx2;
				Dx1 = (MAX_X1 - MIN_X1) * (1 - f);
				Dx2 = (MAX_X2 - MIN_X2) * (1 - f);
			}
			g ++;
		}
		System.out.printf("Mejor solución: R(%.4f, %.4f) = %.7f\n", x1, x2, r);
		System.out.printf("Generaciones = %d, fitness = %.4f\n", g + 1, f);
		System.out.printf("Distancia con la mejor: %.4f\n", norm(x1 - BEST_X1, x2 - BEST_X2));
	}
	
	static void runRandom(int N) {
		double bestX1 = 0, bestX2 = 0, bestR = Double.MAX_VALUE;
		for(int i = 0; i < N; i ++) {
			double x1 = rand(MIN_X1, MAX_X1);
			double x2 = rand(MIN_X2, MAX_X2);
			double  r = R(x1, x2);
			if(r < bestR) {
				bestR = r;
				bestX1 = x1;
				bestX2 = x2;
			}
		}
		System.out.printf("Mejor solución: R(%.4f, %.4f) = %.7f\n", bestX1, bestX2, bestR);
		System.out.printf("Distancia con la mejor: %.4f\n", norm(bestX1 - BEST_X1, bestX2 - BEST_X2));
	}
	
	public static void main(String[] args) {
		runHC(20_000, 0.000_001);
		System.out.println("---------");
		runRandom(20_000);
//		System.out.println(rand( 1,  9));
//		System.out.println(rand(-5, -2));
//		System.out.println(rand(-4,  6));		
//		System.out.println(getFitness(R(1,  1)));
//		System.out.println(getFitness(R(0,  0)));
//		System.out.println(getFitness(R(2, -2)));
//		System.out.println(getFitness(R(-2, 2)));
	}
}
