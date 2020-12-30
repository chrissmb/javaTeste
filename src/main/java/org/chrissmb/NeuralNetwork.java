package org.chrissmb;

public class NeuralNetwork {

    private Matrix
            weightsInHidden,
            weightsHiddenOut,
            biasHidden,
            biasOut;
    
    private double learningHate;

    public NeuralNetwork(int in, int hidden, int out) {
        weightsInHidden = new Matrix(hidden, in);
        weightsHiddenOut = new Matrix(out, hidden);
        biasHidden =  new Matrix(hidden, 1);
        biasOut = new Matrix(out, 1);
    }
}