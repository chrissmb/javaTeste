package org.chrissmb;

public class NeuralNetwork {

    private Matrix
            weightsInputHidden,
            weightsHiddenOutput,
            biasHidden,
            biasOutput;
    
    private double learningHate = 0.01;

    public NeuralNetwork(int input, int hidden, int output) {
        weightsInputHidden = new Matrix(hidden, input);
        weightsInputHidden.randomize();
        weightsHiddenOutput = new Matrix(output, hidden);
        weightsHiddenOutput.randomize();
        biasHidden =  new Matrix(hidden, 1);
        biasHidden.randomize();
        biasOutput = new Matrix(output, 1);
        biasOutput.randomize();
    }
    
    public Matrix predict(double[] inputArray) {
        Matrix input = Matrix.fromArray(inputArray);
        Matrix hidden = weightsInputHidden.multiply(input);
        hidden = hidden.add(biasHidden);
        hidden = hidden.sigmoid();

        Matrix output = weightsHiddenOutput.multiply(hidden);
        output = output.add(biasOutput);
        output = output.sigmoid();

        return output;
    }

    public void train(double[] inputArray, double[] targetArray) {
        Matrix input = Matrix.fromArray(inputArray);
        Matrix hidden = weightsInputHidden.multiply(input);
        hidden = hidden.add(biasHidden);
        hidden = hidden.sigmoid();

        Matrix output = weightsHiddenOutput.multiply(hidden);
        output = output.add(biasOutput);
        output = output.sigmoid();

        Matrix target = Matrix.fromArray(targetArray);
        Matrix error = target.subtract(output);
        Matrix gradient = output.dsigmoid();
        gradient = gradient.multiplyValue(error);
        gradient = gradient.multiply(learningHate);

        Matrix hiddenTransposed = Matrix.transpose(hidden);
        Matrix weightsHiddenOutputDelta = gradient.multiply(hiddenTransposed);
        
        weightsHiddenOutput = weightsHiddenOutput.add(weightsHiddenOutputDelta);
        biasOutput = biasOutput.add(gradient);
        
        Matrix weightsHiddenOutputTransposed = Matrix.transpose(weightsHiddenOutput);
        Matrix hiddenError = weightsHiddenOutputTransposed.multiply(error);

        Matrix hiddenGradient = hidden.dsigmoid();
        hiddenGradient = hiddenGradient.multiplyValue(hiddenError);
        hiddenGradient = hiddenGradient.multiply(learningHate);

        Matrix inputTransposed = Matrix.transpose(input);
        Matrix weightsInputHiddenDelta = hiddenGradient.multiply(inputTransposed);

        weightsInputHidden = weightsInputHidden.add(weightsInputHiddenDelta);
        biasHidden = biasHidden.add(hiddenGradient);
    }

    public void fit(double[][] inputs, double[][] targets, int epochs) {
        for (int i = 0; i < epochs; i++) {
            int sample = (int) (Math.random() * inputs.length);
            train(inputs[sample], targets[sample]);
        }
    }
}