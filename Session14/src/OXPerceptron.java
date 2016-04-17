
public class OXPerceptron {

	static double[][] inputHiddenWeights;
	static double[]   hiddenOutputWeights;
	static double[]   hiddenLayerValues;
	static final int HIDDEN_LAYER_SIZE = 13;
	
	static void initWeights(int input_layer_size) {
		inputHiddenWeights  = new double[input_layer_size][HIDDEN_LAYER_SIZE];
		hiddenOutputWeights = new double[HIDDEN_LAYER_SIZE];
		hiddenLayerValues   = new double[HIDDEN_LAYER_SIZE];	
		for(int i = 0; i < input_layer_size; i ++) {
			for(int h = 0; h < HIDDEN_LAYER_SIZE; h ++) {
				inputHiddenWeights[i][h] = -0.1 + 0.2 * Math.random();
			}
		}
		for(int h = 0; h < HIDDEN_LAYER_SIZE; h ++) {
			hiddenOutputWeights[h] = -0.1 + 0.2 * Math.random();
		}
	}
	
	static double sigmoid(double x) {
		return 1.0 / (1 + Math.exp(-x));
	}
	
	static double getOutput(byte[] currentInput) {
		for(int h = 0; h < HIDDEN_LAYER_SIZE; h ++) {
			double dot = 0;
			for(int i = 0; i < currentInput.length; i ++) {
				dot += currentInput[i] * inputHiddenWeights[i][h];
			}
			hiddenLayerValues[h] = sigmoid(dot);
		}
		double output = 0;
		for(int h = 0; h < HIDDEN_LAYER_SIZE; h ++) {
			output += hiddenLayerValues[h] * hiddenOutputWeights[h];
		}
		output = sigmoid(output);
		return output;
	}
	
	static double dsigmoid(double x) {
		return x * (1 - x);
	}
	
	static void backpropagation(byte[] input, double output, double error) {
		final double LEARNING_RATE = 0.1;
		double outputDelta = error * dsigmoid(output);
		for(int h = 0; h < HIDDEN_LAYER_SIZE; h ++) {
			hiddenOutputWeights[h] += hiddenLayerValues[h] * LEARNING_RATE * outputDelta;
		}
		double[] hiddenDeltas = new double[HIDDEN_LAYER_SIZE];
		for(int h = 0; h < HIDDEN_LAYER_SIZE; h ++) {
			hiddenDeltas[h] = dsigmoid(hiddenLayerValues[h]) * outputDelta * hiddenOutputWeights[h];
		}
		for(int i = 0; i < input.length; i ++) {
			for(int h = 0; h < HIDDEN_LAYER_SIZE; h ++) {
				inputHiddenWeights[i][h] += input[i] * LEARNING_RATE * hiddenDeltas[h];
			}
		}
	}
	
	static void trainPerceptron(byte[][] trainingMatrix, byte[] classes) {
		initWeights(trainingMatrix[0].length);
		int hits = 0;
		int currentRow = 0;
		int epochs = 0;
		while(trainingMatrix.length > hits && epochs < 10000) {
			byte[] currentInput = trainingMatrix[currentRow];
			double output = getOutput(currentInput);
			double expectedOutput = classes[currentRow] == 0? 0.25 : 0.75;
			double error = expectedOutput - output;
			if(Math.abs(error) <= 0.01) {
				hits ++;
			} else {
				hits = 0;
				backpropagation(currentInput, output, error);
			}			
			currentRow ++;
			if(currentRow == trainingMatrix.length) {
				epochs ++;
				currentRow = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		byte[][] trainingMatrix = {
			{	1, 1, 1, 1, 1,
				1, 0, 0, 0, 1,
				1, 0, 0, 0, 1,
				1, 0, 0, 0, 1,
				1, 1, 1, 1, 1 
			},
			{	1, 0, 0, 0, 1,
				0, 1, 0, 1, 0,
				0, 0, 1, 0, 0,
				0, 1, 0, 1, 0,
				1, 0, 0, 0, 1 
			},
			{	0, 1, 1, 1, 0,
				1, 0, 0, 0, 1,
				1, 0, 0, 0, 1,
				1, 0, 0, 0, 1,
				0, 1, 1, 1, 0 
			},
			{	1, 0, 0, 0, 1,
				0, 1, 0, 1, 0,
				0, 0, 1, 0, 0,
				0, 0, 0, 1, 0,
				1, 0, 0, 0, 1
			}
		};
		byte[] classes = {0, 1, 0, 1};
		trainPerceptron(trainingMatrix, classes);

		System.out.println("---");
		System.out.println(getOutput(trainingMatrix[0]));
		System.out.println(getOutput(trainingMatrix[1]));
		System.out.println(getOutput(trainingMatrix[2]));
		System.out.println(getOutput(trainingMatrix[3]));

		byte[] testPattern = {
				1, 0, 0, 0, 1,
				0, 1, 0, 1, 0,
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0,
				1, 0, 0, 0, 1
		};
		System.out.println("---");
		System.out.println(getOutput(testPattern));

		byte[] aPattern = {
				1, 0, 1, 1, 1,
				0, 0, 0, 0, 1,
				1, 0, 0, 0, 1,
				0, 0, 0, 0, 0,
				1, 1, 1, 0, 1
		};
		System.out.println("---");
		System.out.println(getOutput(aPattern));
	}
}
