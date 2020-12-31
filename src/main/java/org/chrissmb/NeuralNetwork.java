package org.chrissmb;

public class NeuralNetwork {

    private Matrix
            weightsInHidden,
            weightsHiddenOutput,
            biasHidden,
            biasOutput;
    
    private double learningHate;

    public NeuralNetwork(int input, int hidden, int output) {
        weightsInHidden = new Matrix(hidden, input);
        weightsInHidden.randomize();
        weightsHiddenOutput = new Matrix(output, hidden);
        weightsHiddenOutput.randomize();
        biasHidden =  new Matrix(hidden, 1);
        biasHidden.randomize();
        biasOutput = new Matrix(output, 1);
        biasOutput.randomize();
    }
    
    public Matrix predict(Double [] inputArray) {
        Matrix input = Matrix.fromArray(inputArray);
        Matrix hidden = input.multiply(weightsInHidden);
        hidden.sum(biasHidden);
        hidden.sigmoid();

        Matrix output = hidden.multiply(weightsHiddenOutput);
        output.sum(biasOutput);
        output.sigmoid();

        return output;
    }
}