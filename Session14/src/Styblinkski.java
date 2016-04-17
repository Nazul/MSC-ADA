import java.util.Arrays;

class Styblinkski {
	
	static final int POPULATION_SIZE = 240;
	static final int GENERATIONS = 100;
	static final int GENOTYPE_LENGTH = 12; // Se espera número par
	static final double MAX_VALUE = Math.pow(10, GENOTYPE_LENGTH / 2) - 1;
	static final double MIN_X1 = -5;
	static final double MAX_X1 =  5;
	static final double MIN_X2 = -5;
	static final double MAX_X2 =  5;

	static class Individual {
		double x1, x2; // Fenotipo
		byte genotype[];
		double fitness, expectedValue;
		public Individual() {
			this(true);
			updatePhenotype();
		}
		private Individual(boolean createArray) {
			if(createArray) {
				this.genotype = new byte[GENOTYPE_LENGTH];
				for (int i = 0; i < GENOTYPE_LENGTH; i++) {
					this.genotype[i] = (byte)(Math.random() * 10); // 0..9
				}
				updatePhenotype();
			}
		}
		public Individual clone() {
			Individual ind = new Individual(false);
			ind.x1 = this.x1;
			ind.x2 = this.x2;
			ind.genotype = this.genotype.clone();
			updatePhenotype();
			return ind;
		}
		
		public void updatePhenotype() {
			x1 = 0.0;
			double f = 1.0;
			for (int i = 0; i < GENOTYPE_LENGTH / 2; i++) {
				x1 += this.genotype[i] * f;
				f *= 10;
			}
			x1 /= MAX_VALUE;
			x1 = MIN_X1 + x1 * (MAX_X1 - MIN_X1);

			x2 = 0.0;
			f = 1.0;
			for (int i = GENOTYPE_LENGTH / 2; i < GENOTYPE_LENGTH; i++) {
				x2 += this.genotype[i] * f;
				f *= 10;
			}
			x2 /= MAX_VALUE;
			x2 = MIN_X2 + x2 * (MAX_X2 - MIN_X2);
		}
		
		public void updateFitness() {
			double f = S(this.x1, this.x2);
			this.fitness = 1 - (100 + f) / 1100;
		}
		
		public String toString() {
			return String.format("<%.5f, %.5f> [%s] {%.5f}", this.x1, this.x2, Arrays.toString(this.genotype), this.fitness);
		}
	}
	
	static double S(double x1, double x2) {
		double x12 = x1 * x1;
		double x22 = x2 * x2;
		return 0.5 * (x12 * x12 - 16 * x12 + 5 * x1 + x22 * x22 - 16 * x22 + 5 * x2);
	}
	
	static void runGA() {
		createPopulation();
		for (int g = 1; g <= GENERATIONS; g++) {
			calculateFitness();
			selection();
			crossover();
			mutation();
		}
		calculateFitness();
	}
	
private static void mutation() {
		// TODO Auto-generated method stub
		
	}

private static void crossover() {
		// TODO Auto-generated method stub
		
	}

private static void selection() {
		// TODO Auto-generated method stub
		
	}


	static Individual[] population;
	static double fitnessSum;

	private static void calculateFitness() {
		for (int i = 0; i < POPULATION_SIZE; i++) {
			population[i].updateFitness();
		}
	}

	static void createPopulation() {
		population = new Individual[POPULATION_SIZE];
		for (int i = 0; i < POPULATION_SIZE; i++) {
			population[i] = new Individual();
		}
		calculateFitness();
	}

	public static void main(String[] args) {
		System.out.println(S(-2.903535, -2.903534));
		Individual i = new Individual();
		Individual x = i.clone();
		System.out.println(i.toString());
		System.out.println(x.toString());
	}
}
