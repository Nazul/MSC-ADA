import java.util.Arrays;

public class NandPerceptron {
	
//	Feed forward
	static double getOutput(byte[] input, double[] weights) {
		double output = 0;
		for(int i = 0; i < input.length; i ++) {
			output += input[i] * weights[i];
		}
		return output;
	}
	
	static double activationFunction(double output) {
		return output > 0.5? 1.0 : 0.0;
	}
	
	static void backpropagation(double error, byte[] input, double[] weights) {
		final double LEARNING_RATE = 0.1;
		double delta  = error * LEARNING_RATE;		  // delta puede ser: -0.1, 0.0, 0.1
		for(int i = 0; i < weights.length; i ++) {
			weights[i] += input[i] * delta;
		}
	}
	
	static double[] trainPerceptron(byte[][] trainingMatrix, byte[] classes) {
		double[] weights = new double[trainingMatrix[0].length];
		int hits = 0;
		int currentRow = 0;
		int iteration = 0;
		while(trainingMatrix.length > hits) {
			byte[] currentInput = trainingMatrix[currentRow];
			double output = getOutput(currentInput, weights);
			double af     = activationFunction(output);   // af puede ser: 0, 1
			double error  = classes[currentRow] - af;     // error puede ser: -1, 0, 1
			System.out.println(iteration + ": " + Arrays.toString(currentInput) + ", " + error);
			if(error == 0) {
				hits ++;
			} else {
				hits = 0;
				backpropagation(error, currentInput, weights);
			}
			currentRow ++;
			iteration ++;
			currentRow %= trainingMatrix.length;
		}
		return weights;
	}
	
	public static void main(String[] args) {
		byte[][] trainingMatrix = { {1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1} };
		byte[] classes 			= {     1,         1,         1,         0 };
		
		double[] perceptron = trainPerceptron(trainingMatrix, classes);
		System.out.println("Weights: " + Arrays.toString(perceptron));
		
		double o = activationFunction(getOutput(new byte[] {1, 1, 1}, perceptron));
		System.out.println(o);
				
	}
}
