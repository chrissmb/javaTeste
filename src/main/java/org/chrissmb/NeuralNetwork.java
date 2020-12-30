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
        Matrix matrix = new Matrix(new Double[][] {inputArray});
        matrix.multiply(weightsInHidden); // hidden
        matrix.sum(biasHidden);
        matrix.sigmoid();

        matrix.multiply(weightsHiddenOutput); // output
        matrix.sum(biasOutput);
        matrix.sigmoid();

        return matrix;
    }
}